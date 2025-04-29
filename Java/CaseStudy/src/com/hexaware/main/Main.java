package com.hexaware.main;

import com.hexaware.dao.AdoptionDao;
import com.hexaware.dao.DonationDao;
import com.hexaware.dao.PetDao;
import com.hexaware.entity.*;
import com.hexaware.exception.NullReferenceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PetDao petDao = new PetDao();
        AdoptionDao event = new AdoptionDao();

        System.out.println("=== Welcome to Pet Adoption Center ===");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Pets");
            System.out.println("2. Show Pets");
            System.out.println("3. Remove Pets");
            System.out.println("4. Adoption Events");
            System.out.println("5. Host a Event");
            System.out.println("6. Record Donation");
            System.out.println("7. Show Donation");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAdd Pets Menu:");
                    System.out.println("1. Add Dog");
                    System.out.println("2. Add Cat");
                    System.out.print("Enter your choice: ");
                    int petChoice = sc.nextInt();
                    sc.nextLine(); // consume leftover \n

                    if (petChoice == 1) {
                        System.out.print("Enter Dog ID: ");
                        int dogId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Dog Name: ");
                        String dogName = sc.nextLine();
                        System.out.print("Enter Dog Age: ");
                        int dogAge = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Dog Breed: ");
                        String dogBreed = sc.nextLine();
                        System.out.print("Enter Specific Dog Breed: ");
                        String specificDogBreed = sc.nextLine();

                        Dog dog = new Dog(dogId, dogName, dogAge, dogBreed, "Dog", specificDogBreed);
                        petDao.addDog(dog);
                    } else if (petChoice == 2) {
                        System.out.print("Enter Cat ID: ");
                        int catId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Cat Name: ");
                        String catName = sc.nextLine();
                        System.out.print("Enter Cat Age: ");
                        int catAge = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Cat Breed: ");
                        String catBreed = sc.nextLine();
                        System.out.print("Enter Cat Color: ");
                        String catColor = sc.nextLine();

                        Cat cat = new Cat(catId, catName, catAge, catBreed, "Cat", catColor);
                        petDao.addCat(cat);
                    } else {
                        System.out.println("Invalid pet choice!");
                    }
                    break;

                case 2:
                    List<Pet> pets = petDao.showPets();
                    System.out.println("\nAvailable Pets:");
                    for (Pet p : pets) {
                        System.out.println("[" + p.getPetType() + "] ID: " + p.getPetId() + " | Name: " + p.getName() + " | Age: " + p.getAge() + " years | Breed: " + p.getBreed());
                    }
                    break;
                case 3:

                    System.out.println("Enter Pet ID to remove: ");
                    int petId = sc.nextInt();

                    boolean deleted = petDao.removePet(petId);

                    if (deleted) {
                        System.out.println("Pet removed successfully!");
                    } else {
                        System.out.println("No pet found with the given ID.");
                    }
                    break;

                case 4:
                    List<AdoptionEvent> events = event.getAllEvents();
                    if (events.isEmpty()) {
                        System.out.println("No events hosted.");
                    } else {
                        for (AdoptionEvent e : events) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Enter event name: ");
                    String eventName = sc.nextLine();

                    if (eventName == null || eventName.trim().isEmpty()) {
                        throw new NullReferenceException("Event name cannot be empty.");
                    }

                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String eventDateStr = sc.nextLine();


                    if (!eventDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        throw new NullReferenceException("Event date must be in YYYY-MM-DD format.");
                    }

                    LocalDate eventDate = LocalDate.parse(eventDateStr);

                    AdoptionEvent adoptionEvent = new AdoptionEvent(eventName, eventDate);

                    boolean hosted = event.hostEvent(adoptionEvent);
                    if (hosted) {
                        System.out.println(" Event hosted successfully.");
                    } else {
                        System.out.println(" Failed to host event.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- Donation Menu ---");
                    System.out.println("1. Add Cash Donation");
                    System.out.println("2. Add Item Donation");
                    System.out.print("Enter your choice: ");
                    int ch = sc.nextInt();
                    sc.nextLine();

                    switch (ch) {
                        case 1:
                            System.out.print("Enter donor name: ");
                            String donorName = sc.nextLine();

                            System.out.print("Enter amount: ");
                            double amount = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Enter donation date (yyyy-mm-dd): ");
                            String dateInput = sc.nextLine();
                            LocalDate donationDate = LocalDate.parse(dateInput);

                            DonationDao.recordDonation(new CashDonation(donorName, amount, donationDate));
                            System.out.println("Cash donation added successfully!");
                            break;

                        case 2:
                            System.out.print("Enter donor name: ");
                            String donorNameItem = sc.nextLine();

                            System.out.print("Enter item type: ");
                            String itemType = sc.nextLine();

                            Donation itemDonation = new ItemDonation(donorNameItem, 0.0, itemType);
                            DonationDao.recordDonation(itemDonation);
                            System.out.println("Item donation added successfully!");
                            break;

                        case 7:
                            List<Donation> allDonations = DonationDao.getAllDonations();
                            if (allDonations.isEmpty()) {
                                System.out.println("No donations found.");
                            } else {
                                for (Donation donation : allDonations) {
                                    System.out.println(donation);
                                }
                            }
                            break;
                        case 8:
                            System.out.println("Thank you!");
                            break;
                        default:
                            System.out.println("Invalid choice! Please enter again.");
                    }
            }
        }
    }
}