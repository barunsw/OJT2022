package com.barunsw.ojt.jmlee.day10;

import java.io.Serializable;

public class GroupVo implements Serializable {

	private int group_id;
	private String group_name;
	private int parent_group_id;
	
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getParent_group_id() {
		return parent_group_id;
	}
	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}
	
	public String toString() {
		return String.format("%s", group_name);
	}
		
}
