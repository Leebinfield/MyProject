package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.sqlite.SQLiteConfig;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.*;
import org.sqlite.SQLiteConfig;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("accounts/")
public class Accounts {
    //public static Connection db = null;
    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddA(
            @FormDataParam("id") Integer id, @FormDataParam("firstname") String firstname, @FormDataParam("lastname") String lastname){
        try{
            if (id == null || firstname == null || lastname == null ){
                throw new Exception("One or more form data parameters are missing in http request.");
            }
            System.out.println("thing/new id=" +id);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Accounts (id,firstname,lastname) VALUES (?,?,?)");
            ps.setInt(1,id);
            ps.setString(2,firstname);
            ps.setString(3,lastname);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }


}
