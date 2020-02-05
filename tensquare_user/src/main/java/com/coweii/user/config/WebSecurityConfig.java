package com.coweii.user.config;

import com.coweii.user.dao.PermissionDao;
import com.coweii.user.dao.RoleDao;
import com.coweii.user.pojo.Permission;
import com.coweii.user.pojo.Role;
import com.coweii.user.service.PermissionService;
import com.coweii.user.service.RoleService;
import com.coweii.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/*@Configuration
@EnableWebSecurity*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();

        List<Role> roles = roleService.getAllRoles();
        for(Role role : roles){
            String[] s = permissionService.getPathsByRoleId(role.getId());
            System.out.println(role.getRoleName()+" 的权限：");
            for(String s1 : s){
                System.out.print(s1+" ");
            }
            registry.antMatchers(s).hasRole(role.getRoleName());
        }
        registry.anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .permitAll()
                .usernameParameter("mobile") //手机号码登录
                .passwordParameter("password")
                .and().csrf().disable();
    }
}
