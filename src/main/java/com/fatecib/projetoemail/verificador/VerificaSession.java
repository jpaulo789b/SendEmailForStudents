/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.verificador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jpsa
 */
@WebFilter(dispatcherTypes = DispatcherType.REQUEST, urlPatterns = "/*")
public class VerificaSession implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Casting do HttpServelt Request
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        //Capturando Sessao
        HttpSession sessao = httpServletRequest.getSession();
        //Está logado ou tentando acessar uma recurso que é publico permito sem estar logado? 
        if (sessao.getAttribute("usuAutenticado") != null || url.lastIndexOf("login.ib") > -1 || url.lastIndexOf("index.jsp") > -
1) {
            chain.doFilter(request, response); //Permite o
        //fluxo da requisicao
        } else {
        //redireciona para login
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }

    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}