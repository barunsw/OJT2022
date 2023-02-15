package com.barunsw.ojt.jmlee.day19;

import java.rmi.Remote;
import java.util.List;

import com.barunsw.ojt.vo.BoardVo;

public interface ServerInterface extends Remote {
	public int register(ClientInterface clientInterface) throws Exception;
	public List<BoardVo> selectBoardList() throws Exception;
}
