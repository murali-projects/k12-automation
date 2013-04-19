package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewMarkupInDocumentBody extends BasePageContainer{

	public ViewMarkupInDocumentBody() throws Exception {
		super();
	}
	
	public boolean verifyMarkupsPresentInDocument() throws InterruptedException{
		new Link(properties.get("")).click();
		return new TextLabel(properties.get("") + "/h5").isPresent()
				&& new TextLabel(properties.get("") + "/br").isPresent()
				&& new TextLabel(properties.get("") + "/sub").isPresent()
				&& new TextLabel(properties.get("") + "/b").isPresent()
				&& new TextLabel(properties.get("") + "/sup").isPresent()
				&& new TextLabel(properties.get("") + "/i").isPresent()
				&& new TextLabel(properties.get("") + "/h1").isPresent()
				&& new TextLabel(properties.get("") + "/under-score").isPresent()
				&& new TextLabel(properties.get("") + "/hr").isPresent()
				&& new TextLabel(properties.get("") + "/small").isPresent();
	}
	
	public boolean verifyBoldLettersPresent(){
		return new TextLabel(properties.get("") + "/b").isPresent();
	}
	
	public boolean verifyItalicsPresent(){
		return new TextLabel(properties.get("") + "/i").isPresent();
	}
	
	public boolean verifyHyperlinksPresent(){
		return new TextLabel(properties.get("") + "/a").isPresent();
	}

}
