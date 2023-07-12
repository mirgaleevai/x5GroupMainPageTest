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

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

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
        sleep(5000);
    }

    @DisplayName("Consumer tab title check")
    @Test
    @Tag("Consumer tab title")
    public void theConsumerTabContent() {
        step("Open main page", () -> open(""));
        step("Open сonsumer tab", () -> mainPage.clickOnText("Покупателю"));
        step("Check header content", () -> mainPage.titleCheck("X5 для покупателя"));
    }


    @DisplayName("For Partners tab content check")
    @Tag("For Partners tab content")
    @ParameterizedTest
    @MethodSource
    public void forPartnersTabContent(List<String> items) {
        step("Open main page", () -> open(""));
        step("Open partners tab", () -> mainPage.clickOnText("Партнерам"));
        step("Check header content", () -> mainPage.titleCheck("Партнерам"));
        step("Open partners tab", () -> mainPage.clickOnText("Подробнее"));
        step("Check navigation items", () -> $$(".aside__nav").should(CollectionCondition.texts(items)));
    }
    static Stream<Arguments> forPartnersTabContent() {
        return Stream.of(
                Arguments.of(List.of("Добросовестное партнёрство\n" +
                        "Поставщикам\n" +
                        "Сервисы для поставщиков\n" +
                        "Маркетинговые возможности\n" +
                        "Стать франчайзи\n" +
                        "X5 Transport\n" +
                        "Операции с недвижимостью\n" +
                        "X5 Import\n" +
                        "X5 Ready Food\n" +
                        "Закупки для собственных нужд X5 Group")));
    }


    @DisplayName("Search field test")
    @Tag("Search field")
    @Test
    public void searchFieldCheck() {
        step("Open main page", () -> open(""));
        step("Click search icon", () -> $(".header-search__search-btn").click());
        step("Set value in search field", () -> $("#search-term-header").sendKeys("Новости"));
        step("Click search button", () -> mainPage.clickOnText("Найти"));
        step("Check title", () -> $(".search-results-header__title").shouldHave(text("Результаты поиска")));
        step("Check result", () -> $(".search-results__content-section").shouldNotBe(empty));
    }
}
