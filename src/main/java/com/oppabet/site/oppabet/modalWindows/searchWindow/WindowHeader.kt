package oppabet.modalWindows.searchWindow

import io.qameta.allure.Description
import io.qameta.atlas.webdriver.AtlasWebElement
import io.qameta.atlas.webdriver.extension.FindBy
import io.qameta.atlas.webdriver.extension.Param
import io.qameta.atlas.webdriver.extension.Selector
import org.openqa.selenium.WebElement

interface WindowHeader{
    @FindBy(".//div[@class='search-popup__title']")
    @Description("Заголовок окна")
    fun title(): AtlasWebElement<*>

    @FindBy(".//span")
    @Description("Кол-во найденных записей")
    fun searchResult(): AtlasWebElement<*>

    @FindBy(".//div[@class='c-checkbox' and ./label[text()='{{ name }}']]")
    @Description("Чебокс {{ name }}")
    fun checkBox(@Param("name") name: String): AtlasWebElement<*>

    @FindBy("search-in-popup", selector = Selector.ID)
    @Description("Поисковое поле")
    fun searchField(): AtlasWebElement<*>

    @FindBy(".//div[@class='search-popup__clear']")
    @Description("кнопка очистки поискового поля")
    fun clearSearchFieldBtn(): AtlasWebElement<*>

    @FindBy(".//button")
    @Description("Кнопка \"Поиск\"")
    fun searchBtn(): AtlasWebElement<*>
}