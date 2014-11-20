/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
        
        String url;
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
        x.setSSN(request.getParameter("ssn"));
        
        if(!checkRegistrationCredentials(x))
        {
            url = "RegistrationFailed.jsp";
        }
        else
        {
            url = "successfulRegistration.jsp";
            createNewUser(x);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    protected void createNewUser(Person p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "INSERT INTO [MatchesFromAbove].[dbo].[Person] "
                    + "VALUES('" + p.getSSN() + "', '"+p.getPassword() + "', '"
                    + p.getFirstName() + "', '" + p.getLastName() + "','"+ p.getStreet()
                    + "','" + p.getCity() + "','" + p.getState() + "'," + p.getZipcode()
                    + ",'" + p.getEmail() + "','" + p.getTelephone() + "')";

            st.executeUpdate(query);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
    }
    
    protected boolean checkRegistrationCredentials(Person p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Person] "
                    + "WHERE Email = '" + p.getEmail() + "'";

            ResultSet rs = st.executeQuery(query);

            if (rs.next())
            {
                con.close();
                return false;
            }
            else
            {
                con.close();
                return true;
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
        return false;
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
