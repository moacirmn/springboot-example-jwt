package br.com.moacirmn.springbootexamplejwt.controller;

import br.com.moacirmn.springbootexamplejwt.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeAdminController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @RequestMapping("/admin")
    @ResponseBody
    public String dashboard() {
        return "Olá "+autenticacaoService.getUsuario().getNome()+" você está logado com o perfil de "+autenticacaoService.getPerfil().getNome()+"!";
    }

}
