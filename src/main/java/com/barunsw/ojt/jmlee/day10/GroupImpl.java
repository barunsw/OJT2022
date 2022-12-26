package com.barunsw.ojt.jmlee.day10;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GroupImpl implements GroupInterface {
   	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupImpl.class);
	
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<GroupVo> selectGroup(GroupVo paramData) {
		
		List<GroupVo> groupBook = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupInterface mapper = session.getMapper(GroupInterface.class);
			
			groupBook = mapper.selectGroup(new GroupVo());
			
			for (GroupVo s : groupBook) {
				LOGGER.debug(s.toString());
			}
		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return groupBook;
	}

	@Override
	public int insertGroup(GroupVo paramData) throws Exception {
		// TODO Auto-generated method stub
		try (SqlSession session = sqlSessionFactory.openSession()) {
			System.out.println("insert 호출");
			GroupInterface mapper = session.getMapper(GroupInterface.class);
			
			mapper.insertGroup(paramData);
			
			session.commit();
			System.out.println("insert end");	
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		
		
		return 0;
	}

	@Override
	public int updateGroup(GroupVo paramData) throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("update 호출");
			GroupInterface mapper = session.getMapper(GroupInterface.class);
			
			mapper.updateGroup(paramData);
			session.commit();
			LOGGER.debug("update end");	
		}		
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

	@Override
	public int deleteGroup(GroupVo paramData) throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("Delete 호출");
			GroupInterface mapper = session.getMapper(GroupInterface.class);
			
			mapper.deleteGroup(paramData);
			session.commit();
			LOGGER.debug("Delete end");	
		}				
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

	@Override
	public GroupVo selectOneGroup(GroupVo paramData) {
		GroupVo groupList = new GroupVo();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupInterface mapper = session.getMapper(GroupInterface.class);
			groupList = mapper.selectOneGroup(paramData);
		}
		return null;
	}

}
