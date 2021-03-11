package controllers;

import play.*;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Seguranca.class)
public class Treinos extends Controller {

   public static void form() {
	   render();
	   
   }
   
   public static void salvar(@Valid Treino tre, Long idUsuario) {
	   
	   if(validation.hasErrors()) {
		   validation.keep();
		   form();
		   
	   }
	   
	   if (idUsuario != null) {
		   Usuario u = Usuario.findById(idUsuario);
		   tre.usuarios.add(u);
		   flash.success("Salvo com sucesso!");
	   }
	   
	   tre.save();
	   editar(tre.id);
	   
   }
   
   public static void listar () {
		List<Treino> treino = Treino.findAll();
		render(treino);
	}
   
   public static void editar(long id) {
	   Treino t1 = Treino.findById(id);
	   
	  // List<Usuario> usuarios = Usuario.findAll();
	   
	   							//Essa lista, lista todos os usuarios que não estejam cadastrados nesse treino
		   						// (Se ele ja estiver, não aparece no select, se não estiver, ele irá aparecer)
	   List<Usuario> usuarios = Usuario.find("select usu from Usuario usu, Treino tre "
												+ " where tre.id = ?1 and "
												+ " usu not member of tre.usuarios "
												+ " order by tre.nome ", id ).fetch();
	   
	   renderTemplate("Treinos/form.html", t1, usuarios);
	   
   }
   
   public static void deletar (long id) {
	   Treino tre = Treino.findById(id);
	   tre.delete();
	   flash.success("Removido com sucesso.");
	   
	   listar();
			
   }
   
   public static void removerUsuario(Long idTreino, Long idUsu) {
	   
	   Treino t1 = Treino.findById(idTreino);
	   Usuario u = Usuario.findById(idUsu);
	   
	   t1.usuarios.remove(u);
	   flash.success("Removido com sucesso!");
	   t1.save();
	   editar(t1.id);
	   
   }
	
}