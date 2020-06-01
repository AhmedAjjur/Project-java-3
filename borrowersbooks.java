/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.sql.Date;

/**
 *
 * @author compulife
 */
public class borrowersbooks {
    
    Integer bookID;
    Integer borrowerID;
    Date borrowerDate;
    Date returnDate;

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(Integer borrowerID) {
        this.borrowerID = borrowerID;
    }

    public Date getBorrowerDate() {
        return borrowerDate;
    }

    public void setBorrowerDate(Date borrowerDate) {
        this.borrowerDate = borrowerDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    
}
