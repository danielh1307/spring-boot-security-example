package danielh1307.springbootsecurityexample.configuration;

import danielh1307.springbootsecurityexample.infrastructure.security.HeaderBasedAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity httpSecurity) throws Exception {
                getHttp().addFilterBefore(new HeaderBasedAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                        .authorizeRequests()
                        .antMatchers("/everyone").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .httpBasic();
            }
        };
    }
}
