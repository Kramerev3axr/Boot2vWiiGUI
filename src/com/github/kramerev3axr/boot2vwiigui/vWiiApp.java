package com.github.kramerev3axr.boot2vwiigui;

import java.io.File;

public class vWiiApp {
	private String APP_NAME;
	private String APP_SHORTNAME;
	private String APP_AUTHOR;
	
	private File PACK;
	
	private String TID;
	
	private DISPLAY DISPLAY_ON; 
	private RESOLUTION FORCERES;
	
	public vWiiApp(String APP_NAME, String APP_SHORTNAME, String APP_AUTHOR,
					File PACK, String TID, DISPLAY DISPLAY_ON, RESOLUTION FORCERES) {
		
		this.APP_NAME = APP_NAME;
		this.APP_SHORTNAME = APP_SHORTNAME;
		this.APP_AUTHOR = APP_AUTHOR;
		
		this.PACK = PACK;
		this.TID = TID;
		
		this.DISPLAY_ON = DISPLAY_ON;
		this.FORCERES = FORCERES;
	}
	
	// App Info
	public String getAppName() {
		return APP_NAME;
	}
	
	public String getAppShortName() {
		return APP_SHORTNAME;
	}
	
	public String getAppAuthor() {
		return APP_AUTHOR;
	}
	
	// Images
	public File getImagePack() {
		return PACK;
	}
	
	// TID
	public String getTID() {
		return TID;
	}
	
	// Display
	public DISPLAY getDisplayOn() { 
		return DISPLAY_ON;
	}
	
	public RESOLUTION getForceRes() {
		return FORCERES;
	}
	
	public static class Builder {
		private String APP_NAME; // Defaults "Boot2vWii"
		private String APP_SHORTNAME;
		private String APP_AUTHOR;
		
		private File PACK;
		
		private String TID; // Defaults 0 (Wii Menu)
		
		private DISPLAY DISPLAY_ON; // Defaults BOTH 
		private RESOLUTION FORCERES; // Defaults NONE
		
		// App Info
		public Builder setAppName(String APP_NAME) {
			this.APP_NAME = APP_NAME;
			return this;
		}
		
		public Builder setAppShortname(String APP_SHORTNAME) {
			this.APP_SHORTNAME = APP_SHORTNAME;
			return this;
		}
		
		public Builder setAppAuthor(String APP_AUTHOR) {
			this.APP_AUTHOR = APP_AUTHOR;
			return this;
		}
		
		// Images
		public Builder setImagePack(File PACK) {
			this.PACK = PACK;
			return this;
		}
		
		// TID
		public Builder setTID(String TID) {
			this.TID = TID;
			return this;
		}
		
		// DISPLAY
		public Builder setDisplayOn(DISPLAY DISPLAY_ON) {
			this.DISPLAY_ON = DISPLAY_ON;
			return this;
		}
		
		public Builder setForceRes(RESOLUTION FORCERES) {
			this.FORCERES = FORCERES;
			return this;
		}
		
		public vWiiApp build() {
			return new vWiiApp(APP_NAME, APP_SHORTNAME, APP_AUTHOR, 
								PACK, TID, DISPLAY_ON, FORCERES);	
		}
	}
	
	public enum DISPLAY {
		TV,
		DRC,
		BOTH
	}
	
	public enum RESOLUTION {
		NONE,
		P720,
		P480
	}
}
