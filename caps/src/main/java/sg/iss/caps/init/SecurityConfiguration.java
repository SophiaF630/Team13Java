package sg.iss.caps.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Autowired
    PasswordEncoder passwordEncoder;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder)
        .withUser("user").password(passwordEncoder.encode("password")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login")
            .permitAll()
        .antMatchers("/**")
            .hasAnyRole("ADMIN", "USER")
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/admin/home")
            .failureUrl("/login/error")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
            .csrf()
            .disable();
    }
}

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.iss.Model.User;
@Configuration
@EnableWebSecurity // Very important!
@EnableGlobalMethodSecurity(securedEnabled = true)
@Profile("dev")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean () throws Exception {
	       super.authenticationManagerBean ();
	       return null;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http		
	    .authorizeRequests()
	       .antMatchers("/login").permitAll()
	       .antMatchers("/admin/**").hasRole("ADMIN") 
	       //.antMatchers("/admin/**").access("hasRole('ADMIN') and hasIpAddress('123.123.123.123')") // pass SPEL using access method
	       .anyRequest().authenticated()
	       .and()
	       .httpBasic()
	          .authenticationEntryPoint(authenticationEntryPoint);
		
	   /*.formLogin()
	       .loginPage("/login")
	       .usernameParameter("UserID")
	       .passwordParameter("Password")
	       .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
	           for (GrantedAuthority authority : auth.getAuthorities()) {
	              System.out.println(authority.getAuthority());
	           }
	           System.out.println(auth.getName());
	           res.sendRedirect("/admin/home"); // Redirect user to admin homepage
	        })
	       .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
	           String errMsg="";
	           if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
	              errMsg="Invalid username or password.";
	           }else{
	              errMsg="Unknown error - "+exp.getMessage();
	           }
	           req.getSession().setAttribute("message", errMsg);
	           res.sendRedirect("/login"); // Redirect user to login page with error message.
	        })
	       .permitAll()
	       .and()
	       .csrf().disable();
	}
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("password").roles("ADMIN")
			.and().withUser("user").password("password").roles("USER");
	}
	
	
	    }
*/

/*
 * http.authorizeRequests() .antMatchers("/user/new/*").hasAnyRole("USER",
 * "ADMIN") .antMatchers("/user/login/admin/*").hasAnyRole("USER", "ADMIN")
 * .antMatchers("/user/*").permitAll() .and() http.formLogin()
 * .loginPage("/user/") .loginProcessingUrl("/login")
 * .usernameParameter("username") .defaultSuccessUrl("/user/", true)
 * .defaultSuccessUrl("/new.html",true); .and() .logout()
 * .logoutUrl("/user/logout") .and() .csrf() .and() .exceptionHandling()
 * .accessDeniedPage("/user/forbidden"); super.configure(http);
 */ // Very important!
