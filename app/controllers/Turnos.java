package controllers;

import play.*;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.*;



import models.*;

@With(Seguranca.class)
public class Turnos extends Controller {

   public static void form() {
	   render();
	   
   }
   
   public static void salvar(@Valid Turno tur, Long idUsuario) {
	   
	   if(validation.hasErrors()) {
		   validation.keep();
		   form();
		   
	   }
	   
	   
	   if (idUsuario != null) {
		   Usuario u = Usuario.findById(idUsuario);
		   tur.usuarios.add(u);
		   flash.success("Salvo com sucesso!");
	   }
	   
	   tur.save();
	   editar(tur.id);
	   
   }
   
   public static void listar () {
		List<Turno> turno = Turno.findAll();
		render(turno);
	}
   
   public static void editar(long id) {
	   Turno t = Turno.findById(id);
	   
	  //  List<Usuario> usuarios = Usuario.findAll();
	   										
	   							//Essa lista, lista todos os usuarios que não estejam cadastrados nesse turno
	   						   // (Se ele ja estiver, não aparece no select, se não estiver, ele irá aparecer) 
	   List<Usuario> usuarios = Usuario.find("select usu from Usuario usu, Turno turn "
	   											+ " where turn.id = ?1 and "
			   									+ " usu not member of turn.usuarios "
	   											+ " order by turn.nome ", id ).fetch();
	   
	   
	   renderTemplate("Turnos/form.html", t, usuarios);
	   
   }
   
   public static void deletar (long id) {
	   Turno tur = Turno.findById(id);
	   tur.delete();
	   flash.success("Removido com sucesso.");
	   
	   listar();
			
   }
   
   public static void removerUsuario(Long idTurno, Long idUsu) {
	   
	   Turno t = Turno.findById(idTurno);
	   Usuario u = Usuario.findById(idUsu);
	   
	   t.usuarios.remove(u);
	   flash.success("Removido com sucesso!");
	   t.save();
	   
	   editar(t.id);
	   
   }
	
}