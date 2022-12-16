package io.github.manrriquez.vendas.models;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_CLIENT")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<DemandModel> demands;

    public ClientModel() {}


    public Set<DemandModel> getDemands() {
        return demands;
    }

    public void setDemands(Set<DemandModel> demands) {
        this.demands = demands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
