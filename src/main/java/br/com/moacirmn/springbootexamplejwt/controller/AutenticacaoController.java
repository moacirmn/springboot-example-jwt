package br.com.moacirmn.springbootexamplejwt.controller;

import br.com.moacirmn.springbootexamplejwt.dto.LoginDto;
import br.com.moacirmn.springbootexamplejwt.dto.TokenDto;
import br.com.moacirmn.springbootexamplejwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, tokenService.TIPO_BEARER));
        } catch (AuthenticationException ex) {
            return new ResponseEntity("Usuário ou senha inválidos", HttpStatus.BAD_REQUEST);
        }
    }
}
