package top.ppnt.spring.boot.utils.web.sevlet.mvc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class RequestMappingUtils {
  /**
   * 获取uri和controller的对应关系
   * @param requestMappingHandlerMapping
   * @return
   */
  public static List<HashMap<String, String>> getMap(RequestMappingHandlerMapping requestMappingHandlerMapping) {
    List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();
    Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
    for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
      HashMap<String, String> hashMap = new HashMap<String, String>();
      RequestMappingInfo info = m.getKey();
      PatternsRequestCondition p = info.getPatternsCondition();
      for (String url : p.getPatterns()) {
        hashMap.put("url", url);
      }
      HandlerMethod method = m.getValue();
      // 得到具体的方法
      Method method2 = method.getMethod();
      // 获取类名,封装到map中
      hashMap.put("className", method2.getDeclaringClass().getName());
      // 得到方法名
      String name = method2.getName();
      // 得到方法上所有参数的类型
      Class<?>[] parameterTypes = method2.getParameterTypes();
      name += "(";
      for (Class<?> class1 : parameterTypes) {
        // 和方法名进行拼接
        name += class1.getName() + ",";
      }
      // 去掉最后一个","
      name = name.substring(0, name.length() - 1);
      name += ")";
      // 方法名(参数1类型,参数2类型)
      hashMap.put("method", name);

      RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
      // 得到@RequestMaping中method的值
      String type = methodsCondition.toString();
      if (type != null && type.startsWith("[") && type.endsWith("]")) {
        type = type.substring(1, type.length() - 1);
        hashMap.put("type", type);
      }
      urlList.add(hashMap);
    }
    return urlList;
  }
}