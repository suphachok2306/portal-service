package pcc.portal.portalback.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.entity.Course;
import pcc.portal.portalback.model.CourseModel;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.model.ReportPortalModel;
import pcc.portal.portalback.repository.CourseJpaRepository;
import pcc.portal.portalback.repository.PortalJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import java.io.Console;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseJpaRepository courseJpaRepository;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CourseService(CourseJpaRepository courseJpaRepository, ModelMapper modelMapper) {
        this.courseJpaRepository = courseJpaRepository;
        this.modelMapper = modelMapper;
        modelMapper.addConverter(context -> {
            System.out.print(modelMapper);
;            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (context.getSource() instanceof String) {
                try {
//                    Date date = dateFormat.parse((String) context.getSource());
//                    return new Timestamp(date.getTime());
                    return new Timestamp(dateFormat.parse((String) context.getSource()).getTime());
                } catch (ParseException e) {
                    // ในกรณีที่เกิดข้อผิดพลาดในการแปลง คุณสามารถจัดการข้อผิดพลาดได้ตามความเหมาะสม
                    e.printStackTrace();
                    return null; // หรือจะเลือกการคืนค่าเรื่องนี้ตามที่คุณต้องการ
                }
            }
            return null; // ในกรณีที่ไม่ใช่ String คุณสามารถจัดการได้ตามความเหมาะสม
        }, String.class, Timestamp.class);
    }


    public int calculateDateDifference(Timestamp startDate, Timestamp endDate) {
        long differenceInMillis = endDate.getTime() - startDate.getTime();
        long differenceInDays = differenceInMillis / (24 * 60 * 60 * 1000);

        return (int) differenceInDays + 1;
    }

    public Object saveCourse(CourseModel courseModel) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startDate = new Timestamp(sdf.parse(courseModel.getStartDate()).getTime());
        Timestamp endDate = new Timestamp(sdf.parse(courseModel.getEndDate()).getTime());

        Course courseEntity = modelMapper.map(courseModel, Course.class);
        courseEntity.setStartDate(startDate);
        courseEntity.setEndDate(endDate);

        return courseJpaRepository.save(courseEntity);
    }
}



