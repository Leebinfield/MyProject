import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Restaurants {
    public static void listRestaurants(){
        try{
            PreparedStatement ps  = Main.db.prepareStatement("SELECT RestID");
            ResultSet results = ps.executeQuery();
            while(results.next()){
                int id = results.getInt(1);
                System.out.println("ID:" + id +",");
            }
        }
        catch (Exception exception){
            System.out.println("DB error"+exception.getMessage());
        }


    }
        public static void insertRest(String Price, String Postcode) {
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO main.Restaurant(price, postcode) VALUES (?,?)");
                ps.setString(1,Price);
                ps.setString(2, Postcode);
                ps.execute();
            }catch (Exception exception){
                System.out.println("DB error:" + exception.getMessage() );
            }
        }


        }


