package controllers;

import play.*;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.*;

import enums.Nivel;
import models.*;

@With(Seguranca.class)
public class Fichas extends Controller {

	public static void formCorporal() {
		
		Ficha fic = (Ficha)Cache.get("fic");
		Cache.clear();
		render(fic);
	}
	
	public static void salvarCorporal(Ficha fic) {
		
		validation.valid(fic);
		
		//verificando se teve erro
		
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar a ficha");
			Cache.set("fic", fic);
			formCorporal();
		}
		
		Long idUsuario = Long.parseLong(session.get("usuarioID"));
		Usuario usuario = Usuario.findById(idUsuario);
		
		fic.usuario = usuario;
		fic.save();
		usuario.fichas.add(fic);
		usuario.save();
		flash.success("Salvo com sucesso!");
		formCorporal();
		
	}
	
	public static void listarCorporal () {
		Long idUsuario = Long.parseLong(session.get("usuarioID"));
		Usuario usuario = Usuario.findById(idUsuario);
		
		List<Ficha> listaC = Ficha.find("usuario = ?" , usuario).fetch();
		render(listaC);
	}

	public static void editarCorporal(long id) {
		Ficha fic = Ficha.findById(id);
	
		renderTemplate("Fichas/formCorporal.html", fic);
		
	}
	
	 public static void removerFicha(Long idFicha) {
		 
		 Long idUsuario = Long.parseLong(session.get("usuarioID"));
		 Usuario u = Usuario.findById(idUsuario);
		 
		 Ficha f = Ficha.findById(idFicha);
		   
		 u.fichas.remove(f);
		 u.save();
		 flash.success("Removido com sucesso!");
		 f.delete();
		 listarCorporal();
		
	}
}
