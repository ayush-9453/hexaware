package com.hexaware.dao;

import com.hexaware.entity.Cat;
import com.hexaware.entity.Dog;
import com.hexaware.entity.Pet;
import com.hexaware.utils.DbConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao implements IPetDao{

    @Override
    public void addDog(Dog dog) {
        String sql = "INSERT INTO Pet (pet_id,name, age, breed, pet_type, dog_breed) VALUES (?, ?,?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,dog.getPetId());
            stmt.setString(2, dog.getName());
            stmt.setInt(3, dog.getAge());
            stmt.setString(4, dog.getBreed());
            stmt.setString(5, dog.getPetType()); // Always "Dog"
            stmt.setString(6, dog.getDogBreed());

            stmt.executeUpdate();
            System.out.println("Dog saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCat(Cat cat) {
        String sql = "INSERT INTO Pet (pet_id,name, age, breed, pet_type, cat_color) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,cat.getPetId());
            stmt.setString(2, cat.getName());
            stmt.setInt(3, cat.getAge());
            stmt.setString(4, cat.getBreed());
            stmt.setString(5, cat.getPetType()); // Always "Cat"
            stmt.setString(6, cat.getCatColor());

            stmt.executeUpdate();
            System.out.println("Cat saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> showPets() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pet";

        try (Connection conn = DbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int petId = rs.getInt("pet_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                String petType = rs.getString("pet_type");

                Pet pet = new Pet(petId, name, age, breed, petType);
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
    public boolean removePet(int petId) {
        String sql = "DELETE FROM Pet WHERE pet_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Pet with ID " + petId + " was successfully removed.");
                return true;
            } else {
                System.out.println("No pet found with ID: " + petId);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
