package ttu.ee.finalproject.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import ttu.ee.finalproject.models.Brand;
import ttu.ee.finalproject.models.PrescribedMedicine;
import ttu.ee.finalproject.models.Prescription;
import ttu.ee.finalproject.models.UserProfile;
import ttu.ee.finalproject.repositories.BrandRepository;
import ttu.ee.finalproject.repositories.PrescriptionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class PrescriptionServiceTest {
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    BrandRepository brandRepository;

    @Test
    public void testGetAllPrescriptionByPatientNid() {
        long startTime = System.currentTimeMillis();

        String nid = "2020010000003";

        List<Prescription> list = prescriptionService.getPrescriptionListByNid(nid);
        System.out.println(list.size());
//        list.forEach(item -> {
//            System.out.println(item);
//        });


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testGetAllPrescriptionByDoctorUserName() {
        long startTime = System.currentTimeMillis();

        String nid = "shamin.asfaq";

        List<Prescription> list = prescriptionService.getPrescriptionListByDoctorUserName(nid);
        System.out.println(list.size());
//        list.forEach(item -> {
//            System.out.println(item);
//        });


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testInsertPrescription() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Integer[] dosage = new Integer[] { 0, 1, 2, 3 };
        List<Integer> dosageList = Arrays.asList(dosage);
        List<Brand> genericList = brandRepository.findAll();
        System.out.println(genericList.size());

        List<Prescription> allPrescription = new ArrayList<>();

        Random rand = new Random();

        String uri = "http://localhost:8081/user_profile/get_all_by_role/DOCTOR";

        RestTemplate restTemplate = new RestTemplate();
        UserProfile[] foundArrayDoctor = restTemplate.getForObject(uri, UserProfile[].class);
        List<UserProfile> doctorList = Arrays.asList(foundArrayDoctor);
        System.out.println("Total Doctors: " + doctorList.size());

        uri = "http://localhost:8081/user_profile/get_all_by_role/PATIENT";

        restTemplate = new RestTemplate();
        UserProfile[] foundArrayPatient = restTemplate.getForObject(uri, UserProfile[].class);
        List<UserProfile> patientList = Arrays.asList(foundArrayPatient);
        System.out.println("Total Patients: " + patientList.size());


        for (UserProfile doctor : doctorList) {
            for (int dx = 0; dx < 25; dx++) {
                int foundRandomPatientIndex = rand.nextInt(patientList.size());
                UserProfile randomPatient = patientList.get(foundRandomPatientIndex);

                Prescription prescription = new Prescription();
                prescription.setPatientNid(randomPatient.getNid());
                prescription.setDoctorUserName(doctor.getUserName());

                if (randomPatient.getDateOfBirth() == null || randomPatient.getDateOfBirth().contains("null")) {
                    randomPatient.setDateOfBirth(LocalDate.of(1995, 7, 17).toString());
                }

                LocalDate dateTime = LocalDate.parse(randomPatient.getDateOfBirth().trim());
                int randomDays = rand.nextInt(9125 - 5475 + 1 ) - 5475;
                dateTime.plusDays(randomDays);
                prescription.setAppointmentDate(dateTime.toString());
                List<PrescribedMedicine> prescribedMedicineList = new ArrayList<>();

                /**
                 * Prescription Generator starts
                 */
                for (int step = 0; step < 3; step++) {
                    int foundRandomBrandIndex = rand.nextInt(genericList.size());
                    Brand randomBrand = genericList.get(foundRandomBrandIndex);

                    StringBuilder dosageString = new StringBuilder();

                    for (int idx = 0; idx < 3; idx++) {
                        int foundRandomDosageIndex = rand.nextInt(dosageList.size());
                        Integer integer = dosageList.get(foundRandomDosageIndex);
                        if (dosageString.toString().length() > 0) {
                            dosageString.append("+");
                        }
                        dosageString.append(integer);
                    }

                    PrescribedMedicine prescribedMedicine = new PrescribedMedicine();
                    prescribedMedicine.setMedicineId(randomBrand.getBrandName());
                    prescribedMedicine.setMedicineDosage(dosageString.toString());

                    prescribedMedicineList.add(prescribedMedicine);
//
//                    System.out.println(randomBrand);
//                    System.out.println(dosageString);
                }

                prescription.setListOfMedicine(prescribedMedicineList);
                allPrescription.add(prescription);
//                System.out.println(prescription);
                /**
                 * Prescription Generator ends
                 */
            }
//            Thread.sleep(5000000);
        }

        prescriptionService.addAllPrescription(allPrescription);


        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }
}



















