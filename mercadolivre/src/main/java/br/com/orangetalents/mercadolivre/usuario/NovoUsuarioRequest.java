package br.com.orangetalents.mercadolivre.usuario;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class NovoUsuarioRequest {
	
	
	@Email @NotBlank @Column(unique=true)
	private String email;
	
	@NotBlank
	@Size(min=6)
	private String senha;


	public String getEmail() {
		return email;
	}



	public String getSenha() {
		return senha;
	}


	public NovoUsuarioRequest(@Email @NotNull @NotEmpty String email,
			@NotBlank @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	
	public Usuario toModel() {
		return new Usuario(this.email, new SenhaLimpa(senha));
	}
	
	
	
}
