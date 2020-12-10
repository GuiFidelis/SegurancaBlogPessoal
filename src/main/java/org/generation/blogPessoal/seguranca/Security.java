package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
		@Autowired
		private ImplementsUserDetailsService userDetailsService;
		
		@Override
//http: Permitirá o cadastro de um novo usuário
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.POST, "/cadastrar").permitAll()

//Acesso restrito ao ADMIN	
			.antMatchers(HttpMethod.GET, "/cadastrarTema").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/cadastrarTema").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/formTema").permitAll()
			.antMatchers(HttpMethod.POST, "/formTema").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		}

		
//Excessão de acesso ao usuário		
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(new BCryptPasswordEncoder());
}

	}
