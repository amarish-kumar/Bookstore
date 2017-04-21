package listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import DAO.BookDAO;
import bean.Book;
import service.AdminService;

/**
 * Application Lifecycle Listener implementation class Popular
 *
 */
@WebListener
public class Popular implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
	private ServletContext mContext;
	public static final String SESSION_PO = "po";
	public Book popular;

    public Popular() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	 handle(event);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         //handle(event);
    }
    
    public void handle(HttpSessionBindingEvent event){
    	mContext = event.getSession().getServletContext();
    	AdminService ADMINSERVICE = (AdminService) mContext.getAttribute("ADMINSERVICE");
    	if ( event.getName().equals(SESSION_PO) ) {
    		try {
    			//mPrinciple = (double) event.getSession().getAttribute(SESSION_PRINCIPLE);
    			popular =  ADMINSERVICE.getMostPopularBook();
    			mContext.setAttribute("popular",popular);
    			
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
	
}
