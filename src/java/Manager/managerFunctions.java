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
            throws ServletException, IOException {
        //json to pass back to our ajax request
        JSONArray jsonArray = new JSONArray();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw;allowMultiQueries=true");

            Statement st = con.createStatement();
            if (request.getParameter("func").equals("getRev")) {
                String query = "SELECT * FROM [MatchesFromAbove].[dbo].[DATE] WHERE DATEPART(month, DATE_TIME) = " + request.getParameter("month") + " AND DATEPART(year, DATE_TIME) = " + request.getParameter("year");

                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                while (rs.next()) {
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
                System.out.println("rev generated");
            }

            if (request.getParameter("func").equals("getRevByDate")) {
                double totalRev = 0;
                String[] arrr = request.getParameter("date").split("/");
                String query = "SELECT * FROM [MatchesFromAbove].[dbo].[DATE] WHERE DATEPART(month, DATE_TIME) = " + arrr[0] + " AND DATEPART(year, DATE_TIME) = " + arrr[2] + " AND DATEPART(day, DATE_TIME) = " + arrr[1];
                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                while (rs.next()) {
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

            if (request.getParameter("func").equals("getRevBySSN")) {
                double totalRev = 0;
                String ssn = request.getParameter("SSN");
                String query = "SELECT *"
                        + "FROM [MatchesFromAbove].[dbo].[DATE],[MatchesFromAbove].[dbo].[Profile]"
                        + "WHERE ([MatchesFromAbove].[dbo].[DATE].Profile1Id = [MatchesFromAbove].[dbo].[Profile].ProfileId AND [MatchesFromAbove].[dbo].[Profile].OwnerSSN ='" + ssn + "') OR "
                        + "([MatchesFromAbove].[dbo].[DATE].Profile2Id = [MatchesFromAbove].[dbo].[Profile].ProfileId AND [MatchesFromAbove].[dbo].[Profile].OwnerSSN ='" + ssn + "')";

                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                while (rs.next()) {
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

            if (request.getParameter("func").equals("getBestRep")) {
                try {
                    st.execute("CREATE VIEW booob AS SELECT [MatchesFromAbove].[dbo].[DATE].CustomerRep, SUM([MatchesFromAbove].[dbo].[DATE].Fee) AS sumFee FROM [MatchesFromAbove].[dbo].[DATE] GROUP BY [MatchesFromAbove].[dbo].[DATE].CustomerRep");
                } catch (Exception e) {
                    System.out.println("view exists ");
                    st.execute("DROP VIEW booob");
                    st.execute("CREATE VIEW booob AS SELECT [MatchesFromAbove].[dbo].[DATE].CustomerRep, SUM([MatchesFromAbove].[dbo].[DATE].Fee) AS sumFee FROM [MatchesFromAbove].[dbo].[DATE] GROUP BY [MatchesFromAbove].[dbo].[DATE].CustomerRep");

                }
                String query = "SELECT * FROM booob";
                ResultSet rs = st.executeQuery(query);

                //loop through result set and create the json objects
                Double max = 0.0;
                String name = "";
                while (rs.next()) {
                    double b = rs.getDouble("sumFee");
                    System.out.println(b + " " + rs.getString("CustomerRep"));
                    if (b > max) {
                        max = b;
                        name = rs.getString("CustomerRep");
                    }

                }
                String query2 = "SELECT * FROM PERSON WHERE SSN = '" + name + "'";
                ResultSet rs2 = st.executeQuery(query2);
                while (rs2.next()) {
                    name = (rs2).getString("FirstName") + " " + (rs2).getString("LastName");
                }
                //set the content type of our response
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print("NAME: " + name + "        Revenue Generated: $" + (long) (max * 100 + 0.5) / 100.0);
                printout.flush();
            }

            if (request.getParameter("func").equals("getBestCust")) {

                try {
                    st.execute("CREATE VIEW Y AS "
                            + "                           SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee "
                            + "                           FROM Profile P, Date D "
                            + "                           WHERE D.Profile2Id = P.ProfileId "
                            + "                           GROUP BY OwnerSSN ");
                } catch (Exception e) {
                    System.out.println("view exists");
                    st.execute("DROP VIEW Y");
                    st.execute("CREATE VIEW Y AS "
                            + "                           SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee "
                            + "                           FROM Profile P, Date D "
                            + "                           WHERE D.Profile2Id = P.ProfileId "
                            + "                           GROUP BY OwnerSSN ");

                }

                try {
                    st.execute("CREATE VIEW X AS "
                            + "                           SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee "
                            + "                           FROM Profile P, Date D "
                            + "                           WHERE D.Profile1Id = P.ProfileId "
                            + "                           GROUP BY OwnerSSN ");
                } catch (Exception e) {
                    System.out.println("view exists");
                    st.execute("DROP VIEW X");
                    st.execute("CREATE VIEW X AS "
                            + "                           SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee "
                            + "                           FROM Profile P, Date D "
                            + "                           WHERE D.Profile1Id = P.ProfileId "
                            + "                           GROUP BY OwnerSSN ");

                }
                try {
                    st.execute("CREATE VIEW NA AS ((SELECT * FROM X) UNION (SELECT * FROM Y))");
                } catch (Exception e) {
                    System.out.println("view exists");
                    st.execute("DROP VIEW NA");
                    st.execute("CREATE VIEW NA AS ((SELECT * FROM X) UNION (SELECT * FROM Y))");

                }

                String query = "SELECT  OwnerSSN, SUM(sumFee) as total FROM NA GROUP BY OwnerSSN";
                ResultSet rs = st.executeQuery(query);
                System.out.println("buttercup");

                while (rs.next()) {
                    System.out.println("--");

                }

                //set the content type of our response
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print("NAME: Revenue Generated: $");
                printout.flush();
            }

            if (request.getParameter("func").equals("bestRatedCust")) {

                String query = "SELECT * FROM Customer C WHERE C.Rating > 3";
                ResultSet rs = st.executeQuery(query);
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();

                //loop through result set and create the json objects
                Double max = 0.0;
                String name = "";
                String rating="";
                while (rs.next()) {
                    name = rs.getString("SSN");
                    rating = rs.getString("Rating");
                    String query2 = "SELECT * FROM PERSON WHERE SSN = '" + name + "'";
                    ResultSet rs2 = st.executeQuery(query2);
                    while (rs2.next()) {
                        name = (rs2).getString("FirstName") + " " + (rs2).getString("LastName");
                        printout.print("<p>NAME: " + name + "        Rating for Customer is: " + rating+"</p>");

                    }

                }
            }
                
                if (request.getParameter("func").equals("bestDateDays")) {
                String query = "SELECT CAST([MatchesFromAbove].[dbo].[DATE].Date_Time AS DATE) as Date, SUM([MatchesFromAbove].[dbo].[DATE].User1Rating+[MatchesFromAbove].[dbo].[DATE].User2Rating) as Score FROM [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[DATE] GROUP BY CAST([MatchesFromAbove].[dbo].[DATE].Date_Time AS DATE) HAVING (SUM([MatchesFromAbove].[dbo].[DATE].User1Rating+[MatchesFromAbove].[dbo].[DATE].User2Rating) >= 1) ORDER BY Score DESC";
                ResultSet rs = st.executeQuery(query);
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                int i = 0; 
                while (rs.next()) {
                    i++; 
                    if (i == 4){
                        break; 
                    }
                    printout.print("<p>RANK: "+i+ "________ DAY FOR DATE: "+rs.getString("Date").substring(5)+"________ SCORE FOR THIS DAY: "+rs.getString("Score"));
                
                }
                    

                

                //set the content type of our response
                printout.flush();
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + "managerFuncetionsClass");
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
