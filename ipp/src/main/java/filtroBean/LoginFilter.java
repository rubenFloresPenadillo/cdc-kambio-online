package filtroBean;

import java.io.IOException;

import com.fasterxml.classmate.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.types.CadenasType;
import util.types.PaginasPrivadasType;

public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Entro al Filter.doFilter");
		 
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) resp;
//	    String nombrePagina = request.getParameter("nombrePagina");
	    HttpSession session = request.getSession(false);
	    if(session!=null) {
	    	System.out.println("id session: "+session.getId());
	    }
	    
	    String loginURI = request.getContextPath() + PaginasPrivadasType.PAGINA_INDEX.getValor();
	    String paginaSessionTimeout = request.getContextPath() + PaginasPrivadasType.PAGINA_SESION_TIMEOUT.getValor();

	    
	    if(request.getRequestURI().equals(request.getContextPath()+CadenasType.SLASH.getValor()) || request.getRequestURI().equals(request.getContextPath()+PaginasPrivadasType.PAGINA_INDEX.getValor()) 
	    		|| request.getRequestURI().equals(request.getContextPath()+PaginasPrivadasType.PAGINA_INGRESO.getValor()) ){
	    	chain.doFilter(req, resp);
	    }else {
	    	
//	    	if(nombrePagina!= null && PaginasPrivadasType.PAGINA_INICIO.getValor().contains(nombrePagina)) {
//	    		if (session==null ) {
//	    			response.sendRedirect(paginaSessionTimeout);
//	    		}else {
//	    			chain.doFilter(req, resp);	
//	    		}
//	    	}else {
	    		
			    if (session!=null && session.getAttribute("usuario") != null) {
			    	chain.doFilter(req, resp);
			    }else {
			    	response.sendRedirect(loginURI);
			    }
//	    	}
	    		

	    }

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public boolean include(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
