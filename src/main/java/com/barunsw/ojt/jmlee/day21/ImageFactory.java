package com.barunsw.ojt.jmlee.day21;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageFactory {
	private static final Logger LOGGER = LogManager.getLogger(ImageFactory.class);
	
	public static ImageIcon backgroundImageIcon;
	
	public static ImageIcon[] mpuImageIcon  = new ImageIcon[4];
	public static ImageIcon[] salcImageIcon = new ImageIcon[4];
	public static ImageIcon[] srguImageIcon = new ImageIcon[4];

	static {
		LOGGER.debug("static 블럭 실행");
		backgroundImageIcon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/background.png"));
		
		// mpuImageIcon
		mpuImageIcon[0] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/MPU_critical.png"));
		mpuImageIcon[1] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/MPU_major.png"));
		mpuImageIcon[2] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/MPU_minor.png"));
		mpuImageIcon[3] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/MPU_normal.png"));
	
		// salcImageIcon
		salcImageIcon[0] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SALC_critical.png"));
		salcImageIcon[1] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SALC_major.png"));
		salcImageIcon[2] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SALC_minor.png"));
		salcImageIcon[3] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SALC_normal.png"));		

		// srguImageIcon
		srguImageIcon[0] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SRGU_critical.png"));
		srguImageIcon[1] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SRGU_major.png"));
		srguImageIcon[2] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SRGU_minor.png"));
		srguImageIcon[3] = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("images/tamms/SRGU_normal.png"));
	}
}
