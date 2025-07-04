package crudconsole.model;
import java.sql.*;

import crudconsole.bean.user;

public class userModel {
    Connection con = DBConnection.getCon();
    public long AddUser(user locUser){
        long pk=0;
        try {
            PreparedStatement stmt = con.prepareStatement("Insert into users values (?,?,?,?,?,?,?,?)");
            stmt.setLong(1, 0);
            stmt.setString(2, locUser.getName());
            stmt.setString(3, locUser.getPassword());
            stmt.setString(4, locUser.getEmail());
            stmt.setString(5, locUser.getUserType());
            stmt.setString(6, locUser.getAddress());
            stmt.setString(7, locUser.getPhno());
            stmt.setDate(8, locUser.getDate());
            pk=stmt.executeUpdate();
            System.out.println("PK value: "+pk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pk;
    }
    public long removeUser(long userId){
        long pk = 0;
        try{
            PreparedStatement stmt = con.prepareStatement("Delete from users where userId=?");
            stmt.setLong(1, userId);
            pk = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pk;
    }
    public void viewUserDetails(long userId){
        try{
            PreparedStatement stmt = con.prepareStatement("select userId,userName,email,userType,address,phone,dateJoined from users where userId=?");
            stmt.setLong(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                long userid = resultSet.getLong("userId");
                String name = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String addr = resultSet.getString("address");
                String phn = resultSet.getString("phone");
                Date dt = resultSet.getDate("dateJoined");
                System.out.println("userId: "+userid);
                System.out.println("UserName: "+name);
                System.out.println("Email: "+email);
                System.out.println("Address: "+ addr);
                System.out.println("Phone: "+phn);
                System.out.println("Date Joined: "+dt);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
