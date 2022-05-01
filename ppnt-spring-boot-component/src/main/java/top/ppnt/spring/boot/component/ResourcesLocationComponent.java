package top.ppnt.spring.boot.component;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResourcesLocationComponent {
  private List<String> staticLocations;
  @Autowired
  private ResourceHttpRequestHandler rhrh;

  /**
   * 获取静态文件路径
   * @return
   */
  public synchronized List<String> getLocations() {
    if (staticLocations == null) {
      staticLocations = new ArrayList<String>();
      // 获取到的路径格式 [URL [file:ppnt-yz-office-agent/], class path resource []]
      List<Resource> locations = rhrh.getLocations();
      log.info(locations.toString());
      for (int i = 0; i < locations.size(); i++) {
        // URL [file:ppnt-yz-office-agent/]
        String description = locations.get(i).getDescription();
        // file:ppnt-yz-office-agent/
        String path = description.substring(description.indexOf("[") + 1, description.lastIndexOf("]"));
        if (path.equals("") || path.equals("/")) {
          continue;
        }
        URI uri = null;
        try {
          // file:ppnt-yz-office-agent/
          uri = locations.get(i).getURI();
          // log.info("uri:{}",uri);
          // 如果传入url的值是file:ppnt-yz-office-agent/,则urlPath的值是null
          String uriPath = null;
          if (uri.toString().startsWith("file")) {
            uriPath = uri.toString().split(":")[1];
          } else {
            uriPath = uri.getPath();
          }
          // log.info("uriPath:{}",uriPath);
          staticLocations.add(uriPath);
        } catch (IOException e) {
          // 发生异常,不理会
        }
      }
    }
    return staticLocations;
  }
}
