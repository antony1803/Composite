package by.kukyan.texthandler.entity;

import java.util.List;
import java.util.Objects;

public class Symbol implements TextComponent{
    private TextElementType elementType;
    private char value;

    public Symbol(char newVal, TextElementType type){
        elementType = type;
        value = newVal;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException("cant add to the leaf element");
    }

    @Override
    public boolean remove(TextComponent component) {
        throw new UnsupportedOperationException("cant remove from the leaf");
    }

    @Override
    public TextElementType getElementType() {
        return elementType;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("cant get element from leaf");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return value == symbol.value && elementType == symbol.elementType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType, value);
    }
}
