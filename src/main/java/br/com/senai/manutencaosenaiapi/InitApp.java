package br.com.senai.manutencaosenaiapi;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.manutencaosenaiapi.view.TelaConsultaDePeca;
import br.com.senai.manutencaosenaiapi.view.TelaConsultaDeTipoPeca;

@SpringBootApplication
public class InitApp {

	@Autowired
	private TelaConsultaDePeca telaDeConsulta;
	@Autowired
	private TelaConsultaDeTipoPeca telaDeConsultaDePeca;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(InitApp.class);
		builder.headless(false);
		builder.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							telaDeConsultaDePeca.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		};
	}

}
