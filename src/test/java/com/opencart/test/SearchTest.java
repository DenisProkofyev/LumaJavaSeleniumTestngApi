package com.opencart.test;

import com.opencart.base.BaseTest;
import com.opencart.model.HomePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    @Story("Search field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the search field visibility")
    public void testSearchFieldVisibility() {

        boolean isSearchFieldVisible = new HomePage(getDriver())
                .getHeader().isSearchFieldVisible();

        Allure.step("Verify the Search field is visible");
        Assert.assertTrue(isSearchFieldVisible, "The Search field is not visible");
    }

    @Test(groups = "regression")
    @Story("Search field")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify the search result page heading")
    public void testSearchResultInPageHeading() {
        final String searchValue = "Samsung";

        String searchPageHeading = new HomePage(getDriver())
                .getHeader().typeInSearchValue(searchValue)
                .clickSearchButton()
                .getPageHeading();

        Allure.step("Verify the Search result page heading has '" + searchValue + "'");
        Assert.assertEquals(searchPageHeading, searchValue);
    }

    @Test(groups = "regression")
    @Story("Search field")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the message when the search term does not match any products")
    public void testMessageForEmptySearchResult() {
        final String searchValue = "Xiaomi";
        final String expectedNoSearchResultMessage = "There is no product that matches the search criteria.";

        String actualNoSearchResultMessage = new HomePage(getDriver())
                .getHeader().typeInSearchValue(searchValue)
                .clickSearchButton()
                .getNoResultsMessage();

        Allure.step("Verify the empty search result message");
        Assert.assertEquals(actualNoSearchResultMessage, expectedNoSearchResultMessage);
    }
}
