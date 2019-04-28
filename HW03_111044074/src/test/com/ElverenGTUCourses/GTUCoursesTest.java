package test.com.ElverenGTUCourses;

import com.ElverenGTUCourses.Course;
import com.ElverenGTUCourses.GTUCourses;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

/**
* GTUCourses Tester. 
* 
* @author Sinan Elveren
* @since <pre>Mar 28, 2018</pre> 
* @version 1.0 
*/
public class GTUCoursesTest {
    @Before
    public void before() throws Exception {



    }


    /**
     * Method: getByCode(String code)
     * Creat a linked list from all courses
     * Assign matched courses given course code to array list
     * and compare them
     * @throws Exception exception while open|read|close to file
     */
    @Test
    public void testGetByCode() throws Exception {
    //TODO: Test goes here...
        GTUCourses courses = new GTUCourses();
        List<Course> matchedCourses = new ArrayList<>();

        matchedCourses = courses.getByCode( courses.getCoursesList().get(10).getCode() );

        assertEquals(courses.getCoursesList().get(10).getCode() , matchedCourses.get(0).getCode());
        assertNotEquals("" , matchedCourses.get(0).getCode());

    }

    /**
     * Method: listSemesterCourses(int semester)
     * Creat a linked list from all courses
     * Assign matched courses given semester info to array list
     * and compare them
     * @throws Exception exception while open|read|close to file
     */
    @Test
    public void testListSemesterCourses() throws Exception {
        //TODO: Test goes here...
        GTUCourses courses = new GTUCourses();
        List<Course> matchedCourses = new ArrayList<>();

        matchedCourses = courses.listSemesterCourses(2);

        assertEquals(2,matchedCourses.get(0).getSemester());
        assertEquals(2,matchedCourses.get(1).getSemester());
        assertEquals(2,matchedCourses.get(2).getSemester());
        assertEquals(2,matchedCourses.get(3).getSemester());


        assertNotEquals(1,matchedCourses.get(0).getSemester());
        assertNotEquals(3,matchedCourses.get(1).getSemester());
        assertNotEquals(4,matchedCourses.get(2).getSemester());
        assertNotEquals(5,matchedCourses.get(3).getSemester());
        assertNotEquals(7,matchedCourses.get(4).getSemester());
    }

    /**
     * Method: getByRange(int startIndex, int lastIndex)
     * Creat a linked list from all courses
     * Assign matched courses given range  to array list
     * and compare them
     * @throws Exception exception while open|read|close to file
     */
    @Test
    public void testGetByRange() throws Exception {
        //TODO: Test goes here...

        GTUCourses courses = new GTUCourses();
        List<Course> matchedCourses = new ArrayList<>();


        assertEquals(0 , matchedCourses.size());


        matchedCourses = courses.getByRange(2,4);


        assertEquals(courses.getCoursesList().get(2) , matchedCourses.get(0));
        assertEquals(courses.getCoursesList().get(3) , matchedCourses.get(1));
        assertEquals(courses.getCoursesList().get(4) , matchedCourses.get(2));


        assertNotEquals(null , matchedCourses.get(0));
        assertNotEquals(null , matchedCourses.get(1));
        assertNotEquals(null , matchedCourses.get(2));
    }
}
