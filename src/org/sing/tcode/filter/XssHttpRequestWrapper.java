package org.sing.tcode.filter;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author wangl
 * @date 2019-01-08
 */
public class XssHttpRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml4(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values != null){
            String[] escapeValues = new String[values.length];
            for(int i=0; i<values.length; i++){
                escapeValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }
}
