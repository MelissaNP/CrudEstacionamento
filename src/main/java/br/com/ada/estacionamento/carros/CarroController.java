package br.com.ada.estacionamento.carros;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarroController {

    public final CarroService carroService;
    
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }
    

    @PostMapping("/carro")
    public ResponseEntity<Carro> cadastrarCarro(@RequestBody @Valid Carro carro){
        carroService.cadastrarCarro(carro);
        return ResponseEntity.status(201).body(carro);
    }

    @PostMapping("/vaga")
    public Carro estacionar(@RequestBody @Valid Carro carro){
        
        return carroService.estacionar(carro);
    }
    
    @PatchMapping("/vaga/{id}")
    public String liberarVaga(@PathVariable Integer id){
        System.out.println(id);
        return carroService.liberarVaga(id);
    }
    
    @GetMapping("/carro")
    public Iterable<Carro> listarCarros(){
        return carroService.listarCarrosCadastrados();
    }
    

}
