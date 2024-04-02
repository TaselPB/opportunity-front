package br.ufpb.dcx.oppfyhub.opportunityhub.filter;


import br.ufpb.dcx.oppfyhub.opportunityhub.service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;


import java.io.IOException;


public class FilterTokenJWT extends GenericFilterBean {
    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");
        String method = req.getMethod();
        String uri = req.getRequestURI();

        if (!method.equals("GET")) {
            if (header == null || !header.startsWith("Bearer ")) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Nonexistent or poorly formatted token!");
                return;
                // throw new ServletException("Token inexistente ou mal formatado!");
            }

            // Extraindo apenas o token do cabecalho (removendo o prefixo "Bearer ").
            String token = header.substring(TOKEN_INDEX);
            try {
                Jwts.parser().setSigningKey(JWTService.TOKEN_KEY).parseClaimsJws(token).getBody();
            } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
                     | UnsupportedJwtException | IllegalArgumentException e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;// a requisição nem precisa passar adiante, retornar já para o cliente pois não
                // pode prosseguir daqui pra frente
                // por falta de autorização
            }

        }
        chain.doFilter(request, response);
    }
}
