/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Javier
 */
public class EditCustomer extends HttpServlet
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

        String dataType = request.getParameter("typeOfData");
        String data = request.getParameter("thingToEdit");

        String customerSSN = request.getParameter("customer");
        if (dataType.equals("ssn"))
        {
            String ssn;
            if (data.charAt(3) != '-' || data.charAt(6) != '-')
            {
                ssn = data.substring(0, 3) + "-" + data.substring(3, 5)
                        + "-" + data.substring(5);
            } else
            {
                ssn = data;
            }
            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

                Statement st = con.createStatement();

                String query = "UPDATE [MatchesFromAbove].[dbo].[Person] "
                        + "SET SSN = '" + ssn + "' " 
                        + "WHERE SSN = '" + customerSSN + "'";

                st.executeUpdate(query);
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        if (dataType.equals("rating"))
        {
            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

                Statement st = con.createStatement();

                String query = "UPDATE [MatchesFromAbove].[dbo].[Customer] "
                        + "SET Rating = '" + data + "' " 
                        + "WHERE SSN = '" + customerSSN + "'";

                st.executeUpdate(query);
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
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
