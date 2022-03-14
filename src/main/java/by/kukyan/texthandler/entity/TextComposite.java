package by.kukyan.texthandler.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TextComposite implements TextComponent{
    private TextElementType elementType;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public String toString() {
        String separator = elementType.getSeparator();
        return this.getComponents().stream()
                .map(textComponent -> textComponent.toString() + separator)
                .collect(Collectors.joining());
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public TextElementType getElementType() {
        return elementType;
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return elementType == that.elementType && Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType, components);
    }
}
