package lv.id.bure.www;

import java.io.File;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.sqlite.SQLiteException;

public class DB {

	public static boolean load() {
		// Check if data.db is existing
        File file = new File(DB.getFileName());
        if (!file.exists()) {
        	Main.errorMessage("Database file \"data.db\" is missing. Load procedure.");
        	return false;
        }
        // Connecting to DB
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:" + DB.getFileName());
            stmt = conn.createStatement();
            // Executing the select
            String sql = "SELECT city, timezone FROM parameters ORDER BY group_value;";
		    ResultSet resultSet = stmt.executeQuery(sql);
		    // Loading SQL result into variables
		    byte i = 0;
            while ( resultSet.next() ) {
            	Main.cities[i] = resultSet.getString("city");
            	Main.timeZones[i] = resultSet.getByte("timezone");
		        i++;
		    }
        // Close connections
		resultSet.close();
		stmt.close();
		conn.close();
	    } catch (SQLiteException ex) {
	    	Main.errorMessage("Error in database. Load procedure.");
	    	ex.printStackTrace();
	    	return false;
		} catch (Exception ex) {
			Main.errorMessage("Unknown error in database. Load procedure.");
			ex.printStackTrace();
		    return false;
		}
		return true;
	}
	
	public static void save() {
		// Check if data.db is existing
        File file = new File(DB.getFileName());
        if (!file.exists()) {
        	Main.errorMessage("Database file \"data.db\" is missing. Save procedure.");
        	return;
        }
        // Connecting to DB
        Connection conn = null;
        Statement stmt = null;
	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DB.getFileName());
	        conn.setAutoCommit(false);
	        stmt = conn.createStatement();
	        // Execute multiple update SQL statements
	        for (byte i = 0; i < 6; i++) {
	        	String sql = "UPDATE parameters SET city = \"" + Main.cities[i] +
	        			     "\", timezone = "+ Main.timeZones[i] +
	        			     " WHERE group_value = " + i + ";";
		        stmt.executeUpdate(sql);
	        }
	        // Close connections
            stmt.close();
	        conn.commit();
	        conn.close();
	    } catch (SQLiteException ex) {
	    	Main.errorMessage("Error in database. Save procedure.");
	    	ex.printStackTrace();
	    	return;
	    } catch (Exception ex) {
	    	Main.errorMessage("Unknown error in database. Save procedure.");
		    ex.printStackTrace();
		    return;
	    }
	}
	
	public static boolean create() {
		// Create directory for Windows version
		if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
		    File theDirectory = new File(DB.getDirectory());
		    if (!theDirectory.exists()) theDirectory.mkdirs();
		}
        // Connecting to DB
        Connection conn = null;
        Statement stmt = null;
	    try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:" + DB.getFileName());
	        conn.setAutoCommit(false);
	        stmt = conn.createStatement();
	        // Execute create table
	        String sql = "CREATE TABLE IF NOT EXISTS parameters " + 
	                     "(city TEXT NOT NULL, timezone INT NOT NULL, group_value INT NOT NULL);";
		    stmt.executeUpdate(sql);
		    // Clear table if not empty
		    sql = "SELECT COUNT(*) AS count FROM parameters;";
		    ResultSet resultSet = stmt.executeQuery(sql);
		    if (resultSet.getInt("count") != 0) {
			    sql = "DELETE FROM parameters;";
			    stmt.executeUpdate(sql);
		    }
		    // Execute insert with sample data
		    final String constSql = "INSERT INTO parameters (city, timezone, group_value) VALUES ";
		    sql = constSql + "(\"Riga\", 0, 0);";
		    stmt.executeUpdate(sql);
		    sql = constSql + "(\"Geneva\", -1, 1);";
		    stmt.executeUpdate(sql);
		    sql = constSql + "(\"New York\", -7, 2);";
		    stmt.executeUpdate(sql);
		    sql = constSql + "(\"London\", -2, 3);";
		    stmt.executeUpdate(sql);
		    sql = constSql + "(\"Paris\", -1, 4);";
		    stmt.executeUpdate(sql);
		    sql = constSql + "(\"Tokyo\", 7, 5);";
		    stmt.executeUpdate(sql);
	        // Close connections
            stmt.close();
	        conn.commit();
	        conn.close();
	    } catch (SQLiteException ex) {
	    	Main.errorMessage("Error in database. Create procedure.");
	    	ex.printStackTrace();
	    	return false;
	    } catch (Exception ex) {
	    	Main.errorMessage("Unknown error in database. Create procedure.");
		    ex.printStackTrace();
		    return false;
	    }
		return true;
	}
	
	public static String getFileName() {
		// if running on windows
		if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
			return DB.getDirectory() + FileSystems.getDefault().getSeparator() + "data.db";
		// other OS
	    } else {
	        return "data.db";
	    }
	}
	
	private static String getDirectory() {
		return System.getenv("LOCALAPPDATA") + FileSystems.getDefault().getSeparator() + "Investors Clock";
	}

}
