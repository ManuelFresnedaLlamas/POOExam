package ejercicio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Programa {

	public static void main(String[] args) {
		Usuarios u1 = new Usuarios("Paqui");
		Usuarios u2 = new Usuarios("Vicente");
		Usuarios u3 = new Usuarios("Beatriz");
		Usuarios u4 = new Usuarios("Fina");
		Usuarios u5 = new Usuarios("Juan");
		
		LinkedList<Usuarios> lista = new LinkedList<Usuarios>();
		lista.add(u1);
		lista.add(u2);
		lista.add(u3);
		lista.add(u4);
		lista.add(u5);
		
		LinkedList<Sesion> sesiones = new LinkedList<Sesion>();
		SesionPersonal sesion1 = new SesionPersonal(Actividad.YOGA, LocalDate.of(2020, 6, 16), "9:00. Hatha Yoga", "Manu");
		sesiones.addAll(sesion1.getSesionesPeriodicas(2, 2)); //funciona
		
		SesionDeNivel sesion2 = new SesionDeNivel(Actividad.PILATES, LocalDate.of(2020, 6, 17), 3, "18:00. Pilates - Nivel Principiante", 0);
		SesionCovid sesion3 = new SesionCovid(Actividad.ZUMBA, LocalDate.of(2020, 6, 17), "21:00. Zumba - Grupo Seguro", u2,u3,u4,u5);
		SesionCovid sesion4 = new SesionCovid(Actividad.ZUMBA, LocalDate.of(2020, 6, 18), "21:00. Zumba - Grupo Seguro", sesion3,u2,u3,u4); //no debe saltar error
		sesiones.add(sesion2);
		sesiones.add(sesion3);
		sesiones.add(sesion4);
		
		for(Sesion s:sesiones) {
			for(Usuarios u:lista) {
				s.inscribirUsuario(u);
			}
			System.out.println(s);
		}
		sesion4.borrarUsuario(u4); //borra a fina
		sesion4.inscribirUsuario(u1);
		sesion4.inscribirUsuario(u5);

		System.out.println(sesion4.getUsuariosInscritos());
		
		sesion2.borrarUsuario(u3);

		System.out.println(sesion2.getUsuariosInscritos());
		
		for(Sesion s:sesiones) {
			if(s instanceof SesionPersonal) {
				((SesionPersonal) s).setMonitor("Tania");
			}
		}
		

	}

}
