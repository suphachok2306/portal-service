package pcc.portal.portalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pcc.portal.portalback.entity.PortalEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


//@Repository
//public interface PortalJpaRepository extends JpaRepository<PortalEntity, Long> {
//    @Query("SELECT p FROM PortalEntity p WHERE p.empName = :empName AND p.empRole = :empRole AND p.dept = :dept")
//    List<PortalEntity> findByNameAndRoleAndDepartment(@Param("empName") String tName, @Param("empRole") String tRole, @Param("dept") String dept);
//}

@Repository
public interface PortalJpaRepository extends JpaRepository<PortalEntity, Long> {

    List<PortalEntity> findByEmpName(String empName);

    List<PortalEntity> findByEmpRole(String empRole);

    List<PortalEntity> findByDept(String dept);

    List<PortalEntity> findByEmpNameAndEmpRole(String empName, String empRole);

    List<PortalEntity> findByEmpNameAndDept(String empName, String dept);

    List<PortalEntity> findByEmpRoleAndDept(String empRole, String dept);

    List<PortalEntity> findByEmpNameAndEmpRoleAndDept(String empName, String empRole, String dept);

    List<PortalEntity> findByEmpNameAndEmpRoleAndDeptAndStartDateAndEndDateAndTopic(String name, String role, String department, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpNameAndEmpRoleAndDeptAndStartDateAndEndDate(String name, String role, String department, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpNameAndEmpRoleAndDeptAndTopic(String name, String role, String department, String topic);

    List<PortalEntity> findByEmpNameAndEmpRoleAndStartDateAndEndDateAndTopic(String name, String role, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpNameAndEmpRoleAndStartDateAndEndDate(String name, String role, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpNameAndEmpRoleAndTopic(String name, String role, String topic);

    List<PortalEntity> findByEmpNameAndDeptAndStartDateAndEndDateAndTopic(String name, String department, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpNameAndDeptAndStartDateAndEndDate(String name, String department, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpNameAndDeptAndTopic(String name, String department, String topic);

    List<PortalEntity> findByEmpNameAndStartDateAndEndDateAndTopic(String name, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpNameAndStartDateAndEndDate(String name, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpNameAndTopic(String name, String topic);

    List<PortalEntity> findByEmpRoleAndDeptAndStartDateAndEndDateAndTopic(String role, String department, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpRoleAndDeptAndStartDateAndEndDate(String role, String department, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpRoleAndDeptAndTopic(String role, String department, String topic);

    List<PortalEntity> findByEmpRoleAndStartDateAndEndDateAndTopic(String role, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByEmpRoleAndStartDateAndEndDate(String role, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByEmpRoleAndTopic(String role, String topic);

    List<PortalEntity> findByDeptAndStartDateAndEndDateAndTopic(String department, Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByDeptAndStartDateAndEndDate(String department, Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByDeptAndTopic(String department, String topic);

    List<PortalEntity> findByStartDateAndEndDateAndTopic(Timestamp startDate, Timestamp endDate, String topic);

    List<PortalEntity> findByStartDateAndEndDate(Timestamp startDate, Timestamp endDate);

    List<PortalEntity> findByTopic(String topic);
}

