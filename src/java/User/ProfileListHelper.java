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
@WebServlet(urlPatterns =
{
    "/ProfileListHelper"
})
public class ProfileListHelper extends HttpServlet
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

        String profileId = request.getParameter("profileId");
        JSONArray jsons = new JSONArray();
        
        Profile p = getOurProfile(profileId, jsons);
        
        getReccomendedProfiles(p,jsons);
        PrintWriter printout = response.getWriter();
        printout.print(jsons);
        printout.flush();
    }

    protected Profile getOurProfile(String profileId, JSONArray jsons)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");

            Statement st = con.createStatement();

            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Profile] "
                    + "WHERE ProfileId = '" + profileId + "'";

            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                if (!rs.getBoolean("Active"))
                {
                    continue;
                }
                Profile p = new Profile();
                JSONObject profileToAdd = new JSONObject();
                profileToAdd.put("profileId", rs.getString("ProfileId"));
                p.setProfileId(rs.getString("ProfileId"));
                profileToAdd.put("age", rs.getString("Age"));
                p.setAge(rs.getInt("Age"));
                profileToAdd.put("ageRangeStart", rs.getString("AgeRangeStart"));
                p.setAgeRangeStart(rs.getInt("AgeRangeStart"));
                profileToAdd.put("ageRangeEnd", rs.getString("AgeRangeEnd"));
                p.setAgeRangeEnd(rs.getInt("AgeRangeEnd"));
                profileToAdd.put("geoRange", rs.getString("GeoRange"));
                p.setGeoRange(rs.getInt("GeoRange"));
                profileToAdd.put("gender", rs.getString("Gender"));
                p.setGender(rs.getString("Gender").charAt(0));
                profileToAdd.put("hobbies", rs.getString("Hobbies"));
                p.setHobbies(rs.getString("Hobbies"));
                profileToAdd.put("height", rs.getString("Height"));
                p.setHeight(rs.getInt("Height"));
                profileToAdd.put("weight", rs.getString("Weight"));
                p.setWeight(rs.getInt("Weight"));
                profileToAdd.put("hairColor", rs.getString("HairColor"));
                p.setHairColor(rs.getString("HairColor"));
                p.setSsn(rs.getString("OwnerSSN"));
                profileToAdd.put("profileCreationDate", rs.getString("ProfileCreationDate"));
                jsons.add(profileToAdd);
                return p;
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected void getReccomendedProfiles(Profile p, JSONArray jsons)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=nopw");
            char gender = ' ';
            Statement st = con.createStatement();
            if(p.getGender() == 'M')
            {
                gender = 'F';
            }
            else if(p.getGender() == 'F')
            {
                gender = 'M';
            }
            String query = "SELECT * "
                    + "FROM [MatchesFromAbove].[dbo].[Profile] "
                    + "WHERE OwnerSSN != '" + p.getSsn() + "' AND Gender = '" 
                    + gender + "';";

            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                if (!rs.getBoolean("Active"))
                {
                    continue;
                }
                JSONObject profileToAdd = new JSONObject();
                profileToAdd.put("profileId", rs.getString("ProfileId"));
                profileToAdd.put("age", rs.getString("Age"));
                profileToAdd.put("ageRangeStart", rs.getString("AgeRangeStart"));
                profileToAdd.put("ageRangeEnd", rs.getString("AgeRangeEnd"));
                profileToAdd.put("geoRange", rs.getString("GeoRange"));
                profileToAdd.put("gender", rs.getString("Gender"));
                profileToAdd.put("hobbies", rs.getString("Hobbies"));
                profileToAdd.put("height", rs.getString("Height"));
                profileToAdd.put("weight", rs.getString("Weight"));
                profileToAdd.put("hairColor", rs.getString("HairColor"));
                profileToAdd.put("profileCreationDate", rs.getString("ProfileCreationDate"));
                jsons.add(profileToAdd);
            }
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
