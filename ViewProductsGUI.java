package inventoryManagementSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ViewProductsGUI extends JFrame {

	private InventoryManager IM;
	private Product[] curProducts;
	
	private JPanel contentPane;
	
	private DefaultListModel<Product> model;
	private JList<Product> productList;
	private JScrollPane productScrollPane;
	
	private JButton btnExit;
	private JButton btnSearchProducts;
	private JButton btnCreateProduct;
	private JButton btnViewLocations;
	
	private JLabel lblViewProductsTitle;
	private JLabel lblProductScrollPaneTooltip;
	private JLabel lblSearchTooltip;
	private JLabel lblMessage;
	
	private JTextField txtSearchProducts;
	

	public ViewProductsGUI() {
		setTitle("Inventory Management System - Michael Steinhof");
		IM = InventoryManager.getInstance();
		curProducts = IM.getProducts();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		model = new DefaultListModel<Product>();
		for(Product p : curProducts) model.addElement(p);
		productList = new JList<Product>(model);
		productList.setBounds(2, 2, 256, 128);
		productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		productList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) { //Opens specific product page when row in list is double clicked
		    	Product p = productList.getSelectedValue();
		        if (evt.getClickCount() == 2) {
		            ViewProductPageGUI VPPG = new ViewProductPageGUI(p, model);
		            VPPG.setVisible(true);
		        }
		    }
		});
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(400, 475, 150, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //closes main product catalog when clicked
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnExit);
		
		productScrollPane = new JScrollPane(productList);
		productScrollPane.setToolTipText("");
		productScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		productScrollPane.setBounds(10, 115, 580, 300);
		contentPane.add(productScrollPane);
		
		lblViewProductsTitle = new JLabel("Product Catalog");
		lblViewProductsTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblViewProductsTitle.setBounds(224, 6, 152, 30);
		contentPane.add(lblViewProductsTitle);
		
		lblProductScrollPaneTooltip = new JLabel("Double click a product to view");
		lblProductScrollPaneTooltip.setLabelFor(productScrollPane);
		lblProductScrollPaneTooltip.setBounds(200, 425, 200, 16);
		contentPane.add(lblProductScrollPaneTooltip);
		
		txtSearchProducts = new JTextField();
		txtSearchProducts.setToolTipText("");
		txtSearchProducts.setBounds(125, 50, 300, 35);
		contentPane.add(txtSearchProducts);
		txtSearchProducts.setColumns(10);
		
		btnSearchProducts = new JButton("Search/Refresh");
		btnSearchProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //filters products by search string when clicked
				curProducts = IM.getProducts(txtSearchProducts.getText());
				model.removeAllElements();
				for(Product p : curProducts) model.addElement(p);
			}
		});
		btnSearchProducts.setBounds(425, 50, 150, 35);
		contentPane.add(btnSearchProducts);
		
		btnCreateProduct = new JButton("Create New Product");
		btnCreateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Opens window to create new product when clicked
				ViewNewProductGUI VNPG = new ViewNewProductGUI(model);
				VNPG.setVisible(true);
			}
		});
		btnCreateProduct.setBounds(50, 475, 150, 50);
		contentPane.add(btnCreateProduct);
		
		lblSearchTooltip = new JLabel("Search by SKU/Name:");
		lblSearchTooltip.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSearchTooltip.setBounds(6, 59, 115, 20);
		contentPane.add(lblSearchTooltip);
		
		btnViewLocations = new JButton("Manage Locations");
		btnViewLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Opens window to manage locations when clicked
				ViewLocationsGUI VLG = new ViewLocationsGUI();
				VLG.setVisible(true);
			}
		});
		btnViewLocations.setBounds(225, 475, 150, 50);
		contentPane.add(btnViewLocations);
		
		lblMessage = new JLabel("<HTML><center>Hi visitor! I used to be CTO of an e-commerce company and inventory management was a nightmare! Here is my attempted fix, albeit with one full-time EMT and only a couple of days, it's more so a proof of concept. I apologize for the broken text alignment in lists, I ran out of time.</center></HTML>");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblMessage.setBounds(10, 525, 580, 50);
		contentPane.add(lblMessage);
	}
}