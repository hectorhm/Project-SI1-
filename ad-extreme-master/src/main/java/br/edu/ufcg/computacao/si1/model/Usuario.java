package br.edu.ufcg.computacao.si1.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.authority.AuthorityUtils;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column(unique = true)
    private String email;
    @Column
    private String senha;
    @Column
    private String role;
    @Column
    private double saldo = 0.0;
    @Resource(name="meus_favoritos")
    private Collection<Usuario> favoritos = new ArrayList<>();
    /*@Resource(name="meus_anuncios")
    private Collection<Anuncio> anuncios = new ArrayList<>();*/

    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }
    
    /**
     * 
     * @param nome
     * @param email
     * @param senha
     * @param role
     * @param saldo foi alterado, pois originalmente o saldo não era um atributo do usuario
     */
    public Usuario(String nome, String email, String senha, String role) {
        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String novoRole) {
        this.role = novoRole;
    }
    
    public double getSaldo() {
    	return saldo;
    }
    
    public void setSaldo(double novoSaldo) {
    	this.saldo = novoSaldo;
    }
    
    public String toString(){
    	return String.format("%s{Nome=%s, Contato='%s'}", role, nome, email);
    }
    /*
    public void cadastrarAnuncio(Anuncio anuncio){
    	anuncios.add(anuncio);
    }
    
    public Collection<Anuncio> getAnuncios(){
    	return anuncios;
    }   */
    
    public Collection<Usuario> getFavoritos(){
    	return favoritos;
    }
    
    public void removeFavorito(Usuario usuario){
    	favoritos.remove(usuario);
    }
    
    public void addFavorito(Usuario usuario){
    	favoritos.add(usuario);
    }
    
}
