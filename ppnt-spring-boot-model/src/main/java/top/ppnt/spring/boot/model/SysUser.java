package top.ppnt.spring.boot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class SysUser implements UserDetails {

  private Integer id;
  private String username;
  private String password;
  private Integer status;
  private List<SysRole> roles = new ArrayList<>();

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return username;
  }

  // 下面这四个方法是根据某些条件来判断用户是否可用，方便起见，可以直接设为true
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() { // 指示用户的帐户是否已过期
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() { // 指示用户是否被锁定或解锁
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() { // 指示用户的凭据（密码）是否已过期
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() { // 指示用户是否被启用或禁用
    return status == 1;
  }
}
