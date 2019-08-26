package org.sing.tcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.sing.tcode.web.TsRequest;

public class CharacterFilter implements Filter{

	@Override
    public void destroy() {
   }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                      FilterChain chain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest)req;
            TsRequest wrapRequest= new TsRequest(request,request.getParameterMap());
            chain.doFilter(wrapRequest, res);
   }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
   }
	
}
