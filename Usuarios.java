package ejercicio;

import java.time.LocalDate;
import java.util.HashMap;

public class Usuarios {
	private final String nombreUsuario;
	private HashMap<Actividad,Integer> mapaNiveles; //registrar nº de sesiones realizadas de cada actividad
	private LocalDate fechaFinSancion;
	private boolean sancionado;
	
	public Usuarios(String nombreUsuario) {
		this.nombreUsuario=nombreUsuario;
		this.mapaNiveles=new HashMap<Actividad,Integer>();
		this.mapaNiveles.put(Actividad.AEROYOGA, 0);
		this.mapaNiveles.put(Actividad.CROSSFIT, 0);
		this.mapaNiveles.put(Actividad.PILATES, 0);
		this.mapaNiveles.put(Actividad.SPINNING, 0);
		this.mapaNiveles.put(Actividad.YOGA, 0);
		this.mapaNiveles.put(Actividad.ZUMBA, 0);
		this.fechaFinSancion=LocalDate.now();
		if(LocalDate.now().isBefore(fechaFinSancion)) {
			this.sancionado=true;
		}else {
			this.sancionado=false;
		}
	}
	
	public void incrementarNivelActividad(Actividad actividad) {
		int aux=0;
		for(Actividad a:getMapaNiveles().keySet()) {
			if(a.equals(actividad)) {
				aux=getMapaNiveles().get(actividad);
				aux=aux+1;
			}
		}
		mapaNiveles.put(actividad, aux);
	}
	
	public void decrementarNivelActividad(Actividad actividad) {
		int aux=0;
		for(Actividad a:getMapaNiveles().keySet()) {
			if(a.equals(actividad)) {
				aux=getMapaNiveles().get(actividad);
				aux=aux-1;
			}
		}
		mapaNiveles.put(actividad, aux);
	}
	
	public void sancionar(int diasSancionado) {
		this.fechaFinSancion=getFechaFinSancion().plusDays(diasSancionado);
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public HashMap<Actividad, Integer> getMapaNiveles() {
		return new HashMap<Actividad,Integer>(mapaNiveles);
	}

	public LocalDate getFechaFinSancion() {
		return fechaFinSancion;
	}

	public boolean isSancionado() {
		return sancionado;
	}

	@Override
	public String toString() {
		return "Usuarios [nombreUsuario=" + nombreUsuario + ", mapaNiveles=" + mapaNiveles + ", fechaFinSancion="
				+ fechaFinSancion + ", sancionado=" + sancionado + "]";
	}
	
	
	
	
}
