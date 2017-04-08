package com.edutilos.main1;

import java.util.Scanner;

public class WorkerScanner {
	private static Scanner sc = new Scanner(System.in); 
	
   public static Worker newWorker() throws Exception {
	long id; 
	String name; 
	int age; 
	double wage; 
	System.out.println("Insert id:");
	id = Long.parseLong(sc.nextLine()); 
	System.out.println("Insert name: ");
	name = sc.nextLine(); 
	System.out.println("Insert age: ");
	age = Integer.parseInt(sc.nextLine()); 
	System.out.println("Insert wage: ");
	wage = Double.parseDouble(sc.nextLine());
	return new Worker(id, name, age, wage); 
   }
   
   
   public static long idForUpdate() throws Exception {
	   System.out.println("Insert id for update: ");
	   long id = sc.nextLong(); 
	   return id; 
   }
   

   public static long idForRemove() throws Exception {
	   System.out.println("Insert id for remove: ");
	   long id = sc.nextLong(); 
	   return id; 
   }
   
   

   public static long idForFind() throws Exception {
	   System.out.println("Insert id for find: ");
	   long id = sc.nextLong(); 
	   return id; 
   }
   
}
