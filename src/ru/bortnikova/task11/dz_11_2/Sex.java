package ru.bortnikova.task11.dz_11_2;

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
