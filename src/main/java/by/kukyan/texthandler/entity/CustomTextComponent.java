package by.kukyan.texthandler.entity;

import java.util.List;

public interface CustomTextComponent {
    boolean add(CustomTextComponent component);

    boolean remove(CustomTextComponent component);

    TextElementType getComponentElementType();

    List<CustomTextComponent> getInnerComponents();

    void setInnerComponents(List<CustomTextComponent> components);

    int size();
}
