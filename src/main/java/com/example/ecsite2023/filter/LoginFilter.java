package com.example.ecsite2023.filter;

import com.example.ecsite2023.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class LoginFilter implements Filter {
    private final HttpSession session;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if ((User) session.getAttribute("loginUser") != null) {
            chain.doFilter(req, res);
        } else {
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect("/");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
    @Bean("loginBean")
    public FilterRegistrationBean<LoginFilter> filter() {
        FilterRegistrationBean<LoginFilter> loginBean = new FilterRegistrationBean<>();

        loginBean.setFilter(new LoginFilter(session));
        loginBean.addUrlPatterns("/addCart/*");
        loginBean.addUrlPatterns("/editCart/*");
        loginBean.addUrlPatterns("/purchase/*");
        return loginBean;
    }
}