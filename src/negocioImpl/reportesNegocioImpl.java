package negocioImpl;

import java.util.ArrayList;

import dao.reportesDao;
import daoImpl.reportesDaoImpl;
import entidad.Reporte;
import negocio.reportesNegocio;

public class reportesNegocioImpl implements reportesNegocio{

	reportesDao dao = new reportesDaoImpl();
	

	@Override
	public ArrayList<Reporte> listarReportes() {
	  			return dao.listarReportes();
	  	}
	
	@Override
	public ArrayList<Reporte> listarReportes_Provincia(int codProv) {
		return dao.listarReportes_Provincia(codProv);
	}

	@Override
	public ArrayList<Reporte> listarReportes_ProvinciaLocalidad( int codLoc) {
		return dao.listarReportes_ProvinciaLocalidad(codLoc);
	}

	@Override
	public ArrayList<Reporte> listarReportes_Anio(String anio) {
		return dao.listarReportes_Anio(anio);
	}

	@Override
	public ArrayList<Reporte> listarReportes_AnioMes(String anio, String mes) {
		return dao.listarReportes_AnioMes(anio, mes);
	}

	@Override
	public ArrayList<Reporte> listarReportes_Saldo(String operador, String saldo) {
		return dao.listarReportes_Saldo(operador, saldo);
	}


}
