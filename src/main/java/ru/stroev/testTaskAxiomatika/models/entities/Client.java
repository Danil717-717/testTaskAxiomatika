package ru.stroev.testTaskAxiomatika.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import ru.stroev.testTaskAxiomatika.models.enums.FamilyStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Client entity
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Data
@Table(name = "client")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName", length = 150, nullable = false)
    private String fullName;

    @Column(name = "idData", nullable = false)
    @Pattern(regexp = "\\d{4} \\d{6}", message = "Passport data must be written in the format: 'ssss nnnnnn'")
    private String idData;

    @Enumerated(EnumType.STRING)
    @Column(name = "familyStatus", nullable = false)
    private FamilyStatus familyStatus;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phoneNumber", nullable = false)
    @Pattern(regexp = "\\+\\d{1,3}\\d{1,4}\\d{7,10}", message = "The phone number must be written in the format: '+CCCNNNNXXXXXXXX'")
    private String phoneNumber;

    @Column(name = "workingExperience", nullable = false)
    private String workingExperience;

    @Column(name = "loanAmount", precision = 19, scale = 2, nullable = false)
    private BigDecimal loanAmount;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ApplicationLoan> clientApplications = new ArrayList<>();
}
