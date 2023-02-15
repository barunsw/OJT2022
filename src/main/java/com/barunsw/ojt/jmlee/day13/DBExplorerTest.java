package com.barunsw.ojt.jmlee.day13;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerTest.class);

	public static void main(String[] args) throws Exception {
		DBExplorerFrame frame = new DBExplorerFrame();
		
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xPos = (scrDim.width - DBExplorerFrame.WIDTH) / 2;
		int yPos = (scrDim.height - DBExplorerFrame.HEIGHT) / 2;

		frame.setBounds(new Rectangle(xPos, yPos, DBExplorerFrame.WIDTH, DBExplorerFrame.HEIGHT));
		frame.setVisible(true);	
	}

}
