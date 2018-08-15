package org.tahmasebi.services;



import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ServerInitListener implements ApplicationListener<ContextRefreshedEvent>{
	
	static
	{
		System.out.println("ServerInitListener.enclosing_method()");
	}
	
//	@Bean
//	HandlerExceptionResolver customExceptionResolver(){
//		return (HandlerExceptionResolver) new MyHandlerException();
//	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("********************************");
		System.out.println("***** Service initialized ******");
		System.out.println("********************************");
		
	}

}
