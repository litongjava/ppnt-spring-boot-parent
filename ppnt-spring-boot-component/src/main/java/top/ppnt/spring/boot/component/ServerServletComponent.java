package top.ppnt.spring.boot.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.stereotype.Service;

/**
 * 需要spring-boot 2.x版本
 * @author Ping E Lee
 * 
 */
@Service
public class ServerServletComponent {
  
  @Autowired
  private TomcatServletWebServerFactory tomcatServletWebServerFactory;
  
  /**
   * 获取contextPath
   * @return eg:/ppnt-yz-office-agent
   */
  public String getContextPath() {
    return tomcatServletWebServerFactory.getContextPath();
  }
}