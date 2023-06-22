package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.myweb.www"})
@MapperScan(basePackages = {"com.myweb.www.repository"})
public class RootConfig {

		@Autowired
		ApplicationContext applicationcontext;
		
		// DB 설정
		@Bean
		public DataSource dataSource() {
			HikariConfig hikariConfig = new HikariConfig();			
			hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
			hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtestdb");
			hikariConfig.setUsername("springuser");
			hikariConfig.setPassword("mysql");
			hikariConfig.setMinimumIdle(5);		// 최소 유휴 커넥션 수
			
			hikariConfig.setConnectionTestQuery("SELECT now()");		// test 쿼리
			hikariConfig.setPoolName("springHikariCP");
			
			// cache 사용 여부
			hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");	
			// Mysql 드라이브가 연결 당 cache 할 statement의 수에 관한 기본 25, 권장 200 ~ 500
			hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "200");
			// catch할 sql 구문의 최대 길이
			hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
			// 서버에서 지원받는 설정이 있다면 가능할지에 대한 부분 설정
			hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
			
			HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
			
			return hikariDataSource;
		}
		
		@Bean
		public SqlSessionFactory sqlSessionFactory() throws Exception {
			SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
			sqlFactoryBean.setDataSource(dataSource());
			// 설정 사항에 대한 경로 설정
			sqlFactoryBean.setConfigLocation(applicationcontext.getResource("classpath:/MybatisConfig.xml"));
			sqlFactoryBean.setMapperLocations(applicationcontext.getResources("classpath:/mappers/*.xml"));
			
			return (SqlSessionFactory)sqlFactoryBean.getObject();
		}

}
