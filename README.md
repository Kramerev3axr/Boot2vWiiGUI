# Boot2vWiiGUI
A GUI for [Boot2vWii](https://github.com/WiiDatabase/Boot2vWii) written in Java. I do not take credit for the original program, just this GUI.

# Prerequisites
- Java
- Msys2
  - Ensure that [make](https://packages.msys2.org/package/make) is installed
  - Ensure that [devkitPro](https://devkitpro.org/wiki/Getting_Started), [wut](https://github.com/devkitPro/wut), and [wut-tools](https://github.com/devkitPro/wut-tools) is also installed

# Installation
1. Download [Boot2vWii](https://github.com/WiiDatabase/Boot2vWii)
2. Download the lastest release of [Boot2vWiiGUI]()
3. Extract the contents of Boot2vWiiGUI into the root folder of Boot2vWii
4. Set 'path.txt' to the path of your Msys2 installation. It should be formated as
`path_to_installation/msys2.exe`

# Notes
- The .png's for the Icon/Splash Screens must be in the Boot2vWii assets folder, in the command line if I link directly to the image it doesn't compile (I have no idea/could not tell you why). The file chooser is mainly just to get the .png name.
- I did this as a small project for myself as a beginner programmer, and in all likelihood I very rarely will have time to update this project if there are bugs. Criticism about the source code is appreciated, though!
