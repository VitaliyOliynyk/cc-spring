package eu.vitaliy.cc.spring;

import com.cc.tabs.TabView;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class TabViewBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        addPropertyValue(bean, element, "name");
        addPropertyValue(bean, element, "url");
        addPropertyReference(bean, element, "controller");
    }

    private void addPropertyReference(BeanDefinitionBuilder bean, Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        bean.addPropertyReference(attributeName, attributeValue);
    }

    private void addPropertyValue(BeanDefinitionBuilder bean, Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        bean.addPropertyValue(attributeName, attributeValue);
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return TabView.class;
    }
}
