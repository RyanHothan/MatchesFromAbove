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
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan
 */
@WebServlet(name = "DateHelper", urlPatterns = {"/DateHelper"})
public class DateHelper extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String profileA = request.getParameter("profileA");
        String profileB = request.getParameter("profileB");
        
        addDate(profileA, profileB, request);
    }

    protected void addDate(String profileA, String profileB, HttpServletRequest request)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
    ResultSet.CONCUR_READ_ONLY);
            
            String firstQuery = "SELECT SSN FROM [MatchesFromAbove].[dbo].[Employee]";
            ResultSet rs = st.executeQuery(firstQuery);
            rs.last();
            int setLength = rs.getRow();
            int randomEmployee = (int)(Math.random()*setLength);
            rs.absolute(randomEmployee);
            String randomEmployeeSSN = rs.getString("SSN");
            //timeofcreation
            Calendar someDate = Calendar.getInstance();
            int year = Integer.parseInt(request.getParameter("year"));
            int month = Integer.parseInt(request.getParameter("month"));
            int day = Integer.parseInt(request.getParameter("day"));
            int hour = Integer.parseInt(request.getParameter("hour"));
            int minute = Integer.parseInt(request.getParameter("minute"));
            someDate.set(year, month , day, hour, minute);
            Timestamp stamp = new Timestamp(someDate.getTimeInMillis());
            //add the profile to DB
            String query = "INSERT INTO [MatchesFromAbove].[dbo].[Date] " 
                    + "VALUES('"  + profileA + "', '" + profileB +  "', '" + randomEmployeeSSN + "', '" + stamp + "','" + request.getParameter("location") 
                    + "', 25.50, '" + request.getParameter("comments") + "', 5, 5 )" ;
            System.out.println(query);
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
