package com.edutilos.main1;

import java.util.List;

public interface WorkerDAO {
   void save(Worker w); 
   void update(long id, Worker newW); 
   void remove(long id); 
   Worker findById(long id); 
   List<Worker> findAll(); 
}
