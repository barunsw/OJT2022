package com.barunsw.ojt.yhkim.day09;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBookMain {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookMain.class);
	
	public static void main(String[] args) {
		
		AddressBookFrame frame = new AddressBookFrame();
		frame.setSize(new Dimension(AddressBookFrame.WIDTH, AddressBookFrame.HEIGHT));
		
		// frame이 가운데 표시되게
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (scrDim.width - frame.getWidth()) / 2;
		int yPos = (scrDim.height - frame.getHeight()) / 2;
		
		frame.setLocation(new Point(xPos, yPos));	
		frame.setVisible(true);
		
	}
}
