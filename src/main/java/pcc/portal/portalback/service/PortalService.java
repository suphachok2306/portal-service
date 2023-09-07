package pcc.portal.portalback.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.repository.PortalJpaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortalService {
    private final PortalJpaRepository portalJpaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PortalService(PortalJpaRepository portalJpaRepository, ModelMapper modelMapper) {
        this.portalJpaRepository = portalJpaRepository;
        this.modelMapper = modelMapper;
        modelMapper.addConverter(context -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(context.getSource());
        }, Date.class, String.class);
    }


    public int calculateDateDifference(Date startDate, Date endDate) {
        long differenceInMillis = endDate.getTime() - startDate.getTime();
        long differenceInDays = differenceInMillis / (24 * 60 * 60 * 1000);

        return (int) differenceInDays + 1;
    }

    public String saveData(PortalModel portalModel) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(portalModel.getStartDate());
        Date endDate = sdf.parse(portalModel.getEndDate());

        int dateDifference = calculateDateDifference(startDate, endDate);

        PortalEntity portalEntity = modelMapper.map(portalModel, PortalEntity.class);
        portalEntity.setDay(dateDifference);
        portalJpaRepository.save(portalEntity);
        return "SUCCESS";
    }

    public String editData(PortalModel portalModel) throws ParseException {
        Optional<PortalEntity> optionalPortalEntity = portalJpaRepository.findById(portalModel.getOf1_id());

        //ใช้ตรวจสอบว่า NULL ก่อนส่งมารึป่าว
        if (optionalPortalEntity.isPresent()) {
            PortalEntity portalEntity = optionalPortalEntity.get();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(portalModel.getStartDate());
            Date endDate = sdf.parse(portalModel.getEndDate());

            int dateDifference = calculateDateDifference(startDate, endDate);

            portalEntity = modelMapper.map(portalModel, PortalEntity.class);
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

    public List<PortalModel> search(String name, String role, String department) {
        List<PortalEntity> entityList;

        if (name != null && !name.isEmpty()) {
            if (role != null && !role.isEmpty()) {
                if (department != null && !department.isEmpty()) {
                    // ค้นหาตามชื่อ, บทบาท, และแผนก
                    entityList = portalJpaRepository.findByEmpNameAndEmpRoleAndDept(name, role, department);
                } else {
                    // ค้นหาตามชื่อและบทบาท
                    entityList = portalJpaRepository.findByEmpNameAndEmpRole(name, role);
                }
            } else if (department != null && !department.isEmpty()) {
                // ค้นหาตามชื่อและแผนก
                entityList = portalJpaRepository.findByEmpNameAndDept(name, department);
            } else {
                // ค้นหาตามชื่อเท่านั้น
                entityList = portalJpaRepository.findByEmpName(name);
            }
        } else if (role != null && !role.isEmpty()) {
            if (department != null && !department.isEmpty()) {
                // ค้นหาตามบทบาทและแผนก
                entityList = portalJpaRepository.findByEmpRoleAndDept(role, department);
            } else {
                // ค้นหาตามบทบาทเท่านั้น
                entityList = portalJpaRepository.findByEmpRole(role);
            }
        } else if (department != null && !department.isEmpty()) {
            // ค้นหาตามแผนก
            entityList = portalJpaRepository.findByDept(department);
        } else {
            // ถ้าไม่ระบุเงื่อนไขใดๆ ให้คืนรายการทั้งหมด
            entityList = portalJpaRepository.findAll();
        }

        return entityList.stream()
                .map(portalEntity -> modelMapper.map(portalEntity, PortalModel.class))
                .collect(Collectors.toList());
    }




}



