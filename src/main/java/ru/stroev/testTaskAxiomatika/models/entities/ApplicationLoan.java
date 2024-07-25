package ru.stroev.testTaskAxiomatika.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Application loan entity
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Data
@Table(name = "applicationLoan")
@Entity
public class ApplicationLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "term")
    @Min(value = 1)
    @Max(value = 360)
    private Integer term;

    @Column(name = "approvedAmount", precision = 19, scale = 2)
    private BigDecimal approvedAmount;

    @OneToOne(mappedBy = "applicationLoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private ContractLoan contractLoan;
}
