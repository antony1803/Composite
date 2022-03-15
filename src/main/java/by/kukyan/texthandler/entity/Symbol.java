package by.kukyan.texthandler.entity;

import java.util.List;
import java.util.Objects;

public class Symbol implements CustomTextComponent {
    private TextElementType elementType;
    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    public Symbol(char newSym, TextElementType type){
        elementType = type;
        symbol = newSym;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean add(CustomTextComponent component) {
        throw new UnsupportedOperationException("cant add to the leaf element");
    }

    @Override
    public boolean remove(CustomTextComponent component) {
        throw new UnsupportedOperationException("cant remove from the leaf");
    }

    @Override
    public TextElementType getCompositeElementType() {
        return elementType;
    }

    @Override
    public List<CustomTextComponent> getInnerComponents() {
        throw new UnsupportedOperationException("cant get element from leaf");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol second = (Symbol) o;
        return symbol == second.symbol && elementType == second.elementType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType, symbol);
    }
}
