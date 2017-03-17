package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "dados/anunciantes/{id}", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Usuario>> getAnunciante(@PathVariable Long id){
		Optional<Usuario> usuario = usuarioService.getById(id);
		ResponseEntity<Optional<Usuario>> response;
		if(usuario == null){
			response  = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		return response;

	}
	// TODO Auto-generated method stub
	// esta retornando algo nao parametrizado e precisa de login
	@RequestMapping(value = "dados/anunciantes", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Usuario>> getAnunciantes(){
		Collection<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.size() < 1){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarios,HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value ="/user/anunciantes", method = RequestMethod.GET)
	public String getPageAnunciantesUser(){
		return "user/anunciantes";
	}

	@RequestMapping(value ="/user/anunciantes/{id}", method = RequestMethod.GET)
	public String getPageAnuncianteUser(@PathVariable Long id){
		return "user/anunciante";
	}

	@RequestMapping(value ="/company/anunciantes", method = RequestMethod.GET)
	public String getPageAnunciantesCompany(){
		return "company/anunciantes";
	}

	@RequestMapping(value ="/company/anunciantes/{id}", method = RequestMethod.GET)
	public String getPageAnuncianteCompany(@PathVariable Long id){
		return "company/anunciante";
	}

}
