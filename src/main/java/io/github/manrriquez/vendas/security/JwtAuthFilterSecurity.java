package io.github.manrriquez.vendas.security;

import io.github.manrriquez.vendas.services.JwtService;
import io.github.manrriquez.vendas.services.ServiceImpl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilterSecurity extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthFilterSecurity(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if (validarToken(authorization)) {
            String token = authorization.split(" ")[1];
            String loginUsuario = jwtService.obterLoginUsuario(token);
            UserDetails usuario = userDetailsService.loadUserByUsername(loginUsuario);
            UsernamePasswordAuthenticationToken user = new
                    UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities());
            user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(user);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean validarToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            if (jwtService.tokenValido(token)) {
                return true;
            }
        }
        return false;
    }

}
