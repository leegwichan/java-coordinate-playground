package coordinatecalculator.view.reader;

import java.util.List;

public class MockReader implements Reader {

    private final List<String> messages;
    private int index = 0;

    public MockReader(String message) {
        this.messages = List.of(message);
    }

    public MockReader() {
        this.messages = List.of();
    }

    @Override
    public String read() {
        if (index >= messages.size()) {
            throw new IllegalStateException("더 이상 읽을 내용이 없습니다");
        }
        return messages.get(index++);
    }
}
