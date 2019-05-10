package filters;

import utils.UserUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smirnov-VA on 21.07.2017.
 */
public class LoginFilter implements Filter {

    private static final String RESOURCES_PATH = "resourcesPath";
    private static final String AUTH_URL = "authUrl";
    private static final String ERROR_URL = "errorUrl";
    private static final String EXIT_URL = "exitUrl";
    private static final int MAP_PATHS_SIZE = 3;
    private static Map<String, String> paths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        if (filterConfig != null) {
            paths = new HashMap<>(MAP_PATHS_SIZE);
            paths.put(RESOURCES_PATH, filterConfig.getInitParameter(RESOURCES_PATH));
            paths.put(AUTH_URL, filterConfig.getInitParameter(AUTH_URL));
            paths.put(ERROR_URL, filterConfig.getInitParameter(ERROR_URL));
            paths.put(EXIT_URL, filterConfig.getInitParameter(EXIT_URL));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Разрешаем переходить по страницам только если пользователь прошел авторизацию, либо на разрешенные урлы
        if (UserUtils.isUserLogin() || isAcceptedUrl(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + paths.get(ERROR_URL));
        }
    }

    private boolean isAcceptedUrl(HttpServletRequest request) {
        return paths.values().contains(request.getServletPath()) ;
    }

    @Override
    public void destroy() {

    }
}
