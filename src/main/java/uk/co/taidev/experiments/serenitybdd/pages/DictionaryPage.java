package uk.co.taidev.experiments.serenitybdd.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://en.wiktionary.org/wiki/Wiktionary:Main_Page")
public class DictionaryPage extends PageObject {

    @FindBy(name = "search")
    private WebElementFacade searchTerms;

    @FindBy(name = "go")
    private WebElementFacade lookupButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        List<WebElement> results = definitionList.findElements(By.tagName("li"));

        return results.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}