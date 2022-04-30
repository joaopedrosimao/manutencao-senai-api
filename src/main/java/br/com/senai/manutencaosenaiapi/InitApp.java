package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.enums.Sexo;
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
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PecaService pecaService;

	@Autowired
	private OrdemDeServicoService ordemService;

	@Transactional
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome("Jhonny");
				novoCliente.setDataDeNascimento(LocalDate.of(1973, 3, 15));
				novoCliente.setSobrenome("Depp");
				novoCliente.setCpf("005.900.289-10");
				novoCliente.setSexo(Sexo.M);
				novoCliente.setEndereco("Rua josé das couves");
				this.clienteService.inserir(novoCliente);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		};
	}

	@Transactional
	public void remover() {
		this.tecnicosRepository.dele
	}

	public Tecnico buscarPor(Integer id) {
		return repository.findById(id).get();
	}

}
