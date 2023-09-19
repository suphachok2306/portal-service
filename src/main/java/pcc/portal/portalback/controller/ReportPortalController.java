package pcc.portal.portalback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.model.ReportPortalModel;
import pcc.portal.portalback.service.ReportPortalService;

import java.util.List;

@RestController
public class ReportPortalController {
    private final ReportPortalService reportPortalService;

    @Autowired
    public ReportPortalController(ReportPortalService reportPortalService){
        this.reportPortalService = reportPortalService;
    }

    @PostMapping("/ftr-sv1/saveData")
    public String save(@RequestBody ReportPortalModel reportPortalModel) {
        return reportPortalService.saveData(reportPortalModel);
    }

//    @PostMapping("/ftr-sv1/saveData")
//    public ResponseEntity<Object> save(@RequestBody ReportPortalModel reportPortalModel) {
//        try {
//            if (isDataIncomplete(reportPortalModel)) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ApiResponse(400, "failure", null)); // HTTP 400 Bad Request
//            } else {
//                Object result = reportPortalService.saveData(reportPortalModel);
//                ApiResponse response = new ApiResponse(200, "success", result);
//                return ResponseEntity.status(HttpStatus.OK).body(response); // HTTP 200 OK
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error."); // HTTP 500 Internal Server Error
//        }
//    }

    @DeleteMapping("/ftr-sv1/deleteById")
    public String delete(@RequestParam Long id) {
        return reportPortalService.deleteData(id);
    }

    @PostMapping("/ftr-sv1/edit")
    public String edit(@RequestBody ReportPortalModel reportPortalModel) {
        return reportPortalService.editData(reportPortalModel);
    }

    @GetMapping("/ftr-sv1/findAll")
    public Object search() {
        return reportPortalService.findAll();
    }

    @GetMapping("/ftr-sv1/search")
//    public List<ReportPortalModel> search(@RequestParam String year, String department) {
    public Object search(@RequestParam String year, String department) {
        return reportPortalService.search(year,department);
    }

    @GetMapping("/ftr-sv1/report")
    public String report() {
        return reportPortalService.covertBase64();
    }
}
