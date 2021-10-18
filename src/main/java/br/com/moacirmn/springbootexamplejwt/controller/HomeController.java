package br.com.moacirmn.springbootexamplejwt.controller;

import br.com.moacirmn.springbootexamplejwt.model.Perfil;
import br.com.moacirmn.springbootexamplejwt.model.Usuario;
import br.com.moacirmn.springbootexamplejwt.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
       return "Olá "+autenticacaoService.getUsuario().getNome();
    }

}
