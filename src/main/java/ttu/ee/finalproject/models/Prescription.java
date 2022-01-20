package ttu.ee.finalproject.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Prescription {
    @Id
    private Long id;
    private String patientNid;
    private String doctorNid;
    private String doctorUserName;
    private String appointmentDate;
    private LocalDateTime convertedAppointmentDateTime;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PrescribedMedicine> listOfMedicine;
}
