package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User{
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public BasHekim(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}

	public BasHekim() {
	}
	public ArrayList <User> addDoctorList() {
		ArrayList <User> list = new ArrayList <>();
		User obj;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User (rs.getInt("id"),rs.getString("name"),rs.getString("tcno"),rs.getString("password"),rs.getString("type"));
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
	public boolean addDoctor (String name,String tcno,String password) throws SQLException {
		c = conn.connDb();
		s = c.createStatement();
		boolean key = false;
		String query = "INSERT INTO user" + "(name,tcno,password,type) VALUES" + "(?,?,?,?)";
		ps = c.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, tcno);
		ps.setString(3, password);
		ps.setString(4, "doktor");
		ps.executeUpdate();
		key = true;
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean deleteDoctor (int ID){
		c = conn.connDb();
		boolean key = false;
		String query = "DELETE FROM user WHERE id = ?";
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
	public boolean updateDoctor (int id,String name,String tcno,String password) throws SQLException {
		c = conn.connDb();
		s = c.createStatement();
		boolean key = false;
		String query = "UPDATE user SET name = ?,tcno = ?, password = ? WHERE id = ?";
		ps = c.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, tcno);
		ps.setString(3, password);
		ps.setInt(4, id);
		ps.executeUpdate();
		key = true;
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean addWorker (int userid, int clinicid) throws SQLException {
		String query = "INSERT INTO worker" + "(userid,clinicid) VALUES" + "(?,?)";
		int counter = 0;
		boolean key = false;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM worker WHERE clinicid = " + clinicid + " AND userid = " + userid);
			while (rs.next()) {
				counter++;
			}
			if (counter == 0) {
				ps = c.prepareStatement(query);
				ps.setInt(1, userid);
				ps.setInt(2, clinicid);
				ps.executeUpdate();
			}
			key = true;
		}
			catch (Exception e) {
				e.printStackTrace();
			};
			
			
			if (key == false) {
				return false;
			}
			else {
				return true;
			}
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
	}


