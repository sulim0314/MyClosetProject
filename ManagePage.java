package MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 메인 화면(Manage Page)을 디자인하는 클래스
 * --------------------------------------
 */
public class ManagePage extends JPanel  implements ActionListener {

	JButton btRegister,btList,btStyling;
	JLabel lbTitle1,lbTitle2 ;
	
	public ManagePage() {
		this.setLayout(null);//레이아웃 해제
		
	    lbTitle1 = new JLabel("My");
	    lbTitle2 = new JLabel("Closet");
	    this.add(lbTitle1,BorderLayout.NORTH);
	    this.add(lbTitle2,BorderLayout.NORTH);
	    lbTitle1.setBounds(50, 30, 300, 100);
	    lbTitle1.setFont(new Font("Serif", Font.BOLD, 80));
	    lbTitle2.setBounds(100,120, 300, 100);
	    lbTitle2.setFont(new Font("Serif", Font.BOLD, 70));
	    
		btRegister = new JButton("Register");
		btList = new JButton("List");
		btStyling = new JButton("Styling");
		
		//옷장에 넣기
		btRegister.setBounds(67,250,200,50);
		btRegister.setBackground(Color.pink);
		btRegister.setForeground(Color.black);
		btRegister.setFont(new Font("Serif", Font.BOLD, 17));
		//목록 보기
		btList.setBounds(67,320,200,50);
		btList.setBackground(Color.pink);
		btList.setForeground(Color.black);
		btList.setFont(new Font("Serif", Font.BOLD, 17));
		//코디 추천받기
		btStyling.setBounds(67,390,200,50);
		btStyling.setBackground(Color.pink);
		btStyling.setForeground(Color.black);
		btStyling.setFont(new Font("Serif", Font.BOLD, 17));
		add(btRegister);
		add(btList);
		add(btStyling);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}


}

