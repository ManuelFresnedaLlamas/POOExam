package ejercicio;

import java.time.LocalDate;


public class SesionPersonal extends Sesion{

	private String monitor;
	
	public SesionPersonal(Actividad actividadImpartida, LocalDate fechaSesion, String descripcionSesion, String monitor) {
		super(actividadImpartida, fechaSesion, 1, descripcionSesion);	
		setMonitor(monitor);
	}

	@Override
	protected void accionesBorrarse(Usuarios usuario) {
		usuario.sancionar(2); //dos dias sancionado
	}
	
	
	
	public SesionPersonal clone() {
		return (SesionPersonal) super.clone();
	}
	
	public void setMonitor(String nombre) {
		this.monitor=nombre;
	}
	
	public String getMonitor() {
		return monitor;
	}

	@Override
	public String toString() {
		return super.toString()+"[monitor=" + monitor + "]";
	}
	
	

}
