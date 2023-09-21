package pcc.portal.portalback.model;

import java.sql.Timestamp;

public class CourseModel {
    private long course_id;
    private String course_name;
    private String startDate;
    private String endDate;
    private String time;
    private String note;
    private float price;
    private float priceProject;
    private String place;
    private String institute;

    public long getCourse_id() {
        return this.course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceProject() {
        return this.priceProject;
    }

    public void setPriceProject(float priceProject) {
        this.priceProject = priceProject;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInstitute() {
        return this.institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    
    
}

