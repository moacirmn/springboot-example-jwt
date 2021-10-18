package br.com.moacirmn.springbootexamplejwt.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotNull
    @NotEmpty(message="Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotNull
    @NotEmpty(message="Senha não pode ser vazia")
    @Length(message = "Senha inválida", min=6)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
