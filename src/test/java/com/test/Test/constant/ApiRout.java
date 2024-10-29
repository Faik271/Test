package com.test.Test.constant;

public enum ApiRout {

    SIMPLE_TEST("/simple/test"),
    UPDATE_SIMPLE_TEST("/simple/test/{id}"),
    GET_ALL_SIMPLE_TESTS("/simple/test/all"),
    GENERATE_EXAM("/simple/test/generate/exam"),
    CHECK_ANSWERS("/simple/test/answers/check");

    private final String url;

    ApiRout(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlWithId(long id) {
        return url.replace("{id}", String.valueOf(id));
    }
}
