/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(name = "EditProfileHelper", urlPatterns =
{
    "/EditProfileHelper"
})
public class EditProfileHelper extends HttpServlet
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
        
        String dataType = request.getParameter("typeOfData");
        String data = request.getParameter("dataToEdit");
        String profileId = request.getParameter("profileId");
        
        processData(dataType,data,profileId);
    }
    
    protected void processData(String dataType, String data, String profileId)
    {
        String query ="";
        Statement st = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            st = con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        if(dataType.equals("profileId"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET ProfileId = '" + data + "' " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("age"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET Age = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("ageRangeStart"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET AgeRangeStart = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("ageRangeEnd"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET AgeRangeEnd = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("geoRange"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET Age = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("hobbies"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET Hobbies = '" + data + "' " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("height"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET Height = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("weight"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET Weight = " + data + " " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        else if(dataType.equals("hairColor"))
        {
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET HairColor = '" + data + "' " 
                        + "WHERE ProfileId = '" + profileId + "'";
        }
        try
        {
            if(st == null)
            {
                throw new Exception();
            }
            st.executeUpdate(query);
            Date currTime = new Date();
            Timestamp modDate = new Timestamp(currTime.getTime());
            query = "UPDATE [MatchesFromAbove].[dbo].[Profile] "
                        + "SET ProfileModDate = '" + modDate + "' " 
                        + "WHERE ProfileId = '" + profileId + "'";
            st.executeUpdate(query);
        }
        catch(Exception e)
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
