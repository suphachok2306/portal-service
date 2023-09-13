package pcc.portal.portalback.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.entity.ReportPortalEntity;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.model.ReportPortalModel;
import pcc.portal.portalback.repository.ReportPortalJpaRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportPortalService {
    private final ReportPortalJpaRepository reportPortalJpaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportPortalService(ReportPortalJpaRepository reportPortalJpaRepository, ModelMapper modelMapper){
        this.reportPortalJpaRepository = reportPortalJpaRepository;
        this.modelMapper = modelMapper;
    }

    public String saveData(ReportPortalModel reportPortalModel){
        ReportPortalEntity reportPortalEntity = modelMapper.map(reportPortalModel, ReportPortalEntity.class);
        reportPortalJpaRepository.save(reportPortalEntity);
        return "SUCCESS";
    }

    public String editData(ReportPortalModel reportPortalModel){
        Optional<ReportPortalEntity> optionalReportPortalEntity = reportPortalJpaRepository.findById(reportPortalModel.getSv1_id());

        //ใช้ตรวจสอบว่า NULL ก่อนส่งมารึป่าว
        if (optionalReportPortalEntity.isPresent()) {
            ReportPortalEntity reportPortalEntity = optionalReportPortalEntity.get();
            reportPortalEntity = modelMapper.map(reportPortalModel, ReportPortalEntity.class);
            reportPortalJpaRepository.save(reportPortalEntity);

            return "SUCCESS";
        } else {
            return "ERROR: Data not found";
        }
    }


    public String deleteData(Long id) {
        Optional<ReportPortalEntity> optionalReportPortalEntity = reportPortalJpaRepository.findById(id);
        //ใช้ตรวจสอบว่า NULL ก่อนส่งมารึป่าว
        if (optionalReportPortalEntity.isPresent()) {
            reportPortalJpaRepository.deleteById(id);
            return "SUCCESS";
        } else {
            return "ERROR: Data not found";
        }
    }


    public Object findAll() {
        return reportPortalJpaRepository.findAll();
    }

//    public List<ReportPortalModel> search(String year, String department) {
    public Object search(String year, String department) {
        List<ReportPortalEntity> entityList;

        if (year != null && !year.isEmpty() && department != null && !department.isEmpty()) {
            entityList = reportPortalJpaRepository.findByYearAndDepartment(year, department);
        } else {
            //entityList = reportPortalJpaRepository.findAll();
            return "ERROR: Null";
        }
        return entityList.stream()
                .map(reportPortalEntity -> modelMapper.map(reportPortalEntity, ReportPortalModel.class))
                .collect(Collectors.toList());
    }



    public List<ReportPortalModel> getReportPortalModel() {
        List<ReportPortalEntity> reportPortalEntityList = reportPortalJpaRepository.findAll();
        List<ReportPortalModel> reportPortalModelList = new ArrayList<>();
        for (ReportPortalEntity reportPortalEntity : reportPortalEntityList) {
            ReportPortalModel reportPortalModel = modelMapper.map(reportPortalEntity, ReportPortalModel.class);
            reportPortalModelList.add(reportPortalModel);
        }
        return reportPortalModelList;
    }


    public String covertBase64() {
        List<ReportPortalModel> result = getReportPortalModel();
        try {
            JRBeanCollectionDataSource JRBdataset = new JRBeanCollectionDataSource(result);
            HashMap<String, Object> params = new HashMap<>();
            params.put("trainingTable", JRBdataset);
            InputStream reportInput = PortalService.class.getClassLoader().getResourceAsStream("report/portalReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportInput);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,JRBdataset);
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

//    public String printByJson(byte[] jsonBytes) throws IOException {
//        String json = null;
//        json = new String(jsonBytes, "UTF-8");
//        List<ReportPortalModel> result = getReportPortalModel();
//
//        try {
//            //put parameters
//            HashMap<String, Object> params = new HashMap<>();
////            params.put("name", "ศุภโชค");
////            params.put("surename", "เฮงโชตน์ปภา");
////            params.put("nickname", "ป้อ");
////            params.put("university", "เกษตรศาสตร์ บางเขน");
//
//            // convert JSON file to Byte Array InputStream
//            ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(json.getBytes());
//            //JsonDataSource
//            JsonDataSource dataSource = new JsonDataSource(jsonDataStream);
//
//            //path jrxml file
//            InputStream reportInput = ReportPortalService.class.getClassLoader().getResourceAsStream("report/manowVrt.jrxml");
//            // compileReport JRXML file to JASPER file
//            JasperReport jasperReport = JasperCompileManager.compileReport(reportInput);
//            //fillReport
//            JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport, params,dataSource);
//
//            //exportReport to PDF
//            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint2);
//            //byte to Base64
//            return Base64.encodeBase64String(bytes);
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
