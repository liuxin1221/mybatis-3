package org.apache.ibatis.lx.proxy;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.lx.entity.User;

/**
 * @author lx
 * @date 2021年12月13日 17:17
 */
public interface UserMapper {

  @Select("select * from t_user where id = #{id}")
  User selectUser(Integer id);
}
