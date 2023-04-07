package MyProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 코디 추천 페이지(Styling Page) 화면과 기능을 구현하는 클래스
 * --------------------------------------
 */
public class StylingPage extends JPanel implements ActionListener {
	
	JLabel lbTitle, lbTitle2;
	JButton btBack, btRefresh;
	JLabel top = new JLabel("");
	JLabel bottoms = new JLabel("");
	JLabel shoes = new JLabel("");
	JLabel plus1  = new JLabel("");
	JLabel plus2  = new JLabel("");
	
	public StylingPage() {

		this.setLayout(null);//레이아웃 해제
		
		// 'List' 타이틀 넣기
		lbTitle = new JLabel("Today's");
		lbTitle2 = new JLabel("Styling");
	    this.add(lbTitle,BorderLayout.CENTER);
	    this.add(lbTitle2,BorderLayout.CENTER);
	    lbTitle.setBounds(70, 50, 300, 100);
	    lbTitle.setFont(new Font("Serif", Font.BOLD, 55));
	    lbTitle2.setBounds(120, 115, 300, 100);
	    lbTitle2.setFont(new Font("Serif", Font.BOLD, 55));
		
	    // Back 버튼
	    btBack = new JButton("◀");
		btBack.setBounds(20,20,50,50);
		btBack.setBackground(Color.lightGray);
		btBack.setForeground(Color.black);
		add(btBack);
		
		// Refresh 버튼
	    btRefresh = new JButton("\u21BB");
		btRefresh.setBounds(270,450,50,50);
		btRefresh.setBackground(Color.pink);
		btRefresh.setForeground(Color.black);
		add(btRefresh);
		btRefresh.addActionListener(this);
		
		// 스타일링 추천
		recommendation();

	}
	/** Styling을 추천해주는 메서드.
	 * ListPage 클래스의 getter 메서드를 호출해서 각 '상의','하의','신발'List를 가져온다.
	 * 그 후 Math.random()을 이용하여 랜덤으로 코디를 추천해준다.
	 * */
	public void recommendation() {
		
		// 추가된 라벨 제거
	    this.remove(top);
	    this.remove(bottoms);
	    this.remove(shoes);
	    this.remove(plus1);
	    this.remove(plus2);
	    
	    // ListPage 클래스의 getter 메서드 호출해서 ArrayList 가져오기
	    ListPage listPage = new ListPage();
	    ArrayList<String> topName = listPage.getTopName();
	    ArrayList<String> bottomsName = listPage.getBottomsName();
	    ArrayList<String> shoesName = listPage.getShoesName();
	        
	    //(상의 + 하의 + 신발)코디를 랜덤으로 추천해주기.
	    int i = (int)(Math.random() * topName.size());
	    top.setText("< Top >  "+ topName.get(i));
			
	    int j = (int)(Math.random() * bottomsName.size());
	    bottoms.setText("< Bottoms >  "+ bottomsName.get(j));
			
	    int k = (int)(Math.random() * shoesName.size());
	    shoes.setText("< Shoes >  "+ shoesName.get(k));
			
	    this.add(top);
	    this.add(bottoms);
	    this.add(shoes);
	    top.setBounds(25,220, 300, 100);
	    top.setFont(new Font("Serif", Font.BOLD, 18));
	    bottoms.setBounds(25,280, 300, 100);
	    bottoms.setFont(new Font("Serif", Font.BOLD, 18));
	    shoes.setBounds(25,340, 300, 100);
	    shoes.setFont(new Font("Serif", Font.BOLD, 18));
			
	    plus1 = new JLabel("+");
	    plus2 = new JLabel("+");
	    this.add(plus1);
	    this.add(plus2);
	    plus1.setBounds(70,250, 300, 100);
	    plus1.setFont(new Font("Serif", Font.BOLD, 20));
	    plus2.setBounds(70,310, 300, 100);
	    plus2.setFont(new Font("Serif", Font.BOLD, 20));

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		recommendation();
	}


	
}
