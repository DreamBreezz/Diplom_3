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
    @DisplayName("Клик по табам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesIngredientsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickSaucesTab()
                .currentTabSauces()
                .scrollToSauces()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickSaucesTab()  // сначала последовательно слева направо
                .currentTabSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Начинки'-'Булки'")
    public void tabClicksBunsIngredientsBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по табам: 'Булки'-'Начинки'-'Соусы'")
    public void tabClicksBunsIngredientsSaucesTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
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
}
