# ProgArk-TD

## Setup
 - Download the latest version of Android Studio from https://developer.android.com/studio
 - In Android Studio, create a new project from version control, by pressing New -> Project from Version Control.
 - When prompted, clone the project from this repository via HTTPS or SSH at your own discretion.
 - After opening, Android Studio should automatically detect the project as a Gradle project and sync it.
 - After syncing, press the green Play button on top to run the game. You can either run the game on a connected Android Device or on an emulator.
If you don’t have an emulator yet, click “Create New Device”. Create the device, press OK, and the game will open.

Under development we have used our own phones as well as emulators for testing using API level 30-32,
although the build is set to support down to API level 26. If any errors occur, try using level 30-32.

If testing on desktop, be aware that there is no Firebase implementation for the desktop module, so the score submission and leaderboard functionality will not work.
You may also need to build the Gradle task list by changing the appropriate setting in the Gradle tab on the right. When this is done, the game can be run
by invoking `gradle :desktop:run` or selecting the run command under desktop -> other.

## Structure
The project is structured as a typical libGDX generated project.
The main part of the source code is located in /core, with the desktop and Android modules containing
platform specific code, which is the Android Firebase SDK implementation in our case.

The code and packages are organized into view, controller and model packages, with the Artemis-odb framework
and our implementation classes contained under /model/entity.