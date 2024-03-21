package com.hcm.grw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLogFilter implements Filter {
	
	private String servletEndPoint = ".do";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("AccessLogFilter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		if(req.getRequestURI().endsWith(servletEndPoint)) {
			
			String url = StringUtils.defaultIfBlank(req.getRequestURL().toString(), "-");
			String queryString = StringUtils.defaultIfEmpty(req.getQueryString(), "");
			String agent = StringUtils.defaultIfEmpty(req.getHeader("User-Agent"), "-");
			String remoteAddr = req.getRemoteAddr();
			
			log.debug("[AccessLogFilter]\t Client 요청주소 : {}", url + "?" + queryString);
			log.debug("[AccessLogFilter]\t Client IP : {}" , remoteAddr);
			log.debug("[AccessLogFilter]\t Client 소프트웨어 정보 : {}" , agent);
			
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		log.info("AccessLogFilter destroy...");
	}
	
}