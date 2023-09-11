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
}

