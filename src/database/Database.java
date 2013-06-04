package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Group;
import entities.Round;
import entities.Score;
import entities.Team;
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

	public boolean signup(User user) {
		String query = "INSERT INTO user(`fname`,`lname`,`username`,`password`,`img`,`status`)VALUES(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImg());
			ps.setString(6, "Pending");
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User signIn(String username, String password) {
		String query = "SELECT * FROM user WHERE `username`=? AND `status`='Approved'";
		PreparedStatement ps;
		User user = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					user = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("password"), rs.getString("img"), rs.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean approveUser(User user) {
		String query = "UPDATE user SET `status`='Approved' WHERE `id`=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createTeam(Team team) {
		String query = "INSERT INTO team(`name`,`description`)VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, team.getName());
			ps.setString(2, team.getDescription());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean createGroup(Group group) {
		String query = "INSERT INTO group(`name`,`description`)VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, group.getName());
			ps.setString(2, group.getDescription());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addUserToTeam(User user, Team team) {
		String query = "INSERT INTO user_team(`userid`,`teamid`)VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setInt(2, team.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addUserToGroup(User user, Group group) {
		String query = "INSERT INTO user_group(`userid`,`groupid`)VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setInt(2, group.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isInGroup(User user, Group group) {
		String query = "SELECT * FROM user_group WHERE `userid`=? AND `groupid`=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setInt(2, group.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Score> getScoresPerTeamAndRound(Team team, Round round) {
		String query = "SELECT * FROM score WHERE `id` IN (SELECT `scoreid` FROM round_team_score WHERE `teamid`=? AND `roundid`=?)";
		PreparedStatement ps;
		ArrayList<Score> scores = new ArrayList<Score>();
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, team.getId());
			ps.setInt(1, round.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				scores.add(new Score(rs.getInt("id"), rs.getDouble("value"), rs.getString("description"), getUser(rs.getInt("userid"))));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scores;
	}

	public User getUser(int id) {
		String query = "SELECT * FROM user WHERE `id`=?";
		PreparedStatement ps;
		User user = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("password"), rs.getString("img"), rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<Team> getAllTeams() {
		String query = "SELECT * FROM team";
		PreparedStatement ps;
		ArrayList<Team> teams = new ArrayList<Team>();
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				teams.add(new Team(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}

	public ArrayList<Round> getAllRounds() {
		String query = "SELECT * FROM round";
		PreparedStatement ps;
		ArrayList<Round> rounds = new ArrayList<Round>();
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				rounds.add(new Round(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rounds;
	}
}
