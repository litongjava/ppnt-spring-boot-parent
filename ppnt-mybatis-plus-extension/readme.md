# ppnt-spring-boot-web
对mybatis-plus做了一些扩展

添加依赖

```
<dependency>
  <groupId>top.ppnt</groupId>
  <artifactId>ppnt-mybatis-plus-extension</artifactId>
  <version>1.0</version>
</dependency>
```

在启动类上添加ComponentScan到top.ppnt.spring.boot.controller

```
@ComponentScan(
    value= {"com.smu.recognize","top.ppnt.spring.boot.controller"},
    excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```