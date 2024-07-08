package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.DriverRule;
import stellarburgers.pages.MainPage;

/**
 * Проверь, что работают переходы к разделам:
 * «Булки»,
 * «Соусы»,
 * «Начинки».
 */

@DisplayName("Навигация по табам конструктора бургеров")
public class MainPageConstructorNavigationTests {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Клик по табам конструктора по порядку")
    public void tabClicksContinuousTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickSaucesTab()  // сначала последовательно слева направо
                .currentTabSauces()
                .scrollToSauces()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickSaucesTab()  // потом слева направо
                .currentTabSauces()
                .scrollToSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по табам конструктора через одного")
    public void tabClicksPassingNextTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickIngredientsTab()  // с Булок сразу на Начинки
                .currentTabIngredients()
                .scrollToIngredients()
                .clickBunsTab()  // с Начинок сразу на Булки
                .currentTabBuns()
                .scrollToBuns();
    }

}
