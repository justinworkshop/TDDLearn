package com.example.tdd;

public class SaveHelper {
    public static final String DEFAULT_TITLE = "DefaultTitle";
    private String mTitle;

    public SaveHelper() {
    }

    public void setData() {
        if (null == mTitle) {
            mTitle = getDefaultTitle();
        }
    }

    public String getDefaultTitle() {
        return DEFAULT_TITLE;
    }

    public String getTitle() {
        return mTitle;
    }
}
