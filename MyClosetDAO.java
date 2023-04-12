package MyProject;
import java.sql.*;
import common.util.*;
import java.util.*;

public class MyClosetDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * List에 register문을 수행하는 메서드
	 */
	public int register(MyClosetVO closet) throws SQLException {
		//ClassNotFoundException,
		try {
			con=DBUtil.getCon();
			StringBuilder buf=new StringBuilder("INSERT INTO CLOSET(SORT,NAME,BRAND,COLOR,PRICE)")
										.append(" values(?,?,?,?,?)");
					
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, closet.getSort());
			ps.setString(2, closet.getName());
			ps.setString(3, closet.getBrand());
			ps.setString(4, closet.getColor());
			ps.setInt(5, closet.getPrice());
			sql += "commit";
			
			int n = ps.executeUpdate();
			return n;
			
		}finally {
			//db 연결자원 반납
			close();
		}
	}//----------------------------
	
	/**전체 List를 가져오는 메서드
	 * */
	public ArrayList <MyClosetVO> list() throws SQLException{
		try {
			con = DBUtil.getCon();
		      
			String sql = "SELECT * FROM CLOSET";
		    System.out.println(sql);
		    ps = con.prepareStatement(sql);
		    rs = ps.executeQuery();
		    
		    ArrayList<MyClosetVO> list = makeList(rs);
		    return list;
		    
		}finally {
			close();
		}
	}//--------------------------------
	
	public ArrayList <MyClosetVO> makeList(ResultSet rs) throws SQLException{
		ArrayList <MyClosetVO> list =new ArrayList<>();
		
	    while(rs.next()) {
	    	String sort = rs.getString("sort");
	    	String name = rs.getString("name");
	    	String brand = rs.getString("brand");
	    	String color = rs.getString("color");
	    	int price = rs.getInt("price");

	    	MyClosetVO closet =new MyClosetVO(sort,name,brand,color,price); //record
	    	list.add(closet); //table
	    }//while----
	    return list;
	}//-----------------------------------------

	/** keyword로 이름을 검색하는 메서드
	 * */
	public ArrayList<MyClosetVO> find(String keyword) throws SQLException{
		try {
			con = DBUtil.getCon();

			String sql = "SELECT * FROM CLOSET WHERE NAME LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();
			
			ArrayList<MyClosetVO> list = makeList(rs);
			return list;
			
		}finally {
			close();
		}
	}//--------------------------------------------
	/**
	 * keyword로 물건을 삭제하는 메서드
	 */
	public int delete(String keyword) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="DELETE FROM CLOSET WHERE NAME LIKE ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, keyword);
			return ps.executeUpdate();		
			
		} finally {
			close();
		}
	}//---------------------------------
	/**
	 * DB관련한 자원들을 반납하는 메서드
	 */
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}//---------------------------------
	
}//////////////////////////////
