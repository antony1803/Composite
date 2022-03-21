package by.kukyan.texthandler.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite implements CustomTextComponent {
    private TextElementType elementType;
    private List<CustomTextComponent> components = new ArrayList<>();

    public TextComposite(TextElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public String toString() {
        String separator = elementType.getSeparator();
        return getInnerComponents().stream()
                .map(textComponent -> textComponent.toString())
                .collect(Collectors.joining()) + separator;
    }

    @Override
    public boolean add(CustomTextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(CustomTextComponent component) {
        return components.remove(component);
    }

    @Override
    public TextElementType getComponentElementType() {
        return elementType;
    }

    @Override
    public List<CustomTextComponent> getInnerComponents() {
        return components;
    }

    @Override
    public void setInnerComponents(List<CustomTextComponent> newComponents) {
        components = newComponents;
    }


    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return elementType == that.elementType && components.equals(that.components);
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }
}
