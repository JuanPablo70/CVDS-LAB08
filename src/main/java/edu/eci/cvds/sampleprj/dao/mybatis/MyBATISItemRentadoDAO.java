package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {
    @Inject
    ItemRentadoMapper itemRentadoMapper;

    @Override
    public List<ItemRentado> loadAll() throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemsRentados();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el items rentados", e);
        }
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemRentado(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item rentado " + id, e);
        }
    }
}
