package com.adriannavarrogabino.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.adriannavarrogabino.oauth.services.IUsuarioService;
import com.adriannavarrogabino.usuarios.commons.models.entity.Usuario;

import brave.Tracer;
import feign.FeignException;

@Component
public class AutheticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(AutheticationSuccessErrorHandler.class);
	
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		
		UserDetails user = (UserDetails)authentication.getPrincipal();
		log.info("Success Login: " + user.getUsername());
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0)
		{
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

		log.error("Error en el login: " + exception.getMessage());
		
		try
		{
			StringBuilder errors = new StringBuilder();
			errors.append("Error en el login: " + exception.getMessage());
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if(usuario.getIntentos() == null)
			{
				usuario.setIntentos(0);
			}
			
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intentos: " + usuario.getIntentos());
			errors.append("\nIntentos: " + usuario.getIntentos());
			
			if(usuario.getIntentos() >= 3)
			{
				log.error("Usuario " + usuario.getNombre() + " deshabilitado");
				errors.append("\nUsuario " + usuario.getNombre() + " deshabilitado");
				usuario.setEnabled(false);
			}
			
			usuarioService.update(usuario, usuario.getId());
			tracer.currentSpan().tag("error.mensaje", errors.toString());
		}
		catch(FeignException e)
		{
			log.error("El usuario " + authentication.getName() + " no existe en el sistema");
		}
		
	}

}
