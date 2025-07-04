create database library;
use library;
create table users(
	userId bigint primary key auto_increment,
    userName varchar(50) not null,
    userPassword varchar(50) not null,
    email varchar(75) unique,
    userType varchar(50) not null,
    address varchar(100) not null,
    phone varchar(20) not null, 
    dateJoined date,
    constraint userTypeValidity check(userType='student' or userType='teacher' or userType='public' or userType='admin')
)auto_increment=202120;
drop table users;
select * from users;
update users set email='' where userId=202123;
desc users;

create table category(
	cat_id int primary key auto_increment,
    cat_name varchar(60) not null
)auto_increment=100;

INSERT INTO category (cat_id, cat_name)
VALUES
(1, 'Fiction'),
(2, 'Non-Fiction'),
(3, 'Science Fiction'),
(4, 'Mystery'),
(5, 'Biography'),
(6, 'History'),
(7, 'Self-Help'),
(8, 'Children’s Books'),
(9, 'Fantasy'),
(10, 'Technology');

INSERT INTO books (bookId, title, author, edition, genre, avail_status, cat_id)
VALUES
-- Fiction (Category 1)
(1, 'The Catcher in the Rye', 'J.D. Salinger', '1st', 'Fiction', 'Available', 1),
(2, 'To Kill a Mockingbird', 'Harper Lee', '2nd', 'Fiction', 'Checked Out', 1),
(3, 'Pride and Prejudice', 'Jane Austen', '5th', 'Fiction', 'Available', 1),
(4, 'The Great Gatsby', 'F. Scott Fitzgerald', '3rd', 'Fiction', 'Checked Out', 1),
(5, 'Moby Dick', 'Herman Melville', '2nd', 'Fiction', 'Available', 1),

