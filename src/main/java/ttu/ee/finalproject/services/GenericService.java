package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttu.ee.finalproject.FinalProjectApplication;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.models.Company;
import ttu.ee.finalproject.models.Generic;
import ttu.ee.finalproject.repositories.BrandRepository;
import ttu.ee.finalproject.repositories.GenericRepository;

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
public class GenericService {
    GenericRepository genericRepository;
    BrandRepository brandRepository;

    public GenericService(GenericRepository genericRepository, BrandRepository brandRepository) throws IOException {
        this.genericRepository = genericRepository;
        this.brandRepository = brandRepository;
//        this.insertAllIntoDB();
    }

    public void insertAllIntoDB() throws IOException {
//        long startTime = System.currentTimeMillis();
        List<Generic> staticList = new ArrayList<>();

        String fileName = "generics.csv";

        ClassLoader classLoader = FinalProjectApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = null;

        if (inputStream != null) {
            streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(streamReader);

            List<Generic> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (!split[0].equals("generic_id")) {
                    int length = split.length;

//                    System.out.println(Arrays.toString(split));
                    Generic generic = new Generic();
                    generic.setGenericId(Long.valueOf(split[0]));
                    generic.setGenericName(split[1]);
                    generic.setPrecaution(split[2]);
                    generic.setIndication(split[3]);
                    generic.setContraIndication(split[4]);
                    generic.setDose(split[5]);
                    generic.setSideEffect(split[6]);
                    generic.setPregnancyCategoryId(split[7]);
                    generic.setModeOfAction(split[8]);
                    generic.setInteraction(split[9]);

                    list.add(generic);
                }
            }
            staticList.addAll(list);
        }
        //  Inserting everything into DB
        insertAllGeneric(staticList);
//        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println("Elapsed Time: " + estimatedTime + " ms");

//        staticList.forEach(System.out::println);
    }

    @Transactional
    public List<Generic> insertAllGeneric(List<Generic> list) {
        return genericRepository.saveAll(list);
    }

    @Transactional
    public Generic insertGeneric(Generic generic) {
        Generic genericById = getGenericById(generic.getGenericId());
        return genericRepository.save(genericById);
    }

    public Generic getGenericById(Long id) {
        return genericRepository.findGenericByGenericId(id).orElse(null);
    }

}
