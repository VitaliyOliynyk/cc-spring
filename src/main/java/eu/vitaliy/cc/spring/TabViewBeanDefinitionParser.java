package eu.vitaliy.cc.spring;

import com.cc.tabs.TabView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class TabViewBeanDefinitionParser extends  AbstractCCBeanDefinitionParser  {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        addPropertyValue(bean, element, "name");
        addPropertyValue(bean, element, "url");
        addPropertyReference(bean, element, "controller");
        addNestedProperty(element, parserContext, bean);
    }

    private void addNestedProperty(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        BeanDefinitionHolder holder = parserContext.getDelegate().parseBeanDefinitionElement(element);
        for (PropertyValue propertyValue : holder.getBeanDefinition().getPropertyValues().getPropertyValueList()) {
            bean.addPropertyValue(propertyValue.getName(), propertyValue.getValue());
        }
    }


    @Override
    protected Class<?> getBeanClass(Element element) {
        String className = element.getAttribute("class");
        if (StringUtils.isNotEmpty(className)) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return TabView.class;
    }
}
