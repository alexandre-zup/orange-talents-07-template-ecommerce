package br.com.zupacademy.mercadolivre.config.security;

import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter extends OncePerRequestFilter {

    private UsuarioRepository repository;
    private TokenService tokenService;

    public AuthFilter(UsuarioRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String conteudoDoHeader = request.getHeader("Authorization");

        Optional<String> optionalToken = getToken(conteudoDoHeader);

        boolean valido = false;

        if(optionalToken.isPresent()) {
            valido = tokenService.isTokenValid(optionalToken.get());
        }

        if(valido) {
            autenticarCliente(optionalToken.get());
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Optional<Usuario> optionalUsuario = repository.findById(idUsuario);
        Usuario usuario = optionalUsuario.get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private Optional<String> getToken(String headerContent) {
        if(headerContent != null && headerContent.startsWith("Bearer ")){
            String token = headerContent.substring(7);
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
