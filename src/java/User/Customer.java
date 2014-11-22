/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;
import java.sql.Timestamp;

/**
 *
 * @author Javier
 */
public class Customer extends Person
{
    private String ppp;
    private int rating;
    private Timestamp lastActiveDate;

    public Customer(){}

    public String getPpp()
    {
        return ppp;
    }

    public void setPpp(String ppp)
    {
        this.ppp = ppp;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public Timestamp getLastActiveDate()
    {
        return lastActiveDate;
    }

    public void setLastActiveDate(Timestamp lastActiveDate)
    {
        this.lastActiveDate = lastActiveDate;
    }

}
