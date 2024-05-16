package java_projet;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class M {

	private JFrame frame;
	private JTextField txtedition;
	private JTable table;
	private JTextField txtbookname;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M window = new M();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public M() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/m", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	void table_load()
	{
		try
		{
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException  e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(268, 0, 195, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 57, 425, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book's name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(37, 43, 103, 40);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(37, 111, 103, 40);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(37, 178, 103, 40);
		panel.add(lblNewLabel_1_1_1);
		
		txtedition = new JTextField();
		txtedition.setBounds(196, 120, 180, 27);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtbookname = new JTextField();
		txtbookname.setColumns(10);
		txtbookname.setBounds(196, 43, 180, 27);
		panel.add(txtbookname);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(196, 187, 180, 27);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String bookname, edition, price;
				
				bookname = txtbookname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				try 
				{
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bookname);
					pst.setString(2, edition);
					pst.setString(1, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added !!!!");
					table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbookname.requestFocus();
					
				}
				catch (SQLException e1) 
				{
			      e1.printStackTrace();
				}
		   }});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 329, 133, 69);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbookname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbookname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setBounds(296, 329, 133, 69);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(153, 329, 133, 69);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(445, 55, 323, 344);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 407, 403, 77);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(10, 27, 63, 40);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				try
				{
					String id = txtbid.getText();
					pst =  con.prepareStatement("select name,edition,price from book where id= ?");
					pst.setString(1 , id);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true)
					{
						String bookname = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						
						txtbookname.setText(bookname);
						txtedition.setText(edition);
						txtprice.setText(price);
					
					
					}
					else 
					{
						txtbookname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						
					}
				
				
			
			
			
			} catch (SQLException e1) 
				{
				e1.printStackTrace();
			}
			
			}});
		txtbid.setColumns(10);
		txtbid.setBounds(102, 36, 144, 27);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	String bookname, edition, price, bid;
				
				bookname = txtbookname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				try 
				{
					pst = con.prepareStatement("update book set name=?, edition=?, price=? where id=?");
					pst.setString(1, bookname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated !!!!");
					table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbookname.requestFocus();
					
				}
				catch (SQLException e1) 
				{
			      e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(445, 415, 133, 69);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelite = new JButton("Delete");
		btnDelite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
       String bid;
				
				
				bid = txtbid.getText();
				try 
				{
					pst = con.prepareStatement("delete from book where id=?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted !!!!");
					table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbookname.requestFocus();
					
				}
				catch (SQLException e1) 
				{
			      e1.printStackTrace();
				}
			}
			
		});
		btnDelite.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelite.setBounds(593, 415, 133, 69);
		frame.getContentPane().add(btnDelite);
	}
}
