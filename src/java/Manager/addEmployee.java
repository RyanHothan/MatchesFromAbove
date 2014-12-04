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
import java.sql.Timestamp;
import java.util.Date;
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
@WebServlet(name = "addEmployee", urlPatterns = {"/addEmployee"})
public class addEmployee extends HttpServlet {

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
        PrintWriter printout = response.getWriter();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();
            String SSN = request.getParameter("SSN");
            String Password = request.getParameter("Password");
            String FirstName = request.getParameter("FirstName");
            String LastName = request.getParameter("LastName");
            String Street = request.getParameter("Street");
            String City = request.getParameter("City");
            String State = request.getParameter("State");
            String ZipCode = request.getParameter("ZipCode");
            String Email = request.getParameter("Email");
            String Telephone = request.getParameter("Telephone");
            String Role = request.getParameter("Role");
            String Rate = request.getParameter("Rate");
            Date currTime = new Date();
            Timestamp creationDate = new Timestamp(currTime.getTime());

            String query = "SELECT * FROM PERSON WHERE SSN = '" + SSN + "'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            
            if (!rs.next()) {
                query = "INSERT INTO [MatchesFromAbove].[dbo].[Person] "
                    + "VALUES('" + SSN + "', '" + Password + "', '"
                    + FirstName + "', '" + LastName + "','"+ Street
                    + "','" + City + "','" + State + "'," + ZipCode
                    + ",'" + Email+ "','" + Telephone + "');\n"
                    + "INSERT INTO [MatchesFromAbove].[dbo].[Employee] "
                    + "VALUES('" + SSN + "', 'User-User', '"+creationDate+"',"+Rate+", 1); ";
                System.out.println(query);
                st.executeUpdate(query);
                
            } else {
                
                query = "INSERT INTO [MatchesFromAbove].[dbo].[Employee] "
                    + "VALUES('" + SSN + "', 'User-User', '"+creationDate+"',"+Rate+", 1); ";
                System.out.println(query);
                st.executeUpdate(query);
            }

            //set the content type of our response
            response.setContentType("application/json");
            //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.
            printout.print("success");
            printout.flush();
        } catch (Exception e) {
            printout.print("failure");
            System.out.println("NO IDEA - - - "+e);
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
