package com.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest implements ApplicationContextAware {
	
	public static ApplicationContext CONTEXT;
	
	@Autowired
	protected MockMvc mockMvc;


	@Test
	public void contextLoads() {
	}
	
	@Override
	@Autowired
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	public void registerSingletonBean(String name, Object obj)
	{
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext)CONTEXT).getBeanFactory();
		
		beanFactory.registerSingleton(name, obj);
	}
	public void clearSingletonBeans()
	{
		try {
			ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext)CONTEXT).getBeanFactory();
			beanFactory.destroyBean(CONTEXT.getBean("stexecution1"));
		} catch (Exception e) {
		}
		
		try {
			BeanDefinitionRegistry factory = (BeanDefinitionRegistry) CONTEXT.getAutowireCapableBeanFactory();
			factory.removeBeanDefinition("stexecution1");
		} catch (Exception e) {
		}
		
		try {
			ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext)CONTEXT).getBeanFactory();
			beanFactory.destroySingletons();
		} catch (Exception e) {
		}
	}
}
