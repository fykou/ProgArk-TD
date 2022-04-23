package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import no.ntnu.tdt4240.g25.td.firebase.DesktopFirebaseDb;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Tower Defense");
		config.setWindowedMode(480, 854); // anything 16:9
		new Lwjgl3Application(new TdGame(new DesktopFirebaseDb()), config);
	}
}
