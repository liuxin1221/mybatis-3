package org.apache.ibatis.lx.annotation;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.lx.annotation.entity.User;
import org.apache.ibatis.lx.annotation.mapper.AnnotationTestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lx
 * @date 2021年12月13日 11:37
 */
public class MybatisMain {
  @Test
  public  void test01() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();
    AnnotationTestMapper mapper = session.getMapper(AnnotationTestMapper.class);
    User user = mapper.selectUser(1);
    System.out.println(user);
  }
}
