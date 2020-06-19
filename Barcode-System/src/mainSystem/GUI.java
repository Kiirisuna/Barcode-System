package mainSystem;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import org.bson.Document;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.eq;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


@SuppressWarnings({ "unused", "serial" })
public class GUI extends JFrame {
	public static String imagePath;
	public static JFrame mainGUI = new JFrame();// creating instance of JFrame
	private JTextField calcText;
	private JTextField totalDisplay;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private String rowSum (TableModel dtm) {
		double finalTotal = 0;
		for (int i =0; i < dtm.getRowCount(); i++) {
			finalTotal += (Double) dtm.getValueAt(i, 2);
		}
		return Double.toString(finalTotal);
	}
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setSize(1050, 600);
		mainGUI.setLocationRelativeTo(null);
		mainGUI.setVisible(true);// making the frame visible
		mainGUI.setMinimumSize(new Dimension(1050,600));
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		
		//Admin Login Button
		JButton adminManage = new JButton("?");
		adminManage.setFocusable(false);
		adminManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					adminLogin adLog = new adminLogin();
					adLog.setLocationRelativeTo(mainGUI);
					adLog.setVisible(true);
			}
		});
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------			
		// Add Calculator functionalities
		calcText = new JTextField();
		calcText.setHorizontalAlignment(SwingConstants.RIGHT);
		calcText.setFont(new Font("Segoe UI", Font.BOLD, 20));
		calcText.setText("0");
		calcText.setEditable(false);
		calcText.setColumns(10);
	
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "1");
				} else {
					calcText.setText("1");
				}
			}
		});
		btn1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn1.setFocusable(false);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "2");
				} else {
					calcText.setText("2");
				}
			}
		});
		btn2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn2.setFocusable(false);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "3");
				} else {
					calcText.setText("3");
				}
			}
		});
		btn3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn3.setFocusable(false);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "4");
				} else {
					calcText.setText("4");
				}
			}
		});
		btn4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn4.setFocusable(false);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "5");
				} else {
					calcText.setText("5");
				}
			}
		});
		btn5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn5.setFocusable(false);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "6");
				} else {
					calcText.setText("6");
				}
			}
		});
		btn6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn6.setFocusable(false);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "7");
				} else {
					calcText.setText("7");
				}
			}
		});
		btn7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn7.setFocusable(false);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "8");
				} else {
					calcText.setText("8");
				}
			}
		});
		btn8.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn8.setFocusable(false);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "9");
				} else {
					calcText.setText("9");
				}
			}
		});
		btn9.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn9.setFocusable(false);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mongoDB.calcCheck(calcText.getText())){
					calcText.setText(calcText.getText() + "0");
				} else {
					calcText.setText("0");
				}
			}
		});
		btn0.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btn0.setFocusable(false);

		JButton btnc = new JButton("C");
		btnc.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnc.setFocusable(false);
		btnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcText.setText("0");
			}
		});
		
		JButton button = new JButton(".");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calcText.getText().contains(".") == false){
					calcText.setText(calcText.getText() + ".");
				}
			}
		});
		button.setFont(new Font("Segoe UI", Font.BOLD, 20));
		button.setFocusable(false);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Add / Remove");
		tglbtnNewToggleButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tglbtnNewToggleButton.setFocusable(false);

