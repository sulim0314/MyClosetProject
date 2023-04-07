package MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 옷 등록(메인) 페이지(Register Main Page) 화면을 디자인하는 클래스
 * --------------------------------------
 */
public class RegisterMain extends JPanel implements ActionListener {

	JButton btOuter,btTop,btBottoms,btShoes;
	JButton btBack;
	JLabel lbTitle;
	public RegisterMain() { //---------------------
		
		this.setLayout(null);//레이아웃 해제

		btOuter = new JButton("1. Outer");
		btTop = new JButton("2. Top");
		btBottoms = new JButton("3. Bottoms");
		btShoes = new JButton("4. Shoes");
		btBack = new JButton("◀");
		
		lbTitle = new JLabel("Register");
	    this.add(lbTitle,BorderLayout.CENTER);
	    lbTitle.setBounds(40, 72, 300, 100);
	    lbTitle.setFont(new Font("Serif", Font.BOLD, 50));
	
		//1. 아우터
		btOuter.setBounds(67,200,200,50);
		btOuter.setBackground(Color.pink);
		btOuter.setForeground(Color.black);
		btOuter.setFont(new Font("Serif", Font.BOLD, 17));
		
		//2. 상의
		btTop.setBounds(67,270,200,50);
		btTop.setBackground(Color.pink);
		btTop.setForeground(Color.black);
		btTop.setFont(new Font("Serif", Font.BOLD, 17));
		//3. 하의
		btBottoms.setBounds(67,340,200,50);
		btBottoms.setBackground(Color.pink);
		btBottoms.setForeground(Color.black);
		btBottoms.setFont(new Font("Serif", Font.BOLD, 17));
		//4. 신발
		btShoes.setBounds(67,410,200,50);
		btShoes.setBackground(Color.pink);
		btShoes.setForeground(Color.black);
		btShoes.setFont(new Font("Serif", Font.BOLD, 17));
		// ←
		btBack.setBounds(20,20,50,50);
		btBack.setBackground(Color.lightGray);
		btBack.setForeground(Color.black);

		add(btOuter);
		add(btTop);
		add(btBottoms);
		add(btShoes);
		add(btBack);
		
	
	}//----------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
}
