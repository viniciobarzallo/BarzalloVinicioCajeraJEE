/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.app.services;

import ec.edu.ups.app.modelo.Cliente;
import ec.edu.ups.app.modelo.Cuenta;
import ec.edu.ups.app.on.CajeroON;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vinny
 */
@Path("/cooperativa")
public class Rest {
    
    @Inject
    private CajeroON cajon;
    
    @POST
    @Path("/recarga")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta crearProducto2(Parametros p) throws IOException, Exception {
        Respuesta resp = new Respuesta();

        Cuenta c = cajon.buscarCuenta(p.getIdCuenta());
        System.out.println("Cuenta ID " + c.getIdCuenta());

        if (c.getSaldo() <= p.getSaldo()) {
            resp.setCodigo(2);
            resp.setMensaje("Registro No satisfactorio");
        } else {

            Cliente cli = cajon.buscarCliente(c.getCliente().getCedula());
            System.out.println("Cliente" + cli.getCedula() + cli.getNombre());

            cajon.recarga(cli.getCedula(), p.getSaldo(), c.getIdCuenta());

            resp.setCodigo(1);
            resp.setMensaje("Registro satisfactorio");
        }

       
        return resp;
    }
    
}
