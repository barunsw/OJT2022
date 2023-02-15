package com.barunsw.ojt.jmlee.day21;

import java.rmi.Remote;

import com.barunsw.ojt.vo.BoardVo;

public interface ClientInterface extends Remote{
	public void pushAlarm(BoardVo boardVo) throws Exception;
}
