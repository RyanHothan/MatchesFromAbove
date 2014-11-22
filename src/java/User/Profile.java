/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Timestamp;

/**
 *
 * @author Ryan Hothan
 */
public class Profile extends Customer
{

    private String profileId;
    private int age;
    private int ageRangeStart;
    private int ageRangeEnd;
    private int geoRange;
    private char gender;
    private String hobbies;
    private double height;
    private int weight;
    private String hairColor;
    private Timestamp profileCreationDate;
    private Timestamp profileModDate;
    
    public Profile(){}

    public String getProfileId()
    {
        return profileId;
    }

    public void setProfileId(String profileId)
    {
        this.profileId = profileId;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAgeRangeStart()
    {
        return ageRangeStart;
    }

    public void setAgeRangeStart(int ageRangeStart)
    {
        this.ageRangeStart = ageRangeStart;
    }

    public int getAgeRangeEnd()
    {
        return ageRangeEnd;
    }

    public void setAgeRangeEnd(int ageRangeEnd)
    {
        this.ageRangeEnd = ageRangeEnd;
    }

    public int getGeoRange()
    {
        return geoRange;
    }

    public void setGeoRange(int geoRange)
    {
        this.geoRange = geoRange;
    }

    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }

    public String getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(String hobbies)
    {
        this.hobbies = hobbies;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public String getHairColor()
    {
        return hairColor;
    }

    public void setHairColor(String hairColor)
    {
        this.hairColor = hairColor;
    }

    public Timestamp getProfileCreationDate()
    {
        return profileCreationDate;
    }

    public void setProfileCreationDate(Timestamp profileCreationDate)
    {
        this.profileCreationDate = profileCreationDate;
    }

    public Timestamp getProfileModDate()
    {
        return profileModDate;
    }

    public void setProfileModDate(Timestamp profileModDate)
    {
        this.profileModDate = profileModDate;
    }
    
    
}
