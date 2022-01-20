package ttu.ee.finalproject.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Company {
    @Id
    private Long companyId;
    private String companyName;
    private double companyOrder;
}
