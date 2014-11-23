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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Javier
 */
@WebServlet(urlPatterns =
{
    "/CreateAccountHelper"
})
public class CreateAccountHelper extends HttpServlet
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
        JSONArray jsons = new JSONArray();
        JSONObject json = new JSONObject();
        String ccn = request.getParameter("ccn");
        String ssn = request.getParameter("ssn"); 
        long time = System.currentTimeMillis();
        int newAccountNum = 0;
        boolean accountNumInDatabase = true;
        //this loop creates a random number and makes sure its not used in our database
        while(accountNumInDatabase)
        {
            newAccountNum = (int)(Math.random()*99999)+10000;
            if(checkAccountNumberAvailability(newAccountNum))
                accountNumInDatabase = false;

        }
        json.put("accountNumber", newAccountNum);
        jsons.add(json);
        java.sql.Date currentDate = new java.sql.Date(time);
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();
            
            String query = "INSERT INTO [MatchesFromAbove].[dbo].[Account]  "
                    + "VALUES('" + ssn + "', '" + ccn + "', '" + newAccountNum 
                    + "', '" + currentDate + "', 1)";
            st.executeUpdate(query);
            


        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        PrintWriter printout = response.getWriter();
            printout.print(jsons);
            printout.flush();
    }
    
    protected boolean checkAccountNumberAvailability(int number)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();
            
            String query;
            query = "SELECT * " +
                    "FROM [MatchesFromAbove].[dbo].[Account] " + 
                    "WHERE AccountNumber = " + number;
            
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
            
                    
            
            

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
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
