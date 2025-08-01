package entidad;

public class Usuario {
	
	private String nombreUser_U;
	private String contra_U;
	
	public Usuario() {

	}
	
	public Usuario(String nombreUser_U, String contra_U) {
		super();
		this.nombreUser_U = nombreUser_U;
		this.contra_U = contra_U;
	}
	public String getNombreUser_U() {
		return nombreUser_U;
	}
	public void setNombreUser_U(String nombreUser_U) {
		this.nombreUser_U = nombreUser_U;
	}
	public String getContra_U() {
		return contra_U;
	}
	public void setContra_U(String contra_U) {
		this.contra_U = contra_U;
	}

	@Override
	public String toString() {
		return nombreUser_U;
	}
	
	

}
