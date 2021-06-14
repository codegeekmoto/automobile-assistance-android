package com.automobile.assistance.otto;

import com.squareup.otto.Bus;

public class EventBus {

    private static Bus BUS;

    public static Bus getInstance() {
        if (BUS == null) {
            BUS = new Bus();
        }

        return BUS;
    }
}
