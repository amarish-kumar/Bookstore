package filter;

import java.io.IOException;
import java.io.Writer;

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

import bean.PO;
import model.PODAO;

/**
 * Servlet Filter implementation class CardCheck
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST
		},
		urlPatterns = { "/Confirm"},
		servletNames = { "/Confirm" })
public class CardCheck implements Filter {

    /**
     * Default constructor.
     *  
     */
	private PODAO podao;

    public CardCheck() {
        // TODO Auto-generated constructor stub
    	podao = new PODAO();

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
		Writer out = resp.getWriter();
		resp.setContentType("text/html");
		Integer cc = (Integer) req.getSession().getAttribute("creditcheck");
		if(cc == null)
		{
			req.getSession().setAttribute("creditcheck",0);
			chain.doFilter(request, response);
			
		}
		else{
//			Object num = cc;
//			Integer cc1 = 0;
			if(cc > 3){
				PO po = (PO) req.getSession().getAttribute("po");
				po.setStatus("DENIED");
				podao.updatePO(po);
				out.write("Invalid Credit card");
				
			}
			else
			{
				cc++;
				req.getSession().setAttribute("creditcheck",cc);
				chain.doFilter(request, response);

			}
		}
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
