package crudconsole.main;
import crudconsole.model.DBConnection;
import crudconsole.model.booksModel;
import crudconsole.model.userModel;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import crudconsole.bean.book;
import crudconsole.bean.user;


public class Main {
	private static void userLogin(String uName,String uPass){ // user login.. admin can also use user login
		System.out.println("User Login Page");
		
		String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
		try(Connection con = DBConnection.getCon();
		PreparedStatement stmt = con.prepareStatement(query)){
			stmt.setString(1, uName);
			stmt.setString(2, uPass);
			
			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()){
				int userId = resultSet.getInt(1);
				System.out.println("User Id: "+userId);
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				System.out.println("Logged In as User: "+uName);
				boolean loop=true;
				while (loop) {
					System.out.println("1.View Categories");
					System.out.println("2.View Books");
					System.out.println("3.Borrow a book");
					System.out.println("4.Return a book");
					System.out.println("5.Exit");

					int choice = sc.nextInt();
					switch (choice) {
						case 1:
							booksModel.viewCategories();
							break;
						case 2: 
							booksModel.viewBooks();
							break;
						case 3:
							booksModel.borrowBook(userId);							
							break;
						case 4:
							booksModel.returnBook(userId);
							break;
						case 5:
							loop=false;
							break;
					}
				}
			}else{
				System.out.println("Invalid Username or Password!!!");
			}
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	private static void adminLogin(String aName , String aPass){ // admin login.. only admin can use admin login
		System.out.println("Admin Login Page");

		String query = "SELECT * FROM users where userName = ? and userPassword = ? and userType = 'admin'";

		try(Connection con = DBConnection.getCon();PreparedStatement stmt = con.prepareStatement(query)){
			stmt.setString(1, aName);
			stmt.setString(2, aPass);

			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()){
				System.out.println("Logged in as admin");
				Scanner sc = new Scanner(System.in);
				boolean loop = true;
				userModel um = new userModel();
				booksModel bm = new booksModel();
				book bk = new book();
				while(loop){
					System.out.println("1.Add a book");
					System.out.println("2.Remove a book");
					System.out.println("3.Remove an user");
					System.out.println("4.View details of an user");
					System.out.println("5.Exit");
					int choice = sc.nextInt();
					switch (choice) {
						case 1:
						sc.nextLine();
						System.out.println("Enter the title of the book: ");
						bk.setTitle(sc.nextLine());

						System.out.println("Enter the author of the book: ");
						bk.setAuthor(sc.nextLine());
						
						System.out.println("Enter the edition of the book: ");
						bk.setEdition(sc.next());

						System.out.println("Enter the genre of the book: ");
						bk.setGenre(sc.next());
						sc.nextLine();
						System.out.println("Enter the availStatus of the book (Available/Checked Out): ");
						bk.setAvail_status(sc.nextLine());
						System.out.println("Enter the Category ID of the book: ");
						bk.setCat_id(sc.nextInt());
						
						bm.AddBook(bk);
						break;
						case 2:
							System.out.println("Enter the bookId to remove: ");
							long bookid = sc.nextLong();
							bm.removeBook(bookid);
							break;
						case 3:
							System.out.println("Enter the userId: ");
							int removeUserId = sc.nextInt();
							long pk = um.removeUser(removeUserId);
							System.out.println("Removed user: "+pk);
							break;
						case 4:
							System.out.println("Enter the userId: ");
							int viewUserId = sc.nextInt();
							um.viewUserDetails(viewUserId);
							break;
						case 5:
							loop=false;
							break;
						default:
							break;
					}
				}
			}else{
				System.out.println("Invalid username or password or not an admin");
			}
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	private static void createUser(){
		System.out.println("User Sign Up page: ");
		Scanner sc = new Scanner(System.in);
		//add user
		user u1 = new user(); //user details are setted using user.java methods
		userModel um = new userModel(); // object for userModel to create user
		System.out.println("Enter Your Name: ");
		u1.setName(sc.next());
		System.out.println("Enter Your Password: ");
		u1.setPassword(sc.next());
		System.out.println("Enter Your Email: ");
		u1.setEmail(sc.next());
		System.out.println("User Type (student,teacher,public,admin): ");
		u1.setUserType(sc.next());
		System.out.println("Enter Your Address: ");
		u1.setAddress(sc.next());
		System.out.println("Enter Your phno: ");
		u1.setPhno(sc.next());
		LocalDate currentDate = LocalDate.now();
		u1.setDate(Date.valueOf(currentDate));
		long pk = um.AddUser(u1); // user created using the AddUser method in userModel.java
		System.out.println("data added: "+pk);
	}

	public static void main(String[]args) { // main console
		System.out.println("DB Connection: "+DBConnection.getCon());
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to The Library Management Console");
		boolean loop=true;
		while (loop) {
			System.out.println("1.User Login");
			System.out.println("2.Admin Login");
			System.out.println("3.New User Sign Up");
			System.out.println("4.Exit");
			System.out.println("Enter Your Choice: ");
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
					System.out.println("Enter your username: ");
					String uName = sc.next();
					System.out.println("Enter your password: ");
					String uPass = sc.next();
					userLogin(uName,uPass);
					break;
				case 2:
					System.out.println("Enter your username: ");
					String aName = sc.next();
					System.out.println("Enter your password: ");
					String aPass = sc.next();
					adminLogin(aName,aPass);
					break;
				case 3:
					createUser();
					break;
				case 4:
					System.out.println("Exiting...");
					loop=false;
					break;
				default:
					loop=false;
					break;
			}
		}
		sc.close();
	}
}
