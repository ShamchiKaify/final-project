package ttu.ee.finalproject.services;

import org.springframework.stereotype.Service;
import ttu.ee.finalproject.models.PrescribedMedicine;
import ttu.ee.finalproject.models.Prescription;
import ttu.ee.finalproject.repositories.PrescribedMedicineRepository;
import ttu.ee.finalproject.repositories.PrescriptionRepository;

import java.util.List;

@Service
public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository;
    private PrescribedMedicineRepository prescribedMedicineRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescribedMedicineRepository prescribedMedicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescribedMedicineRepository = prescribedMedicineRepository;
    }

    public Prescription addPrescription(Prescription prescription) {
        Long maxId = getMaxId();
        prescription.setId(maxId+1);

        List<PrescribedMedicine> listOfMedicine = prescription.getListOfMedicine();
        Long maxIdOfPrescribedMedicine = getMaxIdOfPrescribedMedicine();

        for (PrescribedMedicine item : listOfMedicine) {
            item.setId(maxIdOfPrescribedMedicine + 1);
            maxIdOfPrescribedMedicine++;
        }

        System.out.println(prescription);
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> addAllPrescription(List<Prescription> list) {
        Long maxId = getMaxId();
        Long maxIdOfPrescribedMedicine = getMaxIdOfPrescribedMedicine();
        for (Prescription item : list) {
            item.setId(maxId + 1);
            maxId++;

            List<PrescribedMedicine> listOfMedicine = item.getListOfMedicine();
            for (PrescribedMedicine pm : listOfMedicine) {
                pm.setId(maxIdOfPrescribedMedicine + 1);
                maxIdOfPrescribedMedicine++;
            }
        }

        return prescriptionRepository.saveAll(list);
    }

    public List<Prescription> getPrescriptionListByNid(String nid) {
        return prescriptionRepository.findAllByPatientNid(nid);
    }

    public List<Prescription> getPrescriptionListByDoctorUserName(String userName) {
        return prescriptionRepository.findAllByDoctorUserName(userName);
    }

    public Long getMaxId() {
        Integer maxId = prescriptionRepository.findMaxId();
        if (maxId == null) {
            maxId = 0;
        }
        return Long.valueOf(maxId);
    }

    public Long getMaxIdOfPrescribedMedicine() {
        Integer maxId = prescribedMedicineRepository.findMaxId();
        if (maxId == null) {
            maxId = 0;
        }
        return Long.valueOf(maxId);
    }
}
