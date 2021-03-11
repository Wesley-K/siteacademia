package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import enums.Nivel;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Treino extends Model {
		
	@Required (message="O nome é obrigatório")
	public String nome;
	
	// Um treino tem varios usuários 
	// JoinC cria uma coluna em usuário(no banco de dados)
	
	@OneToMany
	@JoinColumn(name="idtreino")
	public List<Usuario> usuarios;
	
}
