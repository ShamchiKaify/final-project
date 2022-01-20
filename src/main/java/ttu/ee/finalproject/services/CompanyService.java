package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttu.ee.finalproject.FinalProjectApplication;
import ttu.ee.finalproject.models.Company;
import ttu.ee.finalproject.repositories.CompanyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompanyService {
    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) throws IOException {
        this.companyRepository = companyRepository;
//        this.insertAllIntoDB();
    }

    public void insertAllIntoDB() throws IOException {
        List<Company> staticList = new ArrayList<>();

        String fileName = "companies.csv";

        ClassLoader classLoader = FinalProjectApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = null;

        if (inputStream != null) {
            streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(streamReader);

            List<Company> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (!split[0].equals("company_id")) {
                    int length = split.length;

                    System.out.println(Arrays.toString(split));
                    Company company = new Company();
                    company.setCompanyId(Long.valueOf(split[0]));
                    company.setCompanyOrder(Double.parseDouble(split[length - 1]));

                    StringBuilder companyName = new StringBuilder();
                    for (int ii = 1; ii < length-1; ii++) {
                        if (companyName.toString().length() != 0) {
                            companyName.append(",");
                        }
                        companyName.append(split[ii]);
                    }
                    company.setCompanyName(String.valueOf(companyName));

                    list.add(company);
                }
            }
            staticList.addAll(list);
        }
        //  Inserting everything into DB
        insertAllCompany(staticList);
    }

    @Transactional
    public List<Company> insertAllCompany(List<Company> list) {
        return companyRepository.saveAll(list);
    }

    @Transactional
    public Company insertCompany(Company company) {
        Company companyById = getCompanyById(company.getCompanyId());
        return companyRepository.save(companyById);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findCompanyByCompanyId(id).orElse(null);
    }
}
