package ru.bortnikova.task01;

public enum Sex{
    MAN("мужчина"), WOMAN("женщина");
    private String strsex;

    Sex(String strsex) {
        this.strsex = strsex;
    }
    public String getStr(){
        return strsex;
    }

}
