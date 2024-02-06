package lv.id.bure.www;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;

public class AboutFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public AboutFrame() {
		// Generated by window builder
		setTitle("About program");
		setBounds(100, 100, 395, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOkButton = new JButton("OK");
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close window by pressing OK button
				setVisible(false);
			}
		});
		btnOkButton.setBounds(10, 278, 89, 23);
		contentPane.add(btnOkButton);
		
		JLabel lblOneLabel = new JLabel("Investor's Clock version 1.09.", SwingConstants.CENTER);
		lblOneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOneLabel.setBounds(10, 11, 359, 28);
		contentPane.add(lblOneLabel);
		
		JLabel lblTwoLabel = new JLabel("This is HarvardX CS50x final project.");
		lblTwoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTwoLabel.setBounds(10, 49, 359, 23);
		contentPane.add(lblTwoLabel);
		
		final String ABOUT_STR =
		    "<html><p>The Investor's clock shows the time in different time zones. " + 
	        "This program was designed for Wall Street traders and investors. " +
		    "There are few clocks on the wall in the trading hall, each clock " + 
	        "face shows current time in the investing center of certain region. " +
		    "My program is useful for persons, trading stocks from home or small office. " +
	        "With this program it is easy to control different time zones.</p></html>";
		JLabel lblThreeLabel = new JLabel(ABOUT_STR);
		lblThreeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThreeLabel.setBounds(10, 73, 359, 138);
		contentPane.add(lblThreeLabel);
		
		JLabel lblFourLabel = new JLabel("<html><p>Open source code: <u>GitHub</u></p></html>");
		lblFourLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFourLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFourLabel.setBounds(10, 212, 359, 14);
		lblFourLabel.addMouseListener(new MouseAdapter() {
	        @Override
			public void mouseClicked(MouseEvent e) {
	        	openBrowserLink("http://github.com/");
	        }
	    });
		contentPane.add(lblFourLabel);
		
		JLabel lblFiveLabel = new JLabel("<html><p>Homepage: <u>SourceForge.net</u></p></html>");
		lblFiveLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFiveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFiveLabel.setBounds(10, 232, 359, 14);
		lblFiveLabel.addMouseListener(new MouseAdapter() {
	        @Override
			public void mouseClicked(MouseEvent e) {
	        	openBrowserLink("http://sourceforge.net/");
	        }
	    });
		contentPane.add(lblFiveLabel);
		
		JLabel lblSixLabel = new JLabel("Copyright (C) 2023-2024 Sergejs Bure.");
		lblSixLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSixLabel.setBounds(10, 252, 359, 14);
		contentPane.add(lblSixLabel);
	}
	
	private void openBrowserLink(String strURL) {
		// Implemented for Windows version only
        if (Desktop.isDesktopSupported()) {
            try {
				Desktop.getDesktop().browse(new URI(strURL));
			} catch (IOException ex) {
				Main.errorMessage("IOException. About window.");
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				Main.errorMessage("URISyntaxException. About window.");
				ex.printStackTrace();
			} catch (Exception ex) {
				Main.errorMessage("Unknown program error. About window.");
				ex.printStackTrace();
			}
        }
	}

}
