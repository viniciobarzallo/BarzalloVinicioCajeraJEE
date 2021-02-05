/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.app.dao;

import ec.edu.ups.app.modelo.Cliente;
import ec.edu.ups.app.modelo.Cuenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vinny
 */
@Stateless
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public void insertarCliente(Cliente cliente) throws Exception {
        em.persist(cliente);
    }

    public void insertarCuenta(Cuenta cuenta) throws Exception {
        em.persist(cuenta);
    }

    public void recarga(String cedula, double saldo, String idCuenta) throws Exception {
        String jpql = "UPDATE Cliente p SET p.saldo = p.saldo + " + saldo + " WHERE cedula='" + cedula + "'";
        Query query = em.createQuery(jpql);
        query.executeUpdate();
        
        actualizarSaldoCuenta(idCuenta, saldo);

    }

    public Cuenta buscarCuenta(String idCuenta) throws Exception {
        Cuenta c = null;
        try {
            String jpql = "SELECT p FROM Cuenta p " + "WHERE p.idCuenta LIKE :idCuenta";
            TypedQuery<Cuenta> query = em.createQuery(jpql, Cuenta.class);
            query.setParameter("idCuenta", idCuenta);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }
    
    public Cliente buscarCliente(String cedula) throws Exception {
        Cliente c = null;
        try {
            String jpql = "SELECT p FROM Cliente p " + "WHERE p.cedula LIKE :cedula";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cedula", cedula);

            c = query.getSingleResult();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return c;
    }

    public void actualizarSaldoCuenta(String idCuenta, double abono) throws Exception {
        String jpql = "UPDATE Cuenta p SET p.saldo = p.saldo-" + abono + " WHERE idCuenta='" + idCuenta + "'";
        Query query = em.createQuery(jpql);
        query.executeUpdate();

    }
    
    public static long numbGen(){
        while (true){
            long numb = (long)(Math.random()*100000000 * 1000000);
            if(String.valueOf(numb).length()==12)
                return numb;
        }
    }
        
}
