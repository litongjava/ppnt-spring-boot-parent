package top.ppnt.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import top.ppnt.spring.boot.constants.MaperConstatns;
import top.ppnt.spring.boot.model.SysUser;

@Repository("userMapper")
public interface UserMapper {

  @Select("select * from sys_user where username=#{username}")
  @Results({ @Result(id = true, property = "id", column = "id"),
      @Result(property = "roles", column = "id", javaType = List.class, 
      many = @Many(select =MaperConstatns.ROLE_MAPPER+".findByUid")) 
  })
  public SysUser findByUsername(String username);
}
