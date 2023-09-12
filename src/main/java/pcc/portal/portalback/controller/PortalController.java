package pcc.portal.portalback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.service.PortalService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
public class PortalController {
    private final PortalService portalService;

    @Autowired
    public PortalController(PortalService portalService){
        this.portalService = portalService;
    }

    @PostMapping("/ftr-of1/saveData")
    public String save(@RequestBody PortalModel portalModel) throws ParseException {
        return portalService.saveData(portalModel);
    }

    @PostMapping("/ftr-oj1/edit")
    public String edit(@RequestBody PortalModel portalModel) throws ParseException {
        return portalService.editData(portalModel);
    }

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
