package br.com.alura.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoEmailDTO {

	@NotEmpty
	@Email
	@Size(max = 50)
	private String email;

	@NotEmpty
	@Size(max = 50)
	private String assunto;

	@NotEmpty
	@Size(max = 255)
	private String mensagem;

	private Boolean agendado;
	
}
