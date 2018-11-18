package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SBT-Smirnov-VA on 24.07.2017.
 */
public class WebContext {

    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    public static void setCurrentRequest(HttpServletRequest request) {
        currentRequest.set(request);
    }

    public static HttpServletResponse getCurrentResponse() {
        return currentResponse.get();
    }

    public static void setCurrentResponse(HttpServletResponse response) {
        currentResponse.set(response);
    }

}
