package oppabet.modalWindows.searchWindow


import com.oppabet.site.oppabet.modalWindows.searchWindow.Content
import io.qameta.allure.Description
import io.qameta.atlas.webdriver.AtlasWebElement
import io.qameta.atlas.webdriver.extension.FindBy
import org.openqa.selenium.WebElement

interface SearchWindow : AtlasWebElement<SearchWindow>{

    @FindBy(".//div[@class='search-popup__close']")
    @Description("Кнопка закрытия окна")
    fun closeBtn(): AtlasWebElement<WebElement>

    @FindBy(".//div[@class='search-popup__header']")
    @Description("Хэдер всплывшего окна")
    fun header(): WindowHeader

    @FindBy(".//div[@class='search-popup-tabs' and //*[contains(text(),'{{tabName}}')]]")
    @Description("Область с результатами поиска")
    fun content(tabName: String): Content
}