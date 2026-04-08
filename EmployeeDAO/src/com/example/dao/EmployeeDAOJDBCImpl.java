package com.example.dao;

import java.sql.*;
import java.util.*;

import com.example.model.Employee;

public class EmployeeDAOJDBCImpl implements EmployeeDAO {
	private Connection conn;
	
	
	public EmployeeDAOJDBCImpl() {
		String url = "jdbc:mysql://localhost:3306/EmployeeDB";
        String username = "root";
        String password = "lewisxie";
        try {
        	conn = DriverManager.getConnection(url, username, password);       	
        }catch(SQLException ex) {
        	System.err.println("資料庫連線失敗:" + ex.getMessage());
        	System.err.println("程式結束!");
        	System.exit(0);
        }
	}

	@Override
	public void add(Employee emp) throws DAOException {
		String sql = "INSERT IGNORE INTO EMPLOYEE VALUES (?,?,?,?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			//PreparedStatement is expecting a sql.date instance; however, emp.getBirhDate() returns a util.date instance.
			//By chaining .getTime(), it could be recognized as sql.Date object.
			pstmt.setDate(4, new java.sql.Date(emp.getBirthDate().getTime())); 
			pstmt.setFloat(5, emp.getSalary());
			if(pstmt.executeUpdate()!=1) {
				throw new DAOException("編號"+emp.getId()+"已存在!");
			}			
		}catch(SQLException e) {
			throw new DAOException("資料庫新增發生錯誤!",e);
		}		
	}

	@Override
	public void update(Employee emp) throws DAOException {
		String sql = "UPDATE EMPLOYEE SET FIRSTNAME =?, LASTNAME =?, BIRTHDATE=?, SALARY=? WHERE ID =?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){  
		//Driver -> Connection -> Statement -> ResultSet
		//"Statement" could be understand as SQL syntax, and "ResultSet" would be the result you get.
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setDate(3, new java.sql.Date(emp.getBirthDate().getTime()));
			pstmt.setFloat(4, emp.getSalary());
			pstmt.setInt(5, emp.getId());
			if(pstmt.executeUpdate()!=1)
				throw new DAOException("編號"+emp.getId()+"更新失敗");
		}catch(SQLException e) {
			throw new DAOException("資料庫更新發生錯誤!", e);
		}		
	}

	@Override
	public void delete(int id) throws DAOException {
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate()!=1)
				throw new DAOException("編號"+id+"刪除失敗");				
		}catch(SQLException e) {
			throw new DAOException("資料庫刪除發生錯誤!", e);
		}
		
	}

	@Override
	public Employee findByID(int id) throws DAOException {
		String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
		Employee emp = null;
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				emp = new Employee(rs.getInt("ID"), rs.getString("FIRSTNAME"),
								   rs.getString("LASTNAME"), rs.getDate("BIRTHDATE"), rs.getFloat("SALARY"));
			}
			return emp;
		}catch(SQLException e) {
			throw new DAOException("資料庫查詢有誤", e);
		}
	}

	@Override
	public Employee[] getAllEmployees() throws DAOException {
		String query = "SELECT * FROM EMPLOYEE";
		ArrayList<Employee> emps = new ArrayList<>();
		try(Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query)){
			while(rs.next()) {
				emps.add(new Employee(rs.getInt("ID"), rs.getString("FIRSTNAME"),
								   rs.getString("LASTNAME"), rs.getDate("BIRTHDATE"), rs.getFloat("SALARY")));
			}
			return emps.toArray(new Employee[0]);			
		} catch (SQLException e) {
			throw new DAOException("資料抓取失敗~",e);
		}
	}
	
	@Override
	public void close() throws Exception {
		try {
		conn.close();
		}catch(SQLException e) {
			System.out.println("資源關閉失敗:" + e);
		}
	}

}
