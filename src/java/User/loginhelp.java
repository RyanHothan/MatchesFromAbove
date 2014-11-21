package User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Javier
 */
@WebServlet(name = "loginhelp", urlPatterns =
        {
            "/loginhelp"
        })
public class loginhelp extends HttpServlet
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
        /* TODO output your page here. You may use following sample code. */
        String url;
        String email = request.getParameter("email");
        String pw = request.getParameter("password");
        Person x = new Person();
        x.setEmail(email);
        x.setPassword(pw);
        if (!checkLoginCredentials(x))
        {
            url = "loginFailed.jsp";

        } else
        {
            getPersonInformation(x);

            request.setAttribute("currentUser", x);
            if (isEmployee(x))
            {
                 url = "/employeeHome.jsp";
            } 
            else
            {
                ArrayList<Profile> profiles = getProfiles(x);
                request.setAttribute("profiles", profiles);
                url = "/userHome.jsp";
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected boolean checkLoginCredentials(Person p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Person] "
                    + "WHERE Email = '" + p.getEmail() + "' AND Password = '" + p.getPassword() + "'";

            ResultSet rs = st.executeQuery(query);

            if (rs.next())
            {
                con.close();
                return true;
            } else
            {
                con.close();
                return false;
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
        return false;
    }

    protected boolean isEmployee(Person p)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT SSN "
                    + "FROM [MatchesFromAbove].[dbo].[Employee] "
                    + "WHERE SSN = '" + p.getSsn() + "'";

            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                return true;
            }
            return false;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    protected static boolean getPersonInformation(Person p)
    {

        ArrayList returnString = new ArrayList<String>();
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT SSN, Password, FirstName, LastName, Street, City, State, ZipCode, Email, Telephone  "
                    + "FROM [MatchesFromAbove].[dbo].[Person] "
                    + "WHERE Email = '" + p.getEmail() + "' AND Password = '" + p.getPassword() + "'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                p.setFirstName(rs.getString("FirstName"));
                p.setLastName(rs.getString("LastName"));
                p.setSsn(rs.getString("SSN"));
                p.setStreet(rs.getString("Street"));
                p.setCity(rs.getString("City"));
                p.setState(rs.getString("State"));
                p.setZipcode(rs.getString("ZipCode"));
                p.setTelephone(rs.getString("Telephone"));
            }

            con.close();
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    //Get a list of profiles that belong to person P
    protected static ArrayList<Profile> getProfiles(Person p)
    {
        //our return array
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        
        try
        {
            //get DB driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //create connection to DB
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");
            //statement to be passed to DB
            Statement st = con.createStatement();
            //Query to the DB (Finding all profiles whose ownerSSN is our person's)
            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Profile] "
                    + "WHERE OwnerSSN = '" + p.getSsn() + "'";
            //print query for testing
            System.out.println(query);
            //REsult set for our results
            ResultSet rs = st.executeQuery(query);
            
            //loop until we have no more results
            while (rs.next())
            {
                if(!rs.getBoolean("Active"))
                {
                    continue;
                }
                //Add each profile to our return array
                Profile profileToAdd = new Profile(p.getSsn());
                profileToAdd.setAge(rs.getInt("Age"));
                profileToAdd.setAgeRangeEnd(rs.getInt("AgeRangeEnd"));
                profileToAdd.setAgeRangeStart(rs.getInt("AgeRangeStart"));
                profileToAdd.setGeoRange(rs.getInt("GeoRange"));
                profileToAdd.setWeight(rs.getInt("Weight"));
                profileToAdd.setGender(rs.getString("Gender").charAt(0));
                profileToAdd.setProfileId(rs.getString("ProfileId"));
                profileToAdd.setHairColor(rs.getString("HairColor"));
                profileToAdd.setHobbies(rs.getString("Hobbies"));
                profileToAdd.setHeight(rs.getDouble("Height"));
                profileToAdd.setProfileCreationDate(rs.getTimestamp("ProfileCreationDate"));
                profileToAdd.setProfileModDate(rs.getTimestamp("ProfileModDate"));
                profiles.add(profileToAdd);
            }
            //close the connection
            con.close();
            //return the array filled with profiles
            return profiles;
        }
        //trying to catch exception for sql errors
        catch(Exception e)
        {
            //print our message
            System.out.println(e.getMessage());
            return null;
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
