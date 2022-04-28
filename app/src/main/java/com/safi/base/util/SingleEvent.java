package com.safi.base.util;

public class SingleEvent<T> {
    private final T content;
    private Boolean hasBeenHandled = false;

    public SingleEvent(T content) {
        this.content = content;
    }


    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }
}
