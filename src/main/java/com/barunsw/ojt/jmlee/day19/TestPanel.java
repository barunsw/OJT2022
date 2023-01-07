package com.barunsw.ojt.jmlee.day19;

import java.awt.Graphics;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.BoardType;
import com.barunsw.ojt.constants.Severity;
import com.barunsw.ojt.vo.BoardVo;

public class TestPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	public static final int WIDTH 	= 854;
	public static final int HEIGHT	= 604;
	
	public final int SLOT_NUM		= 20;
	
	public final int BOARD_START_X			= 27;
	public final int TOP_BOARD_START_Y		= 26;
	public final int BOTTOM_BOARD_START_Y	= 307;
	
	List<BoardVo> boardList;
	
	public TestPanel() {
		try {
			initComponent();
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	void initRepaint(BoardVo boardVo) {
		for (BoardVo board : boardList) {
			if (board.getBoardId() == boardVo.getBoardId()) {
				board.setSeverity(boardVo.getSeverity());
			}
		}
		repaint();
	}

	private void initComponent() throws Exception {
		this.setLayout(null);
	}
	
	private List<BoardVo> getBoardData() {
		try {
			ClientInterface client = new ClientImpl(this);
			Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);

			Remote remote = registry.lookup("BOARD");
			ServerInterface boardIf = null;
			if (remote instanceof ServerInterface) {
				boardIf = (ServerInterface) remote;
				
				boardList = boardIf.selectBoardList(new BoardVo());
			}
			boardIf.register(client);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} LOGGER.debug("보드사이즈ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"+boardList.size());
				
		return boardList; // DB에서 가져온 데이터를 list로 넘겨줌
	}
	
	private void initData() {
		// 연동에 의해 board 정보 조회
		boardList = getBoardData();  
		
		LOGGER.debug("boardList:" + boardList);
		
		for (BoardVo oneBoardVo : boardList) {
			int boardId = oneBoardVo.getBoardId();
			
			BoardPanel boardPanel = new BoardPanel(oneBoardVo);

			this.add(boardPanel, null);
			
			LOGGER.debug(String.format("+++ TestPanel에 boardPanel(%s, %s) 추가",
					boardPanel.getWidth(), boardPanel.getHeight()));
			boardPanel.repaint();
			
			int boardWidth = boardPanel.getBoardWidth();
			int boardHeight = boardPanel.getBoardHeight();
			
			LOGGER.debug(String.format("boardId:%s, boardWidth:%s, boardHeight:%s", boardId, boardWidth, boardHeight));
			
			if (boardId < 2) {
				boardPanel.setBounds(BOARD_START_X + (BoardPanel.BOARD_WIDTH * (boardId % SLOT_NUM)),
						TOP_BOARD_START_Y,
						boardPanel.getBoardWidth(),
						boardPanel.getBoardHeight());
			}
			else if (boardId < 20) {
				LOGGER.debug("startX:" + (BOARD_START_X + (BoardPanel.BOARD_WIDTH * (boardId % SLOT_NUM))));
				
				boardPanel.setBounds(BOARD_START_X + (BoardPanel.BOARD_WIDTH * (boardId % SLOT_NUM)),
						BOTTOM_BOARD_START_Y,
						boardPanel.getBoardWidth(),
						boardPanel.getBoardHeight());
			}
			else {
				boardPanel.setBounds(BOARD_START_X + (BoardPanel.BOARD_WIDTH * (boardId % SLOT_NUM)),
						TOP_BOARD_START_Y,
						boardPanel.getBoardWidth(),
						boardPanel.getBoardHeight());
			}
			
			LOGGER.debug("--- TestPanel에 boardPanel 추가");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		LOGGER.debug("paintComponent");
/*		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.black);
		g.drawOval(100, 100, 300, 200);
		
		// 문자열을 가운데 쓴다.
		g.setColor(Color.red);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		int stringWidth = g.getFontMetrics().stringWidth(greetings);
		int stringHeight = g.getFontMetrics().getHeight();
		
		int strX = (this.getWidth() - stringWidth) / 2; 
		int strY = (this.getHeight() - stringHeight) / 2; 
		
		g.drawString(greetings, strX, strY);
*/
		g.drawImage(ImageFactory.backgroundImageIcon.getImage(),
				0, 0, this);
	}
}