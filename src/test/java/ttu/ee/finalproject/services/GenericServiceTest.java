package ttu.ee.finalproject.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.models.Generic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenericServiceTest {
    @Autowired GenericService genericService;
    @Autowired BrandService brandService;

    @Test
    public void testGetAlternateList() {
        List<Brand> plus = brandService.getAlternateList("ace plus");
        plus.forEach(System.out::println);
    }
}