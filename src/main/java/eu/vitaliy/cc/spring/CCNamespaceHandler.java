package eu.vitaliy.cc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CCNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("tabview", new TabViewBeanDefinitionParser());
        registerBeanDefinitionParser("tabgroup", new TabGroupBeanDefinitionParser());
    }
}
