package k12.dev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import k12.BaseWebPageTest;
import utils.PropertyReader;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.Url;

public abstract class BaseDevWebPageTest extends BaseWebPageTest {

	private SimpleDateFormat formatter = new SimpleDateFormat(
			"MM/dd/yyyy:HH:mm");

	protected void loadProperties() {
		properties = new PropertyReader(
				"properties/default.dev.search.properties");
	}

	/**
	 * used to time bomb tests at noon usage: isBefore("10/25/2010")
	 */
	protected boolean isBefore(String date) {
		try {
			Date utilDate = formatter.parse(date + ":12:00");
			return (new Date()).before(utilDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return false; // fail hard
		}
	}
	
	public void openPage(String pageUrl) throws Exception {
		Url url = new Url();
		String pageUrlString = url.getUrl() + properties.get(pageUrl);
		url.goToPage(pageUrlString);
	}
	


}
