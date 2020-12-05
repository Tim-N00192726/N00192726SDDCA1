package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {


    static Scanner keyboard = new Scanner(System.in);
    static Model model = Model.getInstance();

    public static void main(String[] args) {

        Watch w;

        int opt;
        do {
            System.out.println("\n\n\n\n   MAIN MENU");
            System.out.println("1. Add  new Watch to DB");
            System.out.println("2. View all Watches in DB");
            System.out.println("3. Delete a Watch by ID");
            System.out.println("4. Update a Watch");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("*****Enter option: *******\n\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            switch (opt) {

                case 1: {
                    w = readWatch();

                    boolean created = model.addWatch(w);

                    if (created) System.out.println("Watch Added");
                    else
                        System.out.println("Watch was not Added");
                    break;
                }

                case 2: {
                    viewWatches();
                    break;
                }

                case 3: {
                    deleteWatch();
                    break;
                }

                case 4: {
                    updateWatch();
                    break;
                }
            }
        }
        while (opt != 5);
        System.out.println("Goodbye");

    }

    private static Watch readWatch() {
        String name, make, colour, chargingType;

        double batteryLife;
        String line;

        System.out.print("Enter name : ");
        name = keyboard.nextLine();

        System.out.print("Enter make : ");
        make = keyboard.nextLine();

        System.out.print("Enter colour : ");
        colour = keyboard.nextLine();


        System.out.print("Enter charging type : ");
        chargingType = keyboard.nextLine();

        System.out.print("Enter battery life length : ");
        batteryLife = keyboard.nextDouble();
        keyboard.nextLine();

        Watch w =
                new Watch(name, make, colour, chargingType, batteryLife);

        return w;

    }

    private static void viewWatches() {

        List<Watch> watches = model.getWatches();

        System.out.println("Printing all Watches from DB");
        for (Watch wchs : watches) {
            System.out.println(wchs);
        }

        System.out.println("Finished Printing all Watches from DB");
    }

    private static void deleteWatch() {
        System.out.print("Enter the ID of the watch to delete:");
        int id = Integer.parseInt(keyboard.nextLine());

        String opt;

        do {
            System.out.print("Are you sure you want to delete?");
            System.out.println();
            opt = keyboard.nextLine();


            switch (opt) {
                case "yes": {
                    model.deleteWatch(id); {
                        System.out.println("\n Watch deleted");
                    }
                }

            }
        }
        while (opt == "no");
        System.out.println("Watch not Deleted");
    }







    private static void updateWatch(){
        System.out.print("Enter the ID of the watch to update:");
        int id = Integer.parseInt(keyboard.nextLine());


        if (model.updateWatch(id)) {
            System.out.println("\n Watch updated");
        } else {
            System.out.println("\n Watch not updated");
        }
    }

    }


