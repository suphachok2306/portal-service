package pcc.portal.portalback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.model.PortalModel;
import pcc.portal.portalback.service.PortalService;

import java.text.ParseException;
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

    @PostMapping("/ftr-of1/edit")
    public String edit(@RequestBody PortalModel portalModel) throws ParseException {
        return portalService.editData(portalModel);
    }

    @PostMapping("/ftr-of1/deleteById")
    public String delete(@RequestParam Long id){
        return portalService.deleteData(id);
    }

    @GetMapping("/ftr-oj1/findAll")
    public Object search() {
        return portalService.findAll();
    }


    @GetMapping("/ftr-oj1/search")
    public List<PortalModel> search(@RequestParam String empName,String empRole,String department) {
        return portalService.search(empName, empRole, department);
    }



}
