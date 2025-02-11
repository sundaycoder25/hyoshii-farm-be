package hyoshifarm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Ini akan mengaktifkan CORS untuk semua endpoints
                .allowedOrigins(
                        "http://localhost:3000",     // Next.js development
                        "http://localhost:8081"      // Next.js production jika dibutuhkan
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // 1 jam cache untuk pre-flight requests
    }
}