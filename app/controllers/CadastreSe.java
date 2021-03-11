package controllers;

import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class CadastreSe extends Controller {

	public static void formCadastrar() {
		Usuario usu = (Usuario)Cache.get("usu");
		Cache.clear();
		render(usu); 
	}
	
	public static void salvarCadastreSe(Usuario usu, String senha) {
		
		if (senha.equals ("") == false) {
			usu.senha = senha;
			
			if(senha.length() <3) {
				validation.addError("usu.senha", "A senha deve conter no mínino 3 digitos ");
			}
		}
			
		validation.valid(usu);
		
		if(validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao cadastrar! Preencha os campus corretamente.");
			Cache.set("usu", usu);
			formCadastrar();
			
		}
		
		usu.save();
		flash.success("Cadastrado com sucesso!");
		formCadastrar();
	}
}
