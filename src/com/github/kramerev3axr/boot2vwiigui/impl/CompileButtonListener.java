package com.github.kramerev3axr.boot2vwiigui.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HexFormat;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.github.kramerev3axr.boot2vwiigui.Compiler;
import com.github.kramerev3axr.boot2vwiigui.vWiiApp;
import com.github.kramerev3axr.boot2vwiigui.vWiiApp.DISPLAY;
import com.github.kramerev3axr.boot2vwiigui.vWiiApp.RESOLUTION;

public class CompileButtonListener implements ActionListener {

	private JTextField APP_NAME; 
	private JTextField SHORTNAME;
	private JTextField AUTHOR;
	private JTextField TID;
	
	private JComboBox<?> imagesBox;
	private JComboBox<?> dispBox;
	private JComboBox<?> resBox;
	
	private Object[] objects;
	
	private String code = "";
	
	public CompileButtonListener(JTextField APP_NAME, JTextField SHORTNAME, JTextField AUTHOR, JTextField TID,
			JComboBox<?> imagesBox, JComboBox<?> dispBox, JComboBox<?> resBox, Object[] objects) {
		this.APP_NAME = APP_NAME;
		this.SHORTNAME = SHORTNAME;
		this.AUTHOR = AUTHOR;
		this.TID = TID;
		
		this.imagesBox = imagesBox;
		this.dispBox = dispBox;
		this.resBox = resBox;
		
		this.objects = objects;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		File[] packs = (File[]) objects[0];
		
		DISPLAY display = DISPLAY.BOTH;
		RESOLUTION resolution = RESOLUTION.NONE;
		
		if (dispBox.getSelectedIndex() == 1)
			display = DISPLAY.TV;
		if (dispBox.getSelectedIndex() == 2)
			display = DISPLAY.DRC;
		
		if (resBox.getSelectedIndex() == 1)
			resolution = RESOLUTION.P720;
		if (resBox.getSelectedIndex() == 2)
			resolution = RESOLUTION.P480;
		
		vWiiApp app = new vWiiApp.Builder()
				.setAppName(APP_NAME.getText())
				.setAppAuthor(AUTHOR.getText())
				.setAppShortname(SHORTNAME.getText())
				
				.setImagePack(packs[imagesBox.getSelectedIndex()])
				.setTID(TID.getText())
				
				.setDisplayOn(display)
				.setForceRes(resolution)
				.build();
		
		convertToCode(app);
	}
	
	private void convertToCode(vWiiApp app) {	
		code = ""; // Reset code
		
		if (!app.getAppName().equals(""))
			code += "APP_NAME=\"" + app.getAppName() + "\" ";
		
		if (!app.getAppAuthor().equals(""))
			code += "APP_AUTHOR=\"" + app.getAppAuthor() + "\" ";
		
		if (!app.getAppShortName().equals(""))
			code += "APP_SHORTNAME=\"" + app.getAppShortName() + "\" ";
		
		if (!app.getTID().equals(""))
			code += "TID=0x00010001" + idToHex(app.getTID()) + " ";
		
		setImages(app.getImagePack());
		
		code += "DISPLAY_ON=" + app.getDisplayOn() + " ";
		code += "FORCERES=" + app.getForceRes() + " ";
		
		System.out.println(code);
		Compiler.compileApp(code);
	}
	
	private String idToHex(String id) {
		return HexFormat.of().formatHex(id.getBytes());
	}
	
	private void setImages(File dir) {
		for (File file : dir.listFiles()) {
			if (file.getName().equals("icon.png"))
				code += "ICON=\"assets/" + dir.getName() + "/" + file.getName() + "\" ";
			if (file.getName().equals("tv.png"))
				code += "TV_SPLASH=\"assets/" + dir.getName() + "/" + file.getName() + "\" ";
			if (file.getName().equals("drc.png"))
				code += "DRC_SPLASH=\"assets/" + dir.getName() + "/" + file.getName() + "\" ";
		}
	}
}
