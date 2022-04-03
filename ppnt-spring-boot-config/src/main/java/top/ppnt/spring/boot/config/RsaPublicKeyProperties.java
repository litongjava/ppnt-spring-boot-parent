package top.ppnt.spring.boot.config;

import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import top.ppnt.spring.boot.utils.rsa.RsaUtils;

/**
 * @author create by Ping E Lee on 2022年4月1日 上午11:13:07 
 *
 */
@Data
@ConfigurationProperties("rsa.key") // 指定配置文件的key
public class RsaPublicKeyProperties {

  private String pubKeyPath;

  private PublicKey publicKey;

  @PostConstruct
  public void createKey() throws Exception {
    this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
  }

}
