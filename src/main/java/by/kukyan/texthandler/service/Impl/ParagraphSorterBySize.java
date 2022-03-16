package by.kukyan.texthandler.service.Impl;

import by.kukyan.texthandler.comporator.TextComponentComparator;
import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.service.CustomTextSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphSorterBySize implements CustomTextSorter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sort(CustomTextComponent component) throws CustomException {
        if(component.getComponentElementType() != TextElementType.PARAGRAPH){
            logger.error("trying to sort component, not including paragraphs");
            throw new CustomException("trying to sort component, not including paragraphs");
        }
        component.getInnerComponents().sort(new TextComponentComparator());
    }
}
