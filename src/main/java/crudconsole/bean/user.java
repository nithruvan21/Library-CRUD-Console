package crudconsole.bean;
import java.sql.Date;

public class user {
	private long id;
	private String name;
	private String password;
	private String email;
	private String userType;
	private String address;
	private String phno;
	private Date date;
	
	public user() {
		
	}
	public user(long id, String name,String password, String email, String userType, String address, String phno,
			Date date) {
		super();
		this.id = id;
		this.name = name;
		this.password=password;
		this.email = email;
		this.userType = userType;
		this.address = address;
		this.phno = phno;
		this.date = date;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
