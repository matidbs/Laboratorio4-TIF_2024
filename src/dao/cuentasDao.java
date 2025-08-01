package dao;

import java.util.ArrayList;

import entidad.Cliente;
import entidad.Cuenta;

public interface cuentasDao {
	
	public Cuenta Agregar(Cuenta cuenta);
	public boolean Modificar(Cuenta cuenta);
	public boolean Eliminar(Cuenta cuenta);
	public ArrayList<Cuenta> listarCuentas();
	public ArrayList<Cuenta> listarCuentasActivas();
	public ArrayList<Cuenta> listarCuentasInactivas();
	public ArrayList<Cuenta> listarCuentasDNI(Cuenta cuenta);
	public ArrayList<Cuenta> listarCuentaCBU(Cuenta cuenta);
	public ArrayList<Cuenta> listarCuentaNroCuenta(Cuenta cuenta);
	public boolean existeCuenta(Cuenta cuenta);
	public boolean activarCuentasCliente(Cuenta cuenta);
	public boolean darBajaCuentasCliente(Cuenta cuenta);
}
