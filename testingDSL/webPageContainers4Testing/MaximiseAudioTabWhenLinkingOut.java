package webPageContainers4Testing;

import webPageContainers4Testing.BasePageContainer;
import webPageElements4Testing.Link;

public class MaximiseAudioTabWhenLinkingOut extends BasePageContainer {

	public MaximiseAudioTabWhenLinkingOut() throws Exception {
		super();

	}

	public boolean verifyWindowMaximisedForAudio() throws Exception {

		new Link(properties.get("audio_search_link")).click();
		new Link(properties.get("audio_image_Link")).clickWithoutWait();
		Thread.sleep(10000);
		// window.outerWidth==1024;
		System.out.println("Before ");
		selenium.windowMaximize();
		String windowwidth = selenium
				.getEval("selenium.browserbot.getCurrentWindow().window.outerWidth");
		selenium.selectWindow("");

		if (Integer.parseInt(windowwidth) == 1032)
			return true;
		return false;

	}

	public boolean verifyWindoMaximisedAfterminimised() throws Exception {

		new Link(properties.get("audio_search_link")).click();
		new Link(properties.get("audio_image_Link")).clickWithoutWait();
		Thread.sleep(10000);
		selenium
				.getEval("selenium.browserbot.getCurrentWindow().window.resizeTo(250, 300)");
		selenium
				.getEval("selenium.browserbot.getCurrentWindow().window.resizeTo(screen.width, screen.height)");
		String windowwidth = selenium
				.getEval("selenium.browserbot.getCurrentWindow().window.outerWidth");
		selenium.selectWindow("");

		if (Integer.parseInt(windowwidth) == 1032)
			return true;
		return false;
	}

	public boolean verifyWindowMaximisedForSecondTime() throws Exception {
		new Link(properties.get("audio_search_link")).click();
		new Link(properties.get("audio_image_Link")).clickWithoutWait();
		Thread.sleep(10000);
		selenium.selectWindow("");

		new Link(properties.get("audio_image_Link")).clickWithoutWait();
		String windowwidth = selenium
				.getEval("selenium.browserbot.getCurrentWindow().window.outerWidth");

		if (Integer.parseInt(windowwidth) == 1032)
			return true;
		return false;

	}
}
