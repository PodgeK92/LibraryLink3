-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jul 05, 2024 at 07:16 PM
-- Server version: 5.7.39
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LibraryLink3`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `email`, `password`, `first_name`, `last_name`, `role`, `enabled`) VALUES
('admin', 'admin@example.com', 'adminpassword', 'Admin', 'User', 'ROLE_ADMIN', 1),
('ChristyMoore', 'ChristyMoore@gmail.com', 'Hello24', 'Christy', 'Moore', 'ROLE_ADMIN', 1),
('PodgeK92', 'podgek92@gmail.com', 'Hello2024!', 'Padraig', 'Keane', 'ROLE_ADMIN', 1);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `isbn` varchar(17) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_published` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`isbn`, `author`, `format`, `genre`, `title`, `year_published`, `status`) VALUES
('9780007322602', 'J. R. R. Tolkien', 'Paperback', 'Young Adult Fiction', 'The Hobbit', 2009, 'Unavailable'),
('9780060731335', 'Gabriel Garcia Marquez', 'Paperback', 'Magic Realism', 'One Hundred Years of Solitude', 1967, 'Available'),
('9780201530827', 'J.R.R. Tolkien', 'Audio', 'Fantasy', 'The Hobbit', 1937, 'Available'),
('9780307956157', 'Leo Tolstoy', 'Audio', 'Historical Fiction', 'War and Peace', 1869, 'Available'),
('9780345534835', 'J.R.R. Tolkien', 'Paperback', 'Fiction', 'The Hobbit (Movie Tie-in Edition)', 2012, 'Available'),
('9780394823754', 'Aldous Huxley', 'Hardcover', 'Dystopian', 'Brave New World', 1932, 'Available'),
('9780399150841', 'Herman Melville', 'Hardcover', 'Adventure', 'Moby Dick', 1851, 'Available'),
('9780446674237', 'Mark Twain', 'Ebook', 'Adventure', 'The Adventures of Huckleberry Finn', 1884, 'Available'),
('9780596520687', 'J.K. Rowling', 'Ebook', 'Fantasy', 'Harry Potter and the Philosopher\'s Stone', 1997, 'Available'),
('9780743273595', 'Jane Austen', 'Paperback', 'Romance', 'Pride and Prejudice', 1813, 'Available'),
('9780743273596', 'George Orwell', 'Paperback', 'Political Fiction', 'Animal Farm', 1945, 'Available'),
('9780743273597', 'Homer', 'Paperback', 'Epic', 'The Odyssey', -800, 'Available'),
('9780743273598', 'Dante Alighieri', 'Ebook', 'Epic', 'The Divine Comedy', 1320, 'Available'),
('9780743273599', 'Fyodor Dostoevsky', 'Audio', 'Philosophical', 'Crime and Punishment', 1866, 'Available'),
('9780743273600', 'Leo Tolstoy', 'Hardcover', 'Romance', 'Anna Karenina', 1877, 'Available'),
('9780743273601', 'Charles Dickens', 'Paperback', 'Historical Fiction', 'A Tale of Two Cities', 1859, 'Available'),
('9780743273602', 'Ernest Hemingway', 'Ebook', 'Fiction', 'The Old Man and the Sea', 1952, 'Available'),
('9780743273603', 'Mary Shelley', 'Audio', 'Science Fiction', 'Frankenstein', 1818, 'Available'),
('9780743273604', 'Miguel de Cervantes', 'Hardcover', 'Adventure', 'Don Quixote', 1605, 'Available'),
('9780743273606', 'William Shakespeare', 'Ebook', 'Tragedy', 'Hamlet', 1600, 'Available'),
('9780743273607', 'George Orwell', 'Audio', 'Dystopian', '1984', 1949, 'Available'),
('9780743273608', 'Victor Hugo', 'Hardcover', 'Historical Fiction', 'Les Misérables', 1862, 'Available'),
('9780743273609', 'J.D. Salinger', 'Paperback', 'Fiction', 'The Catcher in the Rye', 1951, 'Available'),
('9781402894626', 'Harper Lee', 'Paperback', 'Fiction', 'To Kill a Mockingbird', 1960, 'Unavailable');

-- --------------------------------------------------------

--
-- Table structure for table `book_loans`
--

CREATE TABLE `book_loans` (
  `book_loan_id` int(11) NOT NULL,
  `isbn` varchar(17) DEFAULT NULL,
  `card_number_id` varchar(255) NOT NULL,
  `loan_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `renewals_number` int(11) DEFAULT '0',
  `returned_on_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book_loans`
--

INSERT INTO `book_loans` (`book_loan_id`, `isbn`, `card_number_id`, `loan_date`, `return_date`, `renewals_number`, `returned_on_date`) VALUES
(2, '9780007322602', 'G84035192', '2024-07-04', '2024-07-18', 0, '2024-07-04'),
(3, '9780007322602', 'G84035192', '2024-07-05', '2024-07-19', 0, NULL),
(4, '9781402894626', 'G84035192', '2024-07-05', '2024-07-19', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `library_user`
--

CREATE TABLE `library_user` (
  `card_number_id` varchar(255) NOT NULL,
  `county` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `home_branch` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `material_quantity` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `town_city` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `library_user`
--

INSERT INTO `library_user` (`card_number_id`, `county`, `dob`, `email`, `first_name`, `home_branch`, `last_name`, `material_quantity`, `password`, `phone_number`, `street_name`, `town_city`) VALUES
('G32154261', 'Kerry', '1992-03-15', 'new@gmail.com', 'New', 'Galway City Library', 'New', 0, 'Hello2024', '0877641021', 'Coilagurteen', 'Listowel'),
('G84035192', 'Kerry', '1992-03-15', 'podgek92@gmail.com', 'Padraig', 'Galway City Library', 'Keane', 0, 'Hello2024', '0877641021', 'Coilagurteen', 'Moyvane');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `book_loans`
--
ALTER TABLE `book_loans`
  ADD PRIMARY KEY (`book_loan_id`),
  ADD KEY `isbn` (`isbn`),
  ADD KEY `card_number_id` (`card_number_id`);

--
-- Indexes for table `library_user`
--
ALTER TABLE `library_user`
  ADD PRIMARY KEY (`card_number_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book_loans`
--
ALTER TABLE `book_loans`
  MODIFY `book_loan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book_loans`
--
ALTER TABLE `book_loans`
  ADD CONSTRAINT `book_loans_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  ADD CONSTRAINT `book_loans_ibfk_2` FOREIGN KEY (`card_number_id`) REFERENCES `library_user` (`card_number_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
