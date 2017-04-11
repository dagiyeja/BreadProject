//subcategory 레코드 한건을 담는 VO객체
package db;

//dto명은 테이블 명과 동일하게 한다
public class SubCategory {
	private int subcategory_id;
	private String sub_name;
	private int topcategory_id;
	
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	
}
