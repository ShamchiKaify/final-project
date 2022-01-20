package ttu.ee.finalproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String nid;
    private ROLE role;
}

