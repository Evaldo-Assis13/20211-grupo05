package com.fatec.scc.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fatec.scc.servico.UserDetailsServiceI;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	Logger logger = LogManager.getLogger(WebSecurityConfig.class);
	@Autowired
	private UserDetailsServiceI userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info(">>>>>> configura http security");
		http.csrf().disable().authorizeRequests().antMatchers("HttpMethod.GET", "/").permitAll().and().formLogin()
				.loginPage("/login").permitAll().and().logout().logoutUrl("/login?logout").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().authorizeRequests()
				.antMatchers("/h2-console/**").hasRole("ADMIN").anyRequest().authenticated();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info(">>>>>> gerenciador de autenticacao = ");
		auth.userDetailsService(userDetailsService).passwordEncoder(pc());
	}

	@Bean
	public BCryptPasswordEncoder pc() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**");
	}
}