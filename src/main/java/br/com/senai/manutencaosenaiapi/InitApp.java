package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Optional;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.repository.PecasRepository;
import br.com.senai.manutencaosenaiapi.service.ClienteService;
import br.com.senai.manutencaosenaiapi.service.OrdemDeServicoService;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TecnicoService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}

	@Autowired
	private TecnicoService service;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PecaService pecaService;

	@Autowired
	private OrdemDeServicoService ordemService;

	@Autowired
	private PecasRepository pecasRepository;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				/*Peca novaPeca = new Peca();
				novaPeca.setDescricao("Placa Mãe Gigabit");
				novaPeca.setEspecificacoes("Placa da mãe");
				novaPeca.setQtdeEmEstoque(10);
				Peca pecaSalva = pecasRepository.save(novaPeca);
				System.out.println("Id da peça: " + pecaSalva.getId());*/
				
				java.util.Optional<Peca> pecaEncontrada = pecasRepository.findById(7);
				pecaEncontrada.get().setEspecificacoes("Uma porcaria");
				Peca pecaAlterada =pecasRepository.save(pecaEncontrada.get());
				System.out.println(pecaAlterada);
				if(pecaEncontrada.isPresent()) {
					System.out.println("Peça encontrada: " + pecaEncontrada.get());
				}
				System.out.println("Peça encontrada: " + pecaEncontrada);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		};
	}

}
