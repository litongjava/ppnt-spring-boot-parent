package top.ppnt.spring.boot.config;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import top.ppnt.spring.boot.utils.rsa.RsaUtils;

@Data
@ConfigurationProperties("rsa.key") // 指定配置文件的key
public class RsaKeyProperties {

  private String pubKeyPath;

  private String priKeyPath;

  private PublicKey publicKey;
  private PrivateKey privateKey;

  @PostConstruct
  public void createKey() throws Exception {
    this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
  }
}
