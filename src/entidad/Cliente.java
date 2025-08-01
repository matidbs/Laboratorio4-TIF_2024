package entidad;

public class Cliente {

	private String dni_Cl;
	private String cuil_Cl;
	private String nombre_Cl;
	private String apellido_Cl;
	private String sexo_Cl;
	private Nacionalidad codNacionalidad_Cl;
	private String direccion_Cl;
	private Localidad codLoc_Cl;
	private Provincia codProv_Cl;
	private String correoElectronico_Cl;
	private String telefono_Cl;
	private Usuario user_contra_Cl;
	private String fechaNacimiento_Cl;
	private boolean estado_Cl;
	
	public Cliente() {
		super();
	}

	public Cliente(String dni_Cl, String cuil_Cl, String nombre_Cl, String apellido_Cl, String sexo_Cl,
			Nacionalidad codNacionalidad_Cl, String direccion_Cl, Localidad codLoc_Cl, Provincia codProv_Cl,
			String correoElectronico_Cl, String telefono_Cl, Usuario user_contra_Cl, String fechaNacimiento_Cl,
			boolean estado_Cl) {
		super();
		this.dni_Cl = dni_Cl;
		this.cuil_Cl = cuil_Cl;
		this.nombre_Cl = nombre_Cl;
		this.apellido_Cl = apellido_Cl;
		this.sexo_Cl = sexo_Cl;
		this.codNacionalidad_Cl = codNacionalidad_Cl;
		this.direccion_Cl = direccion_Cl;
		this.codLoc_Cl = codLoc_Cl;
		this.codProv_Cl = codProv_Cl;
		this.correoElectronico_Cl = correoElectronico_Cl;
		this.telefono_Cl = telefono_Cl;
		this.user_contra_Cl = user_contra_Cl;
		this.fechaNacimiento_Cl = fechaNacimiento_Cl;
		this.estado_Cl = estado_Cl;
	}

	public String getDni_Cl() {
		return dni_Cl;
	}

	public void setDni_Cl(String dni_Cl) {
		this.dni_Cl = dni_Cl;
	}

	public String getCuil_Cl() {
		return cuil_Cl;
	}

	public void setCuil_Cl(String cuil_Cl) {
		this.cuil_Cl = cuil_Cl;
	}

	public String getNombre_Cl() {
		return nombre_Cl;
	}

	public void setNombre_Cl(String nombre_Cl) {
		this.nombre_Cl = nombre_Cl;
	}

	public String getApellido_Cl() {
		return apellido_Cl;
	}

	public void setApellido_Cl(String apellido_Cl) {
		this.apellido_Cl = apellido_Cl;
	}

	public String getSexo_Cl() {
		return sexo_Cl;
	}

	public void setSexo_Cl(String sexo_Cl) {
		this.sexo_Cl = sexo_Cl;
	}

	public Nacionalidad getCodNacionalidad_Cl() {
		return codNacionalidad_Cl;
	}

	public void setCodNacionalidad_Cl(Nacionalidad codNacionalidad_Cl) {
		this.codNacionalidad_Cl = codNacionalidad_Cl;
	}

	public String getDireccion_Cl() {
		return direccion_Cl;
	}

	public void setDireccion_Cl(String direccion_Cl) {
		this.direccion_Cl = direccion_Cl;
	}

	public Localidad getCodLoc_Cl() {
		return codLoc_Cl;
	}

	public void setCodLoc_Cl(Localidad codLoc_Cl) {
		this.codLoc_Cl = codLoc_Cl;
	}

	public Provincia getCodProv_Cl() {
		return codProv_Cl;
	}

	public void setCodProv_Cl(Provincia codProv_Cl) {
		this.codProv_Cl = codProv_Cl;
	}

	public String getCorreoElectronico_Cl() {
		return correoElectronico_Cl;
	}

	public void setCorreoElectronico_Cl(String correoElectronico_Cl) {
		this.correoElectronico_Cl = correoElectronico_Cl;
	}

	public String getTelefono_Cl() {
		return telefono_Cl;
	}

	public void setTelefono_Cl(String telefono_Cl) {
		this.telefono_Cl = telefono_Cl;
	}

	public Usuario getUser_contra_Cl() {
		return user_contra_Cl;
	}

	public void setUser_contra_Cl(Usuario user_contra_Cl) {
		this.user_contra_Cl = user_contra_Cl;
	}

	public String getFechaNacimiento_Cl() {
		return fechaNacimiento_Cl;
	}

	public void setFechaNacimiento_Cl(String fechaNacimiento_Cl) {
		this.fechaNacimiento_Cl = fechaNacimiento_Cl;
	}

	public boolean isEstado_Cl() {
		return estado_Cl;
	}

	public void setEstado_Cl(boolean estado_Cl) {
		this.estado_Cl = estado_Cl;
	}

	@Override
	public String toString() {
		return "Cliente [dni_Cl=" + dni_Cl + ", cuil_Cl=" + cuil_Cl + ", nombre_Cl=" + nombre_Cl + ", apellido_Cl="
				+ apellido_Cl + ", sexo_Cl=" + sexo_Cl + ", codNacionalidad_Cl=" + codNacionalidad_Cl.getCodNacionalidad_N()
				+ ", direccion_Cl=" + direccion_Cl + ", codLoc_Cl=" + codLoc_Cl.getCodLoc_L() + ", codProv_Cl=" + codProv_Cl.getCodProv_P()
				+ ", correoElectronico_Cl=" + correoElectronico_Cl + ", telefono_Cl=" + telefono_Cl
				+ ", user_contra_Cl=" + user_contra_Cl + ", fechaNacimiento_Cl=" + fechaNacimiento_Cl + ", estado_Cl="
				+ estado_Cl + "]";
	}
	
}
