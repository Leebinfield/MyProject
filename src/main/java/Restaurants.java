import java.sql.PreparedStatement;

public class Restaurants {
    public static void listRestaurants(){
        try{
            PreparedStatement ps  = Main.db.prepareStatement("SELECT Rest");
        }
        catch{

        }
    }
}
