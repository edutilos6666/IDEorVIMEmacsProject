package com.edutilos.main1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class WorkerDAOJdbcImpl implements WorkerDAO{

	
	private JdbcTemplate template; 
	
	public WorkerDAOJdbcImpl() {
	   template = WorkerUtils.mysqlTemplate(); 
	}
	
	public void dropTable() {
		String sql = "drop table if exists Worker"; 
		template.update(sql); 
	}
	
	public void createTable() {
		String sql = "create table if not exists Worker ("
				+ "id bigint primary key , "
				+ "name varchar(50) not null, "
				+ "age int not null, "
				+ "wage double  not null"
				+ ")"; 
		template.update(sql); 
	}

	@Override
	public void save(Worker w) {
		 String sql = "insert into Worker VALUES(?, ?, ?, ?)"; 
		 template.update(sql, w.getId(), w.getName(), w.getAge(), w.getWage()); 
	}

	@Override
	public void update(long id, Worker newW) {
		String sql = "update Worker set name = ?, age = ? , wage = ? where id = ? "; 
		template.update(sql ,  newW.getName(), newW.getAge(), newW.getWage(), id); 
	}

	@Override
	public void remove(long id) {
		String sql = "delete from Worker where id = ?"; 
		template.update(sql, id); 
	}

	@Override
	public Worker findById(long id) {
	   Worker w = new Worker();
	   String sql = "select * from Worker where id = ?"; 
	   w = template.queryForObject(sql, new Object[]{id}, new RowMapper<Worker>() {

		@Override
		public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Worker(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("wage")); 
		}
		   
	   }); 
	   return w ; 
	}

	@Override
	public List<Worker> findAll() {
		String sql = "select * from Worker"; 
		List<Worker> list = template.query(sql, new RowMapper<Worker>() {
			@Override
			public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Worker(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("wage")); 
			}
			   
		}); 
		return list;
	}
  
}
