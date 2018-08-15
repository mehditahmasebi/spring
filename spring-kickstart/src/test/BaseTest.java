package test;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(BaseTest.APPCONTEXT)
@WebAppConfiguration
public class BaseTest implements ApplicationContextAware {
	public static final String APPCONTEXT = "/applicationContext.xml";
	protected static ApplicationContext CONTEXT;

	@Override
	@Autowired
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		CONTEXT = context;
	}
	
//	@Before
//	public void setup() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WebContent/");
//		viewResolver.setSuffix(".jsp");
//		
//		MockMvcBuilders.standaloneSetup(new PublicController())
//				.setViewResolvers(viewResolver).build();
//		MockMvcBuilders.standaloneSetup(new MainViewController())
//				.setViewResolvers(viewResolver).build();
//	}
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@PostConstruct
	private void init() {
		System.out.println("***** BaseTest started *****");
	}

	public void registerSingletonBean(String name, Object obj) {
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) CONTEXT)
				.getBeanFactory();

		beanFactory.registerSingleton(name, obj);
	}

	public void clearSingletonBeans() {
		try {
			ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) CONTEXT)
					.getBeanFactory();
			beanFactory.destroyBean(CONTEXT.getBean("stexecution1"));
		} catch (Exception e) {
		}

		try {
			BeanDefinitionRegistry factory = (BeanDefinitionRegistry) CONTEXT
					.getAutowireCapableBeanFactory();
			factory.removeBeanDefinition("stexecution1");
		} catch (Exception e) {
		}

		try {
			ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) CONTEXT)
					.getBeanFactory();
			beanFactory.destroySingletons();
		} catch (Exception e) {
		}
	}	
}

