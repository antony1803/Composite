package by.kukyan.texthandler.entity;

import java.util.List;

public interface TextComponent {
    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    TextElementType getElementType();

    List<TextComponent> getComponents();
}
