package com.fullsoft.sms.config.ratelimit;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket newBucket() {

        Bandwidth limit = Bandwidth.builder()
                .capacity(10)
                // .refillGreedy(10, Duration.ofMinutes(1)) esta cosa los va generando conforme
                // pasa el tiempo
                .refillIntervally(10, Duration.ofMinutes(1)) // este de acá los regenera todos de golpe al pasar el
                                                             // tiempo
                .build();

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader("X-API-KEY");

        Bucket bucket = cache.computeIfAbsent(
                apiKey,
                k -> newBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setStatus(429);
        response.setContentType("application/json");

        response.getWriter().write("""
                {
                    "code":"TOO_MANY_REQUESTS",
                    "message":"Límite de solicitudes excedido"
                }
                """);
    }

}