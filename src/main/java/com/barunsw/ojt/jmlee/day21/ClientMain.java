package com.barunsw.ojt.jmlee.day21;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMain {
private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static EventQueueWorker eventQueueWorker = new EventQueueWorker();
	
	public static void main(String[] args) {
		
		try {
				eventQueueWorker.start();				
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage() + ex);
			}
						
			ClientFrame frame = new ClientFrame();
			
			frame.setSize(new Dimension(ClientFrame.WIDTH, ClientFrame.HEIGHT));

			// frame이 가운데 표시되게		
			Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
			int xPos = (scrDim.width - frame.getWidth()) / 2;
			int yPos = (scrDim.height - frame.getHeight()) / 2;
			
			frame.setLocation(new Point(xPos, yPos));	
			frame.setVisible(true);			
			
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
