package org.apache.ibatis.lx;

import org.apache.ibatis.domain.blog.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.lx.entity.User;
import org.apache.ibatis.lx.mapper.TestMapper;
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
  /**
   * orm框架：？？-->用于实现面向对象编程语言里不同类型系统的数据之间的装换 如：java string-->mysql  varchar
   * 1.怎么获取数据库源：
   * new SqlSessionFactoryBuilder().build(inputStream);
   *         1.new XMLConfigBuilder(inputStream, environment, properties);
   *         2.parser.parse()
   *         3.parseConfiguration(parser.evalNode("/configuration"));
   *         4.environmentsElement(root.evalNode("environments"));
   *         5.DataSourceFactory dsFactory = dataSourceElement(child.evalNode("dataSource"));
   *         6.configuration.setEnvironment(environmentBuilder.build());
   * 2.如何获取sql语句
   *    1. parseConfiguration(parser.evalNode("/configuration"));
   *    2. mapperElement(root.evalNode("mappers"));
   *    3. String resource = child.getStringAttribute("resource");
   *    4. （这里有四种加载方式，我用的是resource）XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
   *    5. mapperParser.parse();
   *      1. configurationElement(parser.evalNode("/mapper"));
   *      2.buildStatementFromContext(context.evalNodes("select|insert|update|delete")); 获取到sql语句： context.evalNodes("select|insert|update|delete")
   * 什么时候把#变为？：
   *      接着上面：
   *      buildStatementFromContext(list, null);
   *      statementParser.parseStatementNode();
   *      SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass);
   *      XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType); -->builder.parseScriptNode();
   *      sqlSource = new RawSqlSource(configuration, rootSqlNode, parameterType);
   *      sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
   *      sql = parser.parse(originalSql);
   *      handler.handleToken(expression.toString())-->  parameterMappings.add(buildParameterMapping(content));  --> return "?"; //把参数放入parameterMappings集合
   *      builder.append(handler.handleToken(expression.toString()));  这里sql中的#{id已经被替换为问号}
   *      new StaticSqlSource(configuration, sql, handler.getParameterMappings()); 把sql和参数一并返回
   *      builderAssistant.addMappedStatement(id, sqlSource......)
   *      configuration.addMappedStatement(statement);  把statement(基本上包含了需要的所有东西) 添加到 configuration的 mappedStatements中
   *3.插件执行的地方
   *    1.SqlSession session = sqlSessionFactory.openSession();
   *    2.configuration.newExecutor(tx, execType);
   *    3.executor = (Executor) interceptorChain.pluginAll(executor);
   *4.sql执行
   *  session.selectOne
   *  this.selectList(statement, parameter);
   *  MappedStatement ms = configuration.getMappedStatement(statement);
   *  executor.query(ms, wrapCollection(parameter), rowBounds, handler);
   *  把对应的字段、类型放入list，然后返回包装的ResultSetWrapper
   *       columnNames.add(configuration.isUseColumnLabel() ? metaData.getColumnLabel(i) : metaData.getColumnName(i));  //字段
   *       jdbcTypes.add(JdbcType.forCode(metaData.getColumnType(i)));  // 对应mysql
   *       classNames.add(metaData.getColumnClassName(i));  // 对应java
   *
   *  mapper.selectUser(1); 的执行逻辑
   *  MapperProxy>>cachedInvoker(method).invoke(proxy, method, args, sqlSession);
   *  MapperProxy>>return mapperMethod.execute(sqlSession, args);
   *  MapperMethod>>result = sqlSession.selectOne(command.getName(), param);
   */
  @Test
  public  void test01() throws IOException {
    String resource = "resources/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession session = sqlSessionFactory.openSession();
   // User user = session.selectOne("org.apache.ibatis.lx.mapper.TestMapper.selectUser",1);
    TestMapper mapper = session.getMapper(TestMapper.class);
    User user = mapper.selectUser(1);  //通过一系列代理后还是调用了session.selectOne
    System.out.println(user);
  }
}
