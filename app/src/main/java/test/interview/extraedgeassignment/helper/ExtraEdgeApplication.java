package test.interview.extraedgeassignment.helper;

import android.app.Application;

public class ExtraEdgeApplication extends Application {
    private static ExtraEdgeApplication instance;

    public ExtraEdgeApplication() {
        instance = this;
    }

    public static ExtraEdgeApplication getInstance() {
        return instance;
    }
}