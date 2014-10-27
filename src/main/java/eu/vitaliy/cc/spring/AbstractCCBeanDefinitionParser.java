package eu.vitaliy.cc.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Element;
import java.util.List;

public abstract class AbstractCCBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements ApplicationContextAware  {
    private ApplicationContext applicationContext;

    protected void addPropertyReference(BeanDefinitionBuilder bean, Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        bean.addPropertyReference(attributeName, attributeValue);
    }

    protected void addPropertyValue(BeanDefinitionBuilder bean, Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        bean.addPropertyValue(attributeName, attributeValue);
    }

    protected void addPropertyListReference(ParserContext parserContext, BeanDefinitionBuilder bean, Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        String[] referencies = attributeValue.split(",");
        BeanDefinitionRegistry beanDefinitionRegistry = parserContext.getRegistry();

        List<BeanDefinition> listOfComponentsDefinition = new ManagedList<BeanDefinition>(referencies.length);
        for (String referency : referencies) {
            listOfComponentsDefinition.add(beanDefinitionRegistry.getBeanDefinition(referency));
        }

        bean.addPropertyValue(attributeName, listOfComponentsDefinition);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