//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
		totalDisplay = new JTextField();
		totalDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		totalDisplay.setFont(new Font("Segoe UI", Font.BOLD, 20));
		totalDisplay.setText("0");
		totalDisplay.setBackground(Color.white);
		totalDisplay.setEditable(false);
		totalDisplay.setColumns(10);
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------			
		String[] tblHead={"Product","Quantity","Price"};
		DefaultTableModel dtm = new DefaultTableModel(tblHead,0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable model = new JTable(dtm);
		model.setShowHorizontalLines(false);
		model.setBorder(null);
		model.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		model.setRowSelectionAllowed(true);
		model.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		model.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mainGUI.getContentPane().add(model);
		JScrollPane scrollPane = new JScrollPane(model);
		
		JButton btnAdd_1 = new JButton("Finalise");
		btnAdd_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnAdd_1.setFocusable(false);
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check database for ID, if exist add to cart else popup not exist!
				var numberDoc = mongoDB.cart.count();
				if (numberDoc != 0) {
					mongoDB.insertSales(mongoDB.cart, mongoDB.sale, totalDisplay.getText());
					
					MongoCursor<Document> cursor = mongoDB.cart.find().iterator();
					
        			while(cursor.hasNext()) {
        				Document temp = cursor.next();
        				String hello = temp.getString("barcodeID");
        				System.out.print(hello);
        				mongoDB.updateQuantitySale(mongoDB.collection, hello , true, temp.getInteger("quantity"), false);
        			}
        			
        			cursor.close();
					
					mongoDB.clearCollection(mongoDB.cart);
					totalDisplay.setText("0.00");
					dtm.setRowCount(0);
					model.setModel(dtm);
				}
			}
		});
		
		JButton btnRemove = new JButton("Terminate");
		btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnRemove.setFocusable(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mongoDB.clearCollection(mongoDB.cart);
				totalDisplay.setText("0.00");
				dtm.setRowCount(0);
				model.setModel(dtm);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Item ID:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------
		
		JButton btnAdd = new JButton("+");
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add cost to cart
				Document doc = new Document("productName", "manual Add")
						.append("quantity", 1)
						.append("price", Double.parseDouble(calcText.getText()));
				mongoDB.cart.insertOne(doc);
				dtm.setRowCount(0);
    			MongoCursor<Document> cursor = mongoDB.cart.find().iterator();
    			while(cursor.hasNext()) {
    				Document temp = cursor.next();
    				String pro = (String) temp.get("productName");
    				Double pri = temp.getDouble("price");
    				int qua = temp.getInteger("quantity");
    				dtm.addRow(new Object [] {pro,qua,pri});
    			}
    			model.setModel(dtm);
    			cursor.close();
				
				totalDisplay.setText(rowSum(dtm));
				calcText.setText("0");
			}
		});
		
		JButton btnSub = new JButton("-");
		btnSub.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSub.setFocusable(false);
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Subtract cost from cart
				Document doc = new Document("productName", "manual Add")
						.append("quantity", 1)
						.append("price", Double.parseDouble(calcText.getText()) - Double.parseDouble(calcText.getText())*2);
				mongoDB.cart.insertOne(doc);
				dtm.setRowCount(0);
    			MongoCursor<Document> cursor = mongoDB.cart.find().iterator();
    			while(cursor.hasNext()) {
    				Document temp = cursor.next();
    				String pro = (String) temp.get("productName");
    				Double pri = temp.getDouble("price");
    				int qua = temp.getInteger("quantity");
    				dtm.addRow(new Object [] {pro,qua,pri});
    			}
    			model.setModel(dtm);
    			cursor.close();
				
				totalDisplay.setText(rowSum(dtm));
				calcText.setText("0");
			}
		});
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------		

		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(calcText, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn7, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(btn4, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(9)
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn8, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(btn5, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(9)
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn9, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(btn6, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(9)
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(btnc, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(btn0, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn3, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
							.addGap(7)
							.addComponent(btnSub, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
					.addGap(10))
		);
		
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(11)
					.addComponent(calcText, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn7, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btn4, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn8, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btn5, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn9, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btn6, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addGap(11)
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btnc, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btn0, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btn3, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addComponent(btnSub, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
					.addGap(8))
		);
		desktopPane.setLayout(gl_desktopPane);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.BOLD, 16));
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		if (mongoDB.ifExist(mongoDB.collection,"barcodeID", textField.getText()) == 1) {
            			// Default is add to database
            			if (tglbtnNewToggleButton.isSelected()==false) {
            				mongoDB.insertCart(mongoDB.collection, mongoDB.cart, textField.getText());
            			}
            			
            			if (tglbtnNewToggleButton.isSelected()==true) {
            				mongoDB.removeCart(mongoDB.collection, mongoDB.cart, textField.getText());
            			}
            			textField.setText("");
            			dtm.setRowCount(0);
            			MongoCursor<Document> cursor = mongoDB.cart.find().iterator();
            			while(cursor.hasNext()) {
            				Document temp = cursor.next();
            				String pro = (String) temp.get("productName");
            				Double pri = temp.getDouble("price");
            				int qua = temp.getInteger("quantity");
            				dtm.addRow(new Object [] {pro,qua,pri});
            			}
            			model.setModel(dtm);
            			cursor.close();
            			
            			totalDisplay.setText(rowSum(dtm));	
            		} else {
            			JOptionPane.showMessageDialog(textField, "Product does not exist!");
            			textField.setText("");
            			MongoCursor<Document> cursor = mongoDB.cart.find().iterator();
            			while(cursor.hasNext()) {
            				Document temp = cursor.next();
            				String pro = (String) temp.get("productName");
            				String pri = (String) temp.get("price");
            				int qua = temp.getInteger("quantity");
            				dtm.addRow(new Object [] {pro,qua,pri});
            			}
            			model.setModel(dtm);
            			cursor.close();
            			
            			totalDisplay.setText(rowSum(dtm));	
            		}
            	}
            }
        });
		
		
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------		
		GroupLayout groupLayout = new GroupLayout(mainGUI.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnNewToggleButton, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
						.addComponent(totalDisplay, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd_1, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
							.addGap(23)
							.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
							.addComponent(adminManage, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(14))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(totalDisplay, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tglbtnNewToggleButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
							.addGap(8)
							.addComponent(adminManage, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(11))
		);
		mainGUI.getContentPane().setLayout(groupLayout);	
	}

	public static void main(String[] args) {
		//DO NOT CHANGE THIS
		//Creates a GUI instance on execution
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			GUI gui = new GUI();
		}
		});
	}
}

// Resizing and scaling needs to be done
// Multiple dialog/windows management needs to be done