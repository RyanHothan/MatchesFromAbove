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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Ryan Hothan
 */
@WebServlet(name = "DateGetter", urlPatterns =
{
    "/DateGetter"
})
public class DateGetter extends HttpServlet
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
        response.setContentType("application/json");

        //create and initialize our profile object with passed in parameters
        String ssn = request.getParameter("SSN");
        String profileId = "";
        profileId = request.getParameter("profileId");
        JSONArray jsons = new JSONArray();
        getDates(ssn, jsons, profileId);
        PrintWriter printout = response.getWriter();
        printout.print(jsons);
    }

    protected void getDates(String SSN, JSONArray jsons, String profileId)
    {
 
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            //add the profile to DB
            
            String checkUserQuery = "SELECT SSN FROM [MatchesFromAbove].[dbo].[Employee] " 
                    + "WHERE SSN = '" + SSN + "'";
            
            ResultSet rs = st.executeQuery(checkUserQuery);
            String query;
            if(rs.next())
            {
                query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Date] "
                    + "WHERE CustomerRep = '" + SSN + "'";
            }
            else
            {
                query = "SELECT * "
                        + "FROM [MatchesFromAbove].[dbo].[Date] "
                        + "WHERE Profile1Id = '" + profileId + "' OR Profile2Id = '" + profileId + "'";
            }
                    
            
            
            
            System.out.println(query);

            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                JSONObject dateToAdd = new JSONObject();
                dateToAdd.put("profile1Id", rs.getString("Profile1Id"));
                dateToAdd.put("profile2Id", rs.getString("Profile2Id"));
                dateToAdd.put("dateTime", rs.getString("Date_Time"));
                dateToAdd.put("location", rs.getString("Location"));
                dateToAdd.put("fee", rs.getString("Fee"));
                dateToAdd.put("comments", rs.getString("Comments"));
                dateToAdd.put("user1Rating", rs.getString("User1Rating"));
                dateToAdd.put("user2Rating", rs.getString("User2Rating"));
                jsons.add(dateToAdd);
            }
            con.close();
        } catch (Exception e)
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
