package io.github.manrriquez.vendas.models;


import ch.qos.logback.core.net.server.Client;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @Column(length = 20, precision = 20, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "demand")
    private List<ItemDemandModel> items;

    public List<ItemDemandModel> getItems() {
        return items;
    }

    public void setItems(List<ItemDemandModel> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public LocalDate getData_demand() {
        return data_demand;
    }

    public void setData_demand(LocalDate data_demand) {
        this.data_demand = data_demand;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
