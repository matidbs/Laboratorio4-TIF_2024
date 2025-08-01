package negocio;

import java.util.ArrayList;

import entidad.Reporte;

public interface reportesNegocio {
	
	public ArrayList<Reporte> listarReportes();
	public ArrayList<Reporte> listarReportes_Provincia(int codProv);
	public ArrayList<Reporte> listarReportes_ProvinciaLocalidad( int codLoc);
	public ArrayList<Reporte> listarReportes_Anio(String anio);
	public ArrayList<Reporte> listarReportes_AnioMes(String anio, String mes);
	public ArrayList<Reporte> listarReportes_Saldo(String operador, String saldo);
}
