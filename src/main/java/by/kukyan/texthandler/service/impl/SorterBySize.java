package by.kukyan.texthandler.service.impl;

import by.kukyan.texthandler.comporator.TextComponentComparator;
import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.service.CustomTextSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SorterBySize implements CustomTextSorter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sort(CustomTextComponent component) throws CustomException {
        component.getInnerComponents().sort(new TextComponentComparator());
    }
}
