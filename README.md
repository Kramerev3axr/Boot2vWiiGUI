
# Boot2vWiiGUI
A GUI for [Boot2vWii](https://github.com/WiiDatabase/Boot2vWii) written in Java. I do not take credit for the original program, just this GUI.

# Prerequisites
- Java 17 +
- Msys2
  - Ensure [make](https://packages.msys2.org/package/make), [wut](https://github.com/devkitPro/wut), and [wut-tools](https://github.com/devkitPro/wut-tools) is also installed

# Installation
1. Download [Boot2vWii](https://github.com/WiiDatabase/Boot2vWii)
2. Download the lastest release of [Boot2vWiiGUI]()
3. Put Boot2vWiiGUI.jar into the root folder of Boot2vWii
4. Set 'path.txt' to the path of your Msys2 installation. It should be formatted as
`[Drive:]/path_to_installation/msys2.exe`

# Usage
## App Name, Shortname, & Author
Fill in the fields as required, if left blank the field will be omitted and will fall back on the default instead.

## TID
Enter in the Title ID into the field, and the program will automatically convert it into hexadecimal
- Example #1: Entering in `OHBC` will take you to The Homebrew Channel
- Example #2: Entering `UNEO` will take you to USB Loader GX

## Assets
For assets, make a sub folder in the `assets` folder, and name it whatever. 
![Assets Folder](https://imgur.com/s9XnUCA.png)

Within that subfolder, you can put `icon.png`, `tv.png`, and `drc.png` for the gui to recognize and package. 
![Example Sub Folder](https://i.imgur.com/tEQmEAW.png)

You **do not** need to have all 3 in there, you can have whatever combination you want (or none) and the program will omit the assets not included accordingly.

After adding assets, open the program and choose from the drop down list which Image Pack you want to use.
![Drop Down](https://imgur.com/aoTI8u7.png)

## Display & ForceRes
Click the drop down to select which Display and ForceRes you want to set the app to

# Notes
To be honest I rewrote this entire program on like 2 hours of sleep so most of it's pretty hacky code-wise
