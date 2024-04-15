package com.github.kramerev3axr.boot2vwiigui.impl;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TIDDocumentFilter extends DocumentFilter {
	private int letterCount = 0;
	
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		letterCount--;
		fb.remove(offset, length);
	}

	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		letterCount++;
		fb.insertString(offset, string, attr);
	}

	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		if (letterCount < 4) {
			
			letterCount++;
			
			text = removeNums(text);
			text = text.toUpperCase();
			
			fb.replace(offset, length, text, attrs);
		}
	}
	
	private String removeNums(String text) {
		char[] chars = text.toCharArray();
		String result = "";
		
		for (char character : chars) {
			if (!Character.isDigit(character)) {
				result += character;
			}	
			else
				letterCount--;
		}
		return result;
	}
}
