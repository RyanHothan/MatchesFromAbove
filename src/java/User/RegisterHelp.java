/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan Hothan
 */
@WebServlet(name = "RegisterHelp", urlPatterns =
{
    "/RegisterHelp"
})
public class RegisterHelp extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printout = response.getWriter();
        Person x = new Person();
        x.setEmail(request.getParameter("email"));
        x.setPassword(request.getParameter("password"));
        x.setFirstName(request.getParameter("firstname"));
        x.setLastName(request.getParameter("lastname"));
        x.setState(request.getParameter("state"));
        x.setStreet(request.getParameter("street"));
        x.setTelephone(request.getParameter("telephone"));
        x.setZipcode(request.getParameter("zip"));
        x.setCity(request.getParameter("city"));
        x.setSsn(request.getParameter("ssn"));
        
        if(!checkRegistrationCredentials(x))
        {
            printout.print("<script> alert(\"Registration Failed\");\nwindow.location='Register.jsp';</script>");
            printout.flush();
        }
        else
        {
            createNewUser(x);
            printout.print("<script> alert(\"Registration Successfull\");\nwindow.location='index.jsp';</script>");
            printout.flush();
        }
    }
    
    protected void createNewUser(Person p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();
            //format the telephone number
            String telephoneNumber = "(" + p.getTelephone().substring(0, 3)+") "
                    + p.getTelephone().substring(3, 6) +"-" + p.getTelephone().substring(6);
            //format the first name
            String firstName = p.getFirstName().substring(0, 1).toUpperCase() + p.getFirstName().substring(1);
            //format the last name
            String lastName = p.getLastName().substring(0, 1).toUpperCase() + p.getLastName().substring(1);
            //format the ssn
            String ssn = p.getSsn().substring(0, 3) + "-" + p.getSsn().substring(3, 5) 
                    + "-" +p.getSsn().substring(5);
            //format city name
            String city = p.getCity().substring(0, 1).toUpperCase() + p.getCity().substring(1);
            //timeofcreation
            Date currTime = new Date();
            Timestamp creationDate = new Timestamp(currTime.getTime());
            String query = "INSERT INTO [MatchesFromAbove].[dbo].[Person] "
                    + "VALUES('" + ssn + "', '" + p.getPassword() + "', '"
                    + firstName + "', '" + lastName + "','"+ p.getStreet()
                    + "','" + city + "','" + p.getState() + "'," + p.getZipcode()
                    + ",'" + p.getEmail() + "','" + telephoneNumber + "');\n"
                    + "INSERT INTO [MatchesFromAbove].[dbo].[Customer] "
                    + "VALUES('" + ssn + "', 'User-User', NULL, '" + creationDate +"'); ";

            st.executeUpdate(query);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
    }
    
    protected boolean checkRegistrationCredentials(Person p)
    {
        if(p.getTelephone().length() != 10
                    || p.getZipcode().length() != 5 || p.getCity().isEmpty() 
                    || p.getState().isEmpty() || p.getFirstName().isEmpty() 
                    || p.getLastName().isEmpty() || p.getPassword().isEmpty() 
                    || p.getStreet().isEmpty() || p.getZipcode().isEmpty() 
                    || p.getTelephone().isEmpty() || p.getEmail().isEmpty() 
                    || p.getSsn().isEmpty() || p.getSsn().length() != 9)
        {
            //bad info
            return false;
        }
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Person] "
                    + "WHERE Email = '" + p.getEmail() + "' OR SSN = '" + p.getSsn()
                    + "';";

            ResultSet rs = st.executeQuery(query);
            
            if (rs.next())
            {
                //Found a user that exists alrdy decline registration
                con.close();
                return false;
            }
            else
            {
                //No conflicts found continue registration
                con.close();
                return true;
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }  
        return true;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
