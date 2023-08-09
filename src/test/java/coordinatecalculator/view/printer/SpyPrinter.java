package coordinatecalculator.view.printer;

public class SpyPrinter implements Printer {

    private String printedMessage = "";

    @Override
    public void print(String message) {
        printedMessage = printedMessage.concat(message);
    }

    public String getPrintedMessage() {
        return printedMessage;
    }
}
