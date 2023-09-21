package pcc.portal.portalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pcc.portal.portalback.entity.Course;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}

