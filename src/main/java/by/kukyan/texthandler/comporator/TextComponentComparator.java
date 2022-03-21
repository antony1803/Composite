package by.kukyan.texthandler.comporator;

import by.kukyan.texthandler.entity.CustomTextComponent;

import java.util.Comparator;

public class TextComponentComparator implements Comparator<CustomTextComponent> {
    @Override
    public int compare(CustomTextComponent o1, CustomTextComponent o2) {
        return o2.size() - o1.size();
    }
}
