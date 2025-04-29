package com.hexaware.entity;

public class Dog extends Pet{
    private String dogBreed;

    public Dog(int petId,String name, int age, String breed,String petType,String dogBreed) {
        super(petId ,name, age, breed,"Dog");
        this.dogBreed = dogBreed;
    }


    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }
}
