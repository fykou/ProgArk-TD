# ProgArk-TD

## Setup
 - Download the latest version of Android Studio from https://developer.android.com/studio
 - In Android Studio, create a new project from version control, by pressing New -> Project from Version Control.
 - When prompted, clone the project from this repository via HTTPS or SSH at your own discretion.
 - After opening, Android Studio should automatically detect the project as a Gradle project and sync it.
 - After syncing, press the green Play button on top to run the game. You can either run the game on a connected Android Device or on an emulator.
If you don’t have an emulator yet, click “Create New Device”. Create the device, press OK, and the game will open.

If testing on desktop, be aware that there is no Firebase implementation for the desktop module, so the score submission and leaderboard functionality will not work.
You may also need to build the Gradle task list by changing the appropriate setting in the Gradle tab on the right. When this is done, the game can be run
by invoking `gradle :desktop:run` or selecting the run command under desktop -> other.
