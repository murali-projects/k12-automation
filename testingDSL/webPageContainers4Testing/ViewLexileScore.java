package webPageContainers4Testing;

import java.util.Calendar;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewLexileScore extends BasePageContainer {

	public ViewLexileScore() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean recentDate(String date) {
		// 2004-07-15
		String[] appDateStr = date.split("-");
		int year = Integer.parseInt(appDateStr[0]);
		int month = Integer.parseInt(appDateStr[1]);
		int day = Integer.parseInt(appDateStr[2]);
		Calendar appCal = Calendar.getInstance();
		appCal.set(year, month, day);
		Calendar currentCal = Calendar.getInstance();
		currentCal.set(currentCal.get(Calendar.YEAR), currentCal
				.get(Calendar.MONTH), currentCal.get(Calendar.DAY_OF_MONTH));
		long systemDate = currentCal.getTimeInMillis();
		long appDate = appCal.getTimeInMillis();
		if ((systemDate - appDate) <= 5184000)
			return true;
		return false;
	}

	public boolean olderDate(String date) {
		String[] appDateStr = date.split("-");
		int year = Integer.parseInt(appDateStr[0]);
		int month = Integer.parseInt(appDateStr[1]);
		int day = Integer.parseInt(appDateStr[2]);
		Calendar appCal = Calendar.getInstance();
		appCal.set(year, month, day);
		Calendar currentCal = Calendar.getInstance();
		currentCal.set(currentCal.get(Calendar.YEAR), currentCal
				.get(Calendar.MONTH), currentCal.get(Calendar.DAY_OF_MONTH));
		long systemDate = currentCal.getTimeInMillis();
		long appDate = appCal.getTimeInMillis();
		if ((systemDate - appDate) >= 63072000)
			return true;
		return false;
	}

	public boolean verifyLexileScore(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		int count = new TextLabel("//table[@id='searchResults']/tbody/tr")
				.getXpathCount();
		for (int i = 1; i <= count; i++) {
			String score = (new TextLabel(properties
					.get("contentLevel_locator")
					+ i + "]/td[4]/a/img")).getAttribute("alt");

			if (!score.contains("Lexile")) {
				String date = new TextLabel(
						"//table[@id='searchResults']/tbody/tr[1]/td[2]/span[2]")
						.getLabelText();
				if (!(recentDate(date) || olderDate(date)))
					return false;
			}

		}
		return true;
	}

	public boolean verifyLexileScoreNotDisplayed(String displayGroup)
			throws InterruptedException {
		if (new Link(properties.get(displayGroup + "_viewAll")).isPresent()) {
			new Link(properties.get(displayGroup + "_viewAll")).click();
			int count = new TextLabel("//table[@id='searchResults']/tbody/tr")
					.getXpathCount();
			for (int i = 1; i <= count; i++) {
				String score = (new TextLabel(properties
						.get("contentLevel_locator")
						+ i + "]/td[4]/a/img").getAttribute("alt"));
				if (score.contains("Lexile"))
					return false;
			}
		}
		return true;
	}

}