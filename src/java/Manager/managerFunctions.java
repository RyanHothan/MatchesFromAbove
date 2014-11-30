/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Manager;

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
 * @author Acer
 */
@WebServlet(name = "managerFunctions", urlPatterns = {"/managerFunctions"})
public class managerFunctions extends HttpServlet {

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

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw;allowMultiQueries=true");

            Statement st = con.createStatement();
                if (request.getParameter("func").equals("getRev")){
                String query = "SELECT * FROM [MatchesFromAbove].[dbo].[DATE] WHERE DATEPART(month, DATE_TIME) = "+request.getParameter("month") + " AND DATEPART(year, DATE_TIME) = "+request.getParameter("year");

                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                while (rs.next())
                {
                    JSONObject dateToAdd = new JSONObject();
                    dateToAdd.put("fee", rs.getString("Fee"));
                    dateToAdd.put("time", rs.getDate("Date_Time").toString());
                    //add the json object that we're passing into the json array
                    jsonArray.add(dateToAdd); 
                }
                //set the content type of our response
                response.setContentType("application/json");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print(jsonArray);
                printout.flush();
            }   
                
                if (request.getParameter("func").equals("getRevByDate")){
                double totalRev = 0;     
                String [] arrr = request.getParameter("date").split("/");
                String query = "SELECT * FROM [MatchesFromAbove].[dbo].[DATE] WHERE DATEPART(month, DATE_TIME) = "+ arrr[0]+ " AND DATEPART(year, DATE_TIME) = "+arrr[2] + " AND DATEPART(day, DATE_TIME) = "+arrr[1];

                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                
                while (rs.next())
                {   
                    totalRev = totalRev + Double.parseDouble(rs.getString("Fee"));
                    JSONObject dateToAdd = new JSONObject();
                    dateToAdd.put("fee", rs.getString("Fee"));
                    dateToAdd.put("time", rs.getDate("Date_Time").toString());
                    //add the json object that we're passing into the json array
                    jsonArray.add(dateToAdd); 
                }
                JSONObject dateToAdd = new JSONObject();
                dateToAdd.put("total", totalRev);
                jsonArray.add(dateToAdd); 
                //set the content type of our response
                response.setContentType("application/json");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print(jsonArray);
                printout.flush();
            }
                
                
                if (request.getParameter("func").equals("getRevBySSN")){
                double totalRev = 0;     
                String ssn = request.getParameter("SSN");
                String query = "[MatchesFromAbove].[dbo].[DATE].Fee, [MatchesFromAbove].[dbo].[DATE].Date_Time"
                        + " FROM [MatchesFromAbove].[dbo].[DATE], [MatchesFromAbove].[dbo].[CUSTOMER], [MatchesFromAbove].[dbo].[PROFILE] "
                        + "WHERE ([MatchesFromAbove].[dbo].[DATE].Profile1Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId AND [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN = [MatchesFromAbove].[dbo].[CUSTOMER].SSN AND [MatchesFromAbove].[dbo].[CUSTOMER].SSN =" +ssn+") "
                        + "OR ([MatchesFromAbove].[dbo].[DATE].Profile2Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId AND [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN = SSN AND [MatchesFromAbove].[dbo].[CUSTOMER].SSN = "+ssn+")";
                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                
                while (rs.next())
                {   
                    totalRev = totalRev + Double.parseDouble(rs.getString("Fee"));
                    JSONObject dateToAdd = new JSONObject();
                    dateToAdd.put("fee", rs.getString("Fee"));
                    dateToAdd.put("time", rs.getDate("Date_Time").toString());
                    //add the json object that we're passing into the json array
                    jsonArray.add(dateToAdd); 
                }
                JSONObject dateToAdd = new JSONObject();
                dateToAdd.put("total", totalRev);
                jsonArray.add(dateToAdd); 
                //set the content type of our response
                response.setContentType("application/json");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print(jsonArray);
                printout.flush();
            }
                
                if (request.getParameter("func").equals("getBestRep")){
                double totalRev = 0;     
                String query = "SELECT CustomerRep, MAX(sumFee) AS maxFee " +
"FROM (" +
"SELECT [MatchesFromAbove].[dbo].[DATE].CustomerRep, SUM([MatchesFromAbove].[dbo].[DATE].Fee) AS sumFee" +
"FROM [MatchesFromAbove].[dbo].[DATE]" +
"GROUP BY [MatchesFromAbove].[dbo].[DATE].CustomerRep" +
") first_group";
                        ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                
                while (rs.next())
                {   
                   
                }
                JSONObject dateToAdd = new JSONObject();
                dateToAdd.put("total", totalRev);
                jsonArray.add(dateToAdd); 
                //set the content type of our response
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print("boys");
                printout.flush();
            }
            con.close();
            
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
