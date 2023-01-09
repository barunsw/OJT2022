package com.barunsw.ojt.yhkim.day21;

import java.awt.Graphics;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.vo.BoardVo;

public class ClientPanel extends JPanel implements EventListener {
   private static final Logger LOGGER = LogManager.getLogger(ClientPanel.class);
   
   public static final int WIDTH    = 854;
   public static final int HEIGHT   = 604;
   
   public final int SLOT_NUM      = 20;
   
   public final int BOARD_START_X          = 27;
   public final int TOP_BOARD_START_Y      = 26;
   public final int BOTTOM_BOARD_START_Y   = 307;
   
   private ServerInterface serverIf;
   private ClientInterface clientIf;
   
   private List<BoardPanel> boardPanelList = new ArrayList<BoardPanel>();
   
   public ClientPanel() {      
	  try {
    	 initEvent();
         initRemote();
         initComponent();
         initData();
      }
      catch (Exception ex) {
         LOGGER.error(ex.getMessage(), ex);
      }
   }
   
   private void initEvent() {
	   ClientMain.eventQueueWorker.addEventListener(this);
   }
   
   private void initRemote() {
      try {
         clientIf = new ClientImpl(this);
         
         Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
         
         Remote remote = registry.lookup(ServerMain.BIND_NAME);
         if (remote instanceof ServerInterface) {
            serverIf = (ServerInterface)remote;   
         }
         serverIf.register(clientIf);
         
      } 
      catch (Exception e) {
         LOGGER.debug(e.getMessage(), e);
      }
   }
   
   private void initComponent() throws Exception {
      this.setLayout(null);
   }
   
   private void initData() throws Exception {
	   List<BoardVo> boardList = serverIf.selectBoardList();
            
      for (BoardVo oneBoardVo : boardList) {
         int boardId = oneBoardVo.getBoardId();
         
         BoardPanel boardPanel = new BoardPanel(oneBoardVo);
         this.add(boardPanel, null);
         
         LOGGER.debug(String.format("+++ TestPanel에 boardPanel(%s, %s) 추가",
               boardPanel.getBoardWidth(), boardPanel.getBoardHeight()));
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
         
         boardPanelList.add(boardPanel);
         LOGGER.debug("--- TestPanel에 boardPanel 추가");
      }
   }
      
   @Override
   protected void paintComponent(Graphics g) {
      LOGGER.debug("paintComponent");

      g.drawImage(ImageFactory.backgroundImageIcon.getImage(),
            0, 0, this);
   }
   
   @Override
   public void push(Object o) {
	   if (o instanceof BoardVo) {
		   BoardVo board = (BoardVo)o;
		   
		   for (BoardPanel boardPanel : boardPanelList) {
			   if(boardPanel.getBoardVo().getBoardId() == board.getBoardId()) {
				   boardPanel.getBoardVo().setSeverity(board.getSeverity());
				   boardPanel.repaint();
				   break;
			   }
		   }
	   }
   }
}