package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

@RestController
public class RestAnuncioController {

	@Autowired
	private AnuncioServiceImpl anuncioService;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Collection<Usuario>> getUsers(){

		return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method= RequestMethod.GET)
	public ResponseEntity<Usuario> getUser(@PathVariable Long id){
		Usuario usuarioProcurado = usuarioService.getById(id).get();
		ResponseEntity<Usuario> response;
		if(usuarioProcurado == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(usuarioProcurado, HttpStatus.OK);
		}
		return response;
	}

	@RequestMapping(value = "/anuncios", method = RequestMethod.GET)
	public ResponseEntity<Collection<Anuncio>> getPageListarAnuncios(){

		return new ResponseEntity<>(anuncioService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public ResponseEntity<String> getEmailLogged(){

		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getName(), HttpStatus.OK);
	}

	@RequestMapping(value = "/loggedUser", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUserLogged(){
		Optional<Usuario> usuarioProcurado = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(usuarioProcurado.isPresent()){
			return new ResponseEntity<>(usuarioProcurado.get(), HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}

}