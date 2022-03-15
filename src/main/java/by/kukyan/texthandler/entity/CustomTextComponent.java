package by.kukyan.texthandler.entity;

import java.util.List;

public interface CustomTextComponent {
    boolean add(CustomTextComponent component);

    boolean remove(CustomTextComponent component);

    TextElementType getCompositeElementType();

    List<CustomTextComponent> getInnerComponents();
}
