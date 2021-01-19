package com.oppabet.site.pages

import io.qameta.atlas.webdriver.WebPage
import io.qameta.atlas.webdriver.extension.FindBy
import io.qameta.atlas.webdriver.extension.Param
import com.oppabet.site.oppabet.elements.Section
import io.qameta.allure.Description
import oppabet.modalWindows.searchWindow.SearchWindow

interface MainPage: WebPage {
    @FindBy("//section[@class='c-section' and .//h2/span[text() = '{{ title }}']]")
    @Description("Секция {{ title }}")
    fun section(@Param("title") title: String): Section

    @FindBy("//div[@class='search-popup v-modal-search']")
    @Description("Модальное диалоговое окно поиска")
    fun modalSearchWindow(): SearchWindow


}