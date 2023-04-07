package MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.sql.*;
import java.util.ArrayList;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 옷종류별(Outer,Top,Bottoms,Shoes) 등록 페이지(Register Page)를 구성하는  클래스
 * --------------------------------------
 */
public class RegisterPage extends JPanel implements ActionListener {

	JTextField tfName; // 명칭
	JTextField tfBrand; // 브랜드
	JTextField tfColor; // 색깔
	JTextField tfPrice; // 가격
	
	JButton btRg; // 등록 버튼
	JButton btCc; // 취소 버튼

	JButton btBack; // 뒤로가기
	
	public RegisterPage() { 
		this.setLayout(null);//레이아웃 해제
		
		tfName = new JTextField(20);
		tfBrand = new JTextField(20);
		tfColor = new JTextField(20);
		tfPrice = new JTextField(20);
		
		btRg = new JButton("Register");
		btCc = new JButton("Cancle");
		btBack = new JButton("◀");
		
		tfName.setBounds(67, 150, 200,50);//x,y, width,height
		tfBrand.setBounds(67, 220, 200, 50);
		tfColor.setBounds(67, 290, 200, 50);
		tfPrice.setBounds(67, 360, 200, 50);
		
		btRg.setBounds(60, 430,100,50);
		btCc.setBounds(170, 430,100,50);
		
		btRg.setBackground(new Color(110,0,110));
		btCc.setBackground(new Color(110,0,110));
		
		btRg.setForeground(Color.white);
		btCc.setForeground(Color.white);

		tfName.setBorder(new TitledBorder("< Name >"));
		tfBrand.setBorder(new TitledBorder("< Brand >"));
		tfColor.setBorder(new TitledBorder("< Color >"));
		tfPrice.setBorder(new TitledBorder("< Price >"));
		
		// ←
		btBack.setBounds(20,20,50,50);
		btBack.setBackground(Color.lightGray);
		btBack.setForeground(Color.black);
		
		add(tfName);
		add(tfBrand);
		add(tfColor);
		add(tfPrice);
		add(btRg);
		add(btCc);
		add(btBack);
		
		btRg.addActionListener(this);
		btCc.addActionListener(this);
	
		
	} //----------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e){
		/** 등록하기 버튼을 누르면 SQL에 저장되는 기능을 구현하는 메서드가 실행됨.
		 *  취소하기 버튼을 누르면 TestField가 초기화되는 기능을 구현하는 메서드가 실행됨.
		 * */
		Object obj=e.getSource();
		
		 if(obj==btRg) { // 작성 후 등록하기
		        
		        try {
		            confirm();
		        } catch (ClassNotFoundException | SQLException | InvalidPriceException e1) {
		            JOptionPane.showMessageDialog(btCc, e1.getMessage());
		            return;
		        }
		             
		        JOptionPane.showMessageDialog(btCc, "등록되었습니다.");
		        reset();
		        
		    } else if(obj==btCc) { // 작성 후 취소하기
		        reset();
		    }
		
	} 
	/** 등록하기 버튼을 누르면 SQL에 저장되는 기능을 구현하는 메서드.
	 * */
	public void confirm() throws ClassNotFoundException, SQLException, InvalidPriceException { 
		
		try {
	        int price = Integer.parseInt(tfPrice.getText());
	    } catch (NumberFormatException e) {
	        throw new InvalidPriceException("< Price > 에 숫자만 입력해주세요.");
	    }
		
		// SQL 에 데이터 넣기.
		Class.forName("oracle.jdbc.driver.OracleDriver");	
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "scott", pwd = "tiger";
		Connection con = DriverManager.getConnection(url, user, pwd);
							
		String sql = "insert into closet(sort,name,brand,color,price)";	
			   sql += "values(?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		
		MyClosetApp MCA = MyClosetApp.getInstance();
		ps.setString(1, MCA.getSort());
		ps.setString(2, tfName.getText());
		ps.setString(3, tfBrand.getText());
		ps.setString(4, tfColor.getText());
		ps.setInt(5, Integer.parseInt(tfPrice.getText()));
		
		String sql2 = "commit"; 
		Statement stmt = con.createStatement();		
		stmt.execute(sql2);
		
		int n = ps.executeUpdate();

		if(ps != null) ps.close();
		if(stmt != null) stmt.close();
		if(con != null) con.close();
		
	} 
	/**  취소하기 버튼을 누르면 TestField가 초기화되는 기능을 구현하는 메서드.
	 * */
	public void reset() {
		// 작성 후 취소하기 (모두 초기화)
		tfName.setText("");
		tfBrand.setText("");
		tfColor.setText("");
		tfPrice.setText("");
		tfName.requestFocus(); //입력 포커스 주기
	} 
	
	/** 사용자 정의 Exception으로, <Price>에 숫자가 아닌 글자가 오면 오류 메세지를 띄움.
	 * */
	public class InvalidPriceException extends Exception {
	    public InvalidPriceException(String message) {
	        super(message);
	    }
	}
	
}
