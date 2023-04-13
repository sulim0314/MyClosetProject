package MyProject;

import java.sql.Date;
/**
 * @author a
 * Domain 객체-- Model에 속한다
 * VO(Value Object) : DB에서 가져온 값이나, UI통해 입력받은 값을 담고 있는 객체
 * memo테이블에 있는 컬럼들==> class의 member변수로 옮겨 구성
 */

public class MyClosetVO {

	private String sort;
	private String name;
	private String brand;
	private String color;
	private int price;
		
	public MyClosetVO() {
		this(null,null,null,null,0);
	}
	public MyClosetVO(String sort, String name, String brand, String color, int price) {
		super();
		this.sort = sort;
		this.name = name;
		this.brand = brand;
		this.color = color;
		this.price = price;
	}
	
	/** getter, setter
	 * */
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}////////////////////////////


