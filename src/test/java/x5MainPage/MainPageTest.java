package x5MainPage;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@Tag("x5mainPageTest")
public class MainPageTest extends TestBase {

    MainPage mainPage = new MainPage();

    @DisplayName("Switch language check")
    @Tag("Switch Language")
    @ParameterizedTest
    @MethodSource
    public void switchLanguageOnMainPage(List<String> items) {
        step("Open main page", () -> open(""));
        step("Click on the lang switcher", () -> mainPage.clickSwitchLanguageIcon());
        step("Result check", () -> $$(".header__nav-list").should(CollectionCondition.texts(items)));
    }

    static Stream<Arguments> switchLanguageOnMainPage() {
        return Stream.of(
                Arguments.of(List.of("Company\n" +
                        "Consumer\n" +
                        "For Partners\n" +
                        "Investors\n" +
                        "Press Centre")));
    }
//to-do > hide to mainpage.java
    @DisplayName("Company tab titles check")
    @Test
    @Tag("Company tab check")
    public void theCompanyTabContent() {
        step("Open main page", () -> open(""));
        step("Open company tab", () -> mainPage.clickOnText("Компания"));
        step("Check investors content exists", () -> $(".for-investors").scrollIntoView(true));
        step("Check header content", () -> $(".for-investors").shouldHave(text("Инвесторам")));
        step("Check investors content exists", () -> $(".key-numbers").scrollIntoView(true));
        step("Check header content", () -> $(".key-numbers").shouldHave(text("Ключевые цифры")));
        step("Check investors content exists", () -> $(".strategy__header").scrollIntoView(true));
        step("Check header content", () -> $(".strategy__header").shouldHave(text("Стратегия")));
    }

    @DisplayName("Consumer tab title check")
    @Test
    @Tag("Consumer tab title")
    public void theConsumerTabContent() {
        step("Open main page", () -> open(""));
        step("Open consumer tab", () -> mainPage.clickOnText("Покупателю"));
        step("Check header content", () -> mainPage.titleCheck("X5 для покупателя"));
    }


    @DisplayName("Check address on page")
    @Tag("Address view on page")
    @Test
    public void viewAddressOnPage() {
        step("Open main page", () -> open(""));
        step("Scroll to address", () -> mainPage.scrollToFooter());
        step("Check address value", () -> mainPage.footerAddressCheck("119049, Россия, г. Москва, улица Коровий Вал, 5, стр. 1"));
    }

    @DisplayName("Search field test")
    @Tag("Search field")
    @Test
    public void searchFieldCheck() {
        step("Open main page", () -> open(""));
        step("Click search icon", () -> mainPage.searchIconClick());
        step("Set value in search field", () -> mainPage.setValueOnSearchField("Новости"));
        step("Click search button", () -> mainPage.clickOnText("Найти"));
        step("Check title", () -> mainPage.checkTitleOnSearchResult("Результаты поиска"));
        step("Check result", () -> mainPage.searchResultIsNotEmpty());
    }
}
