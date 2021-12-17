package org.apache.ibatis.lx.annotation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.lx.annotation.entity.User;

/**
 * @author lx
 * @date 2021年12月13日 11:53
 */
@Mapper
public interface AnnotationTestMapper {
  @Select("select * from t_user where id = #{id}")
  User selectUser(Integer id);
}
