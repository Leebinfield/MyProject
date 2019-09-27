package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Restaurants {
    public static void listRestaurants(){
        try{
            PreparedStatement ps  = Main.db.prepareStatement("SELECT RestID,price,postcode,Name FROM main.Restaurant");
            ResultSet results = ps.executeQuery();
            while(results.next()){
                int id = results.getInt(1);
                String priceRate = results.getString(2);
                String postcodee = results.getString(3);
                String Rname = results.getString(4);
                System.out.println("ID:" + id +", Price:" + priceRate + ", Postcode:"+postcodee);
            }
        }
        catch (Exception exception){
            System.out.println("DB error"+exception.getMessage());
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
    }


