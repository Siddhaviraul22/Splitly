package com.splitly.backend.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        String path = httpRequest.getRequestURI();

        if (path.contains("/login") || path.contains("/api/users")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader =
                httpRequest.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            ((HttpServletResponse) response)
                    .sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Missing or invalid token");
            return;
        }

        String token =
                authHeader.substring(7);

        try {
            JwtUtil.validateTokenAndGetEmail(token);
            chain.doFilter(request, response);
        } catch (Exception e) {
            ((HttpServletResponse) response)
                    .sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Invalid token");
        }
    }
}
