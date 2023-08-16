package inventoryManagementSystem;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class ViewProductPageGUI extends JFrame {
	
	private InventoryManager IM;

	private JPanel contentPane;
	
	private JLabel lblProductName;
	private JLabel lblProductSku;
	
	private DefaultListModel<Unit> model;
	private JList<Unit> unitList;
	private JScrollPane unitScrollPane;
	
	private JButton btnRemoveUnit;
	private JButton btnExit;
	private JButton btnCreateUnit;
	private JButton btnRemoveProduct;

	public ViewProductPageGUI(Product p, DefaultListModel<Product> otherModel) {
		setTitle("Inventory Management System - Michael Steinhof");
		IM = InventoryManager.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProductName = new JLabel(p.getName());
		lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProductName.setBounds(25, 6, 400, 20);
		contentPane.add(lblProductName);
		
		lblProductSku = new JLabel(p.getSku());
		lblProductSku.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblProductSku.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductSku.setBounds(25, 32, 400, 20);
		contentPane.add(lblProductSku);
		
		model = new DefaultListModel<Unit>();
		for(Unit u : p.getUnitsInStock()) model.addElement(u);
		unitList = new JList<Unit>(model);
		unitList.setBounds(2, 2, 256, 128);
		unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		unitScrollPane = new JScrollPane(unitList);
		unitScrollPane.setToolTipText("");
		unitScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		unitScrollPane.setBounds(25, 75, 400, 200);
		contentPane.add(unitScrollPane);
		
		btnRemoveUnit = new JButton("Remove Selected Unit");
		btnRemoveUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Removes selected unit from Product/Location when clicked
				Unit selected = unitList.getSelectedValue();
				if(selected != null) {
					model.removeElement(selected);
					selected.destroy();
				}
			}
		});
		btnRemoveUnit.setBounds(50, 285, 150, 50);
		contentPane.add(btnRemoveUnit);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //closes individual product page when clicked
				dispose();
			}
		});
		btnExit.setBounds(250, 350, 150, 50);
		contentPane.add(btnExit);
		
		btnCreateUnit = new JButton("Add Unit");
		btnCreateUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Displays window to add new unit when clicked
				ViewNewUnitGUI VNUG = new ViewNewUnitGUI(p, model);
				VNUG.setVisible(true);
			}
		});
		btnCreateUnit.setBounds(250, 285, 150, 50);
		contentPane.add(btnCreateUnit);
		
		btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.setForeground(Color.RED);
		btnRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //removes specific product from InventoryManager when clicked
				IM.removeProduct(p.getSku());
				otherModel.removeElement(p);
				dispose();
			}
		});
		btnRemoveProduct.setBounds(50, 350, 150, 50);
		contentPane.add(btnRemoveProduct);
	}

}
