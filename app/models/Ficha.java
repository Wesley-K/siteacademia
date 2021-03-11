package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Ficha extends Model {
	
	@Required (message="Preenchimento obrigatório")
	public String altura;
	@Required (message="Preenchimento obrigatório")
	public String peso;
	@Required (message="Preenchimento obrigatório")
	public String bracoEsq;
	@Required (message="Preenchimento obrigatório")
	public String bracoDi;
	@Required (message="Preenchimento obrigatório")
	public String abdomem;
	@Required (message="Preenchimento obrigatório")
	public String cintura;
	@Required (message="Preenchimento obrigatório")
	public String quadril;
	@Required (message="Preenchimento obrigatório")
	public String coxaEsq;
	@Required (message="Preenchimento obrigatório")
	public String coxaDi;
	@Required (message="Preenchimento obrigatório")
	public String pantuEsq;
	@Required (message="Preenchimento obrigatório")
	public String pantuDi;
	
	
	@ManyToOne
	public Usuario usuario;
	
}
