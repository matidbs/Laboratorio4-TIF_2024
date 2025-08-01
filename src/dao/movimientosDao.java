package dao;

import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Transferencia;

public interface movimientosDao {
	public ArrayList<Movimiento> listarMovimientos(Cuenta cuenta);
	public ArrayList<Movimiento> filtrarMovimientosCBU(Cuenta cuenta, String cbu);
	public boolean AgregarTransferencia(Transferencia transferencia);
	public ArrayList<Movimiento> filtrarMovimientosTipoMov(int codTipo, Cuenta cuenta);
}
