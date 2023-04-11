package MyProject;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * --------------------------------------- 
 * @author 고수림
 * 작성일: 2023-04-06
 * 버 전 : 1.0
 * MyClosetApp의 옷 목록 페이지(List Page) 화면과 기능을 구현하는 클래스
 * --------------------------------------
 */
public class ListPage extends JPanel implements ActionListener {

	JButton btSearch,btDelete,btRefresh; // 검색/ 삭제/ 새로고침
	JButton btBack;
	JTextArea ta; // 중앙
	JLabel lbTitle, lbSearch;
	JTextField tfSearch;
	JScrollPane scrollPane;
	
	MyClosetDAO dao = new MyClosetDAO();
	
	public ListPage() {
		this.setLayout(null);//레이아웃 해제
		
		// 'List' 타이틀 넣기
		lbTitle = new JLabel("List");
	    this.add(lbTitle,BorderLayout.CENTER);
	    lbTitle.setBounds(120, 50, 300, 100);
	    lbTitle.setFont(new Font("Serif", Font.BOLD, 50));
		
	    // Back 버튼
	    btBack = new JButton("◀");
		btBack.setBounds(20,20,50,50);
		btBack.setBackground(Color.lightGray);
		btBack.setForeground(Color.black);
		add(btBack);

		//Name ▶
		lbSearch = new JLabel("Name ▶ ");
	    lbSearch.setBounds(10,112,300,100);
	    lbSearch.setFont(new Font("Serif", Font.BOLD, 14));
		this.add(lbSearch);
		
	    tfSearch = new JTextField(15);	
		tfSearch.setBounds(70,153,173,23); 
		this.add(tfSearch);
		
		// 검색 버튼
		btSearch = new JButton ("Search");
		btSearch.setBounds(249,153,80,23);
		btSearch.setFont(new Font("Serif", Font.BOLD, 15));
		this.add(btSearch);
		btSearch.addActionListener(this);
		
		// Refresh 버튼
	    btRefresh = new JButton("\u21BB");
		btRefresh.setBounds(270,470,50,50);
		btRefresh.setBackground(Color.pink);
		btRefresh.setForeground(Color.black);
		this.add(btRefresh);
		btRefresh.addActionListener(this);
		
		// 삭제 버튼
		btDelete = new JButton ("Delete");
		btDelete.setBounds(249,435,80,23);
		btDelete.setFont(new Font("Serif", Font.BOLD, 15));
		this.add(btDelete);
		btDelete.addActionListener(this);

		// JTextArea ta에 현재 갖고 있는 List보여주기
		ta = new JTextArea();
		scrollPane = new JScrollPane(ta);
		scrollPane.setBounds(8, 190, 320, 230);
		this.add(scrollPane);
		
		try {
			this.showTextArea(dao.list());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
    
    /**전체 메모 글을 TextArea에 출력해주는 메서드*/
	public void showTextArea(ArrayList<MyClosetVO> list) {

		ta.setText("");
		for(MyClosetVO vo:list) {
			ta.append(vo.getSort()+" || "+vo.getName()+" || "+vo.getBrand()+" || "+vo.getColor()+" || "+vo.getPrice()+"원\n");
			ta.append("");
		}//for-------
	}
	//--------------------------------
    /** btRefresh, btSearch, btDelete 버튼 기능 설정
	 * */
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btRefresh) {
    		try {
    			this.showTextArea(dao.list());
    		} catch (SQLException ee) {
    			ee.printStackTrace();
    		}
    		
    	} else if(e.getSource() == btSearch) {
    		try {
    			ArrayList<MyClosetVO> list = dao.find(tfSearch.getText());
    			if(list==null||list.size()==0) {
    				JOptionPane.showMessageDialog(this, tfSearch.getText()+"로 검색한 결과 해당 물건은 없어요");
    				return;
    			}
    			this.showTextArea(list);
    		}catch(SQLException ee) {
    			ee.printStackTrace();
    		}
    		
    	} else if(e.getSource() == btDelete) {
    		String keyword = JOptionPane.showInputDialog("삭제하실 물건의 이름을 입력해주세요");
    		try {
				dao.delete(keyword);
				this.showTextArea(dao.list());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	}
    
    }
    
    
    
    
}

