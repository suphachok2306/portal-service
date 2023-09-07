package pcc.portal.portalback.model;
import java.util.Date;


//@JsonIgnoreProperties(value = { "code", "dept", "date", "objt", "fee", "company",
//        "location", "budget", "etcDetails", "empCode", "action", "actionDate", "name",
//        "role", "sector", "resultOne", "resultTwo", "resultThree", "resultFour", "resultFive",
//        "resultSix", "resultSeven", "comment"}, ignoreUnknown = true)


public class PortalModel {
    private long of1_id;
    private String deptCode;
    private String dept;
    private String date;
    private String topic;
    private String objt;
    private String startDate;
    private String endDate;
    private int day;
    private float fee;
    private String company;
    private String location;
    private String budget;
    private String empCode;
    private String empName;
    private String empRole;
    private String action;
    private String actionDate;

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

    public long getOf1_id() {return of1_id;}
    public void setOf1_id(long of1_id) {this.of1_id = of1_id;}

    public String getDeptCode() {return deptCode;}
    public void setDeptCode(String deptCode) {this.deptCode = deptCode;}

    public String getDept() {return dept;}
    public void setDept(String dept) {this.dept = dept;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getTopic() {return topic;}
    public void setTopic(String topic) {this.topic = topic;}

    public String getObjt() {return objt;}
    public void setObjt(String objt) {this.objt = objt;}

    public String getStartDate() {return startDate;}
    public void setStartDate(String startDate) {this.startDate = startDate;}

    public String getEndDate() {return endDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}

    public int getDay() {return day;}
    public void setDay(int day) {this.day = day;}

    public float getFee() {return fee;}
    public void setFee(float fee) {this.fee = fee;}

    public String getCompany() {return company;}
    public void setCompany(String company) {this.company = company;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public String getBudget() {return budget;}
    public void setBudget(String budget) {this.budget = budget;}

    public String getEmpCode() {return empCode;}
    public void setEmpCode(String empCode) {this.empCode = empCode;}

    public String getEmpName() {return empName;}
    public void setEmpName(String empName) {this.empName = empName;}

    public String getEmpRole() {return empRole;}
    public void setEmpRole(String empRole) {this.empRole = empRole;}

    public String getAction() {return action;}
    public void setAction(String action) {this.action = action;}

    public String getActionDate() {return actionDate;}
    public void setActionDate(String actionDate) {this.actionDate = actionDate;}

    public String getEvaluatorName() {return evaluatorName;}
    public void setEvaluatorName(String evaluatorName) {this.evaluatorName = evaluatorName;}

    public String getEvaluatorRole() {return evaluatorRole;}
    public void setEvaluatorRole(String evaluatorRole) {this.evaluatorRole = evaluatorRole;}

    public String getEvaluatorDept() {return evaluatorDept;}
    public void setEvaluatorDept(String evaluatorDept) {this.evaluatorDept = evaluatorDept;}

    public String getEvaluatorSector() {return evaluatorSector;}
    public void setEvaluatorSector(String evaluatorSector) {this.evaluatorSector = evaluatorSector;}

    public String getResultOne() {return resultOne;}
    public void setResultOne(String resultOne) {this.resultOne = resultOne;}

    public String getResultTwo() {return resultTwo;}
    public void setResultTwo(String resultTwo) {this.resultTwo = resultTwo;}

    public String getResultThree() {return resultThree;}
    public void setResultThree(String resultThree) {this.resultThree = resultThree;}

    public String getResultFour() {return resultFour;}
    public void setResultFour(String resultFour) {this.resultFour = resultFour;}

    public String getResultFive() {return resultFive;}
    public void setResultFive(String resultFive) {this.resultFive = resultFive;}

    public String getResultSix() {return resultSix;}
    public void setResultSix(String resultSix) {this.resultSix = resultSix;}

    public String getResultSeven() {return resultSeven;}
    public void setResultSeven(String resultSeven) {this.resultSeven = resultSeven;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    public String getCause() {return cause;}
    public void setCause(String cause) {this.cause = cause;}

    public String getPlan() {return plan;}
    public void setPlan(String plan) {this.plan = plan;}
}

