package com.kuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author ht
 * @Date 2020/8/12 16:57
 * @Version 1.0
 **/
@Configuration
//WebSecurityConfigurerAdapter自定义的security策略
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //链式编程
  @Override
  //授权
  protected void configure(HttpSecurity http) throws Exception {
    //super.configure(http);
    //请求授权规则
    http.authorizeRequests()
      //permitAll表示所有用户都能进入/success接口对应的界面
      .antMatchers("/success").permitAll()
      .antMatchers("/vip1").hasRole("vip1");


    //没有权限自动跳转到登陆页（自带登录页）
    //默认是username，password
    //usernameParameter("user").passwordParameter("pwd").loginPage("/login");设置字段名以及跳转的接口
    http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/login");

    //注销
    http.logout();
    //logoutSuccessUrl自定义注销的接口
//    http.logout().logoutSuccessUrl("/");

    //关闭csrf，防止登录注销失败
    http.csrf().disable();

    //开启记住我功能；
    http.rememberMe();
    //自定义接收前端参数
//    http.rememberMe().rememberMeParameter("remember");

  }

  @Override
  //认证权限AuthenticationManagerBuilder 自定义的认证策略
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //super.configure(auth);
    //    //inMemoryAuthentication配置一个虚拟化用户名密码
    auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
      //withUser用户名，password密码，roles角色
      .withUser("jiang").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");

  }

//  //配置密码加密 从spring5开始就强制要求密码加密
//  @Bean
//  PasswordEncoder passwordEncoder(){
//    return  new  BCryptPasswordEncoder();
//  }




//  //认证权限AuthenticationManagerBuilder 自定义的认证策略
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    //super.configure(auth);
//    //inMemoryAuthentication配置内存用户名密码
//    auth.inMemoryAuthentication()
//      .withUser("jiang").roles("admin").password("123")
//      .and()
//      .withUser("lisi").roles("user").password("123");
//  }
//
//
//  //配置密码加密 从spring5开始就强制要求密码加密
//  @Bean
//  PasswordEncoder passwordEncoder(){
//    return  new  BCryptPasswordEncoder();
//  }
}
