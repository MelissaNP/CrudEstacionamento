package br.com.ada.estacionamento.carros;

import br.com.ada.estacionamento.exceptions.CarroCadastradoException;
import br.com.ada.estacionamento.exceptions.GlobalExceptionHandler;
import br.com.ada.estacionamento.exceptions.IdnotNullException;
import br.com.ada.estacionamento.exceptions.VagaOcupadaException;
import br.com.ada.estacionamento.vagas.Vaga;
import br.com.ada.estacionamento.vagas.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CarroService {
    
    
    public final CarroRepository carroRepository;
    public final VagaRepository vagaRepository;
    
    @Autowired
    public CarroService(CarroRepository carroRepository, VagaRepository vagaRepository) {
        this.carroRepository = carroRepository;
        this.vagaRepository = vagaRepository;
    }
    
    
    public void cadastrarCarro(Carro carro) {
        
        if (carroRepository.findById(carro.getPlaca()).isPresent()) {
            throw new CarroCadastradoException("Carro já cadastrado");
        }
        
        carroRepository.save(carro);
    }
    
    public Carro estacionar(Carro carro) {
        int numeroDeVagasDisponivel = vagaRepository.findByOcupada(false).size();
        
        if (numeroDeVagasDisponivel == 0) {
            throw new RuntimeException("Nao ha vagas disponiveis");
        }
        
        if (carro.getVaga() == null) {
            throw new RuntimeException("Vaga nao informada. Informe a vaga que deseja estacionar o carro");
        }
        
        var vaga = vagaRepository.findById(carro.getVaga().getNumero()).get();
        var ocupada = vaga.isOcupada();
        
        if (ocupada) {
            throw new VagaOcupadaException("A Vaga " + vaga.getNumero() + " ja esta ocupada");
        }
        
        vaga.setOcupada(true);
        vagaRepository.save(vaga);
        
        carro.setVaga(vaga);
        vaga.setCarro(carro);
        vagaRepository.save(vaga);
        
        return carroRepository.save(carro);
    }
    
    
    public String liberarVaga(Integer id) {
        Optional<Vaga> optionalVaga = vagaRepository.findById(id);
        
        if (optionalVaga.isEmpty()) {
            throw new IdnotNullException("O número da vaga não existe: " + id);
        }
        var vaga = optionalVaga.get();
        
        vaga.setOcupada(false);
        
        vagaRepository.save(vaga);
        
        return "A vaga " + vaga.getNumero() + " foi liberada com sucesso!";
    }
    
    
    public Iterable<Carro> listarCarrosCadastrados() {
        return carroRepository.findAll();
    }
    
    
    
}