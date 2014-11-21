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
 * @author Javier
 */
@WebServlet(name = "getAllCustomers", urlPatterns =
{
    "/getAllCustomers"
})
public class getAllCustomers extends HttpServlet
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
        //json to pass back to our ajax request
        JSONArray jsonArray = new JSONArray();
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT * FROM [MatchesFromAbove].[dbo].[Customer] WHERE active=1";

            ResultSet rs = st.executeQuery(query);
            //loop through result set and create the json objects
            while (rs.next())
            {
                JSONObject customerToAdd = new JSONObject();
                customerToAdd.put("ssn", rs.getString("SSN"));
                customerToAdd.put("ppp", rs.getString("PPP"));
                customerToAdd.put("rating", rs.getInt("Rating"));
                customerToAdd.put("lastActiveDate", rs.getTimestamp("LastActive").toString());
                //add the json object that we're passing into the json array
                jsonArray.add(customerToAdd); 
            }
            //set the content type of our response
            response.setContentType("application/json");
            //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
            PrintWriter printout = response.getWriter();
            printout.print(jsonArray);
            printout.flush();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
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
