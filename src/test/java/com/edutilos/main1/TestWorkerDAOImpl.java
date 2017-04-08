package com.edutilos.main1;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestWorkerDAOImpl extends TestCase {

	private WorkerDAO dao ; 
	private WorkerScanner wsc ; 
	
	@Before
	public void setUp() {
		dao = new WorkerDAOImpl();
		wsc = new WorkerScanner(); 
    
	}
	
	
	@After
	public void tearDown() {
		dao = null; 
	}
	
	
	@Test
	public void test1() {
		  dao.save(new Worker(1, "foo", 10, 100.0));     		
	      dao.save(new Worker(2, "bar", 20, 200.0));
	      dao.save(new Worker(3, "bim", 30, 300.0));
		List<Worker>  all = dao.findAll(); 
		assertEquals(3, all.size());
		Worker w = dao.findById(1L); 
		assertEquals("foo", w.getName()); 
		assertEquals(10, w.getAge()); 
		assertEquals(100.0, w.getWage()); 
		dao.update(1, new Worker(1, "newfoo", 66, 666.6));
	    w = dao.findById(1); 
	    assertEquals("newfoo", w.getName()); 
	    assertEquals(66, w.getAge()); 
	    assertEquals(666.6, w.getWage(), 0.0); 
	    dao.remove(1L);
	    all = dao.findAll(); 
	    assertEquals(2, all.size()); 
	}
	
	
	@Test
	public void test2() {
       Worker w1 , w2 , w3;
       try  {
       w1 = WorkerScanner.newWorker(); 
       w2 = WorkerScanner.newWorker(); 
       w3 = WorkerScanner.newWorker(); 
       dao.save(w1);
       dao.save(w2);
       dao.save(w3);
       List<Worker> all = dao.findAll(); 
       assertEquals(3, all.size());
       Worker w = dao.findById(1L); 
       assertEquals("foo", w.getName()); 
       assertEquals(10, w.getAge()); 
       assertEquals(100.0, w.getWage()); 
       
       dao.update(2,  new Worker(2, "newbar", 66, 666.6));
       w = dao.findById(2); 
       assertEquals("newbar", w.getName()); 
       assertEquals(66, w.getAge()); 
       assertEquals(666.6, w.getWage(), 0.0); 
       dao.remove(3L);
       all =dao.findAll(); 
       assertEquals(2, all.size()); 
        
       } catch(Exception ex) {
    	   ex.printStackTrace();
       }
	} 
}
