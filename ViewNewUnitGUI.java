package inventoryManagementSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ViewNewUnitGUI extends JFrame {

	private InventoryManager IM;

	private JPanel contentPane;
	
	private JLabel lblNewUnitTitle;
	private JTextField txtSerialNumber;
	
	private JLabel lblLocation;
	private JLabel lblCondition;
	private JLabel lblSerialNumber;
	
	private JButton btnAddUnit;
	private JButton btnCancel;
	
	private JComboBox<Location> comboBoxLocation;
	private JComboBox<ItemConditions> comboBoxCondition;
	private JLabel lblCost;
	private JTextField txtCost;

	public ViewNewUnitGUI(Product p, DefaultListModel<Unit> otherModel) {
		setTitle("Inventory Management System - Michael Steinhof");
		IM = InventoryManager.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewUnitTitle = new JLabel("Add unit to " + p.getSku());
		lblNewUnitTitle.setBounds(25, 10, 400, 40);
		lblNewUnitTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUnitTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewUnitTitle);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLocation.setBounds(25, 60, 75, 20);
		contentPane.add(lblLocation);
		
		lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCondition.setBounds(25, 100, 85, 20);
		contentPane.add(lblCondition);
		
		lblSerialNumber = new JLabel("S/N (optional):");
		lblSerialNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSerialNumber.setBounds(25, 140, 115, 20);
		contentPane.add(lblSerialNumber);
		
		comboBoxLocation = new JComboBox<Location>(IM.getAllLocations());
		comboBoxLocation.setBounds(160, 60, 190, 30);
		contentPane.add(comboBoxLocation);
		
		comboBoxCondition = new JComboBox<ItemConditions>(ItemConditions.values());
		comboBoxCondition.setBounds(160, 100, 190, 30);
		contentPane.add(comboBoxCondition);
		
		txtSerialNumber = new JTextField();
		txtSerialNumber.setBounds(160, 140, 190, 30);
		contentPane.add(txtSerialNumber);
		txtSerialNumber.setColumns(10);
		
		btnAddUnit = new JButton("Add Unit");
		btnAddUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Adds unit to Product/Location when clicked
				Unit u = null;
				Location l = (Location)comboBoxLocation.getSelectedItem();
				ItemConditions c = (ItemConditions)comboBoxCondition.getSelectedItem();
				float cost;
				try {
					cost = Float.parseFloat(txtCost.getText());
				}catch(NumberFormatException nfe) {
					cost = 0;
				}
				
				if(l != null) {
					if(txtSerialNumber.getText().length() == 0) u = new Unit(p, l, c, cost);
					else u = new Unit(p, l, c, cost, txtSerialNumber.getText());
				}
				
				otherModel.addElement(u);
				dispose();
			}
		});
		btnAddUnit.setBounds(49, 220, 150, 40);
		contentPane.add(btnAddUnit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(249, 220, 150, 40);
		contentPane.add(btnCancel);
		
		lblCost = new JLabel("Cost (optional):");
		lblCost.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCost.setBounds(25, 180, 120, 20);
		contentPane.add(lblCost);
		
		txtCost = new JTextField();
		txtCost.setText(String.valueOf(p.getCost()));
		txtCost.setColumns(10);
		txtCost.setBounds(160, 180, 190, 30);
		contentPane.add(txtCost);
	}
}