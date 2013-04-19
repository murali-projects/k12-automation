package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class AccessToolBoxMenu extends BasePageContainer{

	public AccessToolBoxMenu() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyToolBox() {
	return (new TextLabel(properties.get("ToolBox")).isPresent());
	}

	public boolean verifyToolBoxElements() {
		return (new TextLabel(properties.get("ToolBox_Help")).isPresent() &&
				new TextLabel(properties.get("ToolBox_Preferences")).isPresent()&&
				new TextLabel(properties.get("ToolBox_Dictionary")).isPresent() &&
				new TextLabel(properties.get("ToolBox_TitlesList")).isPresent() 
		);
	}

	
		
	public boolean verifyToolsAreWorking() throws InterruptedException {
		return (helpTool()&& preferenceTool()&& dictionaryTool()&& titlesListTool());
	}

	private boolean titlesListTool() throws InterruptedException {
		new Link(properties.get("ToolBox_TitlesList")).click();
		String currentUrl= new Url().getUrl();
		new Url().goBackToPreviousPage();
		return currentUrl.contains("");
	}

	private boolean dictionaryTool() throws InterruptedException {
		new Link(properties.get("ToolBox_Dictionary")).click();
		String currentUrl= new Url().getUrl();
		new Url().goBackToPreviousPage();
		return currentUrl.contains("");
	}

	private boolean preferenceTool() throws InterruptedException {
		new Link(properties.get("ToolBox_Preferences")).click();
		String currentUrl= new Url().getUrl();
		new Url().goBackToPreviousPage();
		return currentUrl.contains("");
	}

	
	private boolean helpTool() throws InterruptedException {
		new Link(properties.get("ToolBox_Help")).click();
		String currentUrl= new Url().getUrl();
		new Url().goBackToPreviousPage();
		return currentUrl.contains("");
	}

	public boolean verifyToolBoxIsAvailableOnViewAllPage() throws InterruptedException {
		new Link(properties.get("reference_viewAll")).click();
		return (verifyToolBoxElements());
	}

	public boolean verifyToolsAreWorkingOnViewAllPage() throws InterruptedException {
		new Link(properties.get("reference_viewAll")).click();
		return (helpTool()&& preferenceTool()&& dictionaryTool()&& titlesListTool());
	}

	
}
