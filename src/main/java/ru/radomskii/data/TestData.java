package ru.radomskii.data;

import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestData implements IData{

    public final static String USER = "USER";
    public final static String BASE_URL = "BASE_URL";

    private HashMap<String, Object> testData = new HashMap<>();

    @Override
    public void addTestData(String key, Object value) {
        log.info("Added test data: key {}: value {}", key, value.toString());
        testData.put(key, value);
    }

    @Override
    public <T> T getTestData(String key, Class<T> clazz) {
        log.info("Get test data by key {}", key);
        return (T) testData.get(key);
    }

    @Override
    public void deleteTestDataByKey(String key) {
        testData.remove(key);
    }
}
