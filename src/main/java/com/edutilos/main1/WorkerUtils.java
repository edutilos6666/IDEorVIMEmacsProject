package com.edutilos.main1;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class WorkerUtils {
  public static Vector<Vector<Object>> convertListToData(List<Worker> list) {
	  Vector<Vector<Object>> res = new Vector<>(); 
	  for(Worker w: list) {
		  Vector<Object> row = new Vector<>(); 
		  row.add(w.getId());
		  row.add(w.getName()); 
		  row.add(w.getAge()); 
		  row.add(w.getWage());
		  res.add(row); 
	  }
	  
	  return res ; 
	  
  }
  
  
  
  public static List<Object> verifyFiels(List<String> fields) {
	  List<Object> statusWithErrorMsg = new ArrayList<>();
	  boolean success = true ; 
	  StringBuilder builder = new StringBuilder();
	  final String newline = "\r\n"; 
	  //id 
	  String pattern  = "\\d+"; 
	  if(!Pattern.matches(pattern , fields.get(0))) {
		  builder.append("id must be numeric").append(newline); 
		  success = false ; 
	  }
	  //name 
	  pattern = "[[a-z][A-Z]_]+"; 
	  if(!Pattern.matches(pattern ,  fields.get(1))) {
		  builder.append("name must contain letters or _").append(newline);
		  success = false ; 
	  }
	  //age 
	  pattern = "\\d+"; 
	  if(!Pattern.matches(pattern, fields.get(2))) {
		  builder.append("age must be numeric").append(newline);
		  success = false ; 
	  }
	  //wage 
	  try {
		  Double.parseDouble(fields.get(3)); 
	  } catch(NumberFormatException ex) {
		  builder.append("wage must be double").append(newline);
		  success = false ; 
	  }
	  
	  statusWithErrorMsg.add(success); 
	  statusWithErrorMsg.add(builder.toString()); 
	  return statusWithErrorMsg; 
  }
  
  
  public static Worker convertRowToWorker(Vector<Object> row) {
	  Worker w = new Worker(); 
	  w.setId(Long.parseLong(row.get(0).toString()));
	  w.setName(row.get(1).toString());
	  w.setAge(Integer.parseInt(row.get(2).toString()));
	  w.setWage(Double.parseDouble(row.get(3).toString()));
	  return w; 
  }
  

  public static DataSource dataSource() {
	  DriverManagerDataSource ds = new DriverManagerDataSource(); 
	  ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	  ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC"); 
	  ds.setUsername("root");
	  ds.setPassword("");
	  return ds ; 
  }
  public static JdbcTemplate mysqlTemplate() {
	  JdbcTemplate template= new JdbcTemplate(dataSource()); 
	  return template ; 
  }
}
