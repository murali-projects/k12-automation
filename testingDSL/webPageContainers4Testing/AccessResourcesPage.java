package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class AccessResourcesPage extends BasePageContainer {

	public AccessResourcesPage() throws Exception {
		super();
	}

	public boolean verifyResourcesLinkIsPresentAndAccesible()
			throws InterruptedException {
		if (new TextLabel(properties.get("resource_tab")).isPresent())
			new Link(properties.get("resource_tab")).click();
		return new TextLabel(properties.get("tools_tabs")).isPresent();
	}

	public boolean verifyTabsUnderResourceLink() throws InterruptedException {
		return new TextLabel(properties.get("guided_tour_tab")).isPresent()
				&& new TextLabel(properties.get("search_tip_tab")).isPresent()
				&& new TextLabel(properties.get("homework_helper_tab"))
						.isPresent()
				&& new TextLabel(properties.get("getting_started_tab"))
						.isPresent()
				&& new TextLabel(properties.get("wrapping_up_tab")).isPresent()
				&& new TextLabel(properties.get("research_guide")).isPresent()
				&& new TextLabel(properties.get("share_work_tab")).isPresent()
				&& new TextLabel(properties.get("feedback_tab")).isPresent()
				&& new TextLabel(properties.get("curiculum_standards_tab"))
						.isPresent()
				&& new TextLabel(properties.get("lesson_plan_builder_tab"))
						.isPresent();
	}

	public boolean verifyBasicSearchAndFooterArePresent()
			throws InterruptedException {
		return new TextLabel(properties.get("search_box")).isPresent()
				&& new TextLabel(properties.get("footer_link")).isPresent();
	}

	public boolean verifyOptionsForGettingStarted() throws InterruptedException {
		new Link(properties.get("getting_started_tab")).click();
		return verifyGettingStartedOptions();
	}

	public boolean verifyOptionsForWrappingItUp() throws InterruptedException {
		new Link(properties.get("getting_started_tab")).click();
		return verifyWrappingItUpOptions();
	}

	public boolean verifyOptionsForResearchGuide() throws InterruptedException {
		new Link(properties.get("getting_started_tab")).click();
		return verifyResearchGuideOptions();
	}

	public void navigateToHomeTab() throws InterruptedException {
		new Link(properties.get("home_tab")).click();
	}

	private boolean verifyGettingStartedOptions() {
		return new TextLabel(properties.get("judge_information")).isPresent()
				&& new TextLabel(properties.get("make_concept_web"))
						.isPresent()
				&& new TextLabel(properties.get("choose_topic")).isPresent()
				&& new TextLabel(properties.get("write_topic")).isPresent()
				&& new TextLabel(properties.get("make_outline")).isPresent()
				&& new TextLabel(properties.get("read_internet_graphs"))
						.isPresent();
	}

	private boolean verifyWrappingItUpOptions() {
		return new TextLabel(properties.get("cite_source")).isPresent()
				&& new TextLabel(properties.get("orgazine_report")).isPresent()
				&& new TextLabel(properties.get("build_argument")).isPresent()
				&& new TextLabel(properties.get("write_conclusion"))
						.isPresent()
				&& new TextLabel(properties.get("write_thesis")).isPresent()
				&& new TextLabel(properties.get("create_visual")).isPresent()
				&& new TextLabel(properties.get("footnote")).isPresent();
	}

	private boolean verifyResearchGuideOptions() {
		return new TextLabel(properties.get("dis_fact_opnion")).isPresent()
				&& new TextLabel(properties.get("dis_primary_secondary"))
						.isPresent()
				&& new TextLabel(properties.get("evaluating_info")).isPresent()
				&& new TextLabel(properties.get("review_authors")).isPresent()
				&& new TextLabel(properties.get("identify_article"))
						.isPresent()
				&& new TextLabel(properties.get("student_resource_center"))
						.isPresent();
	}

	public boolean verifyBreadCrumb() {
		return new TextLabel(properties.get("resource_breadcrumb")).isPresent();
	}
}
