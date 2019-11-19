package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.sql.PreparedStatement;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("accounts/")
public class Accounts {
    //public static Connection db = null;

    @POST
    @Path("Add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String AddAccount(
            @FormDataParam("Accid") Integer Accid, @FormDataParam("firstname") String firstname, @FormDataParam("lastname") String lastname) {
        try {
            if (Accid == null || firstname == null || lastname == null) {
                throw new Exception("One or more form data parameters are missing in http request.");
            }
            System.out.println("thing/new id=" + Accid);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Accounts (id,firstname,lastname) VALUES (?,?,?)");
            ps.setInt(1, Accid);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }

    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteAccount(@FormDataParam("id") Integer id) {
        try {
            if (id == null) {
                throw new Exception("One or more data para not done");
            }
            System.out.println("thing/delete id=" + id);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Accounts WHERE ID =?");
            ps.setInt(1, id);
            ps.execute();
            return "{\"status\":\"OK\"}";


        } catch (Exception exception) {
            System.out.println("DB error:" + exception.getMessage());
            return "{\"error\":\"Unable to delete item, please see server\"}";
        }
    }
    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String upAccount(
            @FormDataParam("Accid") Integer Accid, @FormDataParam("fname") String Firstname, @FormDataParam("lname") String Lastname,@FormDataParam("AccEmail") String email
            ,@FormDataParam("accpost") String posty) {
        try {
            if (Accid == null ) {
                throw new Exception("One or more form data parameters are missing in http request.");
            }
            System.out.println("Account id to update:" + Accid);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Accounts SET Firstname=?,Lastname=?,Email=?,postcode=? WHERE ID = ?");
            ps.setInt(5, Accid);
            ps.setString(1, Firstname);
            ps.setString(2, Lastname);
            ps.setString(3, email);
            ps.setString(4, posty);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, see server for more\"}";
        }
    }
}

