package pcc.portal.portalback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.response.ResponseApi;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.service.PortalService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequiredArgsConstructor
public class PortalController {
    private final PortalService portalService;

    @PostMapping("/ftr-of1/save")
//    public Object save(@RequestBody PortalModel portalModel) throws ParseException {
//        return portalService.saveData(portalModel);
//    }
    public ResponseEntity<?> save(@RequestBody PortalModel portalModel) {
        ResponseApi api = new ResponseApi();
        try {
            api.setResponseCode("200");
            api.setResponseStatus("SUCCESS");
            api.setResponseMessage("ekhiei");
            return ResponseEntity.ok(portalService.saveData(portalModel));
        } catch (Exception e) {
            api.setResponseCode("400");
            api.setResponseStatus("ERROR");
            api.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้");
            return ResponseEntity.status(BAD_REQUEST).body(api);
        }
    }
    @PostMapping("/ftr-oj1/edit")
    public ResponseEntity<?> edit(@RequestBody PortalModel portalModel) {
        ResponseApi api = new ResponseApi();
        try {
            return ResponseEntity.ok(portalService.editData(portalModel));
        } catch (ParseException e) {
            api.setResponseCode("400");
            api.setResponseStatus("ERROR");
            api.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้");
            return ResponseEntity.status(BAD_REQUEST).body(api);
        }

    }

    @DeleteMapping("/ftr-oj1/deleteById")
    public ResponseEntity<?> delete(@RequestParam Long id){
        ResponseApi api = new ResponseApi();
        try {
            return ResponseEntity.ok(portalService.deleteData(id));
        } catch (Exception e) {
            api.setResponseCode("400");
            api.setResponseStatus("ERROR");
            api.setResponseMessage("ไม่สามารถทำรายการได้้");
            return ResponseEntity.status(BAD_REQUEST).body(api);
        }
    }

    @GetMapping("/ftr-oj1/findAll")
    public Object findAll() {
        return portalService.findAll();
    }

    @GetMapping("/ftr-oj1/findById")
    public ResponseEntity<?> findById(@RequestParam long of1_id) {
        ResponseApi api = new ResponseApi();
        try {
            return ResponseEntity.ok(portalService.findById(of1_id));
        } catch (Exception e) {
            api.setResponseCode("400");
            api.setResponseStatus("ERROR");
            api.setResponseMessage("ไม่สามารถทำรายการได้้");
            return ResponseEntity.status(BAD_REQUEST).body(api);
        }
    }


    @GetMapping("/ftr-oj1/search")
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