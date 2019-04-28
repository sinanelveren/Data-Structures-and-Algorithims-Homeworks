package Tests;
import static org.junit.Assert.*;

import com.hotelSinanElveren.Guest;
import com.hotelSinanElveren.Hotel;
import com.hotelSinanElveren.Receptionist;
import org.junit.Test;

import java.io.*;

public class HotelTest {

    /**
     * if 1. room is empty, so return room[0] returns 1 else different number
     * @throws IOException
     */
    @Test
    public void anyEmptyRoom() throws  IOException{
        Hotel hotel = new Hotel();

        if(hotel.getRooms()[0] == 0)
            assertEquals(hotel.anyEmptyRoom(),1);
        else
            assertNotEquals(hotel.anyEmptyRoom(),1);

        hotel.getRooms()[0] = 101;

        if(hotel.getRooms()[0] == 0)
            assertEquals(hotel.anyEmptyRoom(),1);
        else
            assertNotEquals(hotel.anyEmptyRoom(),1);

    }

    /**
     * test-synchronize file and room[]
     * @throws IOException
     */
    @Test
    public void reloadeRooms() throws IOException {
        Hotel hotel = new Hotel();
        Guest guest = new Guest(hotel, "test");


        //read from data for compare to room array
        FileReader file = null;
        file = new FileReader(hotel.getHotelID() + "statusOfRooms.csv");
        BufferedReader br = new BufferedReader(file);

        String line;

        line = br.readLine();
        line = br.readLine();       //second line, so this is first record
        String[] words = line.split(",");


        assertEquals(hotel.getRooms()[0], Long.parseLong(words[1]));        //compare guest id in room and in first record of line
        assertEquals(hotel.getRooms()[0], 0);                         //compare guest id in room and in first record of line

        guest.addBooking(hotel, guest);
        hotel.reloadeRooms();

        assertNotEquals(hotel.getRooms()[0], Long.parseLong(words[1]));     //compare guest id in room and in first record of line


        br.close();
        file.close();

    }


    @Test
    public void checkPaswword() throws IOException {
        Hotel hotel = new Hotel();

        Receptionist receptionist = new Receptionist(hotel, "admin", "admin");
        assertEquals (hotel.checkPaswword(receptionist.getName(), receptionist.getPassword()), true);

        Receptionist receptionist2 = new Receptionist(hotel, "admin", "sifre");
        assertNotEquals (hotel.checkPaswword(receptionist2.getName(), receptionist2.getPassword()), true);
    }

    @Test
    public void makeSystemUsersFile() throws IOException {
        Hotel hotel = new Hotel();

        FileReader fr = null;
        fr = new FileReader(hotel.getHotelID() + "systemUsers.csv");
        BufferedReader br = new BufferedReader(fr);

        String line;

        line = br.readLine();           //read first line, so this is head of csv file content
        String[] words = line.split(",");


        //compare all column of first line that head of csv file
        assertEquals(words[0],"HotelID");
        assertEquals(words[1],"ID");
        assertEquals(words[2],"Name");

        fr.close();
        br.close();
    }


    @Test
    public void makeLogFile() throws IOException {
        Hotel hotel = new Hotel();

        FileReader fr = null;
        fr = new FileReader(hotel.getHotelID() + "statusOfRooms.csv");
        BufferedReader br = new BufferedReader(fr);

        String line;

        line = br.readLine();           //read first line, so this is head of csv file content
        String[] words = line.split(",");


        //compare all column of first line that head of csv file
        assertEquals(words[0],"HotelID");
        assertEquals(words[1],"GuestID");
        assertEquals(words[2],"GuestName");
        assertEquals(words[3],"RoomNo");
        assertEquals(words[4],"isReserve");
        assertEquals(words[5],"CheckInOrOut");

        fr.close();
        br.close();
    }

}