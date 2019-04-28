package com.ElverenGTUCourses;



/**
 * Course class that creating course object for hold the course's info
 * For part 1 of HW3
 * @author Sinan Elveren
 */
public class Course {
    /**
     * Course emester info
     */
    private int semester;
    /**
     * Course ETCS credits info
     */
    private int creditsECTS;
    /**
     * Course GTU credits info
     */
    private int creditsGTU;
    /**
     * Course code info
     */
    private String code = new String();
    /**
     * Course title info
     */
    private String title = new String();
    /**
     * Course hour info as Hour + Teoric + Labrotary
     */
    private String hours = new String();


    /**
     * No parameter constructor that initializing to course's info
     */
    protected Course(){
        semester = 0;
        creditsECTS = 0;
        creditsGTU = 0;
        code = null;
        title = null;
        hours = null;
    }


    /**
     * Getter for semester
     * @return semester info
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Setter for course semester info
     * @param semester semester info
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Getter for creditsECTS
     * @return ECTS credits info
     */
    public int getCreditsECTS() {
        return creditsECTS;
    }

    /**
     * Setter for ECTS credits
     * @param creditsECTS course ects credit info
     */
    public void setCreditsECTS(int creditsECTS) {
        this.creditsECTS = creditsECTS;
    }

    /**
     * Getter for creditsGTU
     * @return GTU credits info
     */
    public int getCreditsGTU() {
        return creditsGTU;
    }

    /**
     * Setter for GTU Credits
     * @param creditsGTU Course GTU Credit info
     */
    public void setCreditsGTU(int creditsGTU) {
        this.creditsGTU = creditsGTU;
    }

    /**
     * Getter for course code
     * @return Couse code info
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for course code
     * @param code Course code info
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for course title
     * @return coourse title info
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title Course title info
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for course hour info
     * @return hours info
     */
    public String getHours() {
        return hours;
    }

    /**
     * Setter for hours
     * @param hours Course hours info
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "**" + this.semester + " " + this.code + " " + this.title + " " +
                this.creditsECTS + " " + this.creditsGTU + " " + this.hours + "**";
    }
}



