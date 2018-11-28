/**
 * @program: webscoket
 * @description:
 * @author: BruceHsu
 * @create: 2018-11-03 20:39
 */
package tw.com.bruce.webscoket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tw.com.bruce.webscoket.security.error.handle.RestAuthenticationEntryPoint;
import tw.com.bruce.webscoket.security.filter.LoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 注入
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    RestAuthenticationEntryPoint point;


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
        // 設置UserDetailsService使用BCrypt密碼hash
    }

    // 使用BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由於使用的是JWT，不需要csrf
                .csrf().disable()
                //處理401,403 exception
                .exceptionHandling().authenticationEntryPoint(point)
                .and()
                // 基於token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允許於web靜態資源的無授權訪問
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
                .permitAll()
                //排除swagger需要安全驗證的問題
                .antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**")
                .permitAll()
                // token的rest api要允許進入
                .antMatchers("/auth/**").permitAll()
                // 除上面外的所有請求全部需要認證
                .anyRequest().authenticated()
                .and()
                // 禁cache
                .headers().cacheControl()
        //登入
//				.and()
//				.formLogin().failureHandler(authenticationFalseLoginHandler)
        ;

        //
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public LoginFilter authenticationTokenFilterBean() throws Exception {
        return new LoginFilter();
    }


}

