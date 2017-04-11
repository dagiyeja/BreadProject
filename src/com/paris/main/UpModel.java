/*
 * ���� ī�װ����� �� ī�װ����� ��ϵ� ��ǰ�� �� ������ �����ϴ� ��
 * */

package com.paris.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class UpModel extends AbstractTableModel{
	Vector<String> columnName=new Vector<String>();
	Vector<Vector> data=new Vector<Vector>(); //������ ����
	Connection con;
	
	public UpModel(Connection con) {
		this.con=con;
		getList();
	}
	
	
	//��� ��������
	public void getList(){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append("select s.subcategory_id as subcategory_id, sub_name as ī�װ�����, count(product_name) as ����");
		sql.append(" from subcategory s left outer join product p");
		sql.append(" on s.subcategory_id=p.subcategory_id");
		sql.append(" group by s.subcategory_id, sub_name");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			//���͵��� �ʱ�ȭ
			columnName.remove(columnName);
			data.removeAll(data);
			
			//�÷��� ����
			ResultSetMetaData meta=rs.getMetaData();
			
			for(int i=1; i<=meta.getColumnCount(); i++){
				columnName.add(meta.getColumnName(i));
			}
			
			
			
			
			while(rs.next()){
				//���ڵ� �Ѱ��� ���Ϳ� �Űܽ���
				//���⼭�� ���ʹ� DTO ����..
				Vector vec=new Vector();
				
				vec.add(rs.getString("subcategory_id"));
				vec.add(rs.getString("ī�װ�����"));
				vec.add(rs.getString("����"));
				
				data.add(vec);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getColumnCount() {
		return columnName.size();
	}

	
	public int getRowCount() {
		
		return data.size();
	}
	

	public String getColumnName(int col) {
		return columnName.get(col);
	}

	public Object getValueAt(int row, int col) {
		
		return data.get(row).get(col);
	}

}