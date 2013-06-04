package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.User;

public class Database {
	private Connection connection;
	private static Database instance;

	private Database() {

	}

	private Connection createConnection() {
		if (connection == null) {
			String url = "jdbc:mysql://localhost/MEETAlumniCompetition2013";
			String username = "root";
			String password = "";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Connection is established");
			} catch (ClassNotFoundException e) {
				System.out.println("JDBC Class not found");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("failed in connecting");
			}
		}
		return connection;
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		instance.createConnection();
		return instance;
	}
	public boolean signup(User user){
		String query = "INSERT INTO user(`fname`,`lname`,`username`,`password`,`img`,`status`)VALUES(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImg());
			ps.setString(6, user.getStatus());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
