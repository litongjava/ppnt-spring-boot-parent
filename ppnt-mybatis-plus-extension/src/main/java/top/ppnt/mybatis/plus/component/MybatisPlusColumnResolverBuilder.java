package top.ppnt.mybatis.plus.component;
import org.springframework.stereotype.Component;

import top.ppnt.mybatis.plus.extension.ColumnResolver;

@Component
public class MybatisPlusColumnResolverBuilder {

  public <T> ColumnResolver<T> build() {
    return new ColumnResolver<>();
  }
}