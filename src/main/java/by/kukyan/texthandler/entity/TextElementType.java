package by.kukyan.texthandler.entity;

public enum TextElementType {
    TEXT(""),
    PARAGRAPH("\n"),
    SENTENCE(""),
    WORD("\s"),
    LETTER(""),
    PUNCTUATION(""),
    NUMBER(""),
    SYMBOL("");

    private String separator;

    TextElementType(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}
