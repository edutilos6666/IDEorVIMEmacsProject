package com.edutilos.main1;

public class Worker {
  private long id; 
  private String name; 
  private int age; 
  private double wage;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public double getWage() {
	return wage;
}
public void setWage(double wage) {
	this.wage = wage;
}
public Worker(long id, String name, int age, double wage) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.wage = wage;
}
public Worker() {
	super();
}
@Override
public String toString() {
	return "Worker [id=" + id + ", name=" + name + ", age=" + age + ", wage=" + wage + "]";
} 
  



}
