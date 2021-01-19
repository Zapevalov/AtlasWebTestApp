package com.oppabet.site

import com.oppabet.site.oppabet.listener.AllureLogger
import io.qameta.atlas.core.Atlas
import org.openqa.selenium.WebDriver
import org.testng.annotations.BeforeTest
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import io.qameta.atlas.webdriver.WebDriverConfiguration
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalToIgnoringCase
import org.openqa.selenium.Keys
import org.testng.annotations.AfterTest
import org.testng.annotations.Test
import ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed
import ru.yandex.qatools.matchers.webdriver.TextMatcher.text

class OppabetTest {
    private lateinit var atlas: Atlas
    private lateinit var driver: WebDriver

    @BeforeTest
    fun startDriver() {
        WebDriverManager.chromedriver().driverVersion("87").setup()
        driver = ChromeDriver()
        atlas = Atlas(WebDriverConfiguration(driver, "https://oppabet.com"))
                .listener(AllureLogger())
    }

    @Test
    fun oppabetSimpleTest() {
        onSite().onMainPage().apply {

            open("https://oppabet.com")


            section("LIVE Bets").apply {
                header().apply {
                    title().should(text(equalToIgnoringCase("LIVE Bets")))
                    searchField().sendKeys("Фирма веников не вяжет", Keys.ENTER)
                }
            }

            modalSearchWindow().should(displayed()).apply {
                closeBtn().click()
                closeBtn().should(Matchers.not(displayed()))
            }
        }
    }

    private fun onSite(): OppabetSite {
        return atlas.create(driver, OppabetSite::class.java)
    }

    @AfterTest
    fun stopDriver() {
        driver.close()
    }
}