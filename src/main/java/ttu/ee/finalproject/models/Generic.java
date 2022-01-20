package ttu.ee.finalproject.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Generic {
    @Id
    private Long genericId;
    private String genericName;
    private String precaution;
    private String indication;
    private String contraIndication;
    private String dose;
    private String sideEffect;
    private String pregnancyCategoryId;
    private String modeOfAction;
    private String interaction;
}
