package sample;


import java.io.Serializable;

public class Book implements Serializable {

    private String bookTitle;
    private String author;
    private String publisher;
    private String genre;
    private String year;

    public Book(String bookTitle, String author, String publisher, String genre, String year) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.year = year;
    }

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre= genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book Title: " + bookTitle + "\nAuthor: " + author +"\nPublisher: " + publisher + "\nGenre: " + genre + "\nYear: " + year;
    }
}
