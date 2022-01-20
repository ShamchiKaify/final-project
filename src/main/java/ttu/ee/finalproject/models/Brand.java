package ttu.ee.finalproject.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Brand {
    @Id
    private Long brandId;
    private Long genericId;
    private Long companyId;
    private String brandName;
    private String form;
    private String strength;
    private String price;
    private String packSize;
}
