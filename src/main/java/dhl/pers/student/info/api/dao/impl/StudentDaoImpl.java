package dhl.pers.student.info.api.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dhl.pers.student.info.api.config.DBConfig;
import dhl.pers.student.info.api.dao.StudentDao;

@Component
public class StudentDaoImpl implements StudentDao {
	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
	private static final String MAPPERPATH = "dhl.pers.student.info.api.mapper.StudentMapper.";
	
	@Autowired
	@Qualifier("dbSqlSessionTemplate")
	private SqlSession sqlSession;
	

}
