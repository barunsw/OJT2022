package com.barunsw.ojt.yhkim.day09;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class CommonTableModel extends AbstractTableModel {
	private Vector<String> columnData = new Vector();
	private Vector<Vector> data = new Vector<>();
	
	public void setColumnData(Vector<String> columnData) {
		this.columnData = columnData;
	}
	
	public void setData(Vector<Vector> data) {
		this.data = data;
	}
	
	// 한 사람의 정보를 받아 data에 추가
	public void addData(Vector oneData) {
		data.add(oneData);
	}
	
	public void addData(Vector oneData, int index) {
		data.add(index, oneData);
	}
	
	@Override
	public String getColumnName(int index) {
		if (index < columnData.size()) {
			return columnData.get(index);
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
 
	@Override
	public int getColumnCount() {
		return columnData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < getRowCount() && columnIndex < getColumnCount()) {
			return data.get(rowIndex).get(columnIndex);
		}	
		return null;
	}
}