package fr.leo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import fr.leo.service.IMetier;
import fr.leo.service.SecurityContext;

@ComponentScan(value = {"fr.leo.service","fr.leo.aspects"})
public class Application {

	public static void main(String[] args) {
		
		try {
			SecurityContext.authenticate("root", "1234", new String[] {"USER"} );
			
			ApplicationContext applicationContext =
					new AnnotationConfigApplicationContext(Application.class); 
			
			IMetier metier = applicationContext.getBean(IMetier.class);
			System.out.println("***********************");
			System.out.println(metier.getClass().getName());
			System.out.println("***********************");	
			
			metier.process();
			System.out.println(metier.compute());
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		}
	}

}
