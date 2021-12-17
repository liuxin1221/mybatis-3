package org.apache.ibatis.lx.annotation.entity;

/**
 * @author lx
 * @date 2021年12月13日 12:39
 */
public class User {
  private Integer id;
  private  String name;
  private  Integer age;
  private String test;

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", age=" + age +
      ", test='" + test + '\'' +
      '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }
}
