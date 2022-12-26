package com.barunsw.ojt.yhkim.day10;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GroupBookImpl implements GroupBookInterface {
	private static Logger LOGGER = LogManager.getLogger(GroupBookImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<GroupBookVo> selectGroupBook(GroupBookVo paramData) {
		List<GroupBookVo> groupBookList = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupBookInterface mapper = session.getMapper(GroupBookInterface.class);

			groupBookList = mapper.selectGroupBook(new GroupBookVo());

		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return groupBookList;	
	}

	@Override
	public int insertGroupBook(GroupBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupBookInterface mapper = session.getMapper(GroupBookInterface.class);

			result = mapper.insertGroupBook(paramData);
			LOGGER.debug("INSERT : "+result);

			session.commit();
		} 
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

	@Override
	public int updateGroupBook(GroupBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupBookInterface mapper = session.getMapper(GroupBookInterface.class);

			result = mapper.updateGroupBook(paramData);
			LOGGER.debug("UPDATE : "+result);

			session.commit();
		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

	@Override
	public int deleteGroupBook(GroupBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupBookInterface mapper = session.getMapper(GroupBookInterface.class);

			result = mapper.deleteGroupBook(paramData);
			LOGGER.debug("DELETE : "+result);

			session.commit();
		}		
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

	@Override
	public GroupBookVo selectOneGroup(GroupBookVo paramData) {
		GroupBookVo g = new GroupBookVo();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			GroupBookInterface mapper = session.getMapper(GroupBookInterface.class);

			g = mapper.selectOneGroup(paramData);			

		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		
		return g;
	}

}
