package sp.action;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import sp.blo.CommonBlo;
/**
 * @author ThuyNT
 */
public class InitDataListener implements ServletContextListener {
    /** . */
    private ServletContext servletContext;
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//    	CommonBlo.createData();

    }

}
