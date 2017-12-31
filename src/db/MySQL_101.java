package db;
/**
 * This is a very simple example presenting how to work with MySQL using jave JDBC interface
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Statement;

public class MySQL_101 {

	  private static String _ip = "5.29.193.52";
	  private static String _url = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	  private static String _user = "oop2";
	  private static String _password = "Lambda2();";
	  private static Connection _con = null;
      
    public static void main(String[] args) {
    	int max_id = test_101();
    	insert_table(max_id);
    }
    public static int test_101() {
        Statement st = null;
        ResultSet rs = null;
        int max_id = -1;
        //String ip = "localhost";
       // String ip = "192.168.1.18";

        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
           
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM test101");
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	int id = rs.getInt(1);
            	if(id>max_id) {max_id=id;}
                System.out.print(id);
                System.out.print(": ");
                System.out.print(rs.getString(2));
                System.out.print(" (");
                double lat = rs.getDouble(3);
                System.out.print(lat);
                System.out.print(", ");
                double lon = rs.getDouble(4);
                System.out.print(lon);
                System.out.println(") ");
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return max_id;
    }
    
    
    public static void insert_table(int max_id) {
        Statement st = null;
        ResultSet rs = null;
        //String ip = "localhost";
       // String ip = "192.168.1.18";

        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            Date now = null;
            for(int i=0;i<3;i++) {
            	int curr_id = 1+i+max_id;
            	String str = "INSERT INTO test101 (ID,NAME,pos_lat,pos_lon, time, ap1, ap2, ap3) "
    + "VALUES ("+curr_id+",'test_name"+curr_id+"',"+(32+curr_id)+",35.01,"+now+",'mac1', 'mac2', 'mac3')";
            PreparedStatement pst = _con.prepareStatement(str);
            pst.execute();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }	
}