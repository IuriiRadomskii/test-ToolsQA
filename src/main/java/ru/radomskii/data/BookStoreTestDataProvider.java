package ru.radomskii.data;

import org.testng.annotations.DataProvider;

public class BookStoreTestDataProvider {

    @DataProvider(name = "Search input data")
    public static Object[][] getSearchInputData() {
        return new Object[][] {
            {"Git", true},
            {"Richard", true},
            {"No Starch", true},
            {"Abracadabra", false},
            {"10", false}
        };
    }

    @DataProvider(name = "Rows on page display options")
    public static Object[][] getBookNumber() {
        return new Object[][] {
            {new int[]{5, 10, 20, 25, 50, 100}}
        };
    }

}
