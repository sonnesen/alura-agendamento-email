package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailServico.class.getName());

	@Inject
	private AgendamentoEmailDAO dao;

	public List<AgendamentoEmail> listar() {
		return dao.listar();
	}

	public void inserir(AgendamentoEmail agendamentoEmail) throws Exception {
		dao.inserir(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarPorNaoAgendado() {
		return dao.listarPorNaoAgendado();
	}

	public void agendar(Long id) throws Exception {
		dao.agendar(id);
	}

	public void enviar(AgendamentoEmail agendamentoEmail) {
		try {
			Thread.sleep(5000);
			LOGGER.info(String.format("O e-mail %s foi enviado com sucesso!", agendamentoEmail.getEmail()));
		}
		catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}

}
