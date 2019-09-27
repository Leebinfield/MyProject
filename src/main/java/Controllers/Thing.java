package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("thing/")
public class Thing {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String newListRestaurants() {
        System.out.println("things/list");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT RestID,price,postcode,Name FROM main.Restaurant");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("id", results.getInt(1));
                item.put("price range", results.getInt(2));
                item.put("Postcode", results.getInt(3));
                item.put("Name", results.getInt(4));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("DB error" + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console.\"}";
        }

    }
}


