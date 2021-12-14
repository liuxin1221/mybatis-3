package org.apache.ibatis.lx.proxy;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lx
 * @date 2021年12月13日 17:19
 */
public class MapperProxy  implements InvocationHandler {
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Select annotation = method.getAnnotation(Select.class);
    String value = annotation.value()[0];
    System.out.println(value);
    return null;
  }
}
