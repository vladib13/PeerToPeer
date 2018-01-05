package peer.modelo;

/**
 *
 * @author vladimir
 */
import java.sql.*;
/*import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;*/

public class BDDManager {

    private final String DR = "jdbc:postgresql://localhost:5432/ViUcab";

    //private Log log = LogFactory.getLog(BDDManager.class);
    private Connection con=null;
    private Statement stmt=null;
    private ResultSet rs=null;

    public BDDManager(){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection( DR,"Vladimir","purrunga13");
            stmt = con.createStatement();
        }
        catch (ClassNotFoundException e){
            //log.error(e);
        }
        catch (SQLException ex){
            //log.error(ex);
        }
    }
    public String query (){

        String consulta ="";

        return consulta;
    }
    public void update (){
    }
    public void delete(){

    }
    public void insert (){

    }
}
