package coordinatecalculator.view.reader;

public class MockReader implements Reader {

    private static final String DEFAULT_MESSAGE = "";

    private final String message;

    public MockReader(String message) {
        this.message = message;
    }

    public MockReader() {
        this.message = DEFAULT_MESSAGE;
    }

    @Override
    public String read() {
        return message;
    }
}
