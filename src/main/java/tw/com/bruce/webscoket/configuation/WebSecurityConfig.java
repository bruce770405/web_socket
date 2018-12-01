/**
 * @program: webscoket
 * @description:
 * @author: BruceHsu
 * @create: 2018-11-03 20:39
 */
package tw.com.bruce.webscoket.configuation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService) throws Exception {
        // 設置UserDetailsService使用BCrypt密碼hash
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    // 使用BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description: 由於Stomp協議依賴於第一個HTTP請求，因此我們需要授權對我們的stomp握手端點進行HTTP調用。
     * @Param: [httpSecurity]
     * @return: void
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/socket").permitAll()
				.antMatchers("/auth/**").permitAll() // token的rest api要允許進入
                .anyRequest().denyAll();
    }


}

