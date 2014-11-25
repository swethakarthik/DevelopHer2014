package hack.manage.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppInitializer implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }
 
        //Run this before web application is started
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");    

    }
}
