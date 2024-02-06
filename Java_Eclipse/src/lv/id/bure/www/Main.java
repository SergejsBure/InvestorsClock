package lv.id.bure.www;

import java.awt.Component;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

class programThread extends Thread {
    @Override
    public void run() {
        while(true) {
            try {
            	// Wait a half-second
                Thread.sleep(500);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            // Repaint every second
            if (!LocalDateTime.now().withNano(0).isEqual(Main.currentTime.withNano(0))) {
            	// Add date to the form title
        		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        		Main.mainFrame.setTitle("Investor's Clock - " + dtf.format(LocalDate.now()));
                // Painting the clock face
                for (Component selectedComponent : Main.mainFrame.getComponents()) {
                    if (selectedComponent instanceof JComponent) {
                        selectedComponent.repaint();
                    }
                }
            }
            // Garbage collector every 10 minutes 
            if (LocalDateTime.now().getMinute() % 10 == 0
                && !LocalDateTime.now().truncatedTo(
            	   ChronoUnit.MINUTES).isEqual(
            	     Main.garbageCollectorTime.truncatedTo(ChronoUnit.MINUTES))) {
            	    System.gc();
            	    Main.garbageCollectorTime = LocalDateTime.now(); 
            }
            Main.currentTime = LocalDateTime.now();
        }
    }
}

public class Main {
	
	static LocalDateTime currentTime, garbageCollectorTime;
	static String[] cities;
	static byte[] timeZones; 
	
	static public MainFrame mainFrame;
	static public OptionsFrame optionsFrame;
	static public LicenseFrame licenseFrame;
	static public AboutFrame aboutFrame;
	
	public static void main(String[] args) {
		currentTime = LocalDateTime.now();
		garbageCollectorTime = LocalDateTime.now();
        
		// Main window
		mainFrame = new MainFrame();
		
		// Options window
		optionsFrame = new OptionsFrame();
        
        // License window
		licenseFrame = new LicenseFrame();
        
        // About window
		aboutFrame = new AboutFrame();

		// Loading parameters from DB
        cities = new String[6];
        timeZones = new byte[6];
        // Creating data.db on the fist run
        File file = new File(DB.getFileName());
        if (!file.exists() && !DB.create()) {
        	Main.errorMessage("Database file \"data.db\" is missing. Create procedure.");
        }
        // Loading from DB or default values
        if (!DB.load()) {
        	// Set default values
            cities = new String[] {"Riga", "London", "Paris", "New York", "Los Angeles", "Tokyo"};
            timeZones = new byte[] {0, -2, -1, -7, -10, 7};
        }
        // Setup option window elements, load from main class variables
		optionsFrame.setOptionsFrame();        
        
		// Loading icon
		URL iconURL = Main.class.getResource("JavaIcon.png");
		// iconURL is null when not found
		if (iconURL != null) {
		    ImageIcon icon = new ImageIcon(iconURL);
		    // set icon for all windows
		    mainFrame.setIconImage(icon.getImage());
		    optionsFrame.setIconImage(icon.getImage());
		    licenseFrame.setIconImage(icon.getImage());
		    aboutFrame.setIconImage(icon.getImage());
		}
		
		// Repaint thread
        programThread ClockEngine = new programThread();
        ClockEngine.setPriority(Thread.MAX_PRIORITY);
        ClockEngine.start();
        
        // Show main form
		mainFrame.setVisible(true);
	}
	
	public static void errorMessage(String str) {
		JOptionPane.showMessageDialog(null, "Investor's Clock - " + str, "Program error", JOptionPane.ERROR_MESSAGE);
	}

}
