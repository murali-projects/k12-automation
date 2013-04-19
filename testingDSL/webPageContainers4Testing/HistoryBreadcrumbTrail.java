package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class HistoryBreadcrumbTrail extends BasePageContainer {

	public HistoryBreadcrumbTrail() throws Exception {
		super();
	}

	public boolean verifyBreadcrumbIsDisplayed() {
		return new TextLabel(properties.get("breadcrumb")).isPresent();
	}

	public boolean verifyUserNavigateToHomePage() throws InterruptedException {
		new Link(properties.get("breadcrumb_home")).click();
		return (new TextLabel(properties.get("breadcrumb_home_clicked")).isPresent())
				&& new TextLabel(properties.get("breadcrumb_list"))
						.getXpathCount() == 1;
	}

	public boolean verifyThreeLinksDisplayed(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		return new TextLabel(properties.get("breadcrumb_list")).getXpathCount() == 3;
	}

	public boolean verifyUserAcessToLinks() throws InterruptedException {
		return verifyResultsPage() && verifyHomePage();
	}

	private boolean verifyResultsPage() throws InterruptedException {
		new Link(properties.get("breadcrumb_results")).click();
		return new TextLabel(properties.get("breadcrumb_results_page"))
				.isPresent();
	}

	private boolean verifyHomePage() throws InterruptedException {
		new Link(properties.get("breadCrumb_home")).click();
		return new TextLabel(properties.get("breadcrumb_home_page"))
				.isPresent();
	}

	public boolean verifyBreadcrumbLinks() {
		return new TextLabel(properties.get("breadCrumb_list") + "[1]/a")
				.isPresent()
				&& new TextLabel(properties.get("breadCrumb_list") + "[2]/a")
						.isPresent()
				&& !new TextLabel(properties.get("breadCrumb_list") + "[3]/a")
						.isPresent();
	}

	public boolean verifyContentInPage() throws InterruptedException {
		new Link(properties.get("viewAll_searchResults") + "[1]/td[2]/h3/a")
				.click();
		return new TextLabel(properties.get("detail_page")).isPresent();
	}

	public boolean verifySearchBoxIsField() throws InterruptedException {
		boolean isValidate = false;
		for (int i = 2; i < new TextLabel(properties.get("breadCrumb_list"))
				.getXpathCount()
				&& i != 0; i--) {
			new Link(properties.get("breadCrumb_list") + "[" + i + "]").click();
			isValidate = new TextLabel(properties.get("searchBox")).isPresent();
		}
		return isValidate;
	}

	public boolean verifyDynamicChangeOfBreadcrumb(String displayGroup)
			throws InterruptedException {
		boolean isValidate = false;
		new Link(properties.get(displayGroup + "_viewAll")).click();
		isValidate = new TextLabel(properties.get("breadCrumb_list"))
				.getXpathCount() == 3;
		new Link(properties.get("breadcrumb_results")).click();
		isValidate = new TextLabel(properties.get("breadCrumb_list"))
				.getXpathCount() == 1;
		return isValidate;
	}

	public boolean verifyAccessToFiveLinks() throws InterruptedException {
		navigateToFiveLinks();
		return verifyFifthPage() && verifyFourthPage() && verifyThirdPage()
				&& verifyResultsPage() && verifyHomePage();
	}

	public boolean verifyLinksForBreadcrumb() {
		boolean isValidate = false;
		for (int i = 1; i <= new TextLabel(properties.get("breadCrumb_list"))
				.getXpathCount() - 1; i++) {
			isValidate = new TextLabel(properties.get("breadCrumb_list") + "["
					+ i + "]/a").isPresent();
		}
		return isValidate
				&& !new TextLabel(properties.get("breadCrumb_list")
						+ "["
						+ new TextLabel(properties.get("breadCrumb_list"))
								.getXpathCount() + "]/a").isPresent();
	}

	private void navigateToFiveLinks() throws InterruptedException {
		new Link(properties.get("breadCrumb_list")).click();
		new Link(properties.get("breadCrumb_list")).click();
		new Link(properties.get("breadCrumb_list")).click();
		new Link(properties.get("breadCrumb_list")).click();
		new Link(properties.get("breadCrumb_list")).click();
		new Link(properties.get("breadCrumb_list")).click();
	}

	private boolean verifyFifthPage() throws InterruptedException {
		new Link(properties.get("breadCrumb_list") + "[5]/a").click();
		return new TextLabel(properties.get("")).isPresent();
	}

	private boolean verifyFourthPage() throws InterruptedException {
		new Link(properties.get("breadCrumb_list") + "[4]/a").click();
		return new TextLabel(properties.get("")).isPresent();
	}

	private boolean verifyThirdPage() throws InterruptedException {
		new Link(properties.get("breadCrumb_list") + "[3]/a").click();
		return new TextLabel(properties.get("")).isPresent();
	}
}
