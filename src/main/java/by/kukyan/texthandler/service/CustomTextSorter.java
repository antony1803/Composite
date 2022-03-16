package by.kukyan.texthandler.service;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.exception.CustomException;

public interface CustomTextSorter {
    void sort(CustomTextComponent component) throws CustomException;
}
