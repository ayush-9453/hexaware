package com.hexaware.dao;

import com.hexaware.entity.Cat;
import com.hexaware.entity.Dog;
import com.hexaware.entity.Pet;

import java.util.List;

public interface IPetDao {
    void addDog(Dog dog);
    void addCat(Cat cat);
    List<Pet> showPets();
}
