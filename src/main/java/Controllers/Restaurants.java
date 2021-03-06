package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.validation.constraints.FutureOrPresent;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

@Path("Restaurant/")
public class Restaurants {
    @GET
    @Path("listRest")
    @Produces(MediaType.APPLICATION_JSON)
    public String listRestaurants() {
        System.out.println("restaurants/listRest");
        JSONArray listRest = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT RestID,price,postcode, Rname  FROM Restaurant");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                System.out.println(results.getString(4));
                item.put("RestaurantID", results.getInt(1));
                item.put("Price", results.getInt(2));
                item.put("Postcode", results.getInt(3));
                item.put("Restaurant Name", results.getString(4));
                listRest.add(item);
            }
            return listRest.toString();
        } catch (Exception exception) {
            System.out.println("DB error" + exception.getMessage());
            return "{\"error\":\"Unable to list items, please see server console for more info.\"}";
        }

    }

    public static void insertRest(int Price, String Postcode, int ID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO main.Restaurant(price, postcode, RestID) VALUES (?,?,?)");
            ps.setInt(1, Price);
            ps.setString(2, Postcode);
            ps.setInt(3, ID);
            ps.execute();
        } catch (Exception exception) {
            System.out.println("DB error:" + exception.getMessage());
        }
    }

    @POST
    @Path("addR")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String addRestaurant(
            @FormDataParam("id") Integer id, @FormDataParam("name") String Name, @FormDataParam("postcode") String postcode, @FormDataParam("price") Integer price) {
        try {
            if (id == null || Name == null || postcode == null || price == null) {
                throw new Exception("One or more fields empty in HTTP request");
            }
            System.out.println("rest/new id =" + id);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Restaurant (RestID,Rname,postcode,price) VALUES (?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, Name);
            ps.setString(3, postcode);
            ps.setInt(4, price);
            ps.execute();
            return "{\"status\":\"Item added\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }
    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateRestaurant(
            @FormDataParam("id") Integer id, @FormDataParam("rname") String rname, @FormDataParam("Posty") String Posty, @FormDataParam("pricing") Integer pricing){
        try{
            if (id==null){
                throw new Exception("Parameters missing.");
            }
            Scanner scanner = new Scanner(System.in); //Scanner is an instance which reads the input of the users.
            System.out.println("thing/update id=" + id);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Restaurant SET Rname = ?, postcode =?, price =? WHERE RestID = ?");
            ps.setString(1,rname);
            ps.setString(2,Posty);
            ps.setInt(3,pricing);
            ps.setInt(4, id);
            ps.execute();
            return "{\"status\":\"Item updated\"}";

        }catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }

    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteRestaurant(@FormDataParam("restID") Integer restID) {
        try {
            if (restID == null) {
                throw new Exception("One or more data para not done");
            }
            System.out.println("Restaurant ID=" + restID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Restaurant WHERE RestID =?");
            ps.setInt(1, restID);
            ps.execute();
            return "{\"status\":\"OK\"}";


        } catch (Exception exception) {
            System.out.println("DB error:" + exception.getMessage());
            return "{\"error\": \"Unable to create new item, see server for more\"}";
        }
    }
}













