package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.dtos.DemandDTO;
import io.github.manrriquez.vendas.models.DemandModel;
import io.github.manrriquez.vendas.services.DemandService;
import io.github.manrriquez.vendas.services.ServiceImpl.DemandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pedidos")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveDemand(@RequestBody DemandDTO dto) {

        DemandModel demand = demandService.save(dto);

        return demand.getId();
    }
}
