package controllers;


import java.util.Arrays;
import java.util.List;

import enums.Nivel;
import models.Servidor;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;


@With(Seguranca.class)
public class Servidores extends Usuarios {

	
	public static void form() {
		
		Servidor serv = (Servidor)Cache.get("serv");
		Cache.clear();
		List<Nivel> nivel = Arrays.asList(Nivel.values());
		render(nivel, serv);
	} 
	
	
	public static void salvar(Servidor serv, String senha) {
		
		if (senha.equals ("") == false) {
			serv.senha = senha;
			
			if(senha.length() <3) {
				validation.addError("serv.senha", "A senha deve conter no mínino 3 digitos ");
			}
		}
		
		validation.valid(serv);
		
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar  o servidor");
			Cache.set("serv", serv);
			form();
		}
		
		serv.save();
		flash.success("Ação realizada com sucesso!");
		Servidores.listar();
	}
	
	public static void listar () {
		String busca = params.get("busca");
		
		List<Servidor> lista;
		if (busca == null) {
			 lista = Servidor.findAll();
		} else {
			 lista = Servidor.find("nome like ?1 or email like ?1 order by email ", "%"+busca+"%").fetch();
		}		
		render(lista);
	}
	
	public static void listarUsuarios () {
		String busca = params.get("busca");
		
		List<Usuario> lista;
		if (busca == null) {
			 lista = Usuario.findAll();
		} else {
			 lista = Usuario.find("nome like ?1 or email like ?1 order by email ", "%"+busca+"%").fetch();
		}			
		render(lista);
	}
	public static void editar(long id) {
		Servidor serv = Servidor.findById(id);
		List<Nivel> nivel = Arrays.asList(Nivel.values());
		renderTemplate("Servidores/form.html", serv, nivel);
		
	}
	
	public static void deletar(long id) {
		Servidor serv = Servidor.findById(id);
		serv.delete();

		flash.success("Removido com sucesso.");
		
		listar();
			}
}
