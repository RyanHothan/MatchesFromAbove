package User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan Hothan
 */
@WebServlet(urlPatterns =
{
    "/CreateProfileHelp"
})
public class CreateProfileHelp extends HttpServlet
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

        PrintWriter printout = response.getWriter();
        //create and initialize our profile object with passed in parameters
        Profile p = new Profile();
        p.setSsn(request.getParameter("ssn"));
            p.setAge(Integer.parseInt(request.getParameter("age")));
            p.setAgeRangeEnd(Integer.parseInt(request.getParameter("ageRangeEnd")));
            p.setAgeRangeStart(Integer.parseInt(request.getParameter("ageRangeStart")));
            p.setGender(request.getParameter("gender").toUpperCase().charAt(0));
            p.setGeoRange(Integer.parseInt(request.getParameter("geoRange")));
            p.setHairColor(request.getParameter("hairColor").substring(0, 1).toUpperCase() + request.getParameter("hairColor").substring(1));
            p.setHeight(Double.parseDouble(request.getParameter("height")));
            p.setWeight(Integer.parseInt(request.getParameter("weight")));
            p.setHobbies(request.getParameter("hobbies"));
            p.setProfileId(request.getParameter("profileId"));
            p.setProfilePicture(request.getParameter("profilePicture"));
            //Check the data inputed
            if (!checkProfileInfo(p))
            {
                //bad info redirect to error page
                printout.print("False");
            } else
            {
                //good info create new profile and add to data base
                createProfile(p);
                printout.print("True");
            }
    }

    protected void createProfile(Profile p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            //timeofcreation
            Date currTime = new Date();
            Timestamp creationDate = new Timestamp(currTime.getTime());

            //add the profile to DB
            String query = "INSERT INTO [MatchesFromAbove].[dbo].[Profile] "
                    + "VALUES('" + p.getProfileId() + "', '" + p.getSsn() + "', "
                    + p.getAge() + ", " + p.getAgeRangeStart() + ", " + p.getAgeRangeEnd()
                    + ", " + p.getGeoRange() + ", '" + p.getGender() + "', '" + p.getHobbies()
                    + "', " + p.getHeight() + ", " + p.getWeight() + ", '" + p.getHairColor()
                    + "', '" + creationDate + "', '" + creationDate + "', " 
                    + "1, '" + p.getProfilePicture() + "')";
            System.out.println(query);
            st.executeUpdate(query);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
    }

    protected boolean checkProfileInfo(Profile p)
    {
        if (p.getAge() <= 17 || p.getAge() > 120 || p.getAgeRangeStart() < 17
                || p.getAgeRangeEnd() < p.getAgeRangeStart() || p.getGeoRange() < 0
                || (p.getGender() != 'M' && p.getGender() != 'F') || p.getHairColor().isEmpty()
                || p.getHeight() <= 3 || p.getHeight() >= 8.5 || p.getHobbies().isEmpty()
                || p.getWeight() <= 80 || p.getWeight() >= 500 || p.getProfileId().isEmpty()
                || p.getSsn().isEmpty() || p.getSsn().length() != 11)
        {
            //bad info
            return false;
        }
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();
            //query to see if profile exists already
            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Profile] "
                    + "WHERE ProfileId = '" + p.getProfileId() + "';";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            if (rs.next())
            {
                //Found a user that exists alrdy decline registration
                con.close();
                return false;
            } else
            {
                //No conflicts found continue registration
                con.close();
                return true;
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return true;
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
