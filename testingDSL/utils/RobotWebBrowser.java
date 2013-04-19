package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class RobotWebBrowser {

	private static Robot robot;

	public RobotWebBrowser() throws AWTException {
		robot = new Robot();
	}

	public static void typeKey(int keyCode, int delayTime) throws AWTException {
		robot = new Robot();
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
		robot.delay(delayTime);
	}
	
	public static void mouseEvent(int delayTime) throws AWTException {
		robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(delayTime);
	}

	public static void typeHotKey(char character, int delayTime)
			throws AWTException {
		robot = new Robot();
		KeyStroke keyStroke = KeyStroke.getKeyStroke("pressed "
				+ Character.toUpperCase(character));
		robot.delay(delayTime);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(keyStroke.getKeyCode());
		robot.keyRelease(keyStroke.getKeyCode());
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.delay(delayTime);
	}

	public static void typeString(String stringChar, int delayTime)
			throws Exception {
		Robot robot = new Robot();
		char[] characters = stringChar.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			KeyStroke key = KeyStroke.getKeyStroke("pressed "
					+ Character.toUpperCase(characters[i]));
			if (Character.isUpperCase(characters[i])) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			}
			if (key != null) {
				typeKey(key.getKeyCode(), delayTime);
				// ':' character needs special handling
			} else if (characters[i] == ':') {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				// '\\' character needs special handling
			} else if (characters[i] == '\\') {
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
				robot.keyPress(KeyEvent.VK_SHIFT);
			} else if (characters[i] == '.') {
				robot.keyPress(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_PERIOD);
			} else if (characters[i] == ' ') {
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
			}	
			if (Character.isUpperCase(characters[i])) {
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
				robot.delay(delayTime);
		}
	}

	public static void closeDownLoadList() throws AWTException {
		robot = new Robot();
		KeyStroke keyStroke = KeyStroke
				.getKeyStroke("pressed " + Character.toUpperCase('j'));
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(keyStroke.getKeyCode());
		robot.keyRelease(keyStroke.getKeyCode());
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
	}

	public static void setFireFoxSettings() throws AWTException, InterruptedException {
		typeHotKey('t',1000);
		KeyStroke keyStroke = KeyStroke
				.getKeyStroke("pressed " + Character.toUpperCase('o'));
		robot.keyPress(keyStroke.getKeyCode());
		robot.keyRelease(keyStroke.getKeyCode());
		robot.delay(1000);
		typeHotKey('a', 1000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	public static void delay(int delayTime) throws AWTException{
		robot = new Robot();
		robot.delay(delayTime);
	}
}
