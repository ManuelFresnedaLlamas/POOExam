package ejercicio;

import java.time.LocalDate;
import java.util.HashSet;

public class SesionCovid extends Sesion{
	
//	private HashSet<Usuarios> usuarios;
	private SesionCovid sesionCovidPrevia;
	
	public SesionCovid(Actividad actividadImpartida, LocalDate fechaSesion, String descripcionSesion,Usuarios...usuarios) {
		super(actividadImpartida, fechaSesion, usuarios.length, descripcionSesion);
		this.usuariosInscritos=new HashSet<Usuarios>();
		for(Usuarios u:usuarios) {
			this.usuariosInscritos.add(u);
		}
		if(usuarios.length>5) {
			throw new IllegalStateException("Se ha excedido el cupo de usuarios permitidos");
		}
		this.sesionCovidPrevia=null;
	}
	
	public SesionCovid(Actividad actividadImpartida, LocalDate fechaSesion, String descripcionSesion,SesionCovid sesionCovidPrevia,Usuarios...usuarios) {
		super(actividadImpartida, fechaSesion, usuarios.length, descripcionSesion);
		this.usuariosInscritos=new HashSet<Usuarios>();
		for(Usuarios u:usuarios) {
			this.usuariosInscritos.add(u);
		}
		if(usuarios.length>5) {
			throw new IllegalStateException("Se ha excedido el cupo de usuarios permitidos");
		}
		this.sesionCovidPrevia=sesionCovidPrevia;	
		if(!getSesionCovidPrevia().getUsuariosInscritos().containsAll(getUsuariosInscritos())) {
			throw new IllegalStateException("No todos los usuarios han participado en la sesion previa");
		}
	}
	
	public boolean inscribirUsuario(Usuarios usuario) {
		if(!(getSesionCovidPrevia()==null)) {
			if(getUsuariosInscritos().size()<getCupo()) {
				if(getSesionCovidPrevia().getUsuariosInscritos().contains(usuario)) {
					this.usuariosInscritos.add(usuario);
					return super.inscribirUsuario(usuario);
				}
			}
		}if(getSesionCovidPrevia()==null) {
			return super.inscribirUsuario(usuario);	
		}
		return false;
	}
	
	

	public SesionCovid getSesionCovidPrevia() {
		return sesionCovidPrevia;
	}

	@Override
	protected void accionesBorrarse(Usuarios usuario) {
		//no se aplica nada
	}

	@Override
	public String toString() {
		return super.toString()+"[sesionCovidPrevia=" + sesionCovidPrevia + "]";
	}


	
	
	
	
	
	
}
