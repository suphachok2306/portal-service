package pcc.portal.portalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.entity.ReportPortalEntity;

import java.util.List;

@Repository
public interface ReportPortalJpaRepository extends JpaRepository<ReportPortalEntity, Long> {

}

