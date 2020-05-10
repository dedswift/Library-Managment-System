package sample;

import java.io.Serializable;

public class Client extends Person implements Serializable {


    private Book loanedBook;
    private String dueDate;
    private String id;

    public Client(String firstName, String lastName, String address, String gender) {
        super(firstName, lastName, address, gender);
    }

    public Book getLoanedBook() {
        return loanedBook;
    }

    public void setLoanedBook(Book loanedBook) {
        this.loanedBook = loanedBook;
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return loanedBook != null ? super.toString() + "\nBook: " + loanedBook.getBookTitle() + '\n' : super.toString();
    }


}
