package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    SelenideElement
            languageIcon = $(".header__lang-switcher"),
            title = $(".hero-company__title");


    public MainPage clickSwitchLanguageIcon() {
        languageIcon.click();
        return this;

    }

    public MainPage titleCheck(String value) {
       title.shouldHave(text(value));
        return this;

    }

    public MainPage clickOnText(String tab) {
        $(byText(tab)).click();
        return this;

    }

}
