package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Date;
import java.util.List;

public interface ItemRentadoDAO {
    public List<ItemRentado> loadAll() throws PersistenceException;
    public List<ItemRentado> loadAlquileres(long idcli);
    public ItemRentado load(int id) throws PersistenceException;
    public long loadMulta(int id, Date fechaDevolucion) throws PersistenceException;
    public void saveAlquiler(Date date, long docu, Item item, int numdias) throws PersistenceException;
}
