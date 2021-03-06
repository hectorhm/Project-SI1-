package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**Classe responsavel pelas politicas de Repositorio do Anuncio
 * Created by Marcus Oliveira on 28/12/16.
 */
@Service
public class AnuncioServiceImpl implements AnuncioService {
	// TODO add validity checks

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
		/* neste codigo apenas atribuimos o repositorio jpa ao atributo */
		this.anuncioRepository = anuncioRepository;
	}

	public AnuncioRepository getAnuncioRepository() {
		return this.anuncioRepository;
	}
	
	/**Metodo que cria o anuncio
	 * 
	 */
	@Override
	public Anuncio create(Anuncio anuncio) {
		/* aqui salvamos o anuncio recem criado no repositorio jpa */
//		System.out.println(anuncio+" estah sendo criado");
		return anuncioRepository.save(anuncio);
	}

	/**Metodo que retorna um anuncio por seu ID
	 * 
	 */
	@Override
	public Optional<Anuncio> getById(Long id) {
		/* aqui recuperamos o anuncio pelo seu id */
		Anuncio anuncio = anuncioRepository.findOne(id);
//		System.out.println(anuncio+" estah sendo buscado");
		return Optional.ofNullable(anuncio);
	}

	/**Metodo que retorna um anuncio por seu tipo
	 * 
	 */
	@Override
	public Collection<Anuncio> get(String tipo) {

		/*
		 * pegamos aqui todos os anuncios, mas retornamos os anuncios por tipo
		 * filtrando o tipo, pelo equals, retornando um arrayLista
		 */
		return anuncioRepository.findAll().stream().filter(anuncio -> anuncio.getTipo().equals(tipo))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**Metodo que retorna todos os anuncios
	 * 
	 */
	@Override
	public Collection<Anuncio> getAll() {
		/* aqui retornamos todos os anuncios, sem distincao */

		return anuncioRepository.findAll();
	}

	/**Metodo que informa se um anuncio existe em uma colecao
	 * 
	 */
	@Override
	public boolean update(Anuncio anuncio) {
		/* a atualizacao do anuncio eh feita apenas se o anuncio ja existir */
		if (anuncioRepository.exists(anuncio.get_id())) {
//			System.out.println(anuncio+" estah sendo atualizado");
			anuncioRepository.save(anuncio);
			return true;
		}
		return false;
	}

	/**Metodo que deleta um anuncio
	 * 
	 */
	@Override
	public boolean delete(Long id) {
		/* aqui se apaga o anuncio se ele existir */

		if (anuncioRepository.exists(id)) {
			anuncioRepository.delete(id);
			return true;
		}
		return false;
	}
}
