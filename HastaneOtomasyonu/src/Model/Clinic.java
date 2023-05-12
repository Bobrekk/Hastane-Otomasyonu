package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {
	private String name;
	private int id;
	DBConnection conn = new DBConnection ();
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Clinic() {
	};

	public Clinic(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public ArrayList<Clinic> addClinicList() {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
				s.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean addClinic (String name) throws SQLException {
		c = conn.connDb();
		s = c.createStatement();
		boolean key = false;
		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
		ps = c.prepareStatement(query);
		ps.setString(1, name);
		ps.executeUpdate();
		key = true;
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean deleteClinic (int ID){
		c = conn.connDb();
		boolean key = false;
		String query = "DELETE FROM clinic WHERE id = ?";
		try {
			s = c.createStatement();
			ps = c.prepareStatement(query);
			ps.setInt(1, ID);
			ps.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean updateClinic (int id,String name) throws SQLException {
		c = conn.connDb();
		s = c.createStatement();
		boolean key = false;
		String query = "UPDATE clinic SET name = ? WHERE id = ?";
		ps = c.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.executeUpdate();
		key = true;
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public Clinic getFetch (int id) throws SQLException {
		c = conn.connDb();
		s = c.createStatement();
		rs = s.executeQuery("SELECT * FROM clinic WHERE id = " + id);
	    Clinic clinic = new Clinic ();
		while (rs.next()) {
			clinic.setId(rs.getInt("id"));
			clinic.setName(rs.getString("name"));
			break;
		}
		return clinic;
	}
	
	public ArrayList <User> addClinicDoctorList(int clinicID) {
		ArrayList <User> list = new ArrayList <>();
		User obj;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM worker w LEFT JOIN user u ON w.userid = u.id WHERE clinicid = " + clinicID);
			while (rs.next()) {
				obj = new User (rs.getInt("u.id"),rs.getString("u.name"),rs.getString("u.tcno"),rs.getString("u.password"),rs.getString("u.type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
				s.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
