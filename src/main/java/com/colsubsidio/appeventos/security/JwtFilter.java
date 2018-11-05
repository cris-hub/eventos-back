//package com.colsubsidio.appeventos.security;
//
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.security.core.Authentication;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;
//
//public class JwtFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain filterChain)
//            throws IOException, ServletException {
//
//
//        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        filterChain.doFilter(request,response);
//    }
//}
