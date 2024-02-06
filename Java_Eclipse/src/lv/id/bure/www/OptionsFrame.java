package lv.id.bure.www;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class OptionsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> textFieldList;
	private ArrayList<JComboBox<String>> comboBoxList;
	
	public void setOptionsFrame() {
		// Setup option window elements, load from main class
		// city names
		byte i = 0;
		for (JTextField lst : this.textFieldList) {
			lst.setText(Main.cities[i]);
			i++;
		}
		// time zones
		i = 0;
		for (JComboBox<String> lst : this.comboBoxList) {
			lst.setSelectedIndex(Main.timeZones[i] + 11);
			i++;
		}		
	}
	
	private void pressOk () {
		// Set main form parameters
		// city names
		byte i = 0;
		for (JTextField lst : this.textFieldList) {
			String inputText = lst.getText();
			// Remove quotes
			inputText = inputText.replaceAll("\"", "");
			inputText = inputText.replaceAll("'", "");
			// Max length is 23 symbols
			inputText = inputText.substring(0, Math.min(inputText.length(), 23));
			Main.cities[i] = inputText;
			i++;
		}
		// time zones
		i = 0;
		for (JComboBox<String> lst : this.comboBoxList) {
			Main.timeZones[i] = (byte) (lst.getSelectedIndex() - 11);
			i++;
		}
		// save on disk, send to DB
		DB.save();
	}

	public OptionsFrame() {
		// constants: strings and array of strings
		final String HOURS_ARRAY[] = {"-11", "-10", "-9", "-8", "-7", "-6", "-5", "-4", "-3", "-2", "-1", "0",
				                      "+1", "+2", "+3", "+4", "+5", "+6", "+7", "+8", "+9", "+10", "+11"};
		final String CITY_STRING = "City:",
                     TIME_STRING = "Time:",
                     HOURS_STRING = "hour(s)";
		
		// Generated by window builder
		setTitle("Options");
		setBackground(new Color(238, 238, 238));
		setBounds(100, 100, 615, 263);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOkButton = new JButton("OK");
		btnOkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Set main form parameters
				pressOk();
				// Close window by pressing OK button
				setVisible(false);
			}
		});
		btnOkButton.setBounds(10, 189, 89, 23);
		contentPane.add(btnOkButton);
		
		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Setup option window elements, load from main class
				setOptionsFrame();
				// Close window by pressing Cancel button
				setVisible(false);
			}
		});
		btnCancelButton.setBounds(107, 189, 89, 23);
		contentPane.add(btnCancelButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
		    new TitledBorder(
		    new EtchedBorder(
		        EtchedBorder.LOWERED,
		        new Color(255, 255, 255),
		        new Color(160, 160, 160)),
		        "1",
		        TitledBorder.LEADING,
		        TitledBorder.TOP,
		        null,
		        new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 186, 78);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCityLabel = new JLabel(CITY_STRING);
		lblCityLabel.setBounds(10, 22, 48, 14);
		panel_1.add(lblCityLabel);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(56, 19, 120, 20);
		panel_1.add(textField_1);
		
		JLabel lblTimeLabel = new JLabel(TIME_STRING);
		lblTimeLabel.setBounds(10, 47, 48, 14);
		panel_1.add(lblTimeLabel);

		JComboBox<String> comboBox_1 = new JComboBox<>(HOURS_ARRAY);
		comboBox_1.setBounds(56, 43, 65, 22);
		panel_1.add(comboBox_1);
		
		JLabel lblHoursLabel = new JLabel(HOURS_STRING);
		lblHoursLabel.setBounds(131, 47, 46, 14);
		panel_1.add(lblHoursLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(
		    new TitledBorder(
		    new EtchedBorder(
		        EtchedBorder.LOWERED,
		        new Color(255, 255, 255),
		        new Color(160, 160, 160)),
		        "2",
		        TitledBorder.LEADING,
		        TitledBorder.TOP,
		        null,
		        new Color(0, 0, 0)));
		panel_1_1.setBounds(206, 11, 186, 78);
		contentPane.add(panel_1_1);
		
		JLabel lblCityLabel_1 = new JLabel(CITY_STRING);
		lblCityLabel_1.setBounds(10, 22, 48, 14);
		panel_1_1.add(lblCityLabel_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(56, 19, 120, 20);
		panel_1_1.add(textField_2);
		
		JLabel lblTimeLabel_1 = new JLabel(TIME_STRING);
		lblTimeLabel_1.setBounds(10, 47, 48, 14);
		panel_1_1.add(lblTimeLabel_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<>(HOURS_ARRAY);
		comboBox_2.setBounds(56, 43, 65, 22);
		panel_1_1.add(comboBox_2);
		
		JLabel lblHoursLabel_1 = new JLabel(HOURS_STRING);
		lblHoursLabel_1.setBounds(131, 47, 46, 14);
		panel_1_1.add(lblHoursLabel_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(
		    new TitledBorder(
		    new EtchedBorder(
			    EtchedBorder.LOWERED,
			    new Color(255, 255, 255),
			    new Color(160, 160, 160)),
			    "3",
			    TitledBorder.LEADING,
			    TitledBorder.TOP,
			    null,
			    new Color(0, 0, 0)));
		panel_1_2.setBounds(403, 11, 186, 78);
		contentPane.add(panel_1_2);
		
		JLabel lblCityLabel_2 = new JLabel(CITY_STRING);
		lblCityLabel_2.setBounds(10, 22, 48, 14);
		panel_1_2.add(lblCityLabel_2);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(56, 19, 120, 20);
		panel_1_2.add(textField_3);
		
		JLabel lblTimeLabel_2 = new JLabel(TIME_STRING);
		lblTimeLabel_2.setBounds(10, 47, 48, 14);
		panel_1_2.add(lblTimeLabel_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<>(HOURS_ARRAY);
		comboBox_3.setBounds(56, 43, 65, 22);
		panel_1_2.add(comboBox_3);
		
		JLabel lblHoursLabel_2 = new JLabel(HOURS_STRING);
		lblHoursLabel_2.setBounds(131, 47, 46, 14);
		panel_1_2.add(lblHoursLabel_2);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setLayout(null);
		panel_1_3.setBorder(
		    new TitledBorder(
    	    new EtchedBorder(
			    EtchedBorder.LOWERED,
			    new Color(255, 255, 255),
			    new Color(160, 160, 160)),
			    "4",
			    TitledBorder.LEADING,
			    TitledBorder.TOP,
			    null,
			    new Color(0, 0, 0)));
		panel_1_3.setBounds(10, 100, 186, 78);
		contentPane.add(panel_1_3);
		
		JLabel lblCityLabel_3 = new JLabel(CITY_STRING);
		lblCityLabel_3.setBounds(10, 22, 48, 14);
		panel_1_3.add(lblCityLabel_3);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(56, 19, 120, 20);
		panel_1_3.add(textField_4);
		
		JLabel lblTimeLabel_3 = new JLabel(TIME_STRING);
		lblTimeLabel_3.setBounds(10, 47, 48, 14);
		panel_1_3.add(lblTimeLabel_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<>(HOURS_ARRAY);
		comboBox_4.setBounds(56, 43, 65, 22);
		panel_1_3.add(comboBox_4);
		
		JLabel lblHoursLabel_3 = new JLabel(HOURS_STRING);
		lblHoursLabel_3.setBounds(131, 47, 46, 14);
		panel_1_3.add(lblHoursLabel_3);
		
		JPanel panel_1_3_1 = new JPanel();
		panel_1_3_1.setLayout(null);
		panel_1_3_1.setBorder(
		    new TitledBorder(
		    new EtchedBorder(
			    EtchedBorder.LOWERED,
			    new Color(255, 255, 255),
			    new Color(160, 160, 160)),
			    "5",
			    TitledBorder.LEADING,
			    TitledBorder.TOP,
			    null,
			    new Color(0, 0, 0)));
		panel_1_3_1.setBounds(206, 100, 186, 78);
		contentPane.add(panel_1_3_1);
		
		JLabel lblCityLabel_3_1 = new JLabel(CITY_STRING);
		lblCityLabel_3_1.setBounds(10, 22, 48, 14);
		panel_1_3_1.add(lblCityLabel_3_1);
		
		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(56, 19, 120, 20);
		panel_1_3_1.add(textField_5);
		
		JLabel lblTimeLabel_3_1 = new JLabel(TIME_STRING);
		lblTimeLabel_3_1.setBounds(10, 47, 48, 14);
		panel_1_3_1.add(lblTimeLabel_3_1);
		
		JComboBox<String> comboBox_5 = new JComboBox<>(HOURS_ARRAY);
		comboBox_5.setBounds(56, 43, 65, 22);
		panel_1_3_1.add(comboBox_5);
		
		JLabel lblHoursLabel_3_1 = new JLabel(HOURS_STRING);
		lblHoursLabel_3_1.setBounds(131, 47, 46, 14);
		panel_1_3_1.add(lblHoursLabel_3_1);
		
		JPanel panel_1_4 = new JPanel();
		panel_1_4.setLayout(null);
		panel_1_4.setBorder(
		    new TitledBorder(
		    new EtchedBorder(
			    EtchedBorder.LOWERED,
			    new Color(255, 255, 255),
			    new Color(160, 160, 160)),
			    "6",
			    TitledBorder.LEADING,
			    TitledBorder.TOP,
			    null,
			    new Color(0, 0, 0)));
		panel_1_4.setBounds(403, 100, 186, 78);
		contentPane.add(panel_1_4);
		
		JLabel lblCityLabel_4 = new JLabel(CITY_STRING);
		lblCityLabel_4.setBounds(10, 22, 48, 14);
		panel_1_4.add(lblCityLabel_4);
		
		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(56, 19, 120, 20);
		panel_1_4.add(textField_6);
		
		JLabel lblTimeLabel_4 = new JLabel(TIME_STRING);
		lblTimeLabel_4.setBounds(10, 47, 48, 14);
		panel_1_4.add(lblTimeLabel_4);
		
		JComboBox<String> comboBox_6 = new JComboBox<>(HOURS_ARRAY);
		comboBox_6.setBounds(56, 43, 65, 22);
		panel_1_4.add(comboBox_6);
		
		JLabel lblHoursLabel_4 = new JLabel(HOURS_STRING);
		lblHoursLabel_4.setBounds(131, 47, 46, 14);
		panel_1_4.add(lblHoursLabel_4);
		
		// Add elements to array lists
		textFieldList = new ArrayList<>();
		textFieldList.add(textField_1);
		textFieldList.add(textField_2);
		textFieldList.add(textField_3);
		textFieldList.add(textField_4);
		textFieldList.add(textField_5);
		textFieldList.add(textField_6);
		
		comboBoxList = new ArrayList<>();
		comboBoxList.add(comboBox_1);
		comboBoxList.add(comboBox_2);
		comboBoxList.add(comboBox_3);
		comboBoxList.add(comboBox_4);
		comboBoxList.add(comboBox_5);
		comboBoxList.add(comboBox_6);
	}
}