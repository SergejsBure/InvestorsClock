package lv.id.bure.www;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

// Main window menu listener implementation
class menuActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    	// Show necessary window
    	switch (actionEvent.getActionCommand()) {
    	    case MainFrame.MENU_OPTIONS_STR :
    	    	Main.optionsFrame.setVisible(true);
    	        break;
    	    case MainFrame.MENU_ABOUT_STR :
    	    	Main.aboutFrame.setVisible(true);
    	        break;
    	    case MainFrame.MENU_LICENSE_STR :
    	    	Main.licenseFrame.setVisible(true);
    	        break;
    	    case MainFrame.MENU_EXIT_STR :
    	    	System.exit(0);
    	        break;
    	}
    }
}

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	// Main frame menu items string constants
	static final String MENU_OPTIONS_STR = "Options...",
			            MENU_ABOUT_STR = "About Investor's Clock...",
			            MENU_LICENSE_STR = "The license...",
			            MENU_EXIT_STR = "Exit";
	
	public MainFrame() {
        // Main window
		setTitle("Investor's Clock");
		setBackground(new Color(238, 238, 238));
        getContentPane().setBackground(new Color(238, 238, 238));
        // Frame will be resizable, commented setResizable(false);
        
        // Main menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Main");
        menu.setMnemonic(KeyEvent.VK_M);
        JMenuItem menuItem = new JMenuItem(MENU_OPTIONS_STR, KeyEvent.VK_S);
        menuActionListener menuActList = new menuActionListener();
        menuItem.addActionListener(menuActList);
        menu.add(menuItem);
        menuItem = new JMenuItem(MENU_ABOUT_STR, KeyEvent.VK_A);
        menuItem.addActionListener(menuActList);
        menu.add(menuItem);
        menuItem = new JMenuItem(MENU_LICENSE_STR, KeyEvent.VK_L);
        menuItem.addActionListener(menuActList);
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem(MENU_EXIT_STR, KeyEvent.VK_X);
        menuItem.addActionListener(menuActList);
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        // Clock face draw area
        JComponent component = new DrawArea();
        component.setPreferredSize(new Dimension(540, 410));
        add(component);
        
        // Main window etc.
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
