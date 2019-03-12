package danielh1307.springbootsecurityexample.infrastructure.security;

import danielh1307.springbootsecurityexample.domain.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.core.context.SecurityContextHolder.*;

public class HeaderBasedAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            String hasRole = headerValue(httpServletRequest, "role", "true");
            User user = new User("Hugo", "Hase", "true".equals(hasRole));

            SecurityContext securityContext = createEmptyContext();
            securityContext.setAuthentication(new PreAuthenticatedUserAuthenticationToken(user));

            setContext(securityContext);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            clearContext();
        }
    }

    private static String headerValue(HttpServletRequest request, String headerName, String defaultValue) {
        String headerValue = request.getHeader(headerName);

        if (headerValue == null) {
            return defaultValue;
        }

        return headerValue;
    }
}
