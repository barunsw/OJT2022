package com.barunsw.ojt.jmlee.day10;

import java.util.List;


public interface GroupInterface {

	public List<GroupVo> selectGroup(GroupVo paramData);
	public GroupVo selectOneGroup(GroupVo paramData);
	public int insertGroup(GroupVo paramData) throws Exception;
	public int updateGroup(GroupVo paramData) throws Exception;
	public int deleteGroup(GroupVo paramData) throws Exception;
	
}
