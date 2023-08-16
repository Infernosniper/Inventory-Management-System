package inventoryManagementSystem;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

//TODO Behavior for having multiple location windows open simultaneously

@SuppressWarnings("serial")
public class ViewLocationsGUI extends JFrame {

	private InventoryManager IM;

	private JPanel contentPane;
	
	private JLabel lblLocationTitle;
	private JLabel lblSelectLocation;
	private JLabel lblMoveUnit;
	private JLabel lblCreateLocation;
	private JLabel lblFailRemove;
	private JLabel lblLocationNameTaken;
	
	private JComboBox<Location> comboBoxSelectLocation;
	private JComboBox<Location> comboBoxMoveUnit;
	
	private DefaultListModel<Unit> model;
	private JList<Unit> unitList;
	private JScrollPane unitScrollPane;
	
	private JTextField txtCreateLocation;
	
	private JSeparator separator_two;
	private JSeparator separator_one;
	
	private JButton btnCreateLocation;
	private JButton btnExit;
	private JButton btnMoveUnit;
	private JButton btnRemoveLocation;
	
	public ViewLocationsGUI() {
		setTitle("Inventory Management System - Michael Steinhof");
		IM = InventoryManager.getInstance();
		
		addWindowFocusListener(new WindowFocusListener() { //updates list of units when focused to account for removals in other windows
			public void windowGainedFocus(WindowEvent e) {
				model.removeAllElements();
				Location l = (Location)comboBoxSelectLocation.getSelectedItem();
				if(l != null) for(Unit u : l.getUnitsInLocation()) model.addElement(u);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(725, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLocationTitle = new JLabel("Manage Locations");
		lblLocationTitle.setBounds(140, 10, 175, 30);
		lblLocationTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblLocationTitle);
		
		comboBoxSelectLocation = new JComboBox<Location>(IM.getAllLocations());
		comboBoxSelectLocation.addActionListener(new ActionListener() { //Update list of units when changing selected location
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				Location l = (Location)comboBoxSelectLocation.getSelectedItem();
				if(l != null) for(Unit u : l.getUnitsInLocation()) model.addElement(u);
			}
		});
		comboBoxSelectLocation.setBounds(145, 50, 200, 30);
		contentPane.add(comboBoxSelectLocation);
		
		lblSelectLocation = new JLabel("Select Location:");
		lblSelectLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSelectLocation.setBounds(10, 50, 125, 30);
		contentPane.add(lblSelectLocation);
		
		model = new DefaultListModel<Unit>();
		Location l = (Location)comboBoxSelectLocation.getSelectedItem();
		if(l != null) for(Unit u : l.getUnitsInLocation()) model.addElement(u);
		unitList = new JList<Unit>(model);
		unitList.setBounds(2, 2, 256, 128);
		unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unitScrollPane = new JScrollPane(unitList);
		unitScrollPane.setToolTipText("");
		unitScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		unitScrollPane.setBounds(25, 100, 400, 200);
		contentPane.add(unitScrollPane);
		
		lblMoveUnit = new JLabel("Move Selected Unit to: ");
		lblMoveUnit.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoveUnit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMoveUnit.setBounds(10, 310, 180, 30);
		contentPane.add(lblMoveUnit);
		
		comboBoxMoveUnit = new JComboBox<Location>(IM.getAllLocations());
		comboBoxMoveUnit.setBounds(200, 310, 150, 30);
		contentPane.add(comboBoxMoveUnit);
		
		btnMoveUnit = new JButton("Confirm");
		btnMoveUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Moves unit to new location when clicked
				Unit unitToMove = unitList.getSelectedValue();
				boolean productWasDeleted = !Arrays.asList(unitToMove.getProduct().getUnitsInStock()).contains(unitToMove);
				
				if(productWasDeleted) {
					model.removeAllElements();
					Location l = (Location)comboBoxSelectLocation.getSelectedItem();
					if(l != null) for(Unit u : l.getUnitsInLocation()) model.addElement(u);
				}else {
					Location newLocation = (Location)comboBoxMoveUnit.getSelectedItem();
					IM.moveUnit(unitToMove, newLocation);
					model.removeElement(unitToMove);
				}
			}
		});
		btnMoveUnit.setBounds(360, 310, 90, 30);
		contentPane.add(btnMoveUnit);
		
		separator_one = new JSeparator();
		separator_one.setForeground(Color.GRAY);
		separator_one.setBounds(25, 350, 400, 12);
		contentPane.add(separator_one);
		
		lblFailRemove = new JLabel("Note: Cannot delete location with unit in it!");
		lblFailRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblFailRemove.setEnabled(true);
		lblFailRemove.setForeground(Color.RED);
		lblFailRemove.setBounds(85, 80, 280, 12);
		contentPane.add(lblFailRemove);
		
		
		btnRemoveLocation = new JButton("Delete");
		btnRemoveLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Location is deleted when clicked && no units are in location
				Location l = (Location)comboBoxSelectLocation.getSelectedItem();
				if(l != null) {
					if(l.getUnitsInLocation().length == 0) {
						IM.removeLocation(l.getCode());
						comboBoxSelectLocation.removeItem(l);
						comboBoxMoveUnit.removeItem(l);
					}
				}				
			}
		});
		btnRemoveLocation.setForeground(Color.RED);
		btnRemoveLocation.setBounds(360, 50, 90, 30);
		contentPane.add(btnRemoveLocation);
		
		lblCreateLocation = new JLabel("New Location Name:");
		lblCreateLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCreateLocation.setBounds(10, 370, 160, 30);
		contentPane.add(lblCreateLocation);
		
		txtCreateLocation = new JTextField();
		txtCreateLocation.setBounds(182, 370, 165, 30);
		contentPane.add(txtCreateLocation);
		txtCreateLocation.setColumns(10);
		
		separator_two = new JSeparator();
		separator_two.setForeground(Color.GRAY);
		separator_two.setBounds(25, 415, 400, 12);
		contentPane.add(separator_two);
		
		lblLocationNameTaken = new JLabel("Location code is already in use!");
		lblLocationNameTaken.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocationNameTaken.setForeground(Color.RED);
		lblLocationNameTaken.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblLocationNameTaken.setBounds(130, 380, 190, 20);
		
		btnCreateLocation = new JButton("Create");
		btnCreateLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Creates a new location, or displays error message if code is already used
				String code = txtCreateLocation.getText();
				boolean locationCreated = IM.createLocation(code);
				if(locationCreated) {
					comboBoxSelectLocation.addItem(IM.getLocation(code));
					comboBoxMoveUnit.addItem(IM.getLocation(code));
					
					lblCreateLocation.setBounds(10, 370, 160, 30);
					txtCreateLocation.setBounds(182, 370, 165, 30);
					btnCreateLocation.setBounds(360, 370, 90, 30);
					
					if(Arrays.asList(contentPane.getComponents()).contains(lblLocationNameTaken)) contentPane.remove(lblLocationNameTaken);
					
					txtCreateLocation.setText("");
					
				}else {
					contentPane.add(lblLocationNameTaken);
					lblCreateLocation.setBounds(10, 355, 160, 30);
					txtCreateLocation.setBounds(182, 355, 165, 30);
					btnCreateLocation.setBounds(360, 355, 90, 30);
				}
			}
		});
		btnCreateLocation.setBounds(360, 370, 90, 30);
		contentPane.add(btnCreateLocation);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(125, 425, 200, 45);
		contentPane.add(btnExit);
	}
}
