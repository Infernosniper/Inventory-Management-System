package inventoryManagementSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ViewNewProductGUI extends JFrame {
	
	private InventoryManager IM;

	private JPanel contentPane;
	private JTextField txtSku;
	private JTextField txtName;
	private JTextField txtCost;

	private JLabel lblNewProductTitle;
	private JLabel lblSku;
	private JLabel lblName;
	private JLabel lblCost;
	private JLabel lblFailedCreation;
	
	private JButton btnCreateProduct;
	private JButton btnCancel;
	
	public ViewNewProductGUI(DefaultListModel<Product> otherModel) {
		setTitle("Inventory Management System - Michael Steinhof");
		IM = InventoryManager.getInstance();	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewProductTitle = new JLabel("Create New Product");
		lblNewProductTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewProductTitle.setBounds(130, 10, 190, 30);
		contentPane.add(lblNewProductTitle);
		
		lblSku = new JLabel("SKU:");
		lblSku.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSku.setBounds(25, 72, 61, 20);
		contentPane.add(lblSku);
		
		txtSku = new JTextField();
		txtSku.setBounds(130, 68, 190, 30);
		contentPane.add(txtSku);
		txtSku.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(130, 106, 190, 30);
		contentPane.add(txtName);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblName.setBounds(25, 110, 61, 20);
		contentPane.add(lblName);
		
		lblCost = new JLabel("Cost (optional):");
		lblCost.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCost.setBounds(25, 148, 128, 20);
		contentPane.add(lblCost);
		
		txtCost = new JTextField();
		txtCost.setColumns(10);
		txtCost.setBounds(200, 144, 120, 30);
		contentPane.add(txtCost);
		
		lblFailedCreation = new JLabel("SKU is already taken or required field is missing!");
		lblFailedCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblFailedCreation.setEnabled(true);
		lblFailedCreation.setForeground(Color.RED);
		lblFailedCreation.setBounds(63, 185, 325, 20);
		
		btnCreateProduct = new JButton("Create Product");
		btnCreateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Creates a product when clicked, or sends an error if the SKU is used
				float cost;
				try {
					cost = Float.parseFloat(txtCost.getText());
				}catch(NumberFormatException nfe) {
					cost = 0;
				}
				boolean productWasCreated = false;
				if(txtSku.getText().length() > 0 && txtName.getText().length() > 0) productWasCreated = IM.createProduct(txtSku.getText(), txtName.getText(), cost);
				if(productWasCreated) {
					otherModel.addElement(IM.getProduct(txtSku.getText().toUpperCase()));
					dispose();
				}else {
					contentPane.add(lblFailedCreation);
					repaint();
				}
			}
		});
		btnCreateProduct.setBounds(50, 210, 150, 40);
		contentPane.add(btnCreateProduct);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() { //Cancels Product creation when clicked
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(250, 210, 150, 40);
		contentPane.add(btnCancel);
	}

}
