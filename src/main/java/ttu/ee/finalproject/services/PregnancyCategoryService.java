package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttu.ee.finalproject.FinalProjectApplication;
import ttu.ee.finalproject.models.PregnancyCategory;
import ttu.ee.finalproject.repositories.PregnancyCategoryRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PregnancyCategoryService {
    PregnancyCategoryRepository pregnancyCategoryRepository;

    public PregnancyCategoryService(PregnancyCategoryRepository pregnancyCategoryRepository) throws IOException {
        this.pregnancyCategoryRepository = pregnancyCategoryRepository;
//        this.insertAllIntoDB();
    }

    public void insertAllIntoDB() throws IOException {
        List<PregnancyCategory> staticList = new ArrayList<>();

        String fileName = "pregnancy_categories.csv";

        ClassLoader classLoader = FinalProjectApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = null;

        if (inputStream != null) {
            streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(streamReader);

            List<PregnancyCategory> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (!split[0].equals("pregnancy_id")) {
                    PregnancyCategory pregnancyCategory = new PregnancyCategory();
                    pregnancyCategory.setPregnancyId(Long.valueOf(split[0]));
                    pregnancyCategory.setPregnancyName(split[1]);
                    pregnancyCategory.setPregnancyDescription(split[2]);

                    list.add(pregnancyCategory);
                }
            }
            staticList.addAll(list);
        }
        //  Inserting everything into DB
        insertAllPregnancyCategories(staticList);
    }

    @Transactional
    public PregnancyCategory insertPregnancyCategory(PregnancyCategory pregnancyCategory) {
        PregnancyCategory pregnancyCategoryById = getPregnancyCategoryById(Long.valueOf(pregnancyCategory.getPregnancyId()));
        return pregnancyCategoryRepository.save(pregnancyCategoryById);
    }

    @Transactional
    public List<PregnancyCategory> insertAllPregnancyCategories(List<PregnancyCategory> list) {
        return pregnancyCategoryRepository.saveAll(list);
    }

    public PregnancyCategory getPregnancyCategoryById(Long id) {
        Optional<PregnancyCategory> byId = pregnancyCategoryRepository.findPregnancyCategoryByPregnancyId(id);
        return byId.orElse(null);
    }

    public List<PregnancyCategory> getAllPregnancyCategoryList() {
        return pregnancyCategoryRepository.findAll();
    }
}
