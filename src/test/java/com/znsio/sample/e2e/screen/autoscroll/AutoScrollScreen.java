package com.znsio.sample.e2e.screen.autoscroll;

import com.znsio.sample.e2e.screen.android.autoscroll.AutoScrollScreenAndroid;
import com.znsio.teswiz.entities.Direction;
import com.znsio.teswiz.entities.Platform;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Drivers;
import com.znsio.teswiz.runner.Runner;
import com.znsio.teswiz.runner.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public abstract class AutoScrollScreen {

    private static final String SCREEN_NAME = AutoScrollScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static AutoScrollScreen get() {
        Driver driver = Drivers.getDriverForCurrentUser(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = Drivers.getVisualDriverForCurrentUser(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new AutoScrollScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(
                SCREEN_NAME + " is not implemented in " + Runner.getPlatform());
    }

    public abstract AutoScrollScreen goToDropdownWindow();

    public abstract AutoScrollScreen scrollInDynamicLayer(Direction direction);

    public abstract boolean isScrollSuccessful();
}
