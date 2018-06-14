/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poruke;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SVETLANA.LOKAL
 */
@WebFilter
public class NoCacheFilter implements Filter {
    private FilterConfig config;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        if (!httpReq.getRequestURI().startsWith(
                httpReq.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { 

            httpRes.setHeader("Cache-Control",
                    "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            httpRes.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            httpRes.setDateHeader("Expires", 0); // Proxies.
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        config = null;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
}