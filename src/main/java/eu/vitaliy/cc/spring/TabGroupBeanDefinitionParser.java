package eu.vitaliy.cc.spring;

import com.cc.tabs.TabGroup;
import com.cc.tabs.TabView;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class TabGroupBeanDefinitionParser extends AbstractCCBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        addPropertyValue(bean, element, "name");
        addPropertyValue(bean, element, "url");
        addPropertyListReference(parserContext, bean, element, "tabs");
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return TabGroup.class;
    }
}
