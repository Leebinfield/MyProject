package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Restaurant/")
public class Restaurants {
    @GET
    @Path("listRest")
    @Produces(MediaType.APPLICATION_JSON)
    public String listRestaurants(){
        System.out.println("restaurants/listRest");
        JSONArray listRest = new JSONArray();
        try{
            PreparedStatement ps  = Main.db.prepareStatement("SELECT RestID,price,postcode, Rname  FROM Restaurant");
            ResultSet results = ps.executeQuery();
            while(results.next()){
                JSONObject item = new JSONObject();
                System.out.println(results.getString(4));
                item.put("RestaurantID",results.getInt(1));
                item.put("Price",results.getInt(2));
                item.put("Postcode",results.getInt(3));
                item.put("Restaurant Name",results.getString(4));
                listRest.add(item);
            }
            return listRest.toString();
        }
        catch (Exception exception){
            System.out.println("DB error"+exception.getMessage());
            return "{\"error\":\"Unable to list items, please see sever console for more info.\"}";
        }


    }
        public static void insertRest(int Price, String Postcode,int ID) {
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO main.Restaurant(price, postcode, RestID) VALUES (?,?,?)");
                ps.setInt(1,Price);
                ps.setString(2, Postcode);
                ps.setInt(3, ID);
                ps.execute();
            }catch (Exception exception){
                System.out.println("DB error:" + exception.getMessage() );
            }
        }

        @POST
        @Path("add")
        @Produces(MediaType.APPLICATION_JSON)
        public String addRestaurant(){

            return "";
        }
    }


