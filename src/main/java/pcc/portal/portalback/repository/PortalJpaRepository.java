package pcc.portal.portalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pcc.portal.portalback.entity.PortalEntity;

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
}

