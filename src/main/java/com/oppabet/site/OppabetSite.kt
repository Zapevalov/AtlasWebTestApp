package com.oppabet.site

import io.qameta.atlas.webdriver.WebSite
import io.qameta.atlas.webdriver.extension.Page
import com.oppabet.site.pages.MainPage

interface OppabetSite : WebSite {
    @Page
    fun onMainPage(): MainPage
}