package controllers;

import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	
	//Os metodos com anotações BEFORE são executados ANTES de cada ação.
	@Before
	static void verificar() {
		
		//Verificando se usuário tem email, se sim então esta logado, se não então não esta logado
		
		if (session.contains("emailUsuario") == false) {
			Login.telaLogin();
		}
		
	}
	// Proteje todas as outras funções, exceto esses listados a baixo. 
	
	@Before(unless={"Application.telaUsu", "Fichas.formCorporal", "Fichas.listarCorporal", "Fichas.salvarCorporal", "Fichas.editarCorporal", "Fichas.removerFicha"})
	static void permissoes () {
		verificar();
		
		 if (session.get("nivelUsuario").equals("ADMIN") == false) {
			// renderText("Acesso negado");
			 renderTemplate("errors/permissao.html");
			 
		 }
		
	}

}
