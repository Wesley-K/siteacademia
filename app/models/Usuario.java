package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

import enums.Nivel;
import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	@Required (message="O nome é obrigatório")
	public String nome;
	
	@Required (message="O email é obrigatório")
	@Email
	@Unique (message="E-mail já cadastrado! Tente outro.")
	public String email;
	
	@Required (message="A senha é obrigatório")
	public String senha;
	
	//Bagunçando a senha e enviando para senha
	public void setSenha (String s) {
		senha = Crypto.passwordHash(s);
	}
	
	@OneToMany(mappedBy = "usuario")
	public List<Ficha> fichas;
	
	
	@ManyToOne
	@JoinColumn(name="idturno")
	public Turno turno;
	
	@ManyToOne
	@JoinColumn(name="idtreino")
	public Treino treino;
	
	@Required (message="O nivel é obrigatório")
	public Nivel nivel; //ADMIN ou COMUM
	
	public Usuario() {
		nivel = Nivel.PADRAO;
	}
	
}
