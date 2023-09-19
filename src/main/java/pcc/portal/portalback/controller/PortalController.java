package pcc.portal.portalback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.response.ApiResponse;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.service.PortalService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;



@RestController
@RequiredArgsConstructor
public class PortalController {
    private final PortalService portalService;

    private boolean isDataIncomplete(PortalModel portalModel) {
        return Objects.requireNonNull(portalModel.getDeptCode(), "DeptCode cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getDept(), "Dept cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getDate(), "Date cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getTopic(), "Topic cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getObjt(), "Objt cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getStartDate(), "StartDate cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEndDate(), "EndDate cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEmpCode(), "EmpCode cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEmpName(), "EmpName cannot be null").isEmpty() ||
                Objects.requireNonNull(portalModel.getEmpRole(), "EmpRole cannot be null").isEmpty() ||
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

//    @PostMapping("/ftr-of1/saveData")
//    public ResponseEntity<Object> save(@RequestBody PortalModel portalModel) {
//        try {
//            if (isDataIncomplete(portalModel)) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ApiResponse(400, "failure", null));
//            }
//            else {
//                Object result = portalService.saveData(portalModel);
//                ApiResponse response = new ApiResponse(200, "success", result);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            }
//    } catch (ParseException e) {
//            return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//    @PostMapping("/ftr-of1/saveData")
//    public String save(@RequestBody PortalModel portalModel) throws ParseException {
//        return portalService.saveData(portalModel);
//    }
//
//    @PostMapping("/ftr-oj1/edit")
//    public String edit(@RequestBody PortalModel portalModel) throws ParseException {
//        return portalService.editData(portalModel);
//    }

    @DeleteMapping("/ftr-oj1/deleteById")
    public String delete(@RequestParam Long id){
        return portalService.deleteData(id);
    }

    @GetMapping("/ftr-oj1/findAll")
    public Object search() {
        return portalService.findAll();
    }

    @GetMapping("/ftr-oj1/findById")
    public PortalModel findById(@RequestParam long of1_id) {
        return portalService.findById(of1_id);
    }

//    @GetMapping("/ftr-oj1/search")
//    public List<PortalModel> search(
//            @RequestParam(required = false) String empName,
//            @RequestParam(required = false) String empRole,
//            @RequestParam(required = false) String department,
//            @RequestParam(required = false) Timestamp startDate,
//            @RequestParam(required = false) Timestamp endDate,
//            @RequestParam(required = false) String topic
//
//    ) {
//        return portalService.search(empName, empRole, department,startDate,endDate,topic);
//    }

    @GetMapping("/ftr-oj1/search")
//    public List<PortalModel> search(
    public Object search(
            @RequestParam(required = false) String empName,
            @RequestParam(required = false) String empRole,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String topic
    ) {
        Timestamp startTimestamp = null;
        Timestamp endTimestamp = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        if (startDate != null && !startDate.isEmpty()) {
            try {
                Date parsedStartDate = dateFormat.parse(startDate);
                startTimestamp = new Timestamp(parsedStartDate.getTime());
                System.out.println(startTimestamp);
            } catch (ParseException e) {
                // ในกรณีที่รูปแบบวันที่ไม่ถูกต้อง
                e.printStackTrace();
            }
        }

        if (endDate != null && !endDate.isEmpty()) {
            try {
                Date parsedEndDate = dateFormat.parse(endDate);
                endTimestamp = new Timestamp(parsedEndDate.getTime());
            } catch (ParseException e) {
                // ในกรณีที่รูปแบบวันที่ไม่ถูกต้อง
                e.printStackTrace();
            }
        }

        return portalService.search(empName, empRole, department, startTimestamp, endTimestamp, topic);
    }


}