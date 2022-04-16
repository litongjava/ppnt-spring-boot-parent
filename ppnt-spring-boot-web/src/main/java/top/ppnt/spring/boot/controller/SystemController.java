package top.ppnt.spring.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by Ping E Lee on 2022年4月14日 上午9:17:11 
 *
 */
@RestController
@RequestMapping("system")
public class SystemController {
  @RequestMapping("getProperty")
  public String getProperty(String key) {
    return System.getProperty(key);
  }
}
