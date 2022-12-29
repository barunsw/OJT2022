package com.barunsw.ojt.jmlee.day13;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExplorerMain {

	private static Logger LOGGER = LoggerFactory.getLogger(FileExplorerFrame.class);

	
	public static void main (String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage() + ex);
		}
		LOGGER.debug("프레임호출");
		FileExplorerFrame frame = new FileExplorerFrame();
		frame.setSize(new Dimension(FileExplorerFrame.WIDTH, FileExplorerFrame.HEIGHT));

		// frame이 가운데 표시되게		
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (scrDim.width - frame.getWidth()) / 2;
		int yPos = (scrDim.height - frame.getHeight()) / 2;
		
		frame.setLocation(new Point(xPos, yPos));	
		frame.setVisible(true);
		
	}
	
}
