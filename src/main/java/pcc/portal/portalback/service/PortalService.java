package pcc.portal.portalback.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.repository.PortalJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortalService {
    private final PortalJpaRepository portalJpaRepository;
    private final ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    public PortalService(PortalJpaRepository portalJpaRepository, ModelMapper modelMapper) {
//        this.portalJpaRepository = portalJpaRepository;
//        this.modelMapper = modelMapper;
//        modelMapper.addConverter(context -> {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            return dateFormat.format(context.getSource());
////        }, Date.class, String.class);
//        }, Timestamp.class, String.class);
//    }
    @Autowired
    public PortalService(PortalJpaRepository portalJpaRepository, ModelMapper modelMapper) {
        this.portalJpaRepository = portalJpaRepository;
        this.modelMapper = modelMapper;
        modelMapper.addConverter(context -> {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    //test timestamp
    public int calculateDateDifference(Timestamp startDate, Timestamp endDate) {
        long differenceInMillis = endDate.getTime() - startDate.getTime();
        long differenceInDays = differenceInMillis / (24 * 60 * 60 * 1000);

        return (int) differenceInDays + 1;
    }

    public String saveData(PortalModel portalModel) throws ParseException {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startDate = new Timestamp(sdf.parse(portalModel.getStartDate()).getTime());
        Timestamp endDate = new Timestamp(sdf.parse(portalModel.getEndDate()).getTime());
        int dateDifference = calculateDateDifference(startDate, endDate);

        PortalEntity portalEntity = modelMapper.map(portalModel, PortalEntity.class);
        portalEntity.setDate(new Timestamp(System.currentTimeMillis()));
//        portalEntity.setStartDate(startDate);
//        portalEntity.setEndDate(endDate);
        portalEntity.setDay(dateDifference);
        portalJpaRepository.save(portalEntity);
        return "SUCCESS";
    }

    //ใช้ saveData แทนได้เหมือนกัน
    public String editData(PortalModel portalModel) throws ParseException {
        Optional<PortalEntity> optionalPortalEntity = portalJpaRepository.findById(portalModel.getOf1_id());

        if (optionalPortalEntity.isPresent()) {
            PortalEntity portalEntity = optionalPortalEntity.get();

            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp startDate = new Timestamp(sdf.parse(portalModel.getStartDate()).getTime());
            Timestamp endDate = new Timestamp(sdf.parse(portalModel.getEndDate()).getTime());

            SimpleDateFormat forDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Timestamp saveDate = new Timestamp(forDate.parse(portalModel.getDate()).getTime());

            int dateDifference = calculateDateDifference(startDate, endDate);

            portalEntity = modelMapper.map(portalModel, PortalEntity.class);
//            portalEntity.setStartDate(startDate);
//            portalEntity.setEndDate(endDate);
            portalEntity.setDay(dateDifference);
            portalEntity.setDate(saveDate);

            portalJpaRepository.save(portalEntity);

            return "SUCCESS";
        } else {
            return "ERROR: Data not found";
        }
    }


    public String deleteData(Long id) {
        Optional<PortalEntity> optionalPortalEntity = portalJpaRepository.findById(id);
        //ใช้ตรวจสอบว่า NULL ก่อนส่งมารึป่าว
        if (optionalPortalEntity.isPresent()) {
            portalJpaRepository.deleteById(id);
            return "SUCCESS";
        } else {
            return "ERROR: Data not found";
        }
    }


    public List<PortalEntity> findAll() {
        return portalJpaRepository.findAll();
    }

    public PortalModel findById(long id) {
        Optional<PortalEntity> entity = portalJpaRepository.findById(id);

        if (entity.isPresent()) {
            PortalEntity portalEntity = entity.get();
            return modelMapper.map(portalEntity, PortalModel.class);
        } else {
            throw new EntityNotFoundException("ID Not Found.");
        }
    }


    public Object search(String name, String role, String department, Timestamp startDate, Timestamp endDate, String topic) {
        List<PortalEntity> entityList;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PortalEntity> query = builder.createQuery(PortalEntity.class);
        Root<PortalEntity> root = query.from(PortalEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            //predicates.add(builder.equal(root.get("empName"), name));
            //predicates.add(builder.like(root.get("empName"),  name + "%"));
            predicates.add(builder.like(builder.lower(root.get("empName")), "%" + name.toLowerCase() + "%"));
        }

        if (role != null) {
            //predicates.add(builder.equal(root.get("empRole"), role));
            predicates.add(builder.like(builder.lower(root.get("empRole")), "%" + role.toLowerCase() + "%"));

        }

        if (department != null) {
            //predicates.add(builder.equal(root.get("dept"), department));
            predicates.add(builder.like(builder.lower(root.get("dept")), "%" + department.toLowerCase() + "%"));
        }

        //search ตรงตัว
//        if (startDate != null && endDate != null) {
//            predicates.add(builder.equal(root.get("startDate"), startDate));
//            predicates.add(builder.equal(root.get("endDate"), endDate));
//        }

        if (startDate != null) {
            predicates.add(builder.equal(root.get("startDate"), startDate));
        }

        if (endDate != null) {
            predicates.add(builder.equal(root.get("endDate"), endDate));
        }

        //ห้าม null ทั้ง2ตัว
        //search ในช่วงเวลา
//        if (startDate != null && endDate != null) {
//            predicates.add(builder.lessThanOrEqualTo(root.get("startDate"), startDate));
//            predicates.add(builder.greaterThanOrEqualTo(root.get("endDate"), endDate));
//        }

        if (topic != null) {
            predicates.add(builder.like(builder.lower(root.get("topic")), "%" + topic.toLowerCase() + "%"));
        }

        if (name == null && role == null && department == null && startDate == null && endDate == null && topic == null){
            return "ERROR: Null";
        }

        query.where(predicates.toArray(new Predicate[0]));

        entityList = entityManager.createQuery(query).getResultList();

        return entityList.stream()
                .map(portalEntity -> modelMapper.map(portalEntity, PortalModel.class))
                .collect(Collectors.toList());
    }





}



