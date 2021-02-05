/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.app.vista;

import static ec.edu.ups.app.dao.ClienteDAO.numbGen;
import ec.edu.ups.app.modelo.Cliente;
import ec.edu.ups.app.modelo.Cuenta;
import ec.edu.ups.app.on.CajeroON;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vinny
 */
@WebServlet("/cajero")
public class Main extends HttpServlet {

    @Inject
    private CajeroON on;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("<h1>Cajero</h1>");

        try {
            Cliente cl = new Cliente();
            cl.setCedula("0104839451");
            cl.setNombre("Melissa");
            cl.setNumeroCelular("0990254304");
            cl.setSaldo(3.50);

            on.insertarCliente(cl);

            String aleatorio = String.valueOf(numbGen());

            Cuenta cuenta = new Cuenta();
            cuenta.setIdCuenta("123456");
            cuenta.setSaldo(75.30);
            cuenta.setCliente(cl);

            on.insertarCuenta(cuenta);

            Cuenta c = on.buscarCuenta("345656");
            System.out.println("Cuenta ID " + c.getIdCuenta());

            Cliente cli = on.buscarCliente(c.getCliente().getCedula());
            System.out.println("Cliente" + cli.getCedula() + cli.getNombre());
            
            on.recarga(cli.getCedula(), 3.2, c.getIdCuenta());

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
