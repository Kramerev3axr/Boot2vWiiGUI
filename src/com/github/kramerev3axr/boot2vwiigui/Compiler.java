package com.github.kramerev3axr.boot2vwiigui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Compiler {
	
	final static String newline = "\n";
	public static void compileApp(String code) {
		// Create code file
	    try {
	        FileWriter shWrite = new FileWriter("makeOutput.sh");
	        shWrite.write("#!/bin/bash" + newline 
	        				+ "cd " + System.getProperty("user.dir").replace('\\', '/') + newline
	        				+ "make " + code + newline
	        				+ "echo " + '"' + "------------------------------------------" + '"' + newline
	        				+ "read -p " + '"' + "Press enter to run 'make clean'..." + '"' + newline
	        				+ "make clean" + newline
	        				+ "$SHELL");
	        shWrite.close();
	    } 
	    catch (IOException ex) {
	        System.out.println("An error occurred.");
	        ex.printStackTrace();
	    }
	    
	    // Read path
		ProcessBuilder msysProc = new ProcessBuilder();
		try {
			msysProc.command(loadPath(), System.getProperty("user.dir").replace('\\', '/') + "/makeOutput.sh");
			msysProc.start();
		} 
		catch (IOException e2) {}
	}
	
	private static String loadPath() throws IOException {
		File path = new File("path.txt");
	    
	    String msysPath = "";
	    Scanner pathReader = new Scanner(path);
	    while (pathReader.hasNextLine()) {
	      msysPath = pathReader.nextLine();
	    }
        
		pathReader.close();
        return msysPath;
	}
}
