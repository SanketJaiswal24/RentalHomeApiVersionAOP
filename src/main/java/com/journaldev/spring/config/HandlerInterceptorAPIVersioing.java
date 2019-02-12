package com.journaldev.spring.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class HandlerInterceptorAPIVersioing   extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception 
	{
		 String split = request.getRequestURI();
		 String[] hope = split.split("/");
		 	 
		 for ( int i = 0; i < hope.length; i++)
		    {
		           //System.out.println(hope[i].equals("v1.0")+"Paths :->"+hope[i]);
		           if(hope[i].equals("v1.0"))
		           {
		        	   System.out.println(" HandlerInterceptor : Older Version : v1.0");
		        	   //response.sendRedirect("");
		        	   return true;
		           }
		           else if(hope[i].equals("v1.1"))
		           {
		        	   System.out.println(" HandlerInterceptor : Current Version : v1.1");
		        	   return true;
		           }
		           else if(hope[i].equals("v1.2"))
		           {
		        	   System.out.println(" HandlerInterceptor :  New Version can you use it : v1.2");
		        	   return true;
		           }
		    }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
