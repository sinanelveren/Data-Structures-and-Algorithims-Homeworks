package com.hotelSinanElveren;



import java.io.*;
import java.util.Scanner;


/**
 * Hotel class that inculde hotel and rooms information.
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
     * Hotel no parameter constructor.
     * @throws IOException Exception for input read/write if there are any problem.
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
     * Two parameter constructor that Hotel Name and Total Room Count.
     * @param name  Hotel name
     * @param totalRoomsCount Total room count
     * @throws IOException
     */
    public Hotel(String name, int totalRoomsCount) throws IOException {
        setHotelName(name);
        setCapacity(totalRoomsCount);
        createID();
        setHotelID(getHotelIDCreater());

        rooms = new long[totalRoomsCount];

        //guest info csv file
        makeLogFile();

        makeSystemUsersFile();
    }


    /**
     * Setter for hotel name.
     * @param name Hotel name
     */
    private void setHotelName(String name){
        hotelName = name;
    }


    /**
     * Setter for hotel id.
     * @param id Hotel id
     */
    private void setHotelID(long id) {
        hotelID = id;
    }


    /**
     * Setter for hotel capacity
     * @param size Hotel capacity
     */
    private void setCapacity(int size){
        capacity = size;
    }


    /**
     * Getter for hotel name
     * @return Hotel name
     */
    public String getName(){
        return hotelName;
    }


    /**
     * Getter for hotel capacity
     * @return Hotel capacity
     */
    public int getCapacity(){
        return capacity;
    }


    /**
     * Getter for hotel id creater, it's private static methot to create id. It creates one id for each new system user.
     * @return Hotel id creater that is private
     */
    private long getHotelIDCreater() {
        return hotelIDCreater;
    }


    /**
     * Getter for hotel id that special id for each new hotel.
     * @return Hotel id
     */
    public long getHotelID(){
        return hotelID;
    }


    /**
     * Getter for password that default password as private static. You can create new password,if you change this.
     * @return Recepcionist's default password.
     */
    public String getPassword(){
        return password;
    }


    /**
     * Getter for recepcionist name that is default recepcionist name. You can create new name,if you change this.
     * @return Recepcionist 's default name.
     */
    public String getRecepcionist() {
        return recepcionist;
    }


    /**
     * Getter for all room information. It is containing informaiton of all rooms.
     * @return All room as an array.
     */
    public long[] getRooms() {
        return rooms;
    }


    /**
     * When a hotel created,that methot will make a file that "bookings info" and will write head of information that just 1 line.
     * @throws IOException
     */
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


    /**
     *  When a hotel created,that methot will make a file that "users info" and will write head of information that just 1 line.
     * @throws IOException
     */
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


    /**
     * It shows all status of rooms as full or empty
     */
    public void viewBooks() {
        for (int i = 0; i < capacity; i++){
            if(rooms[i] != 0)
                System.out.println(1+i + ". room is full.");
            if(rooms[i] == 0)
                System.out.println(1+i + ". room is empty.");
        }
    }


    /**
     * It will find a empty room if there are really a empty room.
     * @return Number of first empty room if there are empty room, else return 0.
     */
    public int anyEmptyRoom(){
        for (int i = 0; i < this.capacity; i++) {
            if (rooms[i] == 0)
                return i+1;         //return first empty room's no
        }

        return 0;       //no empty room
    }


    /**
     * it will cause senchronize Hotel's Room and CSV file. Exactly, It copies from file and uptade info of rooms.
     * @throws FileNotFoundException
     */
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


    /**
     * It checks recepcionist's info so compare them password and name.
     * @param recepcionist recepcionist name Recepcionist's name.
     * @param password recepcionist password Recepcionist's password.
     * @return true if recepcionist's login info is coorrect, else false.
     */
    public boolean checkPaswword(String recepcionist, String password){

        if(recepcionist.equals(getRecepcionist()) && password.equals(this.getPassword()))
            return true;        //true password
        else
            return false;
    }

    /**
     * It increases +1 created constat value when a hotel object is created.
     * Code copied from stackoverflow.com
     * @return id value as +1
     */
    public static synchronized String createID() {
        return String.valueOf(hotelIDCreater++);
    }


    /**
     * toString has been overloaded. ıt contains hotel info
     * @return
     */
    @Override
    public String toString() {
        return  "\nClas name : " + getClass().getSimpleName()  + "\nHotel Name :" + this.getName() +
                "\nHotel Capacity : "+ this.getCapacity() + "\nRecepcionist password : " +
                this.getPassword() + "\nRecepcionist name : " + this.getRecepcionist();
    }
}
