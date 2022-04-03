package top.ppnt.spring.boot.utils.startup;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.litongjava.utils.ip.IpUtils;

/**
 * @author create by Ping E Lee on 2022年4月1日 下午6:07:18 
 *
 */
public class StartupUtils {

  /**
   * 输出项目启动信息
   * @param start
   * @param ctx
   */
  public static void info(long start, ApplicationContext ctx) {
    Environment environment = ctx.getBean(Environment.class);
    String projectName = environment.getProperty("spring.application.name");
    if (StringUtils.isEmpty(projectName)) {
      projectName = "本工程";
    }
    TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx
        .getBean("tomcatServletWebServerFactory");
    int port = tomcatServletWebServerFactory.getPort();
    String contextPath = tomcatServletWebServerFactory.getContextPath();
    System.out.println("访问地址:");
    IpUtils.getThisUrl(port, contextPath);
    long end = System.currentTimeMillis();
    System.out.println("启动" + projectName + "共使用了:" + (end - start) + "ms");
  }

}
