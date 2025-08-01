package negocioImpl;

import java.util.ArrayList;

import dao.movimientosDao;
import daoImpl.movimientosDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Transferencia;
import negocio.movimientosNegocio;

public class movimientosNegocioImpl implements movimientosNegocio{

	movimientosDao dao = new movimientosDaoImpl();
	@Override
	public ArrayList<Movimiento> listarMovimientos(Cuenta cuenta) {
		return dao.listarMovimientos(cuenta);
	}

	@Override
	public ArrayList<Movimiento> filtrarMovimientosCBU(Cuenta cuenta, String cbu) {
		return dao.filtrarMovimientosCBU(cuenta, cbu);
	}

	@Override
	public boolean AgregarTransferencia(Transferencia transferencia) {
		return dao.AgregarTransferencia(transferencia);
	}
	
	@Override
	public ArrayList<Movimiento> filtrarMovimientosTipoMov(int codTipo,
			Cuenta cuenta) {
		return dao.filtrarMovimientosTipoMov(codTipo, cuenta);
	}
}
