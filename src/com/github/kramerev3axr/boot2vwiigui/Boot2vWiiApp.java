package com.github.kramerev3axr.boot2vwiigui;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AbstractDocument;

import com.github.kramerev3axr.boot2vwiigui.impl.CompileButtonListener;
import com.github.kramerev3axr.boot2vwiigui.impl.TIDDocumentFilter;;

public class Boot2vWiiApp {
	private static File ASSETS_FOLDER = new File(System.getProperty("user.dir") + "/assets");
	public static JFrame mainFrame;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {		
		boolean throwError = false;
		if (!ASSETS_FOLDER.exists()) {
			throwError();
		}
		
		makePath();
		gui(throwError);
	}

	private static void gui(boolean throwError) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {	
		mainFrame = new JFrame("Boot2vWii GUI");
		mainFrame.setSize(245, 450);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(null);
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		//APP NAME
		JLabel APP_NAME_LABEL = new JLabel("APP_NAME (Default: Boot2vWii)");
		APP_NAME_LABEL.setBounds(15, 5, 200, 25);
		mainFrame.add(APP_NAME_LABEL);
		
		JTextField APP_NAME = new JTextField();
		APP_NAME.setBounds(15, 25, 200, 25);
		mainFrame.add(APP_NAME);

		//APP SHORTNAME
		JLabel shortName_LABEL = new JLabel("APP_SHORTNAME (Default: None)");
		shortName_LABEL.setBounds(15, 60, 200, 25);
		mainFrame.add(shortName_LABEL);
		
		JTextField SHORTNAME = new JTextField();
		SHORTNAME.setBounds(15, 80, 200, 25);
		mainFrame.add(SHORTNAME);
		
		//APP AUTHOR
		JLabel AUTHOR_LABEL = new JLabel("APP_AUTHOR (Default: None)");
		AUTHOR_LABEL.setBounds(15, 115, 200, 25);
		mainFrame.add(AUTHOR_LABEL);
		
		JTextField AUTHOR = new JTextField();
		AUTHOR.setBounds(15, 135, 200, 25);
		mainFrame.add(AUTHOR);
		
		//TID
		JLabel TID_LABEL = new JLabel("TID (Default: Wii Menu)");
		TID_LABEL.setBounds(15, 170, 200, 25);
		mainFrame.add(TID_LABEL);
		
		JTextField TID = new JTextField("");
		TID.setBounds(15, 190, 200, 25);
		((AbstractDocument) TID.getDocument()).setDocumentFilter(new TIDDocumentFilter());
		mainFrame.add(TID);

		//IMAGES
		JLabel imagesLabel = new JLabel("Image Pack");
		imagesLabel.setBounds(15, 220, 200, 25);
		mainFrame.add(imagesLabel);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox imagesBox = new JComboBox((String[]) getImageData()[1]);
		imagesBox.setSelectedItem(0);
		imagesBox.setBounds(15, 240, 200, 25);
		imagesBox.setFocusable(false);
		mainFrame.add(imagesBox);
		
		//DISPLAY
		JLabel dispLabel = new JLabel("DISPLAY (Default: BOTH)");
		dispLabel.setBounds(15, 270, 200, 25);
		mainFrame.add(dispLabel);
		
		String[] dispOpts = {"BOTH", "TV", "DRC"};
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox dispBox = new JComboBox(dispOpts);
		dispBox.setSelectedItem(0);
		dispBox.setBounds(15, 290, 200, 25);
		dispBox.setFocusable(false);
		mainFrame.add(dispBox);
		
		//FORCERES
		JLabel resLabel = new JLabel("FORCERES (Default: NONE)");
		resLabel.setBounds(15, 320, 200, 25);
		mainFrame.add(resLabel);
		
		String[] resOpts = {"NONE", "P720", "P480"};
			@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox resBox = new JComboBox(resOpts);
		resBox.setSelectedItem(0);
		resBox.setBounds(15, 340, 200, 25);
		resBox.setFocusable(false);
		mainFrame.add(resBox);
		
		//Compile Button
		JButton compileBtn = new JButton("Compile");
		compileBtn.setBounds(15, 370, 200, 30);
		compileBtn.setFont(new Font(Font.DIALOG,  Font.PLAIN, 15));
		compileBtn.setFocusable(false);
		compileBtn.addActionListener(new CompileButtonListener(APP_NAME, SHORTNAME, AUTHOR, TID, imagesBox, dispBox, resBox, getImageData()));

		mainFrame.add(compileBtn);
		mainFrame.setVisible(true);
	}
	
	private static Object[] getImageData() {
		// Make File List
		File[] packs = new File[ASSETS_FOLDER.list().length];
		for (int i = 0; i < packs.length; i++) {
			packs[i] = new File(ASSETS_FOLDER + "/" + ASSETS_FOLDER.list()[i]);
		}
		
		// Filter List into Directories
		packs = Arrays.stream(packs)
				.filter(pack -> pack.isDirectory())
				.toArray(File[]::new);
		
		// Make String Array of Directory Names
		String[] packNames = Arrays.stream(ASSETS_FOLDER.list())
				.filter(pack -> new File(ASSETS_FOLDER + "/" + pack).isDirectory())
				.toArray(String[]::new);
		
		if (packs.length == 0) {
			new File(ASSETS_FOLDER + "/New Pack").mkdirs();
			return getImageData();
		}
		
		return new Object[] {packs, packNames};
	}
	
	private static void makePath() {
		File path = new File("path.txt");
		if (!path.exists()) {
			try {
				path.createNewFile();
				
				JOptionPane.showMessageDialog(Boot2vWiiApp.mainFrame,
					    "Path.txt was not found, so it was created! Please set your 'msys2.exe' path now!",
					    "Attention!",
					    JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void throwError() {
		JOptionPane.showMessageDialog(mainFrame,
			    "No assets folder found! Make sure you have installed this app correctly!",
			    "No Assets Folder!",
			    JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}
