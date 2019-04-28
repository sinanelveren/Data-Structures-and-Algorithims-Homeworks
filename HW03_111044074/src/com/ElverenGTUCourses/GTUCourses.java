package com.ElverenGTUCourses;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * GTUCourse class that reading courses's info from file and hold them in linked list.
 * For part 1 of HW3
 * @author Sinan Elveren
 */
public class GTUCourses {
    /**
     * A linked list that hold all courses's info
     */
    private CustomLinkedList<Course> coursesList = new CustomLinkedList<>();


    /**
     * No parameter constructor that read data and fill to linked linked list obj
     * @throws IOException to file open/close or read
     */
    public GTUCourses() throws IOException {
        readData();

    }


    /**
     * Getter for linked lisst that all courses's info array
     * @return linked list that all courses's info array
     */
    public CustomLinkedList<Course> getCoursesList() {
        return coursesList;
    }


    /**
     * to read csv file and fill to linked list
     * @throws IOException to file open/close or read
     */
    private void readData() throws IOException {

        FileReader fr = new FileReader("Courses(CSV)(Updated).csv");


        BufferedReader inStream = new BufferedReader(fr);

        String[]   columns = new String[200];
        String   line = new String();

        //pass to first line
        inStream.readLine();


        //read lines in csv && and new course into linked list
        while ( (line = inStream.readLine()) != null) {
            columns = line.split(";");

            //make a new Course object and add to linked list
            Course course = new Course();
            course.setSemester(Integer.parseInt(columns[0]));
            course.setCode(columns[1]);
            course.setTitle(columns[2]);
            course.setCreditsECTS(Integer.parseInt(columns[3]));
            course.setCreditsGTU(Integer.parseInt(columns[4]));
            course.setHours(columns[5]);

            //add  into linked list
            coursesList.add(course);
        }
        inStream.close();
        fr.close();
    }


    /**
     * Found matched course given course's code  and return list which found.
     * @param code Course code to find the courses's info
     * @return  Courses's info that matched courses given course's code
     * @throws NoSuchElementException if there are no element matched
     */
    public List<Course> getByCode(String code) throws NoSuchElementException {
        List<Course> matchedCourses = new ArrayList<>();

        for (int i = 0; i < this.coursesList.size(); i++) {
            //add matched courses into list as course object
            if (coursesList.get(i).getCode().equals(code)) {
                matchedCourses.add(coursesList.get(i));
            }

        }

        if (matchedCourses.size() > 0 )
            //return to mathed courses as list
            return matchedCourses;
        else {
            //throw exception if there are no matched course given code
            throw new NoSuchElementException(code);
        }
    }


    /**
     * Found matched course given semester's info  and return list which found.
     * @param semester Course semester to find the courses's info
     * @return Courses's info that matched courses given course's semester
     * @throws NoSuchElementException if there are no element matched
     */
    public List<Course> listSemesterCourses(int semester) throws NoSuchElementException {
        List<Course> matchedCourses = new ArrayList<>();

        for (int i = 0; i < this.coursesList.size(); i++) {
            //add matched courses into list as course object
            if (coursesList.get(i).getSemester() == semester) {
                matchedCourses.add(coursesList.get(i));
            }

        }

        if (matchedCourses.size() > 0 )
            //return to mathed courses as list
            return matchedCourses;
        else {
            //throw exception if there are no matched course given semester
            throw new NoSuchElementException((Integer.toString(semester)));
        }
    }


    /**
     * Found matched course given range info  and return list which found.
     * @param startIndex first course index of range to find the courses's info
     * @param lastIndex last course index of range to find the courses's info
     * @return Courses's info that matched courses given range
     * @throws NoSuchElementException if there are no element matched
     * @throws IndexOutOfBoundsException if the range out of size
     */
    public List<Course> getByRange(int startIndex, int lastIndex)  throws NoSuchElementException, IndexOutOfBoundsException{
        List<Course> matchedCourses = new ArrayList<>();

        if(startIndex < 0 || startIndex > this.coursesList.size())
            throw new IndexOutOfBoundsException(Integer.toString(startIndex));
        if(lastIndex < 0 || lastIndex > this.coursesList.size())
            throw new IndexOutOfBoundsException(Integer.toString(lastIndex));

        //swap if start index smaller then last index
        if(lastIndex < startIndex) {
            int temp = startIndex;
            startIndex = lastIndex;
            lastIndex = temp;
        }

        //add courses that matched into list.
        for (int i = startIndex; i <= lastIndex; i++) {
            //add matched courses into list as course object
                matchedCourses.add(coursesList.get(i));
        }

        if (matchedCourses.size() > 0 )
            //return to mathed courses as list
            return matchedCourses;
        else {
            //throw exception if there are no matched course given range
            throw new NoSuchElementException((Integer.toString(startIndex) + "&" + Integer.toString(lastIndex)));
        }

    }
}
