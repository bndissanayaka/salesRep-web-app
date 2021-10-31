package com.casrilanka.test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.casrilanka.test.model.SalesRep;

public class SalesRepDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/salesrep_info?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "bndis";
	
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO salesrep_info" + "  (name, email, contact_num,joined_date,current_route,comments) VALUES "
			+ " (?, ?, ?, ?, ? ,?);";

	private static final String SELECT_EMPLOYEE_BY_ID = "select id,name,email,contact_num,joined_date,current_route from salesrep_info where id =?";
	private static final String SELECT_ALL_EMPLOYEES = "select * from salesrep_info";
	private static final String DELETE_EMPLOYEES_SQL = "delete from salesrep_info where id = ?;";
	private static final String UPDATE_EMPLOYEE_SQL = "update salesrep_info set name = ?, email = ?, contact_num = ?,joined_date = ? ,current_route = ? ,comments = ? where id = ?;";
	
	
	public SalesRepDAO() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	
	public void insertRep(SalesRep salesRep) throws SQLException {
		
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
			preparedStatement.setString(1, salesRep.getName());
			preparedStatement.setString(2, salesRep.getEmail());
			preparedStatement.setString(3, salesRep.getContactNumber());
			preparedStatement.setDate(4, salesRep.getJoinedDate());
			preparedStatement.setString(5, salesRep.getCuurentRoute());
			preparedStatement.setString(6, salesRep.getComment());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public SalesRep selectRep(int id) {
		SalesRep salesRep = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, id);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String contactNumber = rs.getString("contactNumber");
				Date joinedDate = rs.getDate("joinedDate");
				String cuurentRoute = rs.getString("cuurentRoute");
				String comment = rs.getString("comment");
				
				salesRep = new SalesRep(id, name, email, contactNumber, joinedDate, cuurentRoute, comment);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return salesRep;
	}

	public List<SalesRep> selectAllReps() {

		// using try-with-resources to avoid closing resources 
		List<SalesRep> salesReps = new ArrayList<>();
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String contactNumber = rs.getString("contactNumber");
				Date joinedDate = rs.getDate("joinedDate");
				String cuurentRoute = rs.getString("cuurentRoute");
				String comment = rs.getString("comment");
				salesReps.add(new SalesRep(id, name, email, contactNumber, joinedDate, cuurentRoute, comment));
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return salesReps;
	}

	public boolean deleteRep(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(SalesRep salesRep) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			
			statement.setString(1, salesRep.getName());
			statement.setString(2, salesRep.getEmail());
			statement.setString(3, salesRep.getContactNumber());
			statement.setDate(4, salesRep.getJoinedDate());
			statement.setString(5, salesRep.getCuurentRoute());
			statement.setString(6, salesRep.getComment());
			statement.setInt(4, salesRep.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
}
