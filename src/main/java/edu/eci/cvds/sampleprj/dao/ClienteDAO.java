package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Date;
import java.util.List;

public interface ClienteDAO {
    public void save(Cliente c) throws PersistenceException;
    public void saveAlquilerCliente(Date date, long docu, Item item, int numdias);
    public Cliente load(long doc) throws PersistenceException;
    public List<ItemRentado> loadItemsCliente(long idcliente);
    public List<Cliente> loadAll() throws PersistenceException;
    public void update(long docu, boolean estado) throws PersistenceException;
}
