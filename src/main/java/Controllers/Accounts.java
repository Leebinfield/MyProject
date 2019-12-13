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
            @FormDataParam("aid") Integer aid, @FormDataParam("firstname") String firstname, @FormDataParam("lastname") String lastname, @FormDataParam("email") String email, @FormDataParam("postcode")String postcode, @FormDataParam("aoth")Integer aoth) {
        try {
            if (aid == null || firstname == null || lastname == null || email == null || postcode == null || aoth==null) {
                throw new Exception("One or more form data parameters are missing in http request.");
            }
            System.out.println("thing/new id=" + aid);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Accounts (id,firstname,lastname,Email,postcode,AOTH) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, aid);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, email);
            ps.setString(5, postcode);
            ps.setInt(6, aoth);
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
            @FormDataParam("ID") Integer id,
            @FormDataParam("Firstname") String firstname,
            @FormDataParam("Lastname") String lastname,
            @FormDataParam("Email") String email,
            @FormDataParam("postcode") String postcode) {
        try {
            if (id == null ) {
                throw new Exception("One or more form data parameters are missing in http request.");
            }
            System.out.println("Account id to update:" + id);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Accounts SET Firstname=?,Lastname=?,Email=?,postcode=? WHERE ID = ?");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, postcode);
            ps.setInt(5, id);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, see server for more\"}";
        }
    }
}

