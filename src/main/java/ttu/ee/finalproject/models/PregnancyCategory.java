package ttu.ee.finalproject.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class PregnancyCategory {
    @Id
    private Long pregnancyId;
    private String pregnancyName;
    private String pregnancyDescription;

}
