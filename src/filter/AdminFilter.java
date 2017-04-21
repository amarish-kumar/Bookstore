package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AdminService;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST
		},
		urlPatterns = { "/Admin/*"},
		servletNames = { "/Admin" })
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
	private static AdminService ADMIN = new AdminService();
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String s = request.getParameter("s");
		if(s != null){
			Integer m = Integer.parseInt(s);
			if(m == 14){
				Map<String,Integer> map = ADMIN.getUserSpents();
				Map<String,Integer> transformedMap = anomizedUsers(map);
				req.getSession().setAttribute("ANOMIZEDREPORT",transformedMap);
				request.getRequestDispatcher("/WEB-INF/responses/AnomizedReport.jspx").forward(request,response);

			}
			else{
				chain.doFilter(request, response);
			}
		}
		else{
			chain.doFilter(request, response);
		}
		
		
		
	}
	
	
	public Map<String,Integer> anomizedUsers(Map<String,Integer> m){
		Set<String> users = m.keySet();
		Map<String,Integer> encryptedMap = new HashMap<String,Integer>();
		for(String u : users){
			String encryptedLogin = encryptUserLogin(u);
			Integer val = m.get(u);
			encryptedMap.put(encryptedLogin,val);
		}
		return encryptedMap;
		
	}
	
	public String encryptUserLogin(String login){
		String encryptedLog = "";
		for(int i = 0; i < login.length(); i++){
			encryptedLog += "*";
		}
		return encryptedLog;
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {


	}

}
