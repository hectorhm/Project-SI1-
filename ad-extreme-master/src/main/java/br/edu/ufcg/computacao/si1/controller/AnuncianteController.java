package br.edu.ufcg.computacao.si1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

@Controller
public class AnuncianteController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Usuario>> getAnunciante(@PathVariable Long id){
		Optional<Usuario> usuario = usuarioService.getById(id);
		ResponseEntity<Optional<Usuario>> response;
		if(usuario == null){
			response  = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
		}
		return response;
		
	}
}
