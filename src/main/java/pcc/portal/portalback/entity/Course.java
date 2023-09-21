package pcc.portal.portalback.entity;
//import jakarta.persistence.*;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name="Course", catalog="sd-portal-back")
public class Course{

    private long course_id;
    private String course_name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String time;
    private String note;
    private float price;
    private float priceProject;
    private String place;
    private String institute;

    public Course(){
        super();
    }


    public Course(long course_id, String course_name, Timestamp startDate, Timestamp endDate, String time, String place, String institute) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.place = place;
        this.institute = institute;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", unique = true, nullable = false)
    public long getCourse_id() {return this.course_id;}
    public void setCourse_id(long course_id) {this.course_id = course_id;}

    @Column(name = "course_name", length = 100)
    public String getCourse_name() {return this.course_name;}
    public void setCourse_name(String course_name) {this.course_name = course_name;}
    
    @Column(name = "startDate", length = 50)
    public Timestamp getStartDate() {return this.startDate;}
    public void setStartDate(Timestamp startDate) {this.startDate = startDate;}

    @Column(name = "endDate", length = 50)
    public Timestamp getEndDate() {return this.endDate;}
    public void setEndDate(Timestamp endDate) {this.endDate = endDate;}

    @Column(name = "time", length = 50)
    public String getTime() {return this.time;}
    public void setTime(String time) {this.time = time;}

    @Column(name = "note", length = 150)
    public String getNote() {return this.note;}
    public void setNote(String note) {this.note = note;}

    @Column(name = "price", length = 30)
    public float getPrice() {return this.price;}
    public void setPrice(float price) {this.price = price;}

    @Column(name = "priceProject", length = 30)
    public float getPriceProject() {return this.priceProject;}
    public void setPriceProject(float priceProject) {this.priceProject = priceProject;}

    @Column(name = "place", length = 100)
    public String getPlace() {return this.place;}
    public void setPlace(String place) {this.place = place;}

    @Column(name = "institute", length = 100)
    public String getInstitute() {return this.institute;}
    public void setInstitute(String institute) {this.institute = institute;}




}


