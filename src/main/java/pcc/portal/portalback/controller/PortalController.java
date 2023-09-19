package pcc.portal.portalback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.entity.PortalEntity;
import pcc.portal.portalback.response.ApiResponse;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.response.ResponseData;
import pcc.portal.portalback.service.PortalService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;



@RestController
@RequiredArgsConstructor
public class PortalController {
    private final PortalService portalService;

    private boolean isDataIncomplete(PortalModel portalModel) {
                //////Section1
        return Objects.requireNonNull(portalModel.getDeptCode(), "DeptCode cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getDept(), "Dept cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getDate(), "Date cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getTopic(), "Topic cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getObjt(), "Objt cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getStartDate(), "StartDate cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEndDate(), "EndDate cannot be null").isEmpty() ||
                //fee
                //day
                Objects.requireNonNull(portalModel.getEmpCode(), "EmpCode cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEmpName(), "EmpName cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEmpRole(), "EmpRole cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getAction(), "Action cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getActionDate(), "ActionDate cannot be null").isEmpty() ||


                //////Section2
                Objects.requireNonNull(portalModel.getEvaluatorName(), "EvaluatorName cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEvaluatorRole(), "EvaluatorRole cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEvaluatorDept(), "EvaluatorDept cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEvaluatorSector(), "EvaluatorSector cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultOne(), "ResultOne cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultTwo(), "ResultTwo cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultThree(), "ResultThree cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultFour(), "ResultFour cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultFive(), "ResultFive cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultSix(), "ResultSix cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResultSeven(), "ResultSeven cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getComment(), "Comment cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getResult(), "Result cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getCause(), "Cause cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getPlan(), "Plan cannot be null").isEmpty();
    }


    @PostMapping("/ftr-of1/saveData")
    public ResponseEntity<?> save(@RequestBody PortalModel portalModel) throws ParseException {
        ApiResponse response = new ApiResponse();
        if (isDataIncomplete(portalModel)){
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้");
            Object result = portalService.saveData(portalModel);
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            portalService.saveData(portalModel);
            response.setResponseMessage("ทำรายการเรียบร้อย");
            Object result = portalService.saveData(portalModel);
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.ok(response);

        }catch (Exception e){
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ มีข้อผิดพลาดภายในเซิร์ฟเวอร์");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/ftr-oj1/edit")
    public ResponseEntity<?> edit(@RequestBody PortalModel portalModel) throws ParseException {
        ApiResponse response = new ApiResponse();
        if (isDataIncomplete(portalModel)){
            response.setResponseMessage("ไม่สามารถบแก้ไขและบันทึกข้อมูลลงฐานข้อมูลได้");
            Object result = portalService.saveData(portalModel);
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            portalService.saveData(portalModel);
            response.setResponseMessage("ทำรายการเรียบร้อย");
            Object result = portalService.editData(portalModel);
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.ok(response);

        }catch (Exception e){
            response.setResponseMessage("ไม่สามารถบแก้ไขและบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ มีข้อผิดพลาดภายในเซิร์ฟเวอร์");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/ftr-oj1/deleteById")
    public ResponseEntity<ApiResponse> delete(@RequestParam Long id) {
        ApiResponse response = new ApiResponse();
        try {
            String result = portalService.deleteData(id);
            if (result != null) {
                response.setResponseMessage("ลบข้อมูลเรียบร้อย");
                ResponseData responseData = new ResponseData();
                responseData.setResult(result);
                response.setResponseData(responseData);
                return ResponseEntity.ok(response);
            } else {
                response.setResponseMessage("ไม่พบข้อมูลที่ตรงกับ ID ที่ระบุ");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setResponseMessage("ไม่สามารถลบข้อมูลได้");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @GetMapping("/ftr-oj1/findAll")
    public ResponseEntity<ApiResponse> search() {
        ApiResponse response = new ApiResponse();
        try {
            List<PortalEntity> result = portalService.findAll();
            response.setResponseMessage("แสดงรายการค้นหาข้อมูลทั้งหมด");
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setResponseMessage("ไม่สามารถค้นหาได้");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/ftr-oj1/findById")
    public ResponseEntity<ApiResponse> findById(@RequestParam long of1_id) {
        ApiResponse response = new ApiResponse();
        try {
            PortalModel result = portalService.findById(of1_id);
            response.setResponseMessage("แสดงรายการที่ค้นหาตาม Id");
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setResponseMessage("ไม่สามารถค้นหาได้");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/ftr-oj1/search")
    public ResponseEntity<ApiResponse> search(
            @RequestParam(required = false) String empName,
            @RequestParam(required = false) String empRole,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String topic
    ) {
        ApiResponse response = new ApiResponse();
        Timestamp startTimestamp = null;
        Timestamp endTimestamp = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        if (startDate != null && !startDate.isEmpty()) {
            try {
                Date parsedStartDate = dateFormat.parse(startDate);
                startTimestamp = new Timestamp(parsedStartDate.getTime());
            } catch (ParseException e) {
                response.setResponseMessage("รูปแบบวันที่ไม่ถูกต้อง");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        if (endDate != null && !endDate.isEmpty()) {
            try {
                Date parsedEndDate = dateFormat.parse(endDate);
                endTimestamp = new Timestamp(parsedEndDate.getTime());
            } catch (ParseException e) {
                response.setResponseMessage("รูปแบบวันที่ไม่ถูกต้อง");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        Object result = portalService.search(empName, empRole, department, startTimestamp, endTimestamp, topic);

        if (result != null) {
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            response.setResponseMessage("ไม่พบข้อมูลที่ตรงกับคำค้นหา");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.setResponseMessage("ไม่สามารถค้นหาได้");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}