package com.excercise.lab7.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled " + "from users "
			+ "where username = ?";
	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority " + "from authorities "
			+ "where username = ?";
	public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority "
			+ "from groups g, group_members gm, group_authorities ga " + "where gm.username = ? "
			+ "and g.id = ga.group_id " + "and g.id = gm.group_id";

	// ldap
//	@Autowired
//    private UserDetailsContextMapper userDetailsContextMapper;
//
//    @Bean
//    public AuthenticationProvider ldapAuthenticationProvider() {
//        ActiveDirectoryLdapAuthenticationProvider provider =
//                new ActiveDirectoryLdapAuthenticationProvider("your-ad-domain", "your-ad-url");
//
//        DefaultActiveDirectoryLdapAuthoritiesPopulator authoritiesPopulator =
//                new DefaultActiveDirectoryLdapAuthoritiesPopulator("your-ad-domain", "your-ad-url");
//        authoritiesPopulator.setGroupSearchFilter("member={0}");
//        provider.setAuthoritiesPopulator(authoritiesPopulator);
//
//        provider.setUserDetailsContextMapper(userDetailsContextMapper);
//
//        return provider;
//    }
//
//    @Bean
//    public ProviderManager authenticationManager() {
//        return new ProviderManager(ldapAuthenticationProvider());
//    }
	// end ldap

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().
//		withUser("buzz").password("infinity")
//		.authorities("ROLE_USER").and()
//		.withUser("woody")
//		.password("bullseye").authorities("ROLE_USER");
//	}
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
////		UserDetails user = User.withDefaultPasswordEncoder()
////				.username("buzz").password("infinity").roles("USER")
////				.build();
////		UserDetails user2 = User.withDefaultPasswordEncoder()
////                .username("woody").password("bullseye").roles("USER")
////                .build();
//		PasswordEncoder passwordEncoder = passwordEncoder();
//		UserDetails user1 = User.withUsername("buzz").password(passwordEncoder.encode("infinity")).roles("USER")
//				.build();
//
//		UserDetails user2 = User.withUsername("woody").password(passwordEncoder.encode("bullseye")).roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	// jdpc authentication.

//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Bean
//	public UserDetailsService userDetailServiceImpl() {
//		return new UserRepositoryUserDetailsService();
//	}
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
//	  @Autowired
//	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(users(dataSource())).passwordEncoder(passwordEncoder());
//	    }
//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//				.addScript("schema.sql")
//				.addScripts("ingredient_data.sql")
//				.build();
//	}
	 @Bean
	    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
			String path = JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION;
	        DataSourceInitializer initializer = new DataSourceInitializer();
	        initializer.setDataSource(dataSource);
//	        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource(path)));
//	        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//	        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));

	        return initializer;
	    }
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authz) ->
//		authz
//		.requestMatchers("/design", "/orders").hasRole("USER")
//		)
//		.authorizeHttpRequests((authz) ->
//		authz
//		.requestMatchers("/", "/**").permitAll())
//		.httpBasic(withDefaults());
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz.requestMatchers("/design", "/orders", "/current/orders")
				.hasAuthority("ROLE_USER").requestMatchers("/", "/**").permitAll().anyRequest().authenticated())
				.httpBasic(withDefaults())
				.formLogin((formLogin) -> formLogin.loginPage("/login").defaultSuccessUrl("/design", true)
				// Set your custom login page URL if needed
				).logout(logOut -> logOut.logoutSuccessUrl("/"));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().requestMatchers(PathRequest.toH2Console()); // Allow access to H2 console
		};
	}

//	@Autowired
//	 UserRepository userRepo;
//	@Bean
//	UserRepositoryUserDetailsService customUserDetailsService() {
//		return new UserRepositoryUserDetailsService(userRepo);
//	}
//	@Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configurePasswordEncoder(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
	// this create and set datasource
//	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsManager users(DataSource dataSource) {

		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER")
				.build();
		UserDetails user1 = User.withUsername("buzz").password(passwordEncoder().encode("infinity")).roles("USER")
				.build();

		UserDetails user2 = User.withUsername("woody").password(passwordEncoder().encode("bullseye")).roles("USER")
				.build();

//		UserDetails user = User.withUsername("user")
//				.password("password").roles("USER").build();
//		UserDetails user1 = User.withUsername("buzz")
//				.password("infinity").roles("USER").build();
//		UserDetails user2 = User.withUsername("woody")
//				.password("bullseye").roles("USER").build();
		// open source.
		JdbcUserDetailsManager userSource = new JdbcUserDetailsManager(dataSource);

		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfigurer = new JdbcUserDetailsManagerConfigurer<>(
				userSource);
		// Set the PasswordEncoder - NOT WORK
		userConfigurer.passwordEncoder(passwordEncoder());

		JdbcUserDetailsManager users = userConfigurer.getUserDetailsService();
		// set own query
		users.setUsersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
		users.setAuthoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY);
		// create user
		users.createUser(user);
		users.createUser(user1);
		users.createUser(user2);
		return users;
	}

	// end jdpc authentication
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