-- Non-Fiction (Category 2)
(6, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', '1st', 'Non-Fiction', 'Available', 2),
(7, 'Becoming', 'Michelle Obama', '1st', 'Non-Fiction', 'Checked Out', 2),
(8, 'Educated', 'Tara Westover', '1st', 'Non-Fiction', 'Available', 2),
(9, 'The Immortal Life of Henrietta Lacks', 'Rebecca Skloot', '1st', 'Non-Fiction', 'Available', 2),
(10, 'The Wright Brothers', 'David McCullough', '1st', 'Non-Fiction', 'Checked Out', 2),

-- Science Fiction (Category 3)
(11, '1984', 'George Orwell', '1st', 'Science Fiction', 'Available', 3),
(12, 'Brave New World', 'Aldous Huxley', '2nd', 'Science Fiction', 'Checked Out', 3),
(13, 'Dune', 'Frank Herbert', '1st', 'Science Fiction', 'Available', 3),
(14, 'Fahrenheit 451', 'Ray Bradbury', '3rd', 'Science Fiction', 'Available', 3),
(15, 'Neuromancer', 'William Gibson', '1st', 'Science Fiction', 'Checked Out', 3),

-- Mystery (Category 4)
(16, 'Gone Girl', 'Gillian Flynn', '1st', 'Mystery', 'Available', 4),
(17, 'The Girl with the Dragon Tattoo', 'Stieg Larsson', '1st', 'Mystery', 'Checked Out', 4),
(18, 'The Da Vinci Code', 'Dan Brown', '2nd', 'Mystery', 'Available', 4),
(19, 'Big Little Lies', 'Liane Moriarty', '1st', 'Mystery', 'Checked Out', 4),
(20, 'Sherlock Holmes: The Complete Novels and Stories', 'Arthur Conan Doyle', '1st', 'Mystery', 'Available', 4),

-- Biography (Category 5)
(21, 'Steve Jobs', 'Walter Isaacson', '1st', 'Biography', 'Available', 5),
(22, 'The Diary of a Young Girl', 'Anne Frank', '1st', 'Biography', 'Checked Out', 5),
(23, 'Long Walk to Freedom', 'Nelson Mandela', '1st', 'Biography', 'Available', 5),
(24, 'Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future', 'Ashlee Vance', '1st', 'Biography', 'Available', 5),
(25, 'Alexander Hamilton', 'Ron Chernow', '1st', 'Biography', 'Checked Out', 5),

-- History (Category 6)
(26, 'Guns, Germs, and Steel', 'Jared Diamond', '1st', 'History', 'Available', 6),
(27, 'The Silk Roads: A New History of the World', 'Peter Frankopan', '1st', 'History', 'Checked Out', 6),
(28, 'A People’s History of the United States', 'Howard Zinn', '1st', 'History', 'Available', 6),
(29, 'The History of the Ancient World', 'Susan Wise Bauer', '1st', 'History', 'Available', 6),
(30, 'The Wright Brothers', 'David McCullough', '1st', 'History', 'Checked Out', 6),

-- Self-Help (Category 7)
(31, 'The Power of Habit', 'Charles Duhigg', '1st', 'Self-Help', 'Available', 7),
(32, 'Atomic Habits', 'James Clear', '1st', 'Self-Help', 'Checked Out', 7),
(33, 'The Subtle Art of Not Giving a F*ck', 'Mark Manson', '1st', 'Self-Help', 'Available', 7),
(34, 'How to Win Friends and Influence People', 'Dale Carnegie', '2nd', 'Self-Help', 'Checked Out', 7),
(35, 'The 7 Habits of Highly Effective People', 'Stephen Covey', '1st', 'Self-Help', 'Available', 7),

-- Children’s Books (Category 8)
(36, 'Harry Potter and the Philosopher’s Stone', 'J.K. Rowling', '1st', 'Children’s Books', 'Checked Out', 8),
(37, 'The Cat in the Hat', 'Dr. Seuss', '1st', 'Children’s Books', 'Available', 8),
(38, 'Charlie and the Chocolate Factory', 'Roald Dahl', '1st', 'Children’s Books', 'Available', 8),
(39, 'The Chronicles of Narnia', 'C.S. Lewis', '1st', 'Children’s Books', 'Available', 8),
(40, 'Where the Wild Things Are', 'Maurice Sendak', '1st', 'Children’s Books', 'Checked Out', 8),

-- Fantasy (Category 9)
(41, 'The Hobbit', 'J.R.R. Tolkien', '1st', 'Fantasy', 'Available', 9),
(42, 'A Game of Thrones', 'George R.R. Martin', '1st', 'Fantasy', 'Checked Out', 9),
(43, 'The Name of the Wind', 'Patrick Rothfuss', '1st', 'Fantasy', 'Available', 9),
(44, 'The Way of Kings', 'Brandon Sanderson', '1st', 'Fantasy', 'Available', 9),
(45, 'Mistborn: The Final Empire', 'Brandon Sanderson', '1st', 'Fantasy', 'Checked Out', 9),

-- Technology (Category 10)
(46, 'Clean Code', 'Robert C. Martin', '1st', 'Technology', 'Available', 10),
(47, 'The Pragmatic Programmer', 'Andrew Hunt', '1st', 'Technology', 'Checked Out', 10),
(48, 'Introduction to Algorithms', 'Thomas H. Cormen', '3rd', 'Technology', 'Available', 10),
(49, 'Code Complete', 'Steve McConnell', '2nd', 'Technology', 'Available', 10),
(50, 'Artificial Intelligence: A Modern Approach', 'Stuart Russell', '1st', 'Technology', 'Checked Out', 10);


drop table category;

create table books(
	bookId bigint primary key auto_increment,
    title varchar(160) not null,
	author varchar(100) not null,
    edition varchar(10) not null,
    genre varchar(50) not null,
    avail_status varchar(50) not null,
    cat_id int not null,
    foreign key (cat_id) references category(cat_id)
);
delete from books where bookId = 58;
select bookId from books where bookId = (select max(bookId) from books);
select count(bookId) from books;
select * from borrow;
SELECT b.bookId,b.title,c.cat_name,b.avail_status FROM books b join category c on b.cat_id=c.cat_id WHERE c.cat_id = 2;

create table borrow(
	borrow_id bigint primary key auto_increment,
    bookId bigint,
    userId bigint,
    borrow_date date,
    return_date date,
    foreign key (bookId) references books(bookId),
    foreign key (userId) references users(userId)
)auto_increment=4000;

drop table borrow;

insert into borrow (bookId,userId,borrow_date,return_date) values(
	2,202120,curdate(), date_add(curdate(), INTERVAL 1 month)
);

select * from login;   

use library;
-- query to select userName userPhone, book they borrowed using two joins
select u.userName, u.phone, b.title from users u join borrow br on u.userId=br.userId join books b on br.bookId = b.bookId;

update books set avail_status='Available' where bookId = 49;

create table fines(
	fine int,
    userId bigint,
    dueDate date,
    updatedAt date,
    foreign key (userId) references users(userId)
);
insert into fines value(0,202120);
update fines set fine=fine+10 where userId=202120;

create table login(
	email varchar(255),
    user_password varchar(50)
);

use library;
select * from borrow;
