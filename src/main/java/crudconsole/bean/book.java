package crudconsole.bean;

// bookId bigint primary key auto_increment,
//     title varchar(160) not null,
// 	author varchar(100) not null,
//     edition varchar(10) not null,
//     genre varchar(50) not null,
//     avail_status varchar(50) not null,
//     cat_id int not null,

public class book {
    private long bookId;
    private String title;
    private String author;
    private String edition;
    private String genre;
    private String avail_status;
    private int cat_id;
    public book(){
        
    }
    public book(long bookId, String title, String author, String edition, String genre, String avail_status,
            int cat_id) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.genre = genre;
        this.avail_status = avail_status;
        this.cat_id = cat_id;
    }
    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getAvail_status() {
        return avail_status;
    }
    public void setAvail_status(String avail_status) {
        this.avail_status = avail_status;
    }
    public int getCat_id() {
        return cat_id;
    }
    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    
}
