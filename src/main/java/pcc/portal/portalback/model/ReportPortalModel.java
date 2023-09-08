package pcc.portal.portalback.model;

public class ReportPortalModel {
    private long sv1_id;
    private int index;
    private String year;
    private String department;
    private String position;
    private String className;
    private String no;
    private float fee;
    private float accommodation;
    private float totalExp;
    private String remark;

    public long getSv1_id() {return sv1_id;}
    public void setSv1_id(long sv1_id) {this.sv1_id = sv1_id;}

    public int getIndex() {return index;}
    public void setIndex(int index) {this.index = index;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}

    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}

    public String getClassName() {return className;}
    public void setClassName(String className) {this.className = className;}

    public String getNo() {return no;}
    public void setNo(String no) {this.no = no;}

    public float getFee() {return fee;}
    public void setFee(float fee) {this.fee = fee;}

    public float getAccommodation() {return accommodation;}
    public void setAccommodation(float accommodation) {this.accommodation = accommodation;}

    public float getTotalExp() {return totalExp;}
    public void setTotalExp(float totalExp) {this.totalExp = totalExp;}

    public String getRemark() {return remark;}
    public void setRemark(String remark) {this.remark = remark;}
}
