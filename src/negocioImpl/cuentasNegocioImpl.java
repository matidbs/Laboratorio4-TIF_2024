package negocioImpl;

import java.util.ArrayList;

import dao.cuentasDao;
import daoImpl.cuentasDaoImpl;
import entidad.Cuenta;
import negocio.cuentasNegocio;

public class cuentasNegocioImpl implements cuentasNegocio{
	
	cuentasDao dao = new cuentasDaoImpl();

	@Override
	public Cuenta Agregar(Cuenta cuenta) {
		return dao.Agregar(cuenta);
	}

	@Override
	public boolean Modificar(Cuenta cuenta) {
		return dao.Modificar(cuenta);
	}

	@Override
	public boolean Eliminar(Cuenta cuenta) {
		return dao.Eliminar(cuenta);
	}
	
	@Override
	public ArrayList<Cuenta> listarCuentas() {
		return (ArrayList<Cuenta>)dao.listarCuentas();
	}

	@Override
	public ArrayList<Cuenta> listarCuentasActivas() {
		return (ArrayList<Cuenta>)dao.listarCuentasActivas();
	}

	@Override
	public ArrayList<Cuenta> listarCuentasInactivas() {
		return (ArrayList<Cuenta>)dao.listarCuentasInactivas();
	}

	@Override
	public ArrayList<Cuenta> listarCuentasDni(Cuenta cuenta) {	
		return dao.listarCuentasDNI(cuenta);
	}

	@Override
	public int ContarCuentasDni(Cuenta cuenta) {
		int contador = 0;
		contador = dao.listarCuentasDNI(cuenta).size();
		
		return contador;
	}

	@Override
	public ArrayList<Cuenta> listarCuentaCBU(Cuenta cuenta) {
		return dao.listarCuentaCBU(cuenta);
	}

	@Override
	public ArrayList<Cuenta> listarCuentaNroCuenta(Cuenta cuenta) {
		return dao.listarCuentaNroCuenta(cuenta);
	}

	@Override
	public boolean existeCuenta(Cuenta cuenta) {
		return dao.existeCuenta(cuenta);
	}

	@Override
	public boolean activarCuentasCliente(Cuenta cuenta) {
		return dao.activarCuentasCliente(cuenta);
	}

	@Override
	public boolean darBajaCuentasCliente(Cuenta cuenta) {
		return dao.darBajaCuentasCliente(cuenta);
	}



}
