package jobs;

import enums.Nivel;
import models.Servidor;
import models.Turno;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		
		if(Servidor.count() == 0) {
			
			Turno turno = new Turno();
			turno.nome = "TurnoPadrao";
			turno.save();
			
			Servidor servidor1 = new Servidor();
			servidor1.email = "admin@gmail.com";
			servidor1.nome = "Admin";
			servidor1.senha = "123";
			servidor1.nivel = Nivel.ADMIN;
			servidor1.turno = turno;
			servidor1.save();
		}
		
		
	}
}
