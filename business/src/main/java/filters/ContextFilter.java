package filters;

import utils.WebContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SBT-Smirnov-VA on 24.07.2017.
 */
public class ContextFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        WebContext.setCurrentRequest(request);
        WebContext.setCurrentResponse(response);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            WebContext.setCurrentRequest(null);
            WebContext.setCurrentResponse(null);
        }
    }

    @Override
    public void destroy() {

    }
}
