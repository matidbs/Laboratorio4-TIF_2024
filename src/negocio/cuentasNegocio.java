package negocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface cuentasNegocio {

	public Cuenta Agregar(Cuenta cuenta);
	public boolean Modificar(Cuenta cuenta);
	public boolean Eliminar(Cuenta cuenta);
	public ArrayList<Cuenta> listarCuentas();
	public ArrayList<Cuenta> listarCuentasActivas();
	public ArrayList<Cuenta> listarCuentasInactivas();
	public ArrayList<Cuenta> listarCuentasDni(Cuenta cuenta);
	public int ContarCuentasDni(Cuenta cuenta); //cuenta la cantidad de cuentas que tiene el user
	public ArrayList<Cuenta> listarCuentaCBU(Cuenta cuenta);
	public ArrayList<Cuenta> listarCuentaNroCuenta(Cuenta cuenta);
	public boolean existeCuenta(Cuenta cuenta);
	public boolean activarCuentasCliente(Cuenta cuenta);
	public boolean darBajaCuentasCliente(Cuenta cuenta);

}
