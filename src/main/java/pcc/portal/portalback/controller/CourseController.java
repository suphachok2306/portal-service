package pcc.portal.portalback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.response.ApiResponse;
import pcc.portal.portalback.model.CourseModel;
import pcc.portal.portalback.response.ResponseData;
import pcc.portal.portalback.service.CourseService;

import java.text.ParseException;
import java.util.Objects;



@RestController
@RequiredArgsConstructor
public class CourseController {
    
    private final CourseService courseService;
    private boolean validateCourse(CourseModel mCourseModel) {
        //////Section1
        return Objects.requireNonNull(mCourseModel.getCourse_name(), "DeptCode cannot be null").isEmpty() ||
                Objects.requireNonNull(mCourseModel.getStartDate().toString(), "Dept cannot be null").isEmpty() ||
                Objects.requireNonNull(mCourseModel.getEndDate().toString(), "Date cannot be null").isEmpty() ||
                Objects.requireNonNull(Float.toString(mCourseModel.getPrice()), "Topic cannot be null").isEmpty() ||
                Objects.requireNonNull(mCourseModel.getNote(), "Objt cannot be null").isEmpty() ||
                Objects.requireNonNull(Float.toString(mCourseModel.getPriceProject()), "StartDate cannot be null").isEmpty() ||
                Objects.requireNonNull(mCourseModel.getInstitute(), "EndDate cannot be null").isEmpty() ||
                Objects.requireNonNull(mCourseModel.getPlace(), "EndDate cannot be null").isEmpty();
    }


    @PostMapping("/course")
    public ResponseEntity<?> saveCourse(@RequestBody CourseModel mCourseModel) throws ParseException {
        
        ApiResponse response = new ApiResponse();
        if (validateCourse(mCourseModel)) {
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้");
            ResponseData responseData = new ResponseData();
            response.setResponseData(responseData);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            response.setResponseMessage("ทำรายการเรียบร้อย");
            Object result = courseService.saveCourse(mCourseModel);
            ResponseData responseData = new ResponseData();
            responseData.setResult(result);
            response.setResponseData(responseData);
            return ResponseEntity.ok(response);

        }catch (Exception e){
            response.setResponseMessage("ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้ เพราะ มีข้อผิดพลาดภายในเซิร์ฟเวอร์");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}