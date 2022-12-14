package com.barunsw.ojt.yhkim.day13;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerTest.class);

	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage() + ex);
		}
		
		DBExplorerFrame frame = new DBExplorerFrame();
		
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xPos = (scrDim.width - DBExplorerFrame.WIDTH) / 2;
		int yPos = (scrDim.height - DBExplorerFrame.HEIGHT) / 2;

		frame.setBounds(new Rectangle(xPos, yPos, DBExplorerFrame.WIDTH, DBExplorerFrame.HEIGHT));
		frame.setVisible(true);	
	}

}