package top.ppnt.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by Ping E Lee on 2022年4月8日 上午10:59:41 
 *
 */
@RestController
@RequestMapping("spring/enviorment")
public class SpringEnviormentController {

  @Autowired
  private Environment environment;

  @RequestMapping("")
  public String ac() {
    return environment.toString();
  }

  @RequestMapping("getProperty")
  public String getProperty(String key) {
    return environment.getProperty(key);
  }
}
