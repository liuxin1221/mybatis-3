package org.apache.ibatis.lx.proxy;

import java.lang.reflect.Proxy;

/**
 * @author lx
 * @date 2021年12月13日 17:15
 */
public class SqlSession  {

  public static Object getMapper(Class clazz){

    return  Proxy.newProxyInstance(SqlSession.class.getClassLoader(),new Class[]{clazz},new MapperProxy());
  }

}
