package mainSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class manager extends JDialog {
	//private adminLogin frame;
	private JPanel contentPane;
	private JTextField searchText;
	private JTextField idText;
	private JTextField nameText;
	private JTextField quantityText;
	private JTextField supplierText;
	private JTextField costText;
	private JTextField sellText;
	
	public void resetFields() {
		idText.setText("");
		nameText.setText("");
		quantityText.setText("");
		supplierText.setText("");
		costText.setText("");
		sellText.setText("");
		searchText.setText("");
	}

	/**
	 * Create the frame.
	 */
	public manager() {
		GUI.mainGUI.setEnabled(false);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelSearch = new JLabel("Search:");
		labelSearch.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		String[] searchStrings = {"Product ID", "Product Name", "Supplier"};
		JComboBox comboBox = new JComboBox(searchStrings);
		comboBox.setMaximumRowCount(3);
		
		searchText = new JTextField();
		searchText.setColumns(10);
		
		String[] tblHead={"Barcode ID", "Product Name","Quantity","Supplier","Cost Price", "Sell Price", "Total Sales"};
		DefaultTableModel ktm = new DefaultTableModel(tblHead,0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable model = new JTable(ktm);
		model.setShowHorizontalLines(false);
		model.setBorder(null);
		model.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		model.setRowSelectionAllowed(true);
		model.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		model.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(model);
		model.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount()==2) {
					JTable target = (JTable)me.getSource();
		            int row = target.getSelectedRow(); 
		            idText.setText((String) model.getValueAt(row, 0));
		            nameText.setText((String) model.getValueAt(row, 1));
		            int tempQuan = (int) model.getValueAt(row, 2);
		            quantityText.setText(Integer.toString(tempQuan));
		            supplierText.setText((String) model.getValueAt(row, 3));
		            Double tempCost = (Double) model.getValueAt(row,4);
		            costText.setText(Double.toString(tempCost));
		            Double tempSell = (Double) model.getValueAt(row,5);
		            sellText.setText(Double.toString(tempSell));
				}
			}
		});
		
		MongoCursor<Document> cursor = mongoDB.collection.find().iterator();
		while(cursor.hasNext()) {
			Document temp = cursor.next();
			String bar = (String) temp.get("barcodeID");
			String pro = (String) temp.get("productName");
			int qua = temp.getInteger("quantity");
			String sup = (String) temp.get("supplier");
			Double cost = temp.getDouble("costPrice");
			Double sell = temp.getDouble("sellPrice");
			int tot = temp.getInteger("totalSales");
			ktm.addRow(new Object [] {bar,pro,qua,sup,cost,sell,tot});
		}
		model.setModel(ktm);
		cursor.close();
		
		
		JLabel labelID = new JLabel("Product ID:");
		labelID.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel labelName = new JLabel("Product Name:");
		labelName.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel labelQ = new JLabel("Quantity:");
		labelQ.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel labelSupp = new JLabel("Supplier:");
		labelSupp.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel labelCost = new JLabel("Cost Price:");
		labelCost.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel labelSell = new JLabel("Sell Price:");
		labelSell.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		nameText = new JTextField();
		nameText.setColumns(10);
		
		quantityText = new JTextField();
		quantityText.setColumns(10);
		quantityText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
            		costText.setEditable(true);
            	} else {
            		costText.setEditable(false);
            	}
            }
		});
		
		supplierText = new JTextField();
		supplierText.setColumns(10);
		
		costText = new JTextField();
		costText.setColumns(10);
		costText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyChar() == '.' ) {
            		if (costText.getText().contains(".") == false){
    					costText.setText(costText.getText() + ".");
    				}
            	} 
            	if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
            		costText.setEditable(true);
            	} else {
            		costText.setEditable(false);
            	}
            }
		});
		
		sellText = new JTextField();
		sellText.setColumns(10);
		sellText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyChar() == '.' ) {
            		if (sellText.getText().contains(".") == false){
    					sellText.setText(sellText.getText() + ".");
    				}
            	}
            	if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
            		sellText.setEditable(true);
            	} else {
            		sellText.setEditable(false);
            	}
            }
		});
		
		JButton addButton = new JButton("Add");
		addButton.setFocusable(false);
		addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idText.getText().isBlank() || nameText.getText().isBlank() || quantityText.getText().isBlank() || supplierText.getText().isBlank() || costText.getText().isBlank() || sellText.getText().isBlank()) {
					JOptionPane.showMessageDialog(contentPane, "Please fill in all empty fields!");
				} else {
					if (mongoDB.ifExist(mongoDB.collection, "barcodeID", idText.getText()) == 1) {
						mongoDB.updateField(mongoDB.collection, idText.getText(), "productName", nameText.getText());
						mongoDB.updateField(mongoDB.collection, idText.getText(), "quantity", quantityText.getText());
						mongoDB.updateField(mongoDB.collection, idText.getText(), "supplier", supplierText.getText());
						mongoDB.updateField(mongoDB.collection, idText.getText(), "costPrice", costText.getText());
						mongoDB.updateField(mongoDB.collection, idText.getText(), "sellPrice", sellText.getText());
						resetFields();
					} else {
						String[] temp = new String[]{idText.getText(),nameText.getText(),quantityText.getText(),supplierText.getText(),costText.getText(),sellText.getText(),"0"};
						mongoDB.add2Col(mongoDB.collection, temp);
						resetFields();
					}
					ktm.setRowCount(0);
					MongoCursor<Document> cursor = mongoDB.collection.find().iterator();
					while(cursor.hasNext()) {
						Document temp = cursor.next();
						String bar = (String) temp.get("barcodeID");
						String pro = (String) temp.get("productName");
						int qua = temp.getInteger("quantity");
						String sup = (String) temp.get("supplier");
						Double cost = temp.getDouble("costPrice");
						Double sell = temp.getDouble("sellPrice");
						int tot = temp.getInteger("totalSales");
						ktm.addRow(new Object [] {bar,pro,qua,sup,cost,sell,tot});
					}
					model.setModel(ktm);
					cursor.close();
				}
			}
		});
		
		JButton updateButton = new JButton("Remove");
		updateButton.setFocusable(false);
		updateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idText.getText().isBlank()) {
					JOptionPane.showMessageDialog(contentPane, "Product ID required to remove from database!");
					resetFields();
				} else {
					if (mongoDB.ifExist(mongoDB.collection, "barcodeID", idText.getText()) == 1){
						int reply = JOptionPane.showConfirmDialog(contentPane, "Would you like to permanently remove this item from the database?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							mongoDB.remFromCol(mongoDB.collection, "barcodeID", idText.getText());
							resetFields();
							ktm.setRowCount(0);
							MongoCursor<Document> cursor = mongoDB.collection.find().iterator();
							while(cursor.hasNext()) {
								Document temp = cursor.next();
								String bar = (String) temp.get("barcodeID");
								String pro = (String) temp.get("productName");
								int qua = temp.getInteger("quantity");
								String sup = (String) temp.get("supplier");
								Double cost = temp.getDouble("costPrice");
								Double sell = temp.getDouble("sellPrice");
								int tot = temp.getInteger("totalSales");
								ktm.addRow(new Object [] {bar,pro,qua,sup,cost,sell,tot});
							}
							model.setModel(ktm);
							cursor.close();
						} else {
							resetFields();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Product ID does not exist in the database!");
						resetFields();
					}
				}
			}
		});
		
		JToggleButton manageToggle = new JToggleButton("Scan Item");
		manageToggle.setFocusable(false);
		manageToggle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		manageToggle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manageToggle.isSelected() == true) {
					addButton.setEnabled(false); 
					updateButton.setEnabled(false); 
				} else {
					addButton.setEnabled(true); 
					updateButton.setEnabled(true); 
				}
			}
			
		});
		
		idText = new JTextField();
		idText.setColumns(10);
		idText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		if (manageToggle.isSelected()==true) {
            			if (mongoDB.ifExist(mongoDB.collection, "barcodeID", idText.getText()) == 1) {
            				mongoDB.updateQuantitySale(mongoDB.collection, idText.getText(), false , 1 , false);
            				idText.setText("");
            			} else {
            				JOptionPane.showMessageDialog(contentPane, "Product needs to be in the database first before scanning!");
            				idText.setText("");
            			}
            		}
            	}
            }
		});
		
		JButton resetButton = new JButton("Reset");
		resetButton.setFocusable(false);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetFields();
				
				ktm.setRowCount(0);
				MongoCursor<Document> cursor = mongoDB.collection.find().iterator();
				while(cursor.hasNext()) {
					Document temp = cursor.next();
					String bar = (String) temp.get("barcodeID");
					String pro = (String) temp.get("productName");
					int qua = temp.getInteger("quantity");
					String sup = (String) temp.get("supplier");
					Double cost = temp.getDouble("costPrice");
					Double sell = temp.getDouble("sellPrice");
					int tot = temp.getInteger("totalSales");
					ktm.addRow(new Object [] {bar,pro,qua,sup,cost,sell,tot});
				}
				model.setModel(ktm);
				cursor.close();
			}
		});
		
		searchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		String tempQuery = "";
            		if (comboBox.getSelectedItem().toString() == "Product ID") {
            			tempQuery = "barcodeID";
            		} else if (comboBox.getSelectedItem().toString() == "Product Name") {
            			tempQuery = "productName";
            		} else {
            			tempQuery = "supplier";
            		}
            		ktm.setRowCount(0);
            		MongoCursor<Document> cursor = mongoDB.collection.find(Filters.eq(tempQuery,searchText.getText())).iterator();	
            		while (cursor.hasNext()) {
            			Document temp = cursor.next();
    					String bar = (String) temp.get("barcodeID");
    					String pro = (String) temp.get("productName");
    					int qua = temp.getInteger("quantity");
    					String sup = (String) temp.get("supplier");
    					Double cost = temp.getDouble("costPrice");
    					Double sell = temp.getDouble("sellPrice");
    					int tot = temp.getInteger("totalSales");
    					ktm.addRow(new Object [] {bar,pro,qua,sup,cost,sell,tot});
            		}
            		model.setModel(ktm);
            		cursor.close();
            	}
            }
		});
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(48)
									.addComponent(labelID)
									.addGap(10)
									.addComponent(idText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(55)
									.addComponent(labelSupp))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(19)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(labelName)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(42)
											.addComponent(labelQ, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(nameText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
										.addComponent(quantityText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
									.addGap(44)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(5)
											.addComponent(labelSell))
										.addComponent(labelCost)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(labelSearch, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(searchText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(supplierText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
										.addComponent(costText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
										.addComponent(sellText, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
									.addGap(89)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(manageToggle, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(30)
											.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(30)
											.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(28)
									.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
							.addGap(26))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(idText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelSupp, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(7)
									.addComponent(labelName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(labelQ, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(9)
									.addComponent(nameText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(quantityText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(36)
									.addComponent(labelSell, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addComponent(labelCost, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelSearch)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(searchText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(resetButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(supplierText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(costText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(sellText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(manageToggle, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.mainGUI.setEnabled(true);
            }
        });
	}
}
