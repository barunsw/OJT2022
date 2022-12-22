package com.barunsw.ojt.jmlee.day09;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBookMain {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookMain.class);
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // 테마
		} 
		catch (Exception ex) {
			LOGGER.error(ex.getMessage() + ex);
		}
		
/*		
		//AddressBookFrame frame = new AddressBookFrame();
		JFrame frame = new JFrame();
		frame.setTitle("테스트");
		//frame.setLocation(new Point(100, 100));
		frame.setSize(new Dimension(800, 600));
		//frame.setBounds(new Rectangle(100, 100, 600, 400));
		
		// frame이 가운데 표시되게
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (scrDim.width - frame.getWidth()) / 2;
		int yPos = (scrDim.height - frame.getHeight()) / 2;
		
		frame.setLocation(new Point(xPos, yPos));	
		frame.setVisible(true);
*/
		
		AddressBookFrame frame = new AddressBookFrame();
		frame.setSize(new Dimension(AddressBookFrame.WIDTH, AddressBookFrame.HEIGHT));
		
		// frame이 가운데 표시되게
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
