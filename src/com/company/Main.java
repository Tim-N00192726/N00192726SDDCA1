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
            System.out.println("\n\n********* MAIN MENU ********");
            System.out.println("1. Create new Watch");
            System.out.println("2. View all Watches");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("*****Enter option: *******\n\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            switch (opt) {

                case 1: {
                    w = readWatch();

                    boolean created = model.addWatch(w);

                    if (created) System.out.println("***** Watch Added to the Database *****");
                    else
                        System.out.println("***** Watch NOT Added to the Database *****");


                    break;
                }

                case 2: {
                    viewWatches();
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

        System.out.println("***** Printing All Watches *****");
        for (Watch wchs : watches) {
            System.out.println(wchs);
        }

        System.out.println("***** Finished Printing All Watches *****");
    }

}
