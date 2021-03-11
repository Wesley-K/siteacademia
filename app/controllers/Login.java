package controllers;

import enums.Nivel;
import models.Servidor;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {
	
	 //Tela de Login não tem segurança
	 public static void telaLogin() {
		 render();
	}
	
	 //Encerrando sessão
	public static void sair () {
		
		session.clear();
		Login.telaLogin();
	}
	
	//Autentiando os usuários (admin ou usuário padrão)
	public static void autenticar(String email, String senha) {
																		//Recebendo uma senha e criptografando ela
		Usuario usuario = Usuario.find("email = ? and senha = ?", email, Crypto.passwordHash(senha)).first(); 
																								//first => o primeiro que encontrar
		
		if(usuario == null) {
		
			flash.error("Erro no Email ou senha.");
			Login.telaLogin();
			
		} else {
			
			//Salvando esses dados do usuário na sessão (enquanto estiver logado)
			
			session.put("nomeUsuario", usuario.nome);
			session.put("emailUsuario", usuario.email);
			session.put("usuarioID", usuario.id);
			session.put("nivelUsuario", usuario.nivel);
			
			if (usuario.turno != null){
				session.put("turno", usuario.turno.nome);
			}
			if (usuario.treino != null){
				session.put("treino", usuario.treino.nome);
			}
			
			
			if(usuario.nivel.equals(Nivel.ADMIN)) {
				Application.index();
			} else if (usuario.nivel.equals(Nivel.PADRAO)) {
				Application.telaUsu();
			
					}
			}
		}
	}