package ru.stroev.testTaskAxiomatika.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Contract loan entity
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Data
@Table(name = "contractLoan")
@Entity
public class ContractLoan {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_application_id", nullable = false)
    @ToString.Exclude
    private ApplicationLoan applicationLoan;

    @Column(name = "signatureDate")
    private Date signatureDate;

    @Column(name = "signature_status")
    private Boolean signatureStatus;
}
