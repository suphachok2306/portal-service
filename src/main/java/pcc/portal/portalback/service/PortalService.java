package pcc.portal.portalback.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.repository.PortalJpaRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortalService {
    private final PortalJpaRepository portalJpaRepository;
    private final ModelMapper modelMapper;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (context.getSource() instanceof String) {
                try {
                    Date date = dateFormat.parse((String) context.getSource());
                    return new Timestamp(date.getTime());
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startDate = new Timestamp(sdf.parse(portalModel.getStartDate()).getTime());
        Timestamp endDate = new Timestamp(sdf.parse(portalModel.getEndDate()).getTime());

        int dateDifference = calculateDateDifference(startDate, endDate);

        PortalEntity portalEntity = modelMapper.map(portalModel, PortalEntity.class);
        portalEntity.setDay(dateDifference);
        portalJpaRepository.save(portalEntity);
        return "SUCCESS";
    }

//    public String editData(PortalModel portalModel) throws ParseException {
//        Optional<PortalEntity> optionalPortalEntity = portalJpaRepository.findById(portalModel.getOf1_id());
//
//        //ใช้ตรวจสอบว่า NULL ก่อนส่งมารึป่าว
//        if (optionalPortalEntity.isPresent()) {
//            PortalEntity portalEntity = optionalPortalEntity.get();
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date startDate = sdf.parse(portalModel.getStartDate());
//            Date endDate = sdf.parse(portalModel.getEndDate());
//
//            int dateDifference = calculateDateDifference(startDate, endDate);
//
//            portalEntity = modelMapper.map(portalModel, PortalEntity.class);
//            portalEntity.setDay(dateDifference);
//            portalJpaRepository.save(portalEntity);
//
//            return "SUCCESS";
//        } else {
//            return "ERROR: Data not found";
//        }
//    }

    public String editData(PortalModel portalModel) throws ParseException {
        Optional<PortalEntity> optionalPortalEntity = portalJpaRepository.findById(portalModel.getOf1_id());

        if (optionalPortalEntity.isPresent()) {
            PortalEntity portalEntity = optionalPortalEntity.get();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp startDate = new Timestamp(sdf.parse(portalModel.getStartDate()).getTime());
            Timestamp endDate = new Timestamp(sdf.parse(portalModel.getEndDate()).getTime());

            int dateDifference = calculateDateDifference(startDate, endDate);

            portalEntity = modelMapper.map(portalModel, PortalEntity.class);
            portalEntity.setStartDate(startDate);
            portalEntity.setEndDate(endDate);
            portalEntity.setDay(dateDifference);
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


//    public List<PortalModel> search(String name, String role, String department) {
//        List<PortalEntity> entity = portalJpaRepository.findByNameAndRoleAndDepartment(name, role, department);
////        List<PortalModel> result = new ArrayList<>();
////
////        for(PortalEntity portalEntity : entity) {
////            PortalModel porModel = modelMapper.map(portalEntity, PortalModel.class);
////            result.add(porModel);
////        }
////        return result;
//        return entity.stream()
//                .map(portalEntity -> modelMapper.map(portalEntity, PortalModel.class))
//                .collect(Collectors.toList());
//    }

//    public List<PortalModel> search(String name, String role, String department) {
//        List<PortalEntity> entityList;
//
//        if (name != null && !name.isEmpty()) {
//            if (role != null && !role.isEmpty()) {
//                if (department != null && !department.isEmpty()) {
//                    // ค้นหาตามชื่อ, บทบาท, และแผนก
//                    entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDept(name, role, department);
//                } else {
//                    // ค้นหาตามชื่อและบทบาท
//                    entityList = portalJpaRepository.findByEmpNameAndEmpRole(name, role);
//                }
//            } else if (department != null && !department.isEmpty()) {
//                // ค้นหาตามชื่อและแผนก
//                entityList = portalJpaRepository.findByEmpNameAndDept(name, department);
//            } else {
//                // ค้นหาตามชื่อเท่านั้น
//                entityList = portalJpaRepository.findByEmpName(name);
//            }
//        } else if (role != null && !role.isEmpty()) {
//            if (department != null && !department.isEmpty()) {
//                // ค้นหาตามบทบาทและแผนก
//                entityList = portalJpaRepository.findByEmpRoleAndDept(role, department);
//            } else {
//                // ค้นหาตามบทบาทเท่านั้น
//                entityList = portalJpaRepository.findByEmpRole(role);
//            }
//        } else if (department != null && !department.isEmpty()) {
//            // ค้นหาตามแผนก
//            entityList = portalJpaRepository.findByDept(department);
//        } else {
//            // ถ้าไม่ระบุเงื่อนไขใดๆ ให้คืนรายการทั้งหมด
//            entityList = portalJpaRepository.findAll();
//        }
//
//        return entityList.stream()
//                .map(portalEntity -> modelMapper.map(portalEntity, PortalModel.class))
//                .collect(Collectors.toList());
//    }

    public List<PortalModel> search(String name, String role, String department, Timestamp startDate, Timestamp endDate, String topic) {
        List<PortalEntity> entityList;

        if (name != null && !name.isEmpty()) {
            if (role != null && !role.isEmpty()) {
                if (department != null && !department.isEmpty()) {
                    if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                        // ค้นหาตามชื่อ, บทบาท, แผนก, startDate, endDate, และ topic
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDeptAndStartDateAndEndDateAndTopic(name, role, department, startDate, endDate, topic);
                    } else if (startDate != null && endDate != null) {
                        // ค้นหาตามชื่อ, บทบาท, แผนก, startDate, และ endDate
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDeptAndStartDateAndEndDate(name, role, department, startDate, endDate);
                    } else if (topic != null && !topic.isEmpty()) {
                        // ค้นหาตามชื่อ, บทบาท, แผนก, และ topic
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDeptAndTopic(name, role, department, topic);
                    } else {
                        // ค้นหาตามชื่อ, บทบาท, และแผนก
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDept(name, role, department);
                    }
                } else {
                    if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                        // ค้นหาตามชื่อ, บทบาท, startDate, endDate, และ topic
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndStartDateAndEndDateAndTopic(name, role, startDate, endDate, topic);
                    } else if (startDate != null && endDate != null) {
                        // ค้นหาตามชื่อ, บทบาท, startDate, และ endDate
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndStartDateAndEndDate(name, role, startDate, endDate);
                    } else if (topic != null && !topic.isEmpty()) {
                        // ค้นหาตามชื่อ, บทบาท, และ topic
                        entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndTopic(name, role, topic);
                    } else {
                        // ค้นหาตามชื่อและบทบาท
                        entityList = portalJpaRepository.findByEmpNameAndEmpRole(name, role);
                    }
                }
            } else if (department != null && !department.isEmpty()) {
                if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                    // ค้นหาตามชื่อ, แผนก, startDate, endDate, และ topic
                    entityList = portalJpaRepository.findByEmpNameAndDeptAndStartDateAndEndDateAndTopic(name, department, startDate, endDate, topic);
                } else if (startDate != null && endDate != null) {
                    // ค้นหาตามชื่อ, แผนก, startDate, และ endDate
                    entityList = portalJpaRepository.findByEmpNameAndDeptAndStartDateAndEndDate(name, department, startDate, endDate);
                } else if (topic != null && !topic.isEmpty()) {
                    // ค้นหาตามชื่อ, แผนก, และ topic
                    entityList = portalJpaRepository.findByEmpNameAndDeptAndTopic(name, department, topic);
                } else {
                    // ค้นหาตามชื่อและแผนก
                    entityList = portalJpaRepository.findByEmpNameAndDept(name, department);
                }
            } else {
                if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                    // ค้นหาตามชื่อ, startDate, endDate, และ topic
                    entityList = portalJpaRepository.findByEmpNameAndStartDateAndEndDateAndTopic(name, startDate, endDate, topic);
                } else if (startDate != null && endDate != null) {
                    // ค้นหาตามชื่อ, startDate, และ endDate
                    entityList = portalJpaRepository.findByEmpNameAndStartDateAndEndDate(name, startDate, endDate);
                } else if (topic != null && !topic.isEmpty()) {
                    // ค้นหาตามชื่อและ topic
                    entityList = portalJpaRepository.findByEmpNameAndTopic(name, topic);
                } else {
                    // ค้นหาตามชื่อเท่านั้น
                    entityList = portalJpaRepository.findByEmpName(name);
                }
            }
        } else if (role != null && !role.isEmpty()) {
            if (department != null && !department.isEmpty()) {
                if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                    // ค้นหาตามบทบาท, แผนก, startDate, endDate, และ topic
                    entityList = portalJpaRepository.findByEmpRoleAndDeptAndStartDateAndEndDateAndTopic(role, department, startDate, endDate, topic);
                } else if (startDate != null && endDate != null) {
                    // ค้นหาตามบทบาท, แผนก, startDate, และ endDate
                    entityList = portalJpaRepository.findByEmpRoleAndDeptAndStartDateAndEndDate(role, department, startDate, endDate);
                } else if (topic != null && !topic.isEmpty()) {
                    // ค้นหาตามบทบาท, แผนก, และ topic
                    entityList = portalJpaRepository.findByEmpRoleAndDeptAndTopic(role, department, topic);
                } else {
                    // ค้นหาตามบทบาทและแผนก
                    entityList = portalJpaRepository.findByEmpRoleAndDept(role, department);
                }
            } else {
                if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                    // ค้นหาตามบทบาท, startDate, endDate, และ topic
                    entityList = portalJpaRepository.findByEmpRoleAndStartDateAndEndDateAndTopic(role, startDate, endDate, topic);
                } else if (startDate != null && endDate != null) {
                    // ค้นหาตามบทบาท, startDate, และ endDate
                    entityList = portalJpaRepository.findByEmpRoleAndStartDateAndEndDate(role, startDate, endDate);
                } else if (topic != null && !topic.isEmpty()) {
                    // ค้นหาตามบทบาทและ topic
                    entityList = portalJpaRepository.findByEmpRoleAndTopic(role, topic);
                } else {
                    // ค้นหาตามบทบาทเท่านั้น
                    entityList = portalJpaRepository.findByEmpRole(role);
                }
            }
        } else if (department != null && !department.isEmpty()) {
            if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                // ค้นหาตามแผนก, startDate, endDate, และ topic
                entityList = portalJpaRepository.findByDeptAndStartDateAndEndDateAndTopic(department, startDate, endDate, topic);
            } else if (startDate != null && endDate != null) {
                // ค้นหาตามแผนก, startDate, และ endDate
                entityList = portalJpaRepository.findByDeptAndStartDateAndEndDate(department, startDate, endDate);
            } else if (topic != null && !topic.isEmpty()) {
                // ค้นหาตามแผนกและ topic
                entityList = portalJpaRepository.findByDeptAndTopic(department, topic);
            } else {
                // ค้นหาตามแผนกเท่านั้น
                entityList = portalJpaRepository.findByDept(department);
            }
        } else {
            if (startDate != null && endDate != null && topic != null && !topic.isEmpty()) {
                // ค้นหาตาม startDate, endDate, และ topic
                entityList = portalJpaRepository.findByStartDateAndEndDateAndTopic(startDate, endDate, topic);
            } else if (startDate != null && endDate != null) {
                // ค้นหาตาม startDate และ endDate
                entityList = portalJpaRepository.findByStartDateAndEndDate(startDate, endDate);
            } else if (topic != null && !topic.isEmpty()) {
                // ค้นหาตาม topic
                entityList = portalJpaRepository.findByTopic(topic);
            } else {
                // ถ้าไม่ระบุเงื่อนไขใดๆ ให้คืนรายการทั้งหมด
                entityList = portalJpaRepository.findAll();
            }
        }

        return entityList.stream()
                .map(portalEntity -> modelMapper.map(portalEntity, PortalModel.class))
                .collect(Collectors.toList());
    }





}



