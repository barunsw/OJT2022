package com.barunsw.ojt.day18_3;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageFactory {
	private static final Logger LOGGER = LogManager.getLogger(ImageFactory.class);
	
	public static ImageIcon backgroundImageIcon;
	
	public static ImageIcon mpuNormalImageIcon;
	public static ImageIcon mpuMinorImageIcon;
	public static ImageIcon mpuMajorImageIcon;
	public static ImageIcon mpuCriticalImageIcon;
	
	public static ImageIcon salcNormalImageIcon;
	public static ImageIcon srguNormalImageIcon;
	
	public static ImageIcon[] mpuImageIcon = new ImageIcon[4];

	static {
		LOGGER.debug("static 블럭 실행");
		backgroundImageIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/background.png"));
		
		mpuNormalImageIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/MPU_normal.png"));
		// CRITICAL, MAJOR, MINOR 생성도 해야 한다.
		
		salcNormalImageIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SALC_normal.png"));
		
		srguNormalImageIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SRGU_normal.png"));
	}
}
