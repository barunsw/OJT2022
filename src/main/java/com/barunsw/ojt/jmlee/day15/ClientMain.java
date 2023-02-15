package com.barunsw.ojt.jmlee.day15;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage() + ex);
		}
		
		ClientFrame frame = new ClientFrame();
		
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xPos = (scrDim.width - ClientFrame.WIDTH) / 2;
		int yPos = (scrDim.height - ClientFrame.HEIGHT) / 2;

		frame.setBounds(new Rectangle(xPos, yPos, ClientFrame.WIDTH, ClientFrame.HEIGHT));
		frame.setVisible(true);	
	}

}
