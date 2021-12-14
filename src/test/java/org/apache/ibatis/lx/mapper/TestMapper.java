package org.apache.ibatis.lx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.domain.blog.Blog;
import org.apache.ibatis.lx.entity.User;

/**
 * @author lx
 * @date 2021年12月13日 11:53
 */
@Mapper
public interface TestMapper {
  User selectUser(Integer id);
}
