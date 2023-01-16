package io.github.manrriquez.vendas.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CLIENT")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "{FIELD.NAME.REQUIRED}")
    private String name;

    @Column(length = 11)
    @NotEmpty(message = "{FIELD.CPF.REQUIRED}")
    @CPF(message = "{FIELD.CPF.INVALID}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<DemandModel> demands;

}
