package com.spykins.localefood.model;

public interface DataFetcherNotifier {
    void onSuccess(Restaurant body);
    void onFailure(String message);
}
