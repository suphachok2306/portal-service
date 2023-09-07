package pcc.portal.portalback.entity;
//import jakarta.persistence.*;
import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name="ftr_of1", catalog="sd-portal-back")
public class PortalEntity {

    private long of1_id;
    private String deptCode;
    private String dept;
    private Date date;
    private String topic;
    private String objt;
    private Date startDate;
    private Date endDate;
    private int day;
    private float fee;
    private String company;
    private String location;
    private String budget;
    private String empCode;
    private String empName;
    private String empRole;
    private String action;
    private Date actionDate;

    private String evaluatorName;
    private String evaluatorRole;
    private String evaluatorDept;
    private String evaluatorSector;
    private String resultOne;
    private String resultTwo;
    private String resultThree;
    private String resultFour;
    private String resultFive;
    private String resultSix;
    private String resultSeven;
    private String comment;
    private String result;
    private String cause;
    private String plan;

    public PortalEntity(){
        super();
    }

    public PortalEntity(long of1_id, String deptCode, String dept, Date date, String topic, String objt,
                        Date startDate, Date endDate, int day, float fee, String company, String location,
                        String budget, String empCode, String empName, String empRole, String action,
                        Date actionDate, String evaluatorName, String evaluatorRole, String evaluatorDept,
                        String evaluatorSector, String resultOne, String resultTwo, String resultThree,
                        String resultFour, String resultFive, String resultSix, String resultSeven,
                        String comment, String result, String cause, String plan) {
        this.of1_id = of1_id;
        this.deptCode = deptCode;
        this.dept = dept;
        this.date = date;
        this.topic = topic;
        this.objt = objt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.day = day;
        this.fee = fee;
        this.company = company;
        this.location = location;
        this.budget = budget;
        this.empCode = empCode;
        this.empName = empName;
        this.empRole = empRole;
        this.action = action;
        this.actionDate = actionDate;
        this.evaluatorName = evaluatorName;
        this.evaluatorRole = evaluatorRole;
        this.evaluatorDept = evaluatorDept;
        this.evaluatorSector = evaluatorSector;
        this.resultOne = resultOne;
        this.resultTwo = resultTwo;
        this.resultThree = resultThree;
        this.resultFour = resultFour;
        this.resultFive = resultFive;
        this.resultSix = resultSix;
        this.resultSeven = resultSeven;
        this.comment = comment;
        this.result = result;
        this.cause = cause;
        this.plan = plan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "of1_id", unique = true, nullable = false)
    public long getOf1_id() {return of1_id;}
    public void setOf1_id(long of1_id) {this.of1_id = of1_id;}

    @Column(name = "deptCode", length = 10)
    public String getDeptCode() {return deptCode;}
    public void setDeptCode(String deptCode) {this.deptCode = deptCode;}

    @Column(name = "dept", length = 10)
    public String getDept() {return dept;}
    public void setDept(String dept) {this.dept = dept;}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", length = 50)
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    @Column(name = "topic", length = 50)
    public String getTopic() {return topic;}
    public void setTopic(String topic) {this.topic = topic;}

    @Column(name = "objt", length = 50)
    public String getObjt() {return objt;}
    public void setObjt(String objt) {this.objt = objt;}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", length = 50)
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", length = 50)
    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    @Column(name = "day")
    public int getDay() {return day;}
    public void setDay(int day) {this.day = day;}

    @Column(name = "fee")
    public float getFee() {return fee;}
    public void setFee(float fee) {this.fee = fee;}

    @Column(name = "company", length = 50)
    public String getCompany() {return company;}
    public void setCompany(String company) {this.company = company;}

    @Column(name = "location", length = 50)
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    @Column(name = "budget", length = 50)
    public String getBudget() {return budget;}
    public void setBudget(String budget) {this.budget = budget;}

    @Column(name = "empCode", length = 20)
    public String getEmpCode() {return empCode;}
    public void setEmpCode(String empCode) {this.empCode = empCode;}

    @Column(name = "empName", length = 50)
    public String getEmpName() {return empName;}
    public void setEmpName(String empName) {this.empName = empName;}

    @Column(name = "empRole", length = 50)
    public String getEmpRole() {return empRole;}
    public void setEmpRole(String empRole) {this.empRole = empRole;}


    @Column(name = "action", length = 50)
    public String getAction() {return action;}
    public void setAction(String action) {this.action = action;}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "actionDate", length = 50)
    public Date getActionDate() {return actionDate;}
    public void setActionDate(Date actionDate) {this.actionDate = actionDate;}

    @Column(name = "evaluatorName", length = 50)
    public String getEvaluatorName() {return evaluatorName;}
    public void setEvaluatorName(String evaluatorName) {this.evaluatorName = evaluatorName;}

    @Column(name = "evaluatorRole", length = 50)
    public String getEvaluatorRole() {return evaluatorRole;}
    public void setEvaluatorRole(String evaluatorRole) {this.evaluatorRole = evaluatorRole;}

    @Column(name = "evaluatorDept", length = 50)
    public String getEvaluatorDept() {return evaluatorDept;}
    public void setEvaluatorDept(String evaluatorDept) {this.evaluatorDept = evaluatorDept;}

    @Column(name = "evaluatorSector", length = 50)
    public String getEvaluatorSector() {return evaluatorSector;}
    public void setEvaluatorSector(String evaluatorSector) {this.evaluatorSector = evaluatorSector;}

    @Column(name = "resultOne", length = 50)
    public String getResultOne() {return resultOne;}
    public void setResultOne(String resultOne) {this.resultOne = resultOne;}

    @Column(name = "resultTwo", length = 50)
    public String getResultTwo() {return resultTwo;}
    public void setResultTwo(String resultTwo) {this.resultTwo = resultTwo;}

    @Column(name = "resultThree", length = 50)
    public String getResultThree() {return resultThree;}
    public void setResultThree(String resultThree) {this.resultThree = resultThree;}

    @Column(name = "resultFour", length = 50)
    public String getResultFour() {return resultFour;}
    public void setResultFour(String resultFour) {this.resultFour = resultFour;}

    @Column(name = "resultFive", length = 50)
    public String getResultFive() {return resultFive;}
    public void setResultFive(String resultFive) {this.resultFive = resultFive;}

    @Column(name = "resultSix", length = 50)
    public String getResultSix() {return resultSix;}
    public void setResultSix(String resultSix) {this.resultSix = resultSix;}

    @Column(name = "resultSeven", length = 50)
    public String getResultSeven() {return resultSeven;}
    public void setResultSeven(String resultSeven) {this.resultSeven = resultSeven;}

    @Column(name = "comment", length = 50)
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    @Column(name = "result", length = 50)
    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    @Column(name = "cause", length = 50)
    public String getCause() {return cause;}
    public void setCause(String cause) {this.cause = cause;}

    @Column(name = "plan", length = 50)
    public String getPlan() {return plan;}
    public void setPlan(String plan) {this.plan = plan;}
}
