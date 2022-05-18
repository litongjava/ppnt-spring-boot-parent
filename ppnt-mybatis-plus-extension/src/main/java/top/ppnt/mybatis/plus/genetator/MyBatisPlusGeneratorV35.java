package top.ppnt.mybatis.plus.genetator;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder;

/**
 * mybatis-plus 代码生成器
 */

public class MyBatisPlusGeneratorV35 {

  public static void main(String[] args) {
    // String projectPath = System.getProperty("user.dir");// 获取项目路径
    String author = "Ping E Lee";
    String packageParentName = "com.litongjava.module.spring.boot.ueditor";

    String jdbcUrl = "jdbc:mysql://localhost/litongjava_spring_boot_ueditor?userSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    String jdbcUser = "root";
    String jdbcPswd = "";
    String tablePrefix = "t_";
    String[] include = { "user", "role" };
    execute(author, packageParentName, jdbcUrl, jdbcUser, jdbcPswd, tablePrefix, include);

  }

  public static void execute(String author, String packageParentName, String jdbcUrl, String jdbcUser, String jdbcPswd, String tablePrefix,
      String... include) {
    String projectPath = getProjectPath();
    // globalConfig
    BiConsumer<Function<String, String>, GlobalConfig.Builder> globalConfig = (scanner, builder) -> {
      // String apply = scanner.apply("请输入作者名？");
      builder.author(author).outputDir(projectPath + "/src/main/java")// 输出路径
          .enableSwagger()// 开启swagger3
          .fileOverride()// 覆盖文件
          .disableOpenDir();// 不打开文件夹
    };

    // 包名配置
    BiConsumer<Function<String, String>, PackageConfig.Builder> packageConfig = (scanner, builder) -> {
      // String apply = scanner.apply("请输入包名？");

      builder.parent(packageParentName)
          // domain mapper,service,service.impl,controller
          .entity("domain").mapper("mapper").service("service").serviceImpl("service.impl").controller("controller")
          // 自定义输出路径，mapper.xml生成到resources目录下
          .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"));
    };
    // 策略配置
    BiConsumer<Function<String, String>, Builder> strategyConfig = (scanner, builder) -> {
      // 获取表
      // String apply = scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all");
      // List<String> tables = getTables(apply);

      builder
          // 表名和表前缀
          // .addInclude(include).addTablePrefix(tablePrefix)// 表前缀
          // 去掉Service的 "I" 前缀
          .serviceBuilder().formatServiceFileName("%sService")
          // rest开启
          .controllerBuilder().enableRestStyle()
          // url改变 例如：index_id_1
          //.enableHyphenStyle()
          // 开启mapper注解
          .mapperBuilder().enableMapperAnnotation()
          // 开启lombok
          .entityBuilder().enableLombok();

      if (StringUtils.isNotEmpty(tablePrefix)) {
        // 设置表前缀
        builder.addTablePrefix(tablePrefix);
      }

      if (include != null && include.length > 0) {
        // 设置生成的表
        // 生成的表,可同时传入多个表名
        builder.addInclude(include);
      }
    };

    FastAutoGenerator.create(jdbcUrl, jdbcUser, jdbcPswd)
        // 全局配置
        .globalConfig(globalConfig)
        // 包名配置
        .packageConfig(packageConfig)
        // 策略配置
        .strategyConfig(strategyConfig)
        // 执行
        .execute();
  }

  // 处理 all 情况
  protected static List<String> getTables(String tables) {
    return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
  }

  private static String getProjectPath() {
    String absolutePath = new File("").getAbsolutePath();
    return absolutePath;
  }
}
