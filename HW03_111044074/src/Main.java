import com.ElverenGTUCourses.Course;
import com.ElverenGTUCourses.CustomLinkedList;
import com.ElverenGTUCourses.GTUCourses;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args){

        try {
            //part 1
            testGetByCode();
            testListSemesterCourses();
            testGetByRange();

            //part 2
            testPart2();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while open file or read file");
        } catch (NoSuchElementException e){
            e.printStackTrace();
            System.out.println(e + " Not found in linked list");
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println(e + " is out of index");
        }
    }


    private static void testGetByCode() throws IOException, NoSuchElementException {

        GTUCourses courses = new GTUCourses();

        List<Course> matchedCourses = new ArrayList<>();

        //find all courses that matched courses given course's code
        matchedCourses = courses.getByCode("XXX XXX");


        //show courses that found
        System.out.println("\n\n________________TEST getByCode_______________________");
        System.out.println("\nMatched courses given course's code is " + "XXX XXX");
        for (int i = 0; i < matchedCourses.size() ; i++) {
            System.out.println(matchedCourses.get(i).getTitle());
        }
        matchedCourses.clear();


        //second test
        matchedCourses = courses.getByCode("CSE 222");

        System.out.println("\nMatched courses given course's code is " + "CSE 222");
        for (int i = 0; i < matchedCourses.size() ; i++) {
            System.out.println(matchedCourses.get(i).getTitle());
        }

        //third test
        matchedCourses = courses.getByCode("CSE 4XX");

        System.out.println("\nMatched courses given course's code is " + "CSE 4XX");
        for (int i = 0; i < matchedCourses.size() ; i++) {
            System.out.println(matchedCourses.get(i).getTitle());
        }
    }


    private static void testListSemesterCourses() throws IOException, NoSuchElementException {
        GTUCourses courses = new GTUCourses();

        List<Course> matchedCourses = new ArrayList<>();

        //find all courses that matched courses given semester number
        matchedCourses = courses.listSemesterCourses(4);


        //show courses that found
        System.out.println("\n\n______________________TEST listSemesterCourses_________________");
        System.out.println("\nMatched courses given semester is " + "4");
        for (int i = 0; i < matchedCourses.size() ; i++) {
            System.out.println(matchedCourses.get(i).getTitle());
        }
        matchedCourses.clear();
    }

    private static void testGetByRange()throws IOException, NoSuchElementException,IndexOutOfBoundsException {
        GTUCourses courses = new GTUCourses();

        List<Course> matchedCourses = new ArrayList<>();

        //find all courses that matched courses given ranger
        matchedCourses = courses.getByRange(3,1);


        //show courses that found
        System.out.println("\n\n____________________TEST getByRange_________________________");
        System.out.println("\nMatched courses given range between " + "1 & 3");
        for (int i = 0; i < matchedCourses.size() ; i++) {
            System.out.println(matchedCourses.get(i).getTitle());
        }
        matchedCourses.clear();

    }



    private static void testPart2() throws IOException, NoSuchElementException, IndexOutOfBoundsException {
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();



        mayDisableLinkedList.forEach(System.out::println);

        System.out.println("SIZE : " + mayDisableLinkedList.size());
        System.out.println("________________________DISABLE__________________________________");



        //disable test
        if(mayDisableLinkedList.disable(3))
            System.out.println("Disabled(3) : " + mayDisableLinkedList.getIndexesOfDisabels().get(0)+ "\n");
        if(mayDisableLinkedList.disable(4))
            System.out.println("Disabled(4) : " + mayDisableLinkedList.getIndexesOfDisabels().get(1)+ "\n");
        if(!mayDisableLinkedList.disable(4))
            System.out.println("NOT Disabled(4) : " + mayDisableLinkedList.getIndexesOfDisabels().get(1)+ "\n");



        mayDisableLinkedList.showDisabled();
        System.out.println("SIZE : " + mayDisableLinkedList.size());
        System.out.println("__________________________REMOVE__________________________________");

        //remove test
        System.out.println("\nRemoved(1) : " + mayDisableLinkedList.remove(1).getTitle());
        System.out.println("\nRemoved(2) : " + mayDisableLinkedList.remove(2).getTitle());
        System.out.println("\nis cse108  Removed ? : " + mayDisableLinkedList.remove(course));

        mayDisableLinkedList.showDisabled();
        System.out.println("SIZE : " + mayDisableLinkedList.size());
        System.out.println("__________________________SET______________________________________");

        System.out.println(course.getCode() + "is exist? : " + mayDisableLinkedList.indexOf(course));
        System.out.println("Set (3, cse108) ? returned: " + mayDisableLinkedList.set(3, course).getTitle());
        System.out.println(course.getCode() + "is exist? index is : " + mayDisableLinkedList.indexOf(course));
        System.out.println("get(5) : " + mayDisableLinkedList.get(3).getCode());
        System.out.println("\nis 10 Removed ? : " + mayDisableLinkedList.remove(course));


        System.out.println("_________________________ENABLE______________________________________");


        //enable test
        if(mayDisableLinkedList.enable(3))
            System.out.println("Enable(3) 3.index : " + mayDisableLinkedList.get(2) + "\n");
        if(!mayDisableLinkedList.enable(3))
            System.out.println("NOT Enable(3) 3.index: " + mayDisableLinkedList.get(2) + "\n");

        System.out.println("\nRemove(0) " + mayDisableLinkedList.remove(0).getTitle());
        mayDisableLinkedList.showDisabled();
        System.out.println("SIZE : " + mayDisableLinkedList.size());
        System.out.println("\n________________________TEST via ITERATOR______________________________");


        for (int i = 0; i < 10; i++) {
            System.out.println(i + ". " + mayDisableLinkedList.get(i).toString());
        }
        System.out.println("...\nFirst 10 record");


        Iterator<Course> it;
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ". " + mayDisableLinkedList.listIterator(i).next());
        }

        System.out.println("...\nTested- ITERATOR**");
    }

}
