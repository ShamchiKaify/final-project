package ttu.ee.finalproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.models.Generic;
import ttu.ee.finalproject.services.BrandService;
import ttu.ee.finalproject.services.GenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    private GenericService genericService;
    private BrandService brandService;

    public MedicineController(GenericService genericService, BrandService brandService) {
        this.genericService = genericService;
        this.brandService = brandService;
    }

    @GetMapping("get_alternative_medicine/{searchMedicine}")
    public ResponseEntity getAlternativeList(HttpServletRequest httpServletRequest, @PathVariable String searchMedicine) {
        System.out.println(searchMedicine);
        List<Brand> list = brandService.getAlternateList(searchMedicine);

        HttpStatus httpStatus;

        if(list!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(list, httpStatus);
    }
}
