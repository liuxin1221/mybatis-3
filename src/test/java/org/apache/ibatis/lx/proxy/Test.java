package org.apache.ibatis.lx.proxy;

import org.apache.ibatis.lx.entity.User;

/**
 * @author lx
 * @date 2021年12月13日 17:14
 */
public class Test {
  public static void main(String[] args) {
    SqlSession sqlSession = new SqlSession();
    UserMapper mapper =(UserMapper)sqlSession.getMapper(UserMapper.class);
    User user = mapper.selectUser(1);
  }
}
