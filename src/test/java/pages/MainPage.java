package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    SelenideElement
            languageIcon = $(".header__lang-switcher"),
            title = $(".hero-company__title"),
            searchIcon = $(".header-search__search-btn"),
            searchField = $("#search-term-header"),
            searchResultTitle = $(".search-results-header__title"),
            searchResults = $(".search-results__content-section"),
            footerPosition = $(".footer__top");



    public MainPage clickSwitchLanguageIcon() {
        languageIcon.click();
        return this;

    }

    public MainPage searchIconClick() {
        searchIcon.click();
        return this;

    }

    public MainPage setValueOnSearchField(String value) {
        searchField.sendKeys(value);
        return this;

    }

    public MainPage checkTitleOnSearchResult(String value) {
        searchResultTitle.shouldHave(text(value));
        return this;

    }

    public MainPage titleCheck(String value) {
        title.shouldHave(text(value));
        return this;

    }

    public MainPage searchResultIsNotEmpty() {
        searchResults.shouldNotBe(empty);
        return this;

    }

    public MainPage scrollToFooter() {
        footerPosition.scrollIntoView(true);
        return this;

    }

    public MainPage footerAddressCheck(String value) {
        footerPosition.shouldHave(text(value));
        return this;

    }

    public MainPage clickOnText(String tab) {
        $(byText(tab)).click();
        return this;

    }

}
