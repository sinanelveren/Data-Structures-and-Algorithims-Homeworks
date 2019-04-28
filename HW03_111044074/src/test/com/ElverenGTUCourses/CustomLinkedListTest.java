package test.com.ElverenGTUCourses; 

import com.ElverenGTUCourses.Course;
import com.ElverenGTUCourses.CustomLinkedList;
import com.ElverenGTUCourses.GTUCourses;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.IOException;

import static org.junit.Assert.*;


/** 
* CustomLinkedList Tester. 
* 
* @author Sinan Elveren
* @since <pre>Mar 28, 2018</pre> 
* @version 1.0 
*/ 
public class CustomLinkedListTest {

    /**
     * Method: disable(int index)
     * Creat a linked list from all courses
     * Assign matched index of course  given course index to course object
     * disabel it and compare them
     * also compare via disabeled's size
     * @throws IOException io exception while read open close to file
     */
    @Test
    public void testDisable() throws IOException {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();


        assertEquals(mayDisableLinkedList.get(10), course);
        assertEquals(0, mayDisableLinkedList.getIndexesOfDisabels().size());


        mayDisableLinkedList.disable(10);


        assertNotEquals(mayDisableLinkedList.get(10), course);
        assertNotEquals(0, mayDisableLinkedList.getIndexesOfDisabels().size());
    }

    /**
     * Method: enable(int index)
     * Creat a linked list from all courses
     * Assign matched index of course  given course index to course object
     * disabele some index,so enable again.. and compare them
     * also compare via disabeled's size
     * @throws Exception io exception while read open close to file
     */
    @Test
    public void testEnable() throws Exception {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();


        assertEquals(mayDisableLinkedList.get(10), course);
        assertEquals(0, mayDisableLinkedList.getIndexesOfDisabels().size());

        mayDisableLinkedList.disable(10);


        assertNotEquals(mayDisableLinkedList.get(mayDisableLinkedList.indexOf(course)), course);


        mayDisableLinkedList.enable(mayDisableLinkedList.indexOf(course));


        assertEquals(mayDisableLinkedList.get(mayDisableLinkedList.indexOf(course)), course);
        assertEquals(0, mayDisableLinkedList.getIndexesOfDisabels().size());
    }



    /**
     * Method: remove(Object o)
     * @throws Exception  exception not found element
     */
    @Test
    public void testRemoveO() throws Exception {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();



        //check
        assertEquals(course, mayDisableLinkedList.get(mayDisableLinkedList.indexOf(course)));

        //remove
        mayDisableLinkedList.remove(mayDisableLinkedList.indexOf(course));

        //check after remove
        assertNotEquals(course, mayDisableLinkedList.get(10));
    }

    /**
     * Method: remove(int index)
     * @throws Exception  exception not found element
     */
    @Test
    public void testRemoveIndex() throws Exception {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();



        //check
        assertEquals(course, mayDisableLinkedList.get(mayDisableLinkedList.indexOf(course)));

        //remove
        mayDisableLinkedList.remove(10);

        //check after remove
        assertNotEquals(course, mayDisableLinkedList.get(10));

    }



    /**
     * Method: listIterator(int index)
     * @throws Exception  exception out of index

     */
    @Test
    public void testListIterator() throws Exception {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();

        //check
        assertEquals(mayDisableLinkedList.get(2), mayDisableLinkedList.listIterator(2).next());
    }



    /**
     * Method: set(E e)
     * @throws Exception  exception out of index
     */
    @Test
    public void testSetE() throws Exception {
//TODO: Test goes here...
        GTUCourses MayDisableCourses = new GTUCourses();
        Course course = MayDisableCourses.getCoursesList().get(10);

        CustomLinkedList<Course> mayDisableLinkedList = MayDisableCourses.getCoursesList();


        assertEquals(course, mayDisableLinkedList.get(10));


        //remove
        mayDisableLinkedList.remove(mayDisableLinkedList.indexOf(course));


        //check
        assertNotEquals(course, mayDisableLinkedList.get(10));

        mayDisableLinkedList.set(10, course);

        //check
        assertEquals(course, mayDisableLinkedList.get(10));
    }

}