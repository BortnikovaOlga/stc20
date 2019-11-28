package ru.bortnikova.task08;

/**
 * тестовый класс, содержит сущность Город
 * @author Bortnikova Olga
 */
public class TestClass {

    private String city;     // название города
    private String region;   // название региона
    private int codeRegion;  // код региона
    private Double square;   // площадь города
    private long population; // количество населения

    public TestClass() {
    }

    public TestClass(String city, String region, int codeRegion, Double square, long population) {
        this.city = city;
        this.region = region;
        this.codeRegion = codeRegion;
        this.square = square;
        this.population = population;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", codeRegion=" + codeRegion +
                ", square=" + square +
                ", population=" + population +
                '}';
    }
}
