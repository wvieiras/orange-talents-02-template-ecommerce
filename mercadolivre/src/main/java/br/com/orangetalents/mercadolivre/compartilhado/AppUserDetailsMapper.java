package br.com.orangetalents.mercadolivre.compartilhado;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.orangetalents.mercadolivre.compartilhado.seguranca.UserDetailsMapper;
import br.com.orangetalents.mercadolivre.usuario.Usuario;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{
	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}
}
