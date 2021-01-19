package com.oppabet.site.oppabet.modalWindows.searchWindow

import io.qameta.allure.Description
import io.qameta.atlas.webdriver.AtlasWebElement
import io.qameta.atlas.webdriver.extension.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

interface Content {
    @FindBy(".//div[@class='search-popup-events__item']")
    @Description("Таблица с результатами поиска")
    fun searchResults(): List<AtlasWebElement<*>>
}