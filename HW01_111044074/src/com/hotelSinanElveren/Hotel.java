package com.hotelSinanElveren;



import java.io.*;
import java.util.Scanner;


/**
 * Hotel class
 */
public class Hotel implements GTUHotelManagementSystem {

    private long[] rooms;        //full or emty(guestID or 0)

    private int capacity;

    private String hotelName;

    private static long hotelIDCreater = 1000;

    private long hotelID;

    private String password = "admin";
    private String recepcionist = "admin";


    /**
     * Hotel no parameter constructor
     * @throws IOException
     */
    public Hotel() throws IOException {
        setHotelName("Sinan's Hotel");
        createID();
        setHotelID(getHotelIDCreater());
        setCapacity(20);                         //default 20: num of rooms

        rooms = new long[getCapacity()];      //default false

        //guest info csv file
        makeLogFile();

        makeSystemUsersFile();
    }


    /**
     *
     * @param name  hotel name
     * @param totalRoomsNum total room count
     * @throws IOException
     */
    public Hotel(String name, int totalRoomsNum) throws IOException {
        setHotelName(name);
        setCapacity(totalRoomsNum);
        createID();
        setHotelID(getHotelIDCreater());

        rooms = new long[totalRoomsNum];

        //guest info csv file
        makeLogFile();

        makeSystemUsersFile();
    }


    private void setHotelName(String name){
        hotelName = name;
    }


    private void setHotelID(long id) {
        hotelID = id;
    }


    private void setCapacity(int size){
        capacity = size;
    }


    public String getName(){
        return hotelName;
    }


    public int getCapacity(){
        return capacity;
    }


    private long getHotelIDCreater() {
        return hotelIDCreater;
    }


    public long getHotelID(){
        return hotelID;
    }


    public String getPassword(){
        return password;
    }


    public String getRecepcionist() {
        return recepcionist;
    }


    public long[] getRooms() {
        return rooms;
    }


    private void makeLogFile() throws IOException {
        FileWriter fw;

        fw = new FileWriter(getHotelID()+"statusOfRooms.csv", false);

        StringBuilder sb = new StringBuilder();

        //head
        sb.append("HotelID");
        sb.append(',');
        sb.append("GuestID");
        sb.append(',');
        sb.append("GuestName");
        sb.append(",");
        sb.append("RoomNo");
        sb.append(",");
        sb.append("isReserve");
        sb.append(",");
        sb.append("CheckInOrOut");
        sb.append('\n');

        //emty rooms
        for (int i = 0; i < this.capacity; i++){
            sb.append(getHotelID());
            sb.append(',');
            sb.append("0");
            sb.append(',');
            sb.append("null");
            sb.append(",");
            sb.append(i+1);
            sb.append(",");
            sb.append("empty");
            sb.append(",");
            sb.append("null");
            sb.append('\n');
        }

        fw.write(sb.toString());
        fw.close();
    }


    private void makeSystemUsersFile() throws IOException {
        FileWriter fwSystemUsers;

        fwSystemUsers = new FileWriter(getHotelID()+"systemUsers.csv", false);

        StringBuilder sb = new StringBuilder();
        sb.append("HotelID");
        sb.append(',');
        sb.append("ID");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        fwSystemUsers.write(sb.toString());
        fwSystemUsers.close();
    }



    public void viewBooks() {
        for (int i = 0; i < capacity; i++){
            if(rooms[i] != 0)
                System.out.println(1+i + ". room is full.");
            if(rooms[i] == 0)
                System.out.println(1+i + ". room is empty.");
        }
    }


    public int anyEmptyRoom(){
        for (int i = 0; i < this.capacity; i++) {
            if (rooms[i] == 0)
                return i+1;         //return first empty room's no
        }

        return 0;       //no empty room
    }




    public void reloadeRooms( ) throws FileNotFoundException {

        //  File file = new File(getHotelID()+"systemUsers.csv");
        File f = new File(getHotelID()+"statusOfRooms.csv");
        Scanner sc = new Scanner(f);

        sc.hasNextLine();
        String temp = sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] details = line.split(",");

            int roomNo = Integer.parseInt(details[3]);
            rooms[roomNo-1] = Long.parseLong( details[1]);
        }
    }


    public boolean checkPaswword(String recepcionist, String password){

        if(recepcionist.equals(getRecepcionist()) && password.equals(this.getPassword()))
            return true;        //true password
        else
            return false;
    }

    /**
     * code copied from stackoverflow.com
     * @return id value
     */
    public static synchronized String createID() {
        return String.valueOf(hotelIDCreater++);
    }


    @Override
    public String toString() {
        return  "\nClas name : " + getClass().getSimpleName()  + "\nHotel Name :" + this.getName() +
                "\nHotel Capacity : "+ this.getCapacity() + "\nRecepcionist password : " +
                this.getPassword() + "\nRecepcionist name : " + this.getRecepcionist();
    }
}
