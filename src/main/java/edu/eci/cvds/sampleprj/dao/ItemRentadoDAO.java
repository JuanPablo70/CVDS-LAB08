package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface ItemRentadoDAO {
    public List<ItemRentado> loadAll() throws PersistenceException;
    public ItemRentado load(int id) throws PersistenceException;
}