package crudconsole.model;
import java.sql.*;
import java.util.Scanner;

import crudconsole.bean.book;;

public class booksModel {
    static Connection con = DBConnection.getCon();
    public long AddBook(book bk){
        long pk = 0;
        try{
            PreparedStatement stmt = con.prepareStatement("insert into books values(?,?,?,?,?,?,?)");
            stmt.setLong(1, 0);
            stmt.setString(2, bk.getTitle());
            stmt.setString(3, bk.getAuthor());
            stmt.setString(4, bk.getEdition());
            stmt.setString(5, bk.getGenre());
            stmt.setString(6, bk.getAvail_status());
            stmt.setInt(7, bk.getCat_id());
            pk=stmt.executeUpdate();
            if(pk==1){
                System.out.println("Added book: "+bk.getTitle());
            }
            else{
                System.out.println("Cant add");
            }
        }catch(SQLException e){
            e.getStackTrace();
        }
        return pk;
    }
    public long removeBook(long bookid){
        long pk = 0;
        try{
            PreparedStatement stmt = con.prepareStatement("delete from books where bookId=?");
            System.out.println("Removed Book!");
            stmt.setLong(1, bookid);
            pk = stmt.executeUpdate();
        }catch(SQLException e){
            e.getStackTrace();
        }
        return pk;
    }
    public static void viewCategories(){ //view Categories
		try(Connection con = DBConnection.getCon();Statement stmt = con.createStatement()){
			String query = "SELECT * FROM category";
			ResultSet resultSet = stmt.executeQuery(query);

			System.out.println("Category ID ------------ Category Name");
			while (resultSet.next()) {
				int catId = resultSet.getInt("cat_id");
				String catName = resultSet.getString("cat_name");

				System.out.println(catId+"    |"+catName);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
	}
    public static void viewBooks(){ //viewBookss
		boolean loop = true;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(loop){
			System.out.println("1.View books by availability");
			System.out.println("2.View books by category");
			System.out.println("3.View all books");
			System.out.println("4.Exit...");
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
					try(Connection con = DBConnection.getCon();Statement stmt = con.createStatement()){
						String query = "SELECT * FROM books WHERE avail_status='Available'"; // view avalilable books
						ResultSet resultSet = stmt.executeQuery(query);
						System.out.println("BookId\t\t\tTitle\t\t\tAvailability Status");
						while(resultSet.next()){
							String title = resultSet.getString("title");
							long bookid = resultSet.getInt("bookId");
							String stat = resultSet.getString("avail_status");
							System.out.println(bookid+"\t\t\t"+title+"\t\t\t"+stat);
						}
					}catch(SQLException e){
						System.out.println(e);
					}
					break;
				case 2:
					viewCategories();

					System.out.println("Enter the categoryID of the books you want to view: ");
					int cat = sc.nextInt();
					System.out.println();
					String query1 = "SELECT b.bookId,b.title,c.cat_name,b.avail_status FROM books b join category c on b.cat_id=c.cat_id WHERE c.cat_id = ?";
					//viewing the book along with its category using joins
					try(Connection con = DBConnection.getCon();PreparedStatement stmt = con.prepareStatement(query1);){

						stmt.setInt(1, cat);
						ResultSet resultSet = stmt.executeQuery();
						System.out.println("BookID \t\t\t Title \t\t\t Category \t\t\t Status");

						while(resultSet.next()){
							String title = resultSet.getString("title");
							long bookid = resultSet.getInt("bookId");
							String category = resultSet.getString("cat_name");
							String stat = resultSet.getString("avail_status");

							System.out.println(bookid+"\t\t\t"+title+"\t\t\t"+category+"\t\t\t"+stat);
						}

					}catch(SQLException e){
						System.out.println(e);
					}
					break;
				case 3:
					try(Connection con = DBConnection.getCon();Statement stmt = con.createStatement();){
						String query = "SELECT * FROM books"; // view all books
						ResultSet resultSet = stmt.executeQuery(query);
						System.out.println("BookId----BookName-----------------Status");
						while (resultSet.next()) {
							long bookId = resultSet.getLong("bookId");
							String bookName = resultSet.getString("title");
							String status = resultSet.getString("avail_status");
		
							System.out.println(bookId+"      |"+bookName+" \t\t\t|"+status);
						}
						System.out.println();
					}catch(SQLException e){
						System.out.println(e);
					}
					break;
				case 4: 
					loop = false;
					break;
				default:
				System.out.println("Invalid Input!!!");
					loop=false;
					break;
			}
 		}
	}
    public static void borrowBook(int userId){
        long pk=0;
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book id to borrow: ");
        int borrowBookId = sc.nextInt();
        try{
            String query = "select * from books where bookId=? and avail_status='Available'";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, borrowBookId);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                stmt = con.prepareStatement("insert into borrow values(?,?,?,curdate(), date_add(curdate(), INTERVAL 1 month))");
                stmt.setLong(1, 0);
                stmt.setLong(2, borrowBookId);
                stmt.setLong(3, userId);
                pk=stmt.executeUpdate();
                if(pk==1){
                    System.out.println("Borrow table updated");
                }else{
                    System.out.println("Cant update borrow table!!!");
                }
                stmt = con.prepareStatement("update books set avail_status='Checked Out' where bookId=?");
                stmt.setLong(1, borrowBookId);
                pk=stmt.executeUpdate();
                if(pk==1){
                    System.out.println("Books Table Updated.");
                }
            }else{
                System.out.println("Your Book is either not available or not in the database!!!");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
	public static void returnBook(long userId){
		Scanner sc = new Scanner(System.in);
		long returnerId = userId;
		try{
			String query = "select br.bookId,b.title from borrow br join books b on br.bookId=b.bookId where br.userId=?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, returnerId);
			ResultSet rs = stmt.executeQuery();
			System.out.println("The Books You Borrowed: ");
			while(rs.next()){
				System.out.println();
				System.out.println("BookId: "+rs.getInt("bookId"));
				System.out.println("Book Name: "+rs.getString("title"));
				System.out.println();
			}
			System.out.println("Enter the bookId to return: ");
			long returnId = sc.nextInt();
			try{
				String query1 = "delete from borrow where bookId=? and userId=?";
				stmt = con.prepareStatement(query1);
				stmt.setLong(1, returnId);
				stmt.setLong(2, returnerId);
				long pk = stmt.executeUpdate();
				if(pk!=1){
					System.out.println("Cant Delete");
					return;
				}
				query1 = "update books set avail_status='Available' where bookId=?";
				stmt = con.prepareStatement(query1);
				stmt.setLong(1, returnId);
				pk = stmt.executeUpdate();
				if(pk!=1){
					System.out.println("Cant update the book table!");
					return;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
