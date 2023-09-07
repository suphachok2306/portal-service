package pcc.portal.portalback.entity;

import javax.persistence.*;

@Entity
@Table(name="sv1train", catalog="sd-portal-back")
public class ReportPortalEntity {
    private long sv1_id;
    private String year;
    private String department;
    private String position;
    private String className;
    private String no;
    private float fee;
    private float accommodation;
    private float totalExp;
    private String remark;

    public ReportPortalEntity(){
        super();
    }

    public ReportPortalEntity(long sv1_id, String year, String department, String position, String className,
                              String no, float fee, float accommodation, float totalExp,
                              String remark) {
        this.sv1_id = sv1_id;
        this.year = year;
        this.department = department;
        this.position = position;
        this.className = className;
        this.no = no;
        this.fee = fee;
        this.accommodation = accommodation;
        this.totalExp = totalExp;
        this.remark = remark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sv1_id", unique = true, nullable = false)
    public long getSv1_id() {return sv1_id;}
    public void setSv1_id(long sv1_id) {this.sv1_id = sv1_id;}

    @Column(name = "no", length = 20)
    public String getNo() {return no;}
    public void setNo(String no) {this.no = no;}

    @Column(name = "year", length = 10)
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    @Column(name = "department", length = 100)
    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}

    @Column(name = "position", length = 100)
    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}

    @Column(name = "className", length = 100)
    public String getClassName() {return className;}
    public void setClassName(String className) {this.className = className;}

    @Column(name = "fee")
    public float getFee() {return fee;}
    public void setFee(float fee) {this.fee = fee;}

    @Column(name = "accommodation")
    public float getAccommodation() {return accommodation;}
    public void setAccommodation(float accommodation) {this.accommodation = accommodation;}

    @Column(name = "totalExp")
    public float getTotalExp() {return totalExp;}
    public void setTotalExp(float totalExp) {this.totalExp = totalExp;}

    @Column(name = "remark", length = 250)
    public String getRemark() {return remark;}
    public void setRemark(String remark) {this.remark = remark;}
}
