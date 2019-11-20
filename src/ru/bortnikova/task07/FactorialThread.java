package ru.bortnikova.task07;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * класс реализует задачу вычисления факториала от числа из массива, на основе предыдущих вычислений
 *
 * @author Bortnikova Olga
 */
public class FactorialThread implements Runnable {
    private AtomicInteger curIdx;
    private AtomicInteger lastIdx;
    private Integer[] ia;
    private BigDecimal[] fa;
    private CountDownLatch cdl;

    /**
     * @param ia      массив чисел по которым нужно вычислить факториалы
     * @param fa      массив в который сохранять вычисления
     * @param cdl     счетчик событий по количеству чисел в массиве ia
     * @param curIdx  текущий индекс числа
     * @param lastIdx индекс последнего числа, от которого посчитан факториал
     */
    public FactorialThread(Integer[] ia, BigDecimal[] fa, CountDownLatch cdl, AtomicInteger curIdx, AtomicInteger lastIdx) {

        this.ia = ia;
        this.fa = fa;
        this.cdl = cdl;
        this.curIdx = curIdx;
        this.lastIdx = lastIdx;
        new Thread(this);

    }

    @Override
    public void run() {
        // забрать индекс числа-аргумента факториала и увеличить счетчик индексов
        int idx = curIdx.getAndIncrement();
        int stop = ia[idx];
        // получить индекс последнего вычесленного факториала
        int lidx = lastIdx.get();
        //что делать, если этот поток отстает, т.е. lidx>idx
        if (lidx > idx) lidx = ((idx > 0) && (fa[idx - 1] != null)) ? (idx - 1) : 0;
        // вычисление факториала для текущего числа с уетом предыдущих вычислений
        int start = lidx > 0 ? ia[lidx] + 1 : 1;
        BigDecimal f = new BigDecimal((lidx > 0) ? fa[lidx].toString() : "1");
        for (int i = start; i <= stop; i++) {
            f = f.multiply(new BigDecimal(i));
        }
        //положить результат в массив
        fa[idx] = f;
        //установить индекс последнего вычисления в новое значение
        lastIdx.set(idx);
        // увеличить счетчик количества проиведенных вычислений
        cdl.countDown();
    }

}
