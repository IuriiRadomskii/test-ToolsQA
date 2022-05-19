package ru.radomskii.data;

public interface IData {

    void addTestData(String key, Object value);

    <T> T getTestData(String key, Class<T> clazz);

    public void deleteTestDataByKey(String key);
}
