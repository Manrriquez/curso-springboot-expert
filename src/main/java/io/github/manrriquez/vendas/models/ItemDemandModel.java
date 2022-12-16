package io.github.manrriquez.vendas.models;


import ch.qos.logback.core.net.server.Client;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_ITEM_DEMAND")
public class ItemDemandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "demand_id")
    private DemandModel demand;

    @Column
    private Integer amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public DemandModel getDemand() {
        return demand;
    }

    public void setDemand(DemandModel demand) {
        this.demand = demand;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
