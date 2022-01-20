package ttu.ee.finalproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttu.ee.finalproject.models.Prescription;
import ttu.ee.finalproject.services.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/add")
    public ResponseEntity addPrescription(HttpServletRequest httpServletRequest, @RequestBody Prescription prescription) {
        Prescription foundPrescription = prescriptionService.addPrescription(prescription);

        HttpStatus httpStatus;

        if(foundPrescription!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(foundPrescription, httpStatus);
    }

    @GetMapping("/get_list/{nid}")
    public ResponseEntity getListByNid(HttpServletRequest httpServletRequest, @PathVariable("nid") String nid) {
        List<Prescription> listByNid = prescriptionService.getPrescriptionListByNid(nid);

        HttpStatus httpStatus;

        if(listByNid!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(listByNid, httpStatus);
    }
}
