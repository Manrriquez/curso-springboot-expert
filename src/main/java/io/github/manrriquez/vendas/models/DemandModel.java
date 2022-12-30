package io.github.manrriquez.vendas.models;


import ch.qos.logback.core.net.server.Client;
import io.github.manrriquez.vendas.Enums.StatusDemand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_DEMAND")
public class DemandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @Column
    private LocalDate data_demand;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusDemand status;

    @Column(length = 20, precision = 20, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "demand")
    private List<ItemDemandModel> items;

}
