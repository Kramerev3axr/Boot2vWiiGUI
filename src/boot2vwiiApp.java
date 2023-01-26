import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class boot2vwiiApp 
{
	static String code = "make";
	final static String newline = "\n";
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		JFrame mainFrame = new JFrame("Boot2vWii GUI");
		mainFrame.setSize(245,610);
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
		APP_NAME.setBounds(15, 30, 200, 25);
		mainFrame.add(APP_NAME);

		//APP SHORTNAME
		JLabel shortName_LABEL = new JLabel("APP_SHORTNAME (Default: None)");
		shortName_LABEL.setBounds(15, 65, 200, 25);
		mainFrame.add(shortName_LABEL);
		
		JTextField SHORTNAME = new JTextField();
		SHORTNAME.setBounds(15, 90, 200, 25);
		mainFrame.add(SHORTNAME);
		
		//APP AUTHOR
		JLabel AUTHOR_LABEL = new JLabel("APP_AUTHOR (Default: None)");
		AUTHOR_LABEL.setBounds(15, 125, 200, 25);
		mainFrame.add(AUTHOR_LABEL);
		
		JTextField AUTHOR = new JTextField();
		AUTHOR.setBounds(15, 150, 200, 25);
		mainFrame.add(AUTHOR);
		
		//TIDHIGH
		JLabel TIDHIGH_LABEL = new JLabel("TIDHIGH (Default: 0x00010001)");
		TIDHIGH_LABEL.setBounds(15, 185, 200, 25);
		mainFrame.add(TIDHIGH_LABEL);
		
		JTextField TIDHIGH = new JTextField("");
		TIDHIGH.setBounds(15, 210, 200, 25);
		mainFrame.add(TIDHIGH);
		
		//TIDLOW
		JLabel TIDLOW_LABEL = new JLabel("TIDLOW (Default: 0x44434f41)");
		TIDLOW_LABEL.setBounds(15, 245, 200, 25);
		mainFrame.add(TIDLOW_LABEL);
		
		JTextField TIDLOW = new JTextField("");
		TIDLOW.setBounds(15, 270, 200, 25);
		mainFrame.add(TIDLOW);
		
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("PNG Files (.png)", "png");
		
		//ICON
		JButton iconBtn = new JButton("ICON (128x128)");
		iconBtn.setBounds(15, 310, 200, 25);
		iconBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser iconDialog = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				File workingDirectory = new File(System.getProperty("user.dir"));
				iconDialog.setCurrentDirectory(workingDirectory);
				
				iconDialog.setFileFilter(imgFilter);
				int i = iconDialog.showOpenDialog(null);
				
	            // if the user selects a file
	            if (i == JFileChooser.APPROVE_OPTION)
	            {
	            	iconDialog.getSelectedFile().getAbsolutePath();
	            	String filename=iconDialog.getSelectedFile().getName();
	            	code += " ICON=" + '"' + "assets/" + filename + '"';
	            }
	            else
	            {
	                code += "";
	            }
			}
		});
		mainFrame.add(iconBtn);

		//SPLASH
		JButton splashBtn = new JButton("TV_SPLASH (1280x720)");
		splashBtn.setBounds(15, 340, 200, 25);
		splashBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser splashDialog = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				File workingDirectory = new File(System.getProperty("user.dir"));
				splashDialog.setCurrentDirectory(workingDirectory);
				
				splashDialog.setFileFilter(imgFilter);
				int i = splashDialog.showOpenDialog(null);
				
	            // if the user selects a file
	            if (i == JFileChooser.APPROVE_OPTION)
	            {
	            	splashDialog.getSelectedFile().getAbsolutePath();
	            	String filename=splashDialog.getSelectedFile().getName();
	            	code += " TV_SPLASH=" + '"' + "assets/" + filename + '"';
	            }
	            else
	            {
	                code += "";
	            }
			}
		});
		mainFrame.add(splashBtn);

		//DRC
		JButton drcBtn = new JButton("DRC_SPLASH (854x480)");
		drcBtn.setBounds(15, 370, 200, 25);
		drcBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser drcDialog = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				File workingDirectory = new File(System.getProperty("user.dir"));
				drcDialog.setCurrentDirectory(workingDirectory);
				
				drcDialog.setFileFilter(imgFilter);
				int i = drcDialog.showOpenDialog(null);
				
	            // if the user selects a file
	            if (i == JFileChooser.APPROVE_OPTION)
	            {
	            	drcDialog.getSelectedFile().getAbsolutePath();
	            	String filename=drcDialog.getSelectedFile().getName();
	            	code += " DRC_SPLASH=" + '"' + "assets/" + filename + '"';
	            }
	            else
	            {
	                code += "";
	            }
			}
		});
		mainFrame.add(drcBtn);
		
		
		//DISPLAY
		JLabel dispLabel = new JLabel("DISPLAY (Default: BOTH)");
		dispLabel.setBounds(15, 405, 200, 25);
		mainFrame.add(dispLabel);
		
		String[] dispOpts = {"BOTH", "TV", "DRC"};
			@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox dispBox = new JComboBox(dispOpts);
		dispBox.setSelectedItem(0);
		dispBox.setBounds(15, 430, 200, 25);
		dispBox.setFocusable(false);
		mainFrame.add(dispBox);
		
		//FORCERES
		JLabel resLabel = new JLabel("FORCERES (Default: NONE)");
		resLabel.setBounds(15, 465, 200, 25);
		mainFrame.add(resLabel);
		
		String[] resOpts = {"NONE", "P720", "P480"};
			@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox resBox = new JComboBox(resOpts);
		resBox.setSelectedItem(0);
		resBox.setBounds(15, 490, 200, 25);
		resBox.setFocusable(false);
		mainFrame.add(resBox);
		
		//Compile Button
		JButton compileBtn = new JButton("Compile");
		compileBtn.setBounds(15, 530, 200, 30);
		compileBtn.setFont(new Font(Font.DIALOG,  Font.PLAIN, 15));
		compileBtn.setFocusable(false);
		compileBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				code += APP_NAME.getText().equals("") ? "" : " APP_NAME=" + '"' + APP_NAME.getText() + '"';
				code += SHORTNAME.getText().equals("") ? "" : " APP_SHORTNAME=" + '"' + SHORTNAME.getText() + '"';
				code += AUTHOR.getText().equals("") ? "" : " APP_AUTHOR=" + '"' + AUTHOR.getText() + '"';
				
				code += TIDHIGH.getText().equals("") ? "" : " TIDHIGH=" + TIDHIGH.getText();
				code += TIDLOW.getText().equals("") ? "" : " TIDLOW=" + TIDLOW.getText();
				
				code += dispBox.getSelectedItem().equals("BOTH") ? "" : " DISPLAY=" + dispBox.getSelectedItem();
				code += resBox.getSelectedItem().equals("NONE") ? "" : " FORCERES=" + resBox.getSelectedItem();
				 
						
			    try 
			    {
			        FileWriter shWrite = new FileWriter("makeOutput.sh");
			        shWrite.write("#!/bin/bash" + newline 
			        				+ "cd " + System.getProperty("user.dir").replace('\\', '/') + newline
			        				+ code + newline
			        				+ "echo " + '"' + "------------------------------------------" + '"' + newline
			        				+ "read -p " + '"' + "Press enter to run 'make clean'..." + '"' + newline
			        				+ "make clean" + newline
			        				+ "$SHELL");
			        shWrite.close();
			    } 
			    catch (IOException ex) 
			    {
			        System.out.println("An error occurred.");
			        ex.printStackTrace();
			    }
					
				ProcessBuilder msysProc = new ProcessBuilder();
				try 
				{
					msysProc.command(loadPath(), System.getProperty("user.dir").replace('\\', '/') + "/makeOutput.sh");
				} 
				catch (IOException e2) 
				{
					JDialog error = new JDialog(mainFrame, "Error!");
					error.setSize(625, 75);
					error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					error.setLocationRelativeTo(null);
					error.setLayout(null);
					
					JLabel errorLabel = new JLabel("Error! Please ensure you have 'path.txt' created with the path to 'msys2.exe' and restart the program!");
					errorLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
					errorLabel.setBounds(10, 0, 650, 25);
					error.add(errorLabel);
					
					error.setVisible(true);
				}
				
				try 
				{
					msysProc.start();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		mainFrame.add(compileBtn);
		mainFrame.setVisible(true);
    }
	
	private static String loadPath() throws IOException
	{
		File path = new File("path.txt");
	    
	    String msysPath = "";
	    Scanner pathReader = new Scanner(path);
	    while (pathReader.hasNextLine()) 
	    {
	      msysPath = pathReader.nextLine();
	    }
        
		pathReader.close();
        return msysPath;
	}
}
