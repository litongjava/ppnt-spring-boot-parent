package top.ppnt.spring.boot.model;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class SysRole implements GrantedAuthority {

  private Integer id;
  private String roleName;
  private String roleDesc;

  /**
   * 如果授予的权限可以当作一个String的话，就可以返回一个String
   * @return
   */
  @JsonIgnore
  @Override
  public String getAuthority() {
    return roleName;
  }
}
