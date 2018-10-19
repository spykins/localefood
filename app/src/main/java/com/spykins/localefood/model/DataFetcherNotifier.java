package com.spykins.localefood.model;

import java.util.List;

public interface DataFetcherNotifier {
    void onSuccess(List<Venue> venues);
    void onFailure(String message);
}
