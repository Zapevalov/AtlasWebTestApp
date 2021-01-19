package com.oppabet.site.oppabet.elements

import io.qameta.allure.Description
import io.qameta.atlas.webdriver.AtlasWebElement
import io.qameta.atlas.webdriver.ElementsCollection
import io.qameta.atlas.webdriver.extension.FindBy

interface Section : AtlasWebElement<Section>{
    @FindBy(".//div[@class='c-section__header ']")
    @Description("Хэдер секции")
    fun header(): SectionHeader

    @FindBy(".//div[@data-name='dashboard-champ-content']")
    @Description("Расписание матчей")
    fun dashBoardChampItems():  ElementsCollection<AtlasWebElement<*>>
}