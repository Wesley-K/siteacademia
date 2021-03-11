package controllers;

import java.util.Arrays;
import java.util.List;

import enums.Nivel;
import models.Servidor;
import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {

	//criando formulario//
	public static void form() {
		
		//Pegando as informações que estão no CACHE
		Usuario usu = (Usuario)Cache.get("usu");
		Cache.clear();
		List<Nivel> nivel = Arrays.asList(Nivel.values());
		render(nivel, usu);
	}
	
	//public static void formCadastrar() {
	//	render();
	//}
	
	public static void dados() {
		render();
	}
	
	public static void salvar(Usuario usu, String senha) {
		
		//Esse if serve para podermos deixar a senha no editar vazio,por isso recebemos String senha separadamente
		if (senha.equals ("") == false) {
			usu.senha = senha;
			
			//Esse if serve para validar os digitos digitados
			if(senha.length() <3) {
				validation.addError("usu.senha", "A senha deve conter no mínino 3 digitos ");
			}
		}
		//Outra forma de validação
		validation.valid(usu);
		
		//verificando se teve erro
		//Guardando as informações no CACHE
		
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar  o usuário");
			Cache.set("usu", usu);
			form();
		}
		
		usu.save();
		flash.success("Ação realizada com sucesso!");
		Servidores.listarUsuarios();
	}
	
	//public static void salvarCadastreSe(Usuario usu) {
	//  usu.save();
	//	flash.success("Inscrição realizada com sucesso!");
	//	formCadastrar();
	//}
	
	
	public static void listar () {
		List<Usuario> lista = Usuario.findAll();
		render(lista);
	}
	
	public static void editar(long id) {
		Usuario usu = Usuario.findById(id);
		List<Nivel> nivel = Arrays.asList(Nivel.values());
		renderTemplate("Usuarios/form.html", usu, nivel);
	}
	
	public static void deletar(long id) {
		Usuario usu = Usuario.findById(id);
		usu.delete();
		
		flash.success("Removido com sucesso.");
		
		Servidores.listarUsuarios();
	}
}
