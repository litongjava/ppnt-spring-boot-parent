package top.ppnt.spring.boot.component;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ping E Lee
 *
 */
@Slf4j
@Component
public class DownloadComponent {

  @Autowired
  private ResourcesLocationComponent resourcesLocationComponent;
  @Autowired
  private ServerServletComponent serverServletComponent;
  // 保存下载路径位置
  private Map<String, String> dowloadPathMap = new ConcurrentHashMap<>();

  /**
   * 获取本地文件保存路径
   * @param downloadPath
   * @param folderName
   * @param filename
   * @return
   */
  public String getDownloadLocalPath(String downloadPath, String folderName, String filename) {
    // 获取文件名长度,如果文件名长于255,这取出前250个字节
    if (filename.length() > 255) {
      filename = filename.substring(0, 250);
    }
    if (folderName == null) {
      return downloadPath + File.separator + filename;
    } else {
      String folderPath = downloadPath + File.separator + folderName;
      File file = new File(folderPath);
      if (!file.exists()) {
        file.mkdirs();
      }
      return folderPath + File.separator + filename;
    }
  }

  /**
   * 下载保存路径
   * @return
   */
  public String getDownloadSavePath(String dirName) {

    return dowloadPathMap.computeIfAbsent(dirName, new Function<String, String>() {

      @Override
      public String apply(String t) {
        String downloadPath = resourcesLocationComponent.getLocations().get(0) + File.separator + dirName;
        File file = new File(downloadPath);
        if (!file.exists()) {
          file.mkdirs();
        }
        return downloadPath;
      }
    });
  }
}
