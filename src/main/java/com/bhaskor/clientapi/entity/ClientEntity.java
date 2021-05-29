package com.bhaskor.clientapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UC_idNumber", columnNames = { "idNumber" } ),
    @UniqueConstraint(name = "UC_mobileNumber", columnNames = { "mobileNumber" })
    }
)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    private String lastName;

    // @Column(unique = true)
    @NotBlank(message = "ID Number cannot be blank")
    @Pattern(regexp = "^(((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229))(( |-)(\\d{4})( |-)(\\d{3})|(\\d{7}))$", message = "Invalid South African ID received")
    private String idNumber;

    // @Column(unique = true)
    @NotBlank(message = "Mobile Number cannot be blank")
    private String mobileNumber;

    private String physicalAddress;

}
