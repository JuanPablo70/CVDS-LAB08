package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.SQLException;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {
    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }
    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }
    }

    @Override
    public void update(int id, long tarifaxdia) throws PersistenceException {
        try {
            itemMapper.actualizarItem(id, tarifaxdia);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al actualizar el item " + id, e);
        }
    }

    @Override
    public long loadRentalCost(int id, int numdias) throws PersistenceException {
        try {
            return itemMapper.costoAlquilerItem(id, numdias);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el costo por dias del item " + id, e);
        }
    }

    @Override
    public List<Item> loadAll() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles " + e);
        }
    }

    @Override
    public int loadItemRentalCostxDay(int id) throws PersistenceException {
        try {
            return itemMapper.consultarAlquilerxDia(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles " + e);
        }
    }


}