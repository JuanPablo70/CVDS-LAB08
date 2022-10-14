package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface ItemDAO {
	public void save(Item it) throws PersistenceException;
	public Item load(int id) throws PersistenceException;
	public void update(int id, long tarifaxdia) throws PersistenceException;
	public long loadRentalCost(int id, int numdias) throws PersistenceException;
	public List<Item> loadAll() throws PersistenceException;
	public int loadItemRentalCostxDay(int id) throws PersistenceException;
}
