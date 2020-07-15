package com.cogent.insurance.security;

import com.cogent.insurance.service.AgentService;
import com.cogent.insurance.service.BranchManagerService;
import com.cogent.insurance.service.CeoService;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.repository.AgentRepository;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private static final String LOGIN_URL = "/api/login";

  private final CustomerService customerService;
  private final CeoService ceoService;
  private final BranchManagerService branchManagerService;
  private final AgentService agentService;
  private final AgentRepository agentRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurity(
      CustomerService customerService,
      CeoService ceoService,
      BranchManagerService branchManagerService,
      AgentService agentService,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomerRepository customerRepository,
      AgentRepository agentRepository) {
    this.customerService = customerService;
    this.ceoService = ceoService;
    this.branchManagerService = branchManagerService;
    this.agentService = agentService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.agentRepository = agentRepository;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, SecurityConstants.SING_UP_URL)
        .permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/agents/**")
        .hasAnyRole("AGENT")
        .anyRequest()
        .authenticated()
        .and()
        .addFilter(getAuthenticationFilter())
        .addFilter(new AuthorizationFilter(authenticationManager(), agentRepository))
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(ceoService).passwordEncoder(bCryptPasswordEncoder);
    auth.userDetailsService(customerService).passwordEncoder(bCryptPasswordEncoder);
    auth.userDetailsService(branchManagerService).passwordEncoder(bCryptPasswordEncoder);
    auth.userDetailsService(agentService).passwordEncoder(bCryptPasswordEncoder);
  }

  private AuthenticationFilter getAuthenticationFilter() throws Exception {
    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
    filter.setFilterProcessesUrl(LOGIN_URL);

    return filter;
  }
}
