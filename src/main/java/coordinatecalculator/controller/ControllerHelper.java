package coordinatecalculator.controller;

import coordinatecalculator.view.OutputView;
import java.util.Objects;

public abstract class ControllerHelper {

    private final OutputView outputView;

    ControllerHelper(OutputView outputView) {
        this.outputView = Objects.requireNonNull(outputView);
    }

    void repeatWhenThrowException(Runnable runnable) {
        boolean isContinue = true;
        while (isContinue) {
            isContinue = !runMethod(runnable);
        }
    }

    private boolean runMethod(Runnable runnable) {
        try {
            runnable.run();
            return true;
        } catch (IllegalArgumentException exception) {
            outputView.print(exception);
            return false;
        }
    }
}
