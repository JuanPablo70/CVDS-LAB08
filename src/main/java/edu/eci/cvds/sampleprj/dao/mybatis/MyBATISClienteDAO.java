package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.exceptions.PersistenceException;

import javax.inject.Inject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO {
    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente c) throws PersistenceException {
        try {
            clienteMapper.insertarCliente(c);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + c.toString(), e);
        }
    }

    @Override
    public void saveAlquilerCliente(Date date, long docu, Item item, int numdias) { // REVISAR!!!!!!
        try {
            java.util.Date currentTime = Calendar.getInstance().getTime();
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(currentTime);
            //clienteMapper.agregarItemRentadoACliente((int) docu, item.getId(), Date.valueOf(timeStamp), );
        } catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item rentado" + item.getId() + "con cliente" + docu, e);
        }
    }

    @Override
    public Cliente load(long doc) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente((int) doc);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el cliente " + doc, e);
        }
    }

    @Override
    public List<ItemRentado> loadItemsCliente(long idcliente) {
        try {
            return clienteMapper.consultarCliente((int) idcliente).getRentados();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar rentados del cliente " + idcliente, e);
        }
    }

    @Override
    public List<Cliente> loadAll() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes", e);
        }
    }

    @Override
    public void update(long docu, boolean vetado) throws PersistenceException {
        try {
            clienteMapper.actualizarEstado((int) docu, vetado ? 1 : 0);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException("Error al actualizar el cliente " + docu, e);
        }
    }
}
