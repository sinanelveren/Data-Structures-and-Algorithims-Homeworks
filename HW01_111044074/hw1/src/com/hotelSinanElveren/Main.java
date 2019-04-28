package com.hotelSinanElveren;

import java.io.*;
import java.util.InputMismatchException;;
import java.util.Scanner;

public class Main {
    private static int choice = 1;
    private static Scanner input;


    // static BufferedReader input = new BufferedReader(new InputStreamReader((InputStream) Paths.get("input.txt")));

    private static int usersMenu = 0;                //guest :1, recepcionist :2


    public static void main(String[] args) {
        try {
                input = new Scanner(new File("input.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("\n\n wrong input file name! FILE NOT FOUND");
                e.printStackTrace();
            }



        try {

            testViaInterface();

        } catch (IOException e) {

            System.out.println("\n\n\nError while read/write to file\n\n");
            e.printStackTrace();
        }catch (InputMismatchException e){

            System.out.println("\n\n\nYou entered wrong input\n\n");
            e.printStackTrace();
        }

    }

    public static void testPolymorphism(SystemUsersAbs user){
        System.out.println("\n");
        user.showUserInfo();
        user.toString();
        System.out.println("\n\nPolymorphism has been tested succesfuly\n\n");

    }

    private static void testViaInterface() throws IOException, InputMismatchException {
        String  menu = "hotelMenu";
        boolean isExit = false;

        Hotel HotelSinan = new Hotel();
        Hotel HotelCustom = new Hotel("Custom Hotel", 50);



        while (true) {

            isExit = false;
            choice = 1;
            usersMenu = 0;


            switch (menu) {
                case "hotelMenu":
                    isExit = choseHotelMenu();       //default or custom hotel

                    if (isExit) {
                        //poly
                        SystemUsersAbs abstr1 = new Guest(HotelSinan,"test1");
                        SystemUsersAbs abstr2 = new Receptionist(HotelSinan, "admin", "admin");

                        testPolymorphism(abstr1);
                        testPolymorphism(abstr2);
                        System.exit(0);
                    }else if (usersMenu == 1)
                        HotelSinan = HotelSinan;
                        //enterHotelManagementSystem(HotelSinan);
                    else if (usersMenu == 2)
                        HotelSinan = HotelCustom;
                        //enterHotelManagementSystem(HotelCustom);
                    menu = "loginMenu";
                    break;

                case "loginMenu":
                    if ( loginMenu(HotelSinan) )
                        menu = "hotelMenu";
                    if (usersMenu == 1)
                        isExit = guestMenu(HotelSinan);

                    if (usersMenu == 2)
                        isExit = recepcionistMenu(HotelSinan);

                    if (isExit) {
                        menu = "loginMenu";
                        System.out.println("");
                    }
                    break;

                default:
                    System.out.println("test default");
                    //no need- menu = "hotelMenu";
            }
        }
    }


    private static boolean choseHotelMenu() throws InputMismatchException{
        while (choice != 0) {
            System.out.println("\n  ## Hotel Management System ##");
            System.out.println("\n\n1 - Enter to NEW default Hotel ");
            System.out.println("2 - Create NEW hotel ");
            System.out.println("0 - Exit ");
            choice = input.nextInt();

            switch (choice) {
                case 1 :
                    System.out.println("\n   ## Hotel Management System - Sinan's Hotel ##");
                    choice = 0;
                    usersMenu = 1;
                    break;
                case 2:
                    System.out.println("   ##Hotel Management System - Custom Hotel ##");
                    choice = 0;
                    usersMenu = 2;
                    break;

                case 0:
                    return true;
                default:
                    System.out.println("Wrong choice");
            }
        }
        return false;
    }


    private static boolean loginMenu(Hotel HotelS) throws InputMismatchException{
        String pass;
        choice = 1;

        while (choice != 0) {
            System.out.println("\n\n1 - Guest");
            System.out.println("2 - Recepcionist ");
            System.out.println("0 - Back to hotel menu ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    usersMenu = 1;
                    choice = 0;
                    break;
                case 2:
                    usersMenu = 2;
                    choice = 0;

                break;

                case 0:
                    return true;

                default:
                    System.out.println("Wrong choice\n");
            }
        }
        return false;
    }


    private static boolean guestMenu(Hotel HotelS) throws IOException, InputMismatchException {
        System.out.println("Enter your name(only one word!)");
        String newGuestName = input.next();

        Guest newGuest = new Guest(HotelS, newGuestName);

        choice = 1;
        while (choice != 0) {
            System.out.println("\n   ## Hotel Management System - Sinan's Hotel - Guest##");
            System.out.println("\n\n1 - Book a room");
            System.out.println("2 - Cancel your reservation ");
            System.out.println("0 - Back to login menu ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n   ## Hotel Management System - Sinan's Hotel - Guest - Booking##\n\n");

                    //any empty room?
                    int emptyRoomNum = newGuest.addBooking(HotelS, newGuest);
                    if (0 == emptyRoomNum){
                        System.out.println("\n\nSorry, Hotel FULL!");
                    }else {
                        //if there are emty room
                        System.out.println(newGuest.getName() + " has booked a new room succesfully.");
                        System.out.println("Reservation info :");
                        System.out.println("Guest ID : " + newGuest.getID());
                        System.out.println("Room Number : " + emptyRoomNum);
                    }
                    break;

                case 2:
                    System.out.println("Enter to GUEST ID:");
                    long guestID = input.nextLong();
                    System.out.println("Enter to ROOM NUMBER that has booked:");
                    int roomNo = input.nextInt();

                    if (newGuest.cancelReservation(HotelS, guestID, roomNo))
                        System.out.println("Reservation has been canceled succesfuly");
                    else
                        System.out.println("\n\nErr : Reservation hasNOT been cancelled");

                    break;

                case 0:
                    return true;
            }
        }
        return false;
    }


    private static boolean recepcionistMenu(Hotel HotelS) throws IOException , InputMismatchException {

        String newPassword;
        String newRecepcionistName;

        choice = 1;

        System.out.println("Enter recepcionist name(hint:'admin')");
        newRecepcionistName = input.next();

        System.out.println("Enter recepcionist password(hint:'admin')");
        newPassword = input.next();

        if (!HotelS.checkPaswword(newRecepcionistName, newPassword)) {
            choice = 0;
            System.out.println("\n\nWrong password");
        }

        Receptionist newRecepcionist = new Receptionist(HotelS, newRecepcionistName, newPassword);


        while (choice != 0) {
            String guestNameToRec;
            long newGuestId;
            int roomNum;

            System.out.println("   ##Hotel Management System - Custom Hotel - Recepcionist##");
            System.out.println("\n\n1 - Book a room");
            System.out.println("2 - Cancel Booking");
            System.out.println("3 - Check in ");
            System.out.println("4 - Check out ");
            System.out.println("5 - Show booking ");
            System.out.println("0 - Back to login menu ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("   ##Hotel Management System - Custom Hotel - Recepcionist - Booking##");

                    //ask guest name who will reserve
                    System.out.println("Enter guest name (only word): ");
                    guestNameToRec = input.next();

                    Guest GuestToRec = new Guest(HotelS, guestNameToRec);

                    //any empty room?
                    int emptyRoomNum = newRecepcionist.addBooking(HotelS, GuestToRec);
                    if (0 == emptyRoomNum){
                        System.out.println("\n\nSorry, Hotel FULL!");
                    }else {
                        //if there are emty room, print reservation info
                        System.out.println(GuestToRec.getName() + " has booked a new room succesfully.");
                        System.out.println("Reservation info :");
                        System.out.println("Guest ID : " + GuestToRec.getID());
                        System.out.println("Room Number : " + emptyRoomNum);
                    }


                    break;

                case 2:
                    System.out.println("   ##Hotel Management System - Custom Hotel - Recepcionist - Cancel Booking##");
                    //ask guest name who will check in
                    System.out.println("Enter guest id : ");
                    newGuestId = input.nextLong();
                    System.out.println("Enter room number: ");
                    roomNum = input.nextInt();

                    if (newRecepcionist.cancelReservation(HotelS, newGuestId, roomNum))
                        System.out.println("cancelled");
                    else
                        System.out.println("fail cancelled");

                    break;

                case 3:
                    System.out.println("   ##Hotel Management System - Custom Hotel - Recepcionist - Check in##");
                    //ask guest name who will check in
                    System.out.println("Enter guest id : ");
                    newGuestId = input.nextLong();
                    System.out.println("Enter room number: ");
                    roomNum = input.nextInt();

                    newRecepcionist.checkIn(HotelS, newGuestId, roomNum);

                    break;

                case 4:
                    System.out.println("   ##Hotel Management System - Custom Hotel - Recepcionist - Check out##");
                    //ask guest name who will check in
                    System.out.println("Enter guest id : ");
                    newGuestId = input.nextLong();
                    System.out.println("Enter room number: ");
                    roomNum = input.nextInt();

                    if(newRecepcionist.checkOut(HotelS, newGuestId, roomNum))
                        System.out.println("checked out");
                    else
                        System.out.println("fail to checked out");

                    break;

                case 5:
                    HotelS.viewBooks();
                    break;

                case 0:
                    return true;

                default:
            }
        }
        return false;
    }

}
