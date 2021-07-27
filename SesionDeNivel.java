package ejercicio;

import java.time.LocalDate;

public class SesionDeNivel extends Sesion{
	
	private int nivelMinimo;
	
	public SesionDeNivel(Actividad actividadImpartida, LocalDate fechaSesion, int cupo, String descripcionSesion, int nivelMinimo) {
		super(actividadImpartida, fechaSesion, cupo, descripcionSesion);
		this.nivelMinimo=nivelMinimo;
	}
	
	public boolean inscribirUsuario(Usuarios usuario) {
		for(Actividad a:usuario.getMapaNiveles().keySet()) {
			if(a.equals(getActividadImpartida())) {
				if(usuario.getMapaNiveles().get(a)>=getNivelMinimo()) {
					return super.inscribirUsuario(usuario);
				}
			}
		}
		return false;
	}

	@Override
	protected void accionesBorrarse(Usuarios usuario) {
		//se desocntara un nivel de la actividad
		usuario.decrementarNivelActividad(getActividadImpartida());
		
	}
	
	public SesionDeNivel clone() {
		return (SesionDeNivel) super.clone();
	}
	
	public int getNivelMinimo() {
		return nivelMinimo;
	}

	@Override
	public String toString() {
		return super.toString()+"[nivelMinimo=" + nivelMinimo + "]";
	}

	

	
	
}
