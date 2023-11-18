package hu.bankblaze.bankblaze.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission")
public class Permission {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    private Boolean forRetail=false;

    private Boolean forCorporate=false;

    private Boolean forTeller=false;

    private Boolean forPremium=false;


}
