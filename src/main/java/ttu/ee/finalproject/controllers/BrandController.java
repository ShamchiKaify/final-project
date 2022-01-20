package ttu.ee.finalproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.services.BrandService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/get_all")
    public ResponseEntity getAllBrandList(HttpServletRequest httpServletRequest) {
        List<Brand> allBrand = brandService.getAllBrand();
        return ResponseEntity.ok().body(allBrand);
    }
}
