package MyProject;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

	
	private String titleArray[] = {"Sort", "Name", "Brand", "Color", "Price"};
	// SORT 값을 저장할 ArrayList 선언
	private ArrayList<String> topName = new ArrayList<String>(); 
	private ArrayList<String> bottomsName = new ArrayList<String>();
	private ArrayList<String> shoesName = new ArrayList<String>();
	
	JButton btSearch,btDelete,btRefresh; // 검색/ 삭제/ 새로고침
	JButton btBack;
	JTextArea ta; // 중앙
	JLabel lbTitle, lbSearch;
	JTextField tfSearch;
	JTable table; // JTable 추가
	JScrollPane scrollPane;
	
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
		
		// 삭제 버튼
		btDelete = new JButton ("Delete");
		btDelete.setBounds(249,435,80,23);
		btDelete.setFont(new Font("Serif", Font.BOLD, 15));
		this.add(btDelete);
		
		/** 목록 삭제하기
		 * */
		btDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("어떤 것을 삭제하시겠습니까? 이름키워드를 입력해주세요.");
				
		        if (name == null) {
		            JOptionPane.showMessageDialog(null, "다시 입력해주세요.");
		        } else if (name.equals("")) {
		        	JOptionPane.showMessageDialog(null, "다시 입력해주세요.");
		        } else {
		        	try {
		                // SQL 연결
		                Class.forName("oracle.jdbc.driver.OracleDriver");    
		                String url = "jdbc:oracle:thin:@localhost:1521:XE";
		                String user = "scott", pwd = "tiger";
		                Connection con = DriverManager.getConnection(url, user, pwd);
		                
		                // 쿼리 실행
		                String sql = "DELETE FROM CLOSET WHERE NAME LIKE ?";
		                PreparedStatement ps = con.prepareStatement(sql);
		                ps.setString(1, "%" + name + "%");
		                ps.executeUpdate();

		                // 자원 해제
		                ps.close();
		                con.close();

		                JOptionPane.showMessageDialog(null, "삭제되었습니다.");
		            } catch (ClassNotFoundException | SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
			}
		}); 
	
		
		/** SQL에서 테이블 가져오기
		 * */
	    try { 
	    	// SQL 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "scott", pwd = "tiger";
			Connection con = DriverManager.getConnection(url, user, pwd);
			
		    // 쿼리 실행
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM CLOSET");

		    // 테이블 모델 생성
		    DefaultTableModel model = new DefaultTableModel(titleArray, 0);

		    // 검색된 데이터를 모델에 추가
		    while (rs.next()) {
		        String sort = rs.getString("SORT");
		        String name = rs.getString("NAME");
		        String brand = rs.getString("BRAND");
		        String color = rs.getString("COLOR");
		        int price = rs.getInt("PRICE");
		        model.addRow(new Object[]{sort, name, brand, color, price});
		     // JTable의 데이터를 ArrayList에 추가
	           
		        if (sort != null && name != null) {
		            if (sort.equals("상의")) {
		                topName.add(name);
		            } else if (sort.equals("하의")) {
		                bottomsName.add(name);
		            } else if (sort.equals("신발")) {
		                shoesName.add(name);
		            }
		        }
		    }

		    // 자원 해제
		    rs.close();
		    stmt.close();
		    con.close();

		    // JTable 생성
		    table = new JTable(model);
		    table.setEnabled(false);
		    table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 비활성화
		    
		    // JScrollPane에 JTable 추가
		    scrollPane = new JScrollPane(table);
		    scrollPane.getViewport().setBackground(Color.white);
		    scrollPane.setBounds(1,180, 333, 250);
		    add(scrollPane);

		    table.setModel(model);
		    
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    
	}

	/** ArrayList 'Top'종류 getter 메서드
	 * */
    public ArrayList<String> getTopName() {
        return topName;
    }
    /** ArrayList 'Bottoms'종류 getter 메서드
	 * */
    public ArrayList<String> getBottomsName() {
        return bottomsName;
    }
    /** ArrayList 'Shoes'종류 getter 메서드
	 * */
    public ArrayList<String> getShoesName() {
        return shoesName;
    }
    
    /** 검색어를 가져온 뒤, SQL과 연결 후, 해당 검색어를 검색하여 테이블을 전환해주는 메서드.
	 * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 검색어 가져오기
        String searchKeyword = tfSearch.getText();
        
        try {
            // SQL 연결
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String user = "scott", pwd = "tiger";
            Connection con = DriverManager.getConnection(url, user, pwd);
            
            // 쿼리 실행
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CLOSET WHERE NAME LIKE ?");
            ps.setString(1, "%" + searchKeyword + "%");
            ResultSet rs = ps.executeQuery();

            // 테이블 모델 생성
            DefaultTableModel model = new DefaultTableModel(titleArray, 0);

            // 검색된 데이터를 모델에 추가
            while (rs.next()) {
                String sort = rs.getString("SORT");
                String name = rs.getString("NAME");
                String brand = rs.getString("BRAND");
                String color = rs.getString("COLOR");
                int price = rs.getInt("PRICE");
                model.addRow(new Object[]{sort, name, brand, color, price});
            }

            // 자원 해제
            rs.close();
            ps.close();
            con.close();

            // 기존의 JScrollPane에서 JTable 객체 제거
            this.remove(scrollPane);
            
            // 새로운 JTable 객체 생성
            JTable newTable = new JTable(model);
            newTable.setEnabled(false);
            newTable.getTableHeader().setReorderingAllowed(false);
            
            // JScrollPane에 새로운 JTable 객체 추가
            JScrollPane newScrollPane = new JScrollPane(newTable);
            newScrollPane.getViewport().setBackground(Color.white);
            newScrollPane.setBounds(1,180, 333, 250);
            add(newScrollPane);
            
            // 화면 갱신
            revalidate();
            repaint();
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
	
   
}

