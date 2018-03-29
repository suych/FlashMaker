package org.suych.fm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BannerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("___________.__                .__        _____          __                 ");
		System.out.println("\\_   _____/|  | _____    _____|  |__    /     \\ _____  |  | __ ___________ ");
		System.out.println(" |    __)  |  | \\__  \\  /  ___/  |  \\  /  \\ /  \\\\__  \\ |  |/ // __ \\_  __ \\");
		System.out.println(" |     \\   |  |__/ __ \\_\\___ \\|   Y  \\/    Y    \\/ __ \\|    <\\  ___/|  | \\/");
		System.out.println(" \\___  /   |____(____  /____  >___|  /\\____|__  (____  /__|_ \\\\___  >__|   ");
		System.out.println("     \\/              \\/     \\/     \\/         \\/     \\/     \\/    \\/       ");
		System.out.println(":: Flash Maker :: :: Make business development easier :: :: :: (v0.1.0.Beta)");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
