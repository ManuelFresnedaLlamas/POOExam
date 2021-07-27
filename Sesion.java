package ejercicio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Sesion implements Cloneable{
	private final Actividad actividadImpartida;
	private LocalDate fechaSesion;
	private final String descripcionSesion;
	private final int cupo;
	protected HashSet<Usuarios> usuariosInscritos;
	private boolean completa;
	
	public Sesion(Actividad actividadImpartida, LocalDate fechaSesion, int cupo, String descripcionSesion) {
		this.actividadImpartida=actividadImpartida;
		this.fechaSesion=fechaSesion;
		this.cupo=cupo;
		this.descripcionSesion=descripcionSesion;
		this.usuariosInscritos=new HashSet<Usuarios>();
		setCompleta();
	}
	
	public Sesion(Actividad actividadImpartida, LocalDate fechaSesion, String descripcionSesion) {
		this(actividadImpartida,fechaSesion,15,descripcionSesion);
	}
	
	public boolean inscribirUsuario(Usuarios usuario) {
		
		LocalDate diaAntes = getFechaSesion().minusDays(1);
		if(LocalDate.now().equals(diaAntes) || LocalDate.now().isBefore(diaAntes)) {
			if(!isCompleta()) {
				if(!usuario.isSancionado()) {
					usuariosInscritos.add(usuario);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean consultarUsuarioInscrito(Usuarios usuario) {
		if(getUsuariosInscritos().contains(usuario)) {
			return true;
		}
		return false;
	}
	
	protected abstract void accionesBorrarse(Usuarios usuario); //TODO
	
	public void borrarUsuario(Usuarios usuario) {
		LocalDate diaAntes = getFechaSesion().minusDays(1);
		if(getUsuariosInscritos().contains(usuario)) {
			if(LocalDate.now().equals(diaAntes) || LocalDate.now().isBefore(diaAntes)) {
				usuariosInscritos.remove(usuario);
				accionesBorrarse(usuario);
			}
		}
	}
	
	
	public void confirmarSesion() {
		if(LocalDate.now().isEqual(getFechaSesion()) || LocalDate.now().isAfter(getFechaSesion())) {
			//se asume participacion total
			for(Usuarios u:getUsuariosInscritos()) {
				u.incrementarNivelActividad(getActividadImpartida());
			}
		}
	}
	
	private Sesion copiaSuperficial() {
		try {
			Sesion copiaSuperficial = (Sesion) super.clone();
			return copiaSuperficial;
		}
		catch (CloneNotSupportedException e) {
			System.err.println("La clase no es cloneable");
		}
		return null;
	}
	
	public Sesion clone() {
		Sesion copia = copiaSuperficial();
		copia.usuariosInscritos=new HashSet<Usuarios>();
		return copia;
	}
	
	//Metodo getSesionesPeriodicas
	public LinkedList<Sesion> getSesionesPeriodicas(int numDiasEntreSesiones, int repeticiones){
		LinkedList<Sesion> sesionesPeriodicas = new LinkedList<Sesion>();
		sesionesPeriodicas.add(this.clone());
		for(int i=0;i<repeticiones;i++) {
			Sesion copiaAcumulativa = this.clone();
			copiaAcumulativa.fechaSesion=getFechaSesion().plusDays(numDiasEntreSesiones*(i+1));
			sesionesPeriodicas.add(copiaAcumulativa);
		}
		return sesionesPeriodicas;
	}
	
	

	public LocalDate getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(LocalDate fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public HashSet<Usuarios> getUsuariosInscritos() {
		return new HashSet<Usuarios>(usuariosInscritos);
	}

	public void setUsuariosInscritos(HashSet<Usuarios> usuariosInscritos) {
		this.usuariosInscritos = usuariosInscritos;
	}

	public boolean isCompleta() {
		return completa;
	}

	public void setCompleta() {
		int numUsuarios=getUsuariosInscritos().size();
		int numPlazas=getCupo();
		if(numPlazas==numUsuarios) {
			this.completa=true;
		}else {
			this.completa=false;
		}
		
	}

	public Actividad getActividadImpartida() {
		return actividadImpartida;
	}

	public String getDescripcionSesion() {
		return descripcionSesion;
	}

	public int getCupo() {
		return cupo;
	}

	@Override
	public String toString() {
		return getClass().getName()+"[actividadImpartida=" + actividadImpartida + ", fechaSesion=" + fechaSesion
				+ ", descripcionSesion=" + descripcionSesion + ", cupo=" + cupo + ", usuariosInscritos="
				+ usuariosInscritos + ", completa=" + completa + "]";
	}
	
	
	
}
