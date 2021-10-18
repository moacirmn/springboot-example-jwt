package br.com.moacirmn.springbootexamplejwt.service;

import br.com.moacirmn.springbootexamplejwt.model.Perfil;
import br.com.moacirmn.springbootexamplejwt.model.Usuario;
import br.com.moacirmn.springbootexamplejwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if(usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Usuário não localizado!");
    }

    public Usuario getUsuario () {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return usuario != null ? usuario : null;
    }

    public Perfil getPerfil () {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<Perfil> perfis = (List<Perfil>) usuario.getAuthorities();
        return perfis != null ? perfis.get(0) : null;
    }
}
