/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Ryan Hothan
 */
public class Account extends Person
{     
    String creditCardNumber;
    int accountNumber;
    Date accountCreationDate;
    public Account(){}

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public Date getAccountCreationDate()
    {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate)
    {
        this.accountCreationDate = accountCreationDate;
    }
}
