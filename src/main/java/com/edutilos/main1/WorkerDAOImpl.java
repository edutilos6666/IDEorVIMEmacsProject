package com.edutilos.main1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerDAOImpl implements WorkerDAO {
  
	private Map<Long, Worker> container ; 
	

	public WorkerDAOImpl() {
       container =  new HashMap<>(); 	
	}

	@Override
	public void save(Worker w) {
		container.put(w.getId(), w); 
	}

	@Override
	public void update(long id, Worker newW) {
		container.put(id, newW); 
	}

	@Override
	public void remove(long id) {
		container.remove(id); 
	}

	@Override
	public Worker findById(long id) {
		Worker w = container.get(id);  
		return w;
	}

	@Override
	public List<Worker> findAll() {
		return new ArrayList<>(container.values()); 
	}

}
