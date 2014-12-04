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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw;allowMultiQueries=true");

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

                ResultSet rs = st.executeQuery("                          SELECT [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN, SUM([MatchesFromAbove].[dbo].[DATE].Fee) AS sumFee "
                        + "                           FROM [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[DATE] "
                        + "                           WHERE [MatchesFromAbove].[dbo].[DATE].Profile1Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId "
                        + "                           GROUP BY OwnerSSN ");

                

                
                Map<String, Integer> my = new HashMap<String, Integer>();

                
                while (rs.next()){
                    System.out.println("tack");
                    if (!my.containsKey(rs.getString("OwnerSSN"))){
                        my.put(rs.getString("OwnerSSN"),rs.getInt("sumFee"));
                    }else{
                        my.put(rs.getString("OwnerSSN"),(rs.getInt("sumFee"))+my.get(rs.getString("OwnerSSN")));
                    
                    }
                    System.out.println("2");
                }
                ResultSet rs1 = st.executeQuery("                          SELECT [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN, SUM([MatchesFromAbove].[dbo].[DATE].Fee) AS sumFee "
                        + "                           FROM [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[DATE] "
                        + "                           WHERE [MatchesFromAbove].[dbo].[DATE].Profile2Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId "
                        + "                           GROUP BY OwnerSSN ");
                System.out.println("lalaland");
                if (rs1 != null)
                while (rs1.next()){
                   System.out.println("tack");
                    if (!my.containsKey(rs1.getString("OwnerSSN"))){
                        my.put(rs1.getString("OwnerSSN"),rs1.getInt("sumFee"));
                    }else{
                        my.put(rs1.getString("OwnerSSN"),(rs1.getInt("sumFee"))+my.get(rs1.getString("OwnerSSN")));
                    
                    }
                    System.out.println("2");
                }
                String ssn = "";
                int max = 0; 
                Iterator it = my.entrySet().iterator();
                while (it.hasNext()) {
                    
                    Map.Entry pairs = (Map.Entry)it.next();
                    System.out.println(pairs.getKey()+" "+pairs.getValue());
                    if ((Integer)pairs.getValue() > max){
                        ssn = (String)pairs.getKey();
                        max = (Integer) pairs.getValue(); 
                    
                    }
                    it.remove(); // avoids a ConcurrentModificationException
                }
                
                String query = "SELECT * FROM PERSON WHERE SSN = '" + ssn + "'";
                    ResultSet t = st.executeQuery(query);
                    t.next();
                String name = t.getString("FirstName")+" "+ t.getString("LastName");
                System.out.println(my);

                //set the content type of our response
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
                printout.print("NAME: "+name+" Revenue Generated: $"+max);
                printout.flush();
            }

            
            
            
            
            
            
            
            
            
            
            if (request.getParameter("func").equals("bestRatedCust")) {

                String query = "SELECT * FROM Customer C WHERE Rating > 3";
                ResultSet rs = st.executeQuery(query);
                response.setContentType("text/html");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();

                //loop through result set and create the json objects
                Double max = 0.0;
                String name = "";
                String rating = "";
                while (rs.next()) {
                    name = rs.getString("SSN");
                    rating = rs.getString("Rating");
                    String query2 = "SELECT * FROM PERSON WHERE SSN = '" + name + "'";
                    ResultSet rs2 = st.executeQuery(query2);
                    while (rs2.next()) {
                        name = (rs2).getString("FirstName") + " " + (rs2).getString("LastName");
                        printout.print("<p>NAME: " + name + "        Rating for Customer is: " + rating + "</p>");

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
                    if (i == 4) {
                        break;
                    }
                    printout.print("<p>RANK: " + i + "________ DAY FOR DATE: " + rs.getString("Date").substring(5) + "________ SCORE FOR THIS DAY: " + rs.getString("Score"));

                }

                //set the content type of our response
                printout.flush();
            }

            if (request.getParameter("func").equals("mostActCust")) {
                
                
                String query; 
                query = "SELECT [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN, COUNT(*) AS numDates\n"
                        + "		FROM  [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[DATE]\n"
                        + "		WHERE [MatchesFromAbove].[dbo].[DATE].Profile1Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId\n"
                        + "		GROUP BY OwnerSSN\n"
                        + "		UNION ALL\n"
                        + "		SELECT [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN, COUNT(*) AS numDates\n"
                        + "		FROM [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[DATE]\n"
                        + "		WHERE [MatchesFromAbove].[dbo].[DATE].Profile2Id = [MatchesFromAbove].[dbo].[PROFILE].ProfileId\n"
                        + "		GROUP BY OwnerSSN";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                Map<String, Integer> my = new HashMap<String, Integer>();

                
                while (rs.next()){
                    if (!my.containsKey(rs.getString("OwnerSSN"))){
                        my.put(rs.getString("OwnerSSN"),1);
                    }else{
                        my.put(rs.getString("OwnerSSN"),(Integer)my.get(rs.getString("OwnerSSN"))+1);
                    
                    }
                    
                }
                System.out.println(my);

               
                query = "SELECT [MatchesFromAbove].[dbo].[PROFILE].OwnerSSN, COUNT(*) AS numLikes\n"
                        + "		FROM  [MatchesFromAbove].[dbo].[PROFILE], [MatchesFromAbove].[dbo].[LIKES]\n"
                        + "		WHERE [MatchesFromAbove].[dbo].[LIKES].LikerId = [MatchesFromAbove].[dbo].[PROFILE].ProfileId\n"
                        + "		GROUP BY OwnerSSN";
                
                rs = st.executeQuery(query);
                 while (rs.next()){
                    if (!my.containsKey(rs.getString("OwnerSSN"))){
                        my.put(rs.getString("OwnerSSN"),1);
                    }else{
                        my.put(rs.getString("OwnerSSN"),(Integer)my.get(rs.getString("OwnerSSN"))+1);
                    
                    }
                    
                }
    
                response.setContentType("application/json");
                //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
                PrintWriter printout = response.getWriter();
//                String s = " buns"; 
//                while (rs.next()) {
//                    System.out.println("bub");
//                       s = s+"  "+ rs.getString("OwnerSSN");
//                   
//                }
                
                Iterator it = my.entrySet().iterator();
                while (it.hasNext()) {
                    
                    Map.Entry pairs = (Map.Entry)it.next();
                    
                    JSONObject dateToAdd = new JSONObject();
                    query = "SELECT * FROM PERSON WHERE SSN = '" + pairs.getKey() + "'";
                    ResultSet t = st.executeQuery(query);
                    t.next();
                    
                    dateToAdd.put("name",t.getString("FirstName") +" "+t.getString("LastName") );
                    dateToAdd.put("level", pairs.getValue());
                    if( (Integer)pairs.getValue() > 1)
                                    jsonArray.add(dateToAdd);

                    it.remove(); // avoids a ConcurrentModificationException
                }
                
                printout.print(jsonArray);

                //set the content type of our response
                printout.flush();
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + "managerFuncetionsClass");
            if(con != null)            
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(managerFunctions.class.getName()).log(Level.SEVERE, null, ex);
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
