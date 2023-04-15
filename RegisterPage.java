package MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.sql.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 옷종류별(Outer,Top,Bottoms,Shoes) 등록 페이지(Register Page)를 구성하는  클래스
 * --------------------------------------
 */
public class RegisterPage extends JPanel implements ActionListener {

	MyClosetDAO dao = new MyClosetDAO();//Model
	
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
			} catch (NameLengthExceededException e1) {
				e1.printStackTrace();
			}
		     reset();
		     
		 } else if(obj==btCc) { // 작성 후 취소하기
			 
			 JOptionPane.showMessageDialog(this, "취소되었습니다.");
		     reset();
		     
		     }

	} 
	//----------------------------------
	public void confirm() throws NameLengthExceededException {
		//[1] text field에  입력한 값 얻어오기
		String name = tfName.getText();
		String brand = tfBrand.getText();
		String color = tfColor.getText();
		int price = Integer.parseInt(tfPrice.getText());

		if(name.length() > 20) {
			try {
		        throw new NameLengthExceededException("이름은 20자 이내로만 등록이 가능합니다.");
		    } catch(NameLengthExceededException ex) {
		        JOptionPane.showMessageDialog(this, "이름은 20자 이내로만 등록이 가능합니다.");
		        return;
		    }
		}
		
		//[2] 유효성 체크(null,빈문자열 체크)
		if(name==null||brand==null||color==null||price==0||name.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "다 입력해주셔야 합니다.");
			return;
		}
		
		MyClosetApp my = MyClosetApp.getInstance();
		String sort = my.getSort();
	
		//[3] [1] 번에서 받은 값을 MyClosetVO객체에 담아 준다
		MyClosetVO closet = new MyClosetVO(sort,name,brand,color,price);
		
		//[4] dao의 register(MyClosetVO closet) 호출한다
		try {
			int result = dao.register(closet);
			
			//[5] 그 결과값에 따라 메시지 처리
			if(result>0) {
			JOptionPane.showMessageDialog(this, "등록되었습니다.");		
			} else {
			JOptionPane.showMessageDialog(this, "등록 실패하셨습니다.");		
			}
		
		} catch(SQLException e) {
			
		}
	}//--------------------
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
}
