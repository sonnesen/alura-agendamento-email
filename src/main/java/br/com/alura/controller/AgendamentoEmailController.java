package br.com.alura.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;

import br.com.alura.dto.AgendamentoEmailDTO;
import br.com.alura.dto.NovoAgendamentoEmailDTO;
import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Path("/emails")
public class AgendamentoEmailController {

	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;

	@Inject
	private ModelMapper modelMapper;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		List<AgendamentoEmail> agendamentos = agendamentoEmailServico.listar();
		List<AgendamentoEmailDTO> dtos = new ArrayList<>();

		agendamentos.stream().forEach(agendamento -> {
			AgendamentoEmailDTO dto = modelMapper.map(agendamento, AgendamentoEmailDTO.class);
			dtos.add(dto);
		});

		return Response.ok(dtos).build();
	}

	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response inserir(@Valid NovoAgendamentoEmailDTO novoAgendamentoEmailDTO) throws Exception {
		AgendamentoEmail agendamentoEmail = modelMapper
				.map(novoAgendamentoEmailDTO, AgendamentoEmail.class);
		agendamentoEmailServico.inserir(agendamentoEmail);
		return Response.status(201).build();
	}
}
