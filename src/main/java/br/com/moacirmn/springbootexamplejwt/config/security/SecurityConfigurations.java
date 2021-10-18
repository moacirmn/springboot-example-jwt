package br.com.moacirmn.springbootexamplejwt.config.security;

import br.com.moacirmn.springbootexamplejwt.repository.UsuarioRepository;
import br.com.moacirmn.springbootexamplejwt.security.AutenticacaoViaTokenFilter;
import br.com.moacirmn.springbootexamplejwt.service.AutenticacaoService;
import br.com.moacirmn.springbootexamplejwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        //Configuração do jwt
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Configurações de autorização
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/admin/**").hasAuthority("admin")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Configuração de serviços estáticos
        web.ignoring().antMatchers("/**.html");
    }
    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }*/
}
