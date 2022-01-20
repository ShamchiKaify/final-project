package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttu.ee.finalproject.FinalProjectApplication;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.models.Generic;
import ttu.ee.finalproject.repositories.BrandRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) throws IOException {
        this.brandRepository = brandRepository;
        this.insertAllIntoDB();
    }

    public void insertAllIntoDB() throws IOException {
        long startTime = System.currentTimeMillis();
        List<Brand> staticList = new ArrayList<>();

        String fileName = "brands.csv";

        ClassLoader classLoader = FinalProjectApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = null;

        if (inputStream != null) {
            streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(streamReader);

            List<Brand> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (!split[0].equals("brand_id")) {
//                    System.out.println(Arrays.toString(split));
                    Brand brand = new Brand();
                    brand.setBrandId(Long.valueOf(split[0]));
                    brand.setGenericId(Long.valueOf(split[1]));
                    brand.setCompanyId(Long.valueOf(split[2]));
                    brand.setBrandName(split[3]);
                    brand.setForm(split[4]);
                    brand.setStrength(split[5]);
                    String foundPrice = split[6];

                    foundPrice = foundPrice.replace("\\t", "");
                    brand.setPrice(foundPrice);

                    brand.setPackSize(split[7]);

                    list.add(brand);
                }
            }
            staticList.addAll(list);
        }
        //  Inserting everything into DB
        insertAllBrand(staticList);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");

//        staticList.forEach(System.out::println);
    }

    @Transactional
    public List<Brand> insertAllBrand(List<Brand> list) {
        return brandRepository.saveAll(list);
    }

    @Transactional
    public Brand insertBrand(Brand brand) {
        Brand brandById = getBrandById(brand.getBrandId());
        return brandRepository.save(brandById);
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findBrandByBrandId(id).orElse(null);
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public List<Brand> getAlternateList(String searchMedicine) {
        Optional<Brand> found = brandRepository.findFirstByBrandNameContaining(searchMedicine);
        if (found.isPresent()) {
            List<Brand> list = brandRepository.findAllByGenericId(found.get().getGenericId());
            return list;
        }
        return new ArrayList<>();
    }
}
