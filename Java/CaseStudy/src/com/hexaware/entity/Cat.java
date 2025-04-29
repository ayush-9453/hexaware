package com.hexaware.entity;

public class Cat extends Pet{
    private String catColor;

    public Cat(int petId,String name , int age , String breed,String petType,String catColor){
        super(petId,name,age,breed,"Cat");
        this.catColor = catColor;
    }

    public String getCatColor() {
        return catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }
}
