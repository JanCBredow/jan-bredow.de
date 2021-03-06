package bredow.jan.webserverdemo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /** make sure all requests uses SSL
   * @param http ignored
   * @throws Exception ignored
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.requiresChannel().anyRequest().requiresSecure();
  }
}
