package com.oppabet.site.oppabet.elements

import io.qameta.allure.Description
import io.qameta.atlas.webdriver.AtlasWebElement
import io.qameta.atlas.webdriver.extension.FindBy
import io.qameta.atlas.webdriver.extension.Selector
import org.openqa.selenium.WebElement

interface SectionHeader: AtlasWebElement<SectionHeader>{
    @FindBy("page_title", selector = Selector.ID)
    @Description("Заголовок")
    fun title(): AtlasWebElement<WebElement>

    @FindBy(".//input")
    @Description("Поле для ввода поискового запроса")
    fun searchField(): AtlasWebElement<WebElement>
}