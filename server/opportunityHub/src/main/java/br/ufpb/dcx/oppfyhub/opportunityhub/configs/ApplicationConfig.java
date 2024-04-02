package br.ufpb.dcx.oppfyhub.opportunityhub.configs;

import br.ufpb.dcx.oppfyhub.opportunityhub.filter.FilterTokenJWT;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<FilterTokenJWT> filterJwt() {
        FilterRegistrationBean<FilterTokenJWT> filterRB = new FilterRegistrationBean<>();
        filterRB.setFilter(new FilterTokenJWT());
        filterRB.addUrlPatterns("/auth/user", "/v1/api/jobs", "/v1/api/jobs/{id}/change", "/v1/api/jobs/{id}/title", "/v1/api/jobs/{id}/delete", "/v1/api/jobs/{id}/interest", "/v1/api/jobs/interest");
        return filterRB;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}