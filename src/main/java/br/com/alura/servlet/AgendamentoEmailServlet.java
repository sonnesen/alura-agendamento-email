package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

//@WebServlet("emails")
public class AgendamentoEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final AgendamentoEmailServico servico;

	@Inject
	public AgendamentoEmailServlet(AgendamentoEmailServico servico) {
		this.servico = servico;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		servico.listar().stream().forEach(email -> pw.print("Os e-mails disponíveis são: " + email));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String[] email = reader.readLine().split(",");

		AgendamentoEmail agendamentoEmail = AgendamentoEmail.builder()
				.email(email[0])
				.assunto(email[1])
				.mensagem(email[2])
				.build();

		try {
			servico.inserir(agendamentoEmail);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
