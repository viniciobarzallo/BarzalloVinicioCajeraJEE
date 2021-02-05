/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.app.on;

import ec.edu.ups.app.dao.ClienteDAO;
import ec.edu.ups.app.modelo.Cliente;
import ec.edu.ups.app.modelo.Cuenta;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vinny
 */
@Stateless
public class CajeroON {

    @Inject
    private ClienteDAO clidao;

    public void insertarCliente(Cliente cliente) throws Exception {
        clidao.insertarCliente(cliente);
    }

    public void insertarCuenta(Cuenta cuenta) throws Exception {
        clidao.insertarCuenta(cuenta);
    }

    public void recarga(String cedula, double saldo, String idCuenta) throws Exception {
        clidao.recarga(cedula, saldo, idCuenta);
    }

    public Cuenta buscarCuenta(String idCuenta) throws Exception {
        return clidao.buscarCuenta(idCuenta);
    }
    
       public Cliente buscarCliente(String cedula) throws Exception {
        return clidao.buscarCliente(cedula);
    }

    public void actulizarSaldoCuenta(String idCuenta, double abono) throws Exception {
        clidao.actualizarSaldoCuenta(idCuenta, abono);
    }

}
