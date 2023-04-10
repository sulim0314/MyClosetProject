package MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 화면 전환을 담당하는 클래스 + 메인 클래스
 * --------------------------------------
 */
public class MyClosetApp extends JFrame implements ActionListener {
	
	CardLayout card;
	Container cp;
	ManagePage m = new ManagePage();// 메인 페이지
	RegisterMain rm = new RegisterMain();// '옷장에 넣기' 메인 페이지
	RegisterPage rp = new RegisterPage(); // 아우터,상의,하의,신발 등록 페이지
	ListPage lp = new ListPage();// 목록 보여주기
	StylingPage sp = new StylingPage();
	
	private String sort; // 분류 (예: 아우터, 상의, 하의, 신발)
	private static MyClosetApp instance;
	
	public MyClosetApp() {
		
		super("::MyClosetApp::");
		cp = this.getContentPane();
		card=new CardLayout();
		cp.setLayout(card);
		
		cp.add(m, "Manage Main Page");
			cp.add(rm,"Register Main Page");
				cp.add(rp,"Register Page");
			cp.add(lp,"List Page");
			cp.add(sp,"Styling Page");
			
		m.setBackground(new Color(0,0,200,50));
		rm.setBackground(new Color(0,0,200,50));
		rp.setBackground(new Color(0,0,200,50));
		lp.setBackground(Color.pink);
		sp.setBackground(Color.pink);

		rm.btOuter.addActionListener(this);
		rm.btTop.addActionListener(this);
		rm.btBottoms.addActionListener(this);
		rm.btShoes.addActionListener(this);
		
		rm.btBack.addActionListener(this);
		rp.btBack.addActionListener(this);
		m.btRegister.addActionListener(this);
		m.btList.addActionListener(this);
		lp.btBack.addActionListener(this);
		m.btStyling.addActionListener(this);
		sp.btBack.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // -------------------------------------------------------------
	@Override
	public void actionPerformed (ActionEvent e) {
		/** 'Outer','Top','Bottoms','Shoes' 각 버튼들을 누를 때마다
		 *  sort가 해당 값으로 설정되고, 원래 있던 Register Page의 라벨을 지우고,
		 *  새로운 페이지에 해당하는 라벨을 설정한다.
		 * */
		if (e.getSource() == rm.btOuter) {
			setSort("아우터");
	        card.show(cp, "Register Page");
	        JLabel oldLabel = (JLabel) rp.getClientProperty("currentLabel");
	        if (oldLabel != null) {
	            rp.remove(oldLabel);
	        }
	        JLabel newLabel = new JLabel("Outer");
	        newLabel.setBounds(102, 50, 300, 100);
	        newLabel.setFont(new Font("Serif", Font.BOLD, 50));
	        rp.add(newLabel, BorderLayout.CENTER);
	        rp.putClientProperty("currentLabel", newLabel);
			
		} else if (e.getSource() == rm.btTop) {
			setSort("상의");
	        card.show(cp, "Register Page");
	        JLabel oldLabel = (JLabel) rp.getClientProperty("currentLabel");
	        if (oldLabel != null) {
	            rp.remove(oldLabel);
	        }
	        JLabel newLabel = new JLabel("Top");
	        newLabel.setBounds(120, 50, 300, 100);
	        newLabel.setFont(new Font("Serif", Font.BOLD, 50));
	        rp.add(newLabel, BorderLayout.CENTER);
	        rp.putClientProperty("currentLabel", newLabel);

		} else if (e.getSource() == rm.btBottoms) {
			setSort("하의");
	        card.show(cp, "Register Page");
	        JLabel oldLabel = (JLabel) rp.getClientProperty("currentLabel");
	        if (oldLabel != null) {
	            rp.remove(oldLabel);
	        }
	        JLabel newLabel = new JLabel("Bottoms");
	        newLabel.setBounds(78, 50, 300, 100);
	        newLabel.setFont(new Font("Serif", Font.BOLD, 50));
	        rp.add(newLabel, BorderLayout.CENTER);
	        rp.putClientProperty("currentLabel", newLabel);
	        
		} else if (e.getSource() == rm.btShoes) {
			setSort("신발");
	        card.show(cp, "Register Page");
	        JLabel oldLabel = (JLabel) rp.getClientProperty("currentLabel");
	        if (oldLabel != null) {
	            rp.remove(oldLabel);
	        }
	        JLabel newLabel = new JLabel("Shoes");
	        newLabel.setBounds(102, 50, 300, 100);
	        newLabel.setFont(new Font("Serif", Font.BOLD, 50));
	        rp.add(newLabel, BorderLayout.CENTER);
	        rp.putClientProperty("currentLabel", newLabel);
	        
		} else if(e.getSource() == m.btRegister) {
			card.show(cp,"Register Main Page");
		} else if (e.getSource() == rm.btBack) {
			card.show(cp,"Manage Main Page");
		} else if (e.getSource() == rp.btBack) {
			card.show(cp,"Register Main Page");
		} else if (e.getSource() == m.btList) {
			card.show(cp,"List Page");
		} else if (e.getSource() == lp.btBack) {
			card.show(cp,"Manage Main Page");
		} else if (e.getSource() == m.btStyling) {
			card.show(cp,"Styling Page");
		} else if (e.getSource() == sp.btBack) {
			card.show(cp,"Manage Main Page");
		}

	}

	/** sort를 다른 클래스에서 가져오기 위한 setSort(), getSort() 메서드
	* */
	public void setSort(String sort) {
		this.sort = sort;
	}	
	public String getSort() {
	    return sort;
	}
		
	/** 인스턴스 생성 메서드 getInstance() 추가하는 메서드
	* */ 
	public static MyClosetApp getInstance() {
	    if (instance == null) {
	    	instance = new MyClosetApp();
	    }
	    return instance;
	}
		
	public static void main(String[] args) {
		MyClosetApp my = MyClosetApp.getInstance();
		my.setSize(350, 600);
		my.setVisible(true);
	}
	
}


