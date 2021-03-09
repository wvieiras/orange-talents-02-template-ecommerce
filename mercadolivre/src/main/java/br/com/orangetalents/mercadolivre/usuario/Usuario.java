package br.com.orangetalents.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;

	private String senha;
	
	@NotNull
	private LocalDateTime localDateTime = LocalDateTime.now();
	


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", localDateTime=" + localDateTime + "]";
	}
	
	@Deprecated
	public Usuario() {
		
	}
	
	
	/*
	 * @param email string no formato de email
	 * @param senha string em texto limpo
	 */
	public Usuario(@Email @NotBlank String email, @Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "email não pode ser em branco");
		Assert.notNull(senhaLimpa, "o objeto do tipo SenhaLimpa não pode ser nulo");
		
		//Assert.isTrue(senha.length() >=6, "senha tem que ter no mínimo 6 caracteres");
		
		this.email = email;
		this.senha = senhaLimpa.hash();
	}



	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	
}
