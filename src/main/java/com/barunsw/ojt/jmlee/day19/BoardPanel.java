package com.barunsw.ojt.jmlee.day19;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.vo.BoardVo;

public class BoardPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(BoardPanel.class);
	
	public static final int BOARD_WIDTH 	= 40;
	public static final int BOARD_HEIGHT	= 271;
	
	public static final int MPU_BOARD_HEIGHT	= 550;
	
	private BoardVo boardVo;
	
	public BoardPanel(BoardVo boardVo) {
		LOGGER.debug("board Panel");
		this.boardVo = boardVo;
		
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() throws Exception {
		this.setToolTipText(boardVo.getBoardName());
	}
	
	public BoardVo getBoardVo() {
		return boardVo;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		LOGGER.debug("paintComponent boardName:" + boardVo.getBoardName());
		
		int severity = boardVo.getSeverity();
		
		switch (boardVo.getBoardType()){
		case MPU: 
			g.drawImage(ImageFactory.mpuImageIcon[severity].getImage(),
					0, 
					0,
					this);
			break;
		case SALC:
			g.drawImage(ImageFactory.salcImageIcon[severity].getImage(),
					0, 
					0,
					this);
			break;
		case SRGU:
			g.drawImage(ImageFactory.srguImageIcon[severity].getImage(),
					0, 
					0,
					this);
			break;
		}
	}
	
	public int getBoardWidth() {
		int width = 0;
		
		switch (boardVo.getBoardType()) {
		case MPU:
		case SALC:
			width = BOARD_WIDTH;
			break;
		case SRGU:
			width = BOARD_WIDTH * 2;
			break;
		}
		
		return width;
	}
	
	public int getBoardHeight() {
		int height = 0;
		
		switch (boardVo.getBoardType()) {
		case MPU:
			height = MPU_BOARD_HEIGHT;
			break;
		case SALC:
		case SRGU:
			height = BOARD_HEIGHT;
			break;
		}
		
		return height;	
	}
}
