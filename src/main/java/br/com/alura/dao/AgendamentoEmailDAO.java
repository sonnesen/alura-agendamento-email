package br.com.alura.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction userTransaction;

	public List<AgendamentoEmail> listar() {
		return entityManager
				.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) throws Exception {
		userTransaction.begin();
		entityManager.persist(agendamentoEmail);
		userTransaction.commit();
	}

	public List<AgendamentoEmail> listarPorNaoAgendado() {
		return entityManager
				.createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = false", AgendamentoEmail.class)
				.getResultList();
	}

	public void agendar(Long id) throws Exception {
		Optional<AgendamentoEmail> agendamentoEmailOptional = Optional
				.ofNullable(entityManager.find(AgendamentoEmail.class, id));

		if (agendamentoEmailOptional.isPresent()) {
			AgendamentoEmail agendamentoEmail = agendamentoEmailOptional.get();
			agendamentoEmail.setAgendado(true);
			userTransaction.begin();
			entityManager.merge(agendamentoEmail);
			userTransaction.commit();
		}
		else {
			throw new IllegalArgumentException(String.format("Agendamento %s não encontrado!", id));
		}
	}

}
