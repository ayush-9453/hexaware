package com.hexaware.entity;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
    private List<Pet> availablePets = new ArrayList<>();

    public void addPets(Pet pet){
        availablePets.add(pet);
    }

    public void removePets(Pet pet){
        availablePets.remove(pet);
    }

    public List<Pet> showPets(){
        return availablePets;
    }
}
