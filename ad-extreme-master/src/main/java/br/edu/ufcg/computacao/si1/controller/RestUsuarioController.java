package br.edu.ufcg.computacao.si1.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

@Controller
public class RestUsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@RequestMapping(value = "/favoritos", method = RequestMethod.GET)
	public ResponseEntity<Collection<String>> getFavoritos(String emailUserLogged){
		Usuario userLogged = usuarioService.getByEmail(emailUserLogged).get();		
		if(userLogged.getFavoritos().size() > 0){
			return new ResponseEntity<>(userLogged.getFavoritos(), HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
		
	}
	
	@RequestMapping(value = "/favoritos/{id}", method = RequestMethod.POST)
	public ResponseEntity<Collection<String>> addFavorito(@PathVariable Long id){
		Usuario usuarioLogged = getUserLogged().getBody();
		Usuario usuarioFavoritado = usuarioService.getById(id).get();
		if(usuarioLogged.getFavoritos().contains(usuarioFavoritado.getEmail())){
			usuarioLogged.addFavorito(usuarioFavoritado.getEmail());
			
			usuarioService.update(usuarioLogged);
			return new ResponseEntity<>(usuarioLogged.getFavoritos(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/favoritos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Collection<String>> removeFavorito(@PathVariable Long id){
		Usuario usuarioLogged = getUserLogged().getBody();
		Usuario usuarioFavoritado = usuarioService.getById(id).get();
		
		if(usuarioLogged.getFavoritos().contains(usuarioFavoritado.getEmail())){
			usuarioLogged.removeFavorito(usuarioFavoritado.getEmail());
			
			usuarioService.update(usuarioLogged);
			
			return new ResponseEntity<>(usuarioLogged.getFavoritos(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public ResponseEntity<String> getLoggedUser(){

        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication().getName(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUserLogged(){
    	Optional<Usuario> usuarioProcurado = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	return new ResponseEntity<>(usuarioProcurado.get(), HttpStatus.OK);
    }
	
}
