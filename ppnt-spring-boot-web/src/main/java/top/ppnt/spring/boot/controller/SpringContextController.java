package top.ppnt.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by Ping E Lee on 2022年4月3日 下午10:44:10 
 *
 */
@RestController
@RequestMapping("spring/context")
public class SpringContextController {

  @Autowired
  private ApplicationContext ac;

  @RequestMapping("ac")
  public String ac() {
    return ac.toString();
  }

  @RequestMapping("getBeanDefinitionNames")
  public String[] getBeanDefinitionNames() {
    return ac.getBeanDefinitionNames();
  }

}
