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
 * @author Javier
 */
@WebServlet(name = "makeReferral", urlPatterns =
{
    "/makeReferral"
})
public class makeReferral extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            Date currTime = new Date();
            Timestamp creationDate = new Timestamp(currTime.getTime());
            
            String checkIfProfileExists = "SELECT * FROM [MatchesFromAbove].[dbo].[Profile] WHERE ProfileId = '" + request.getParameter("profileA") + "'";
            ResultSet rs = st.executeQuery(checkIfProfileExists);
            if(!rs.next())
            {
                return;
            }
            checkIfProfileExists = "SELECT * FROM [MatchesFromAbove].[dbo].[Profile] WHERE ProfileId = '" + request.getParameter("profileB") + "'";
            rs = st.executeQuery(checkIfProfileExists);
            if(!rs.next())
            {
                return;
            }
            String firstQuery = "INSERT INTO [MatchesFromAbove].[dbo].[Referral] "
                    + "VALUES('" + request.getParameter("profileA")  + "', '" + request.getParameter("profileB") + "', '" + request.getParameter("profileC") + "', '" + creationDate + "')";
            
            st.executeUpdate(firstQuery);
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
