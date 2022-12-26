package com.barunsw.ojt.yhkim.day10;

import java.util.List;



public interface GroupBookInterface {
	public List<GroupBookVo> selectGroupBook(GroupBookVo paramData);
	public GroupBookVo selectOneGroup(GroupBookVo paramData);
	public int insertGroupBook(GroupBookVo paramData) throws Exception;
	public int updateGroupBook(GroupBookVo paramData) throws Exception;
	public int deleteGroupBook(GroupBookVo paramData) throws Exception;
}
