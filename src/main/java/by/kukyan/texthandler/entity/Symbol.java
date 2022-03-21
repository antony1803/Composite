package by.kukyan.texthandler.entity;

import java.util.List;

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
    public boolean add(CustomTextComponent component) {
        throw new UnsupportedOperationException("cant add to the leaf element");
    }

    @Override
    public boolean remove(CustomTextComponent component) {
        throw new UnsupportedOperationException("cant remove from the leaf");
    }

    @Override
    public TextElementType getComponentElementType() {
        return elementType;
    }

    @Override
    public List<CustomTextComponent> getInnerComponents() {
        throw new UnsupportedOperationException("cant get element from leaf");
    }

    @Override
    public void setInnerComponents(List<CustomTextComponent> components) {
        throw new UnsupportedOperationException("cant set element in leaf");
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null) {return false;}
        TextComposite composite = new TextComposite(TextElementType.SYMBOL);
        Symbol second = new Symbol(' ', TextElementType.SYMBOL);
        if(o.getClass() == composite.getClass()){
            second = (Symbol) ((TextComposite)o).getInnerComponents().get(0);
        }
        else if(o.getClass()!=getClass()){
            return false;
        }
        else {
            second = (Symbol) o;
        }
        return symbol == second.symbol;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(symbol) + elementType.hashCode();
    }
}
