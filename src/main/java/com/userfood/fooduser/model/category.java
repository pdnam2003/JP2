package com.userfood.fooduser.model;

public class category {
    private int idCategory    ;
    private String nameCategory;
    public category() {;}
    public category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "category{" +
                "idCategory='" + idCategory + '\'' +
                ", nameCategory='" + nameCategory + '\'' +
                '}';
    }
}
