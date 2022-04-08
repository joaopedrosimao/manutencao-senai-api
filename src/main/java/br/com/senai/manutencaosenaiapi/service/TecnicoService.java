package br.com.senai.manutencaosenaiapi.service;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.manutencaosenaiapi.entity.Tecnico;

@Service
@Validated
public class TecnicoService {

	public Tecnico inserir(@Valid @NotNull(message = "O técnico não pode ser nulo") Tecnico novoTecnico) {

		Preconditions.checkNotNull(novoTecnico, "O técnico " + "não pode ser nulo");

		Preconditions.checkArgument(novoTecnico.getNomeCompleto() != null && !novoTecnico.getNomeCompleto().isEmpty(),
				"O nome pe obrigatório");

		Preconditions.checkArgument(novoTecnico.getDataDeAdmissao() != null, "A data de admissão é obrigatória");

		LocalDate dataAtual = LocalDate.now();

		LocalDate dataDeAdimissao = novoTecnico.getDataDeAdmissao();

		Preconditions.checkArgument(
				dataDeAdimissao.isBefore(dataAtual) || novoTecnico.getDataDeAdmissao().equals(dataAtual),
				"A data de admissão deve ser igual ou anterior a data atual");

		Tecnico tecnicoSalvo = novoTecnico;
		return tecnicoSalvo;

	}
}
