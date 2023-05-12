package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class wHour {
	private int id, doctorid;
	private String doctorname, wdate, status;
	
	DBConnection conn = new DBConnection();
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public wHour(int doctorid, String doctorname, String wdate, String status) {
		super();
		this.doctorid = doctorid;
		this.doctorname = doctorname;
		this.wdate = wdate;
		this.status = status;
	}
    public wHour () {}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList <wHour> addwHourList(int doctorID) {
		ArrayList <wHour> list = new ArrayList <>();
		wHour obj;
		try {
			Connection c = conn.connDb();
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM whour WHERE status = 'A' AND doctorid = " + doctorID);
			while (rs.next()) {
				obj = new wHour ();
				obj.setDoctorid(rs.getInt("id"));
				obj.setDoctorname(rs.getString("doctorname"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				Connection c = conn.connDb();
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
