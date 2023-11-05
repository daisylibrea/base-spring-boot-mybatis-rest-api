package dhl.pers.student.info.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dhl.pers.student.info.api.dao.impl.StudentDaoImpl;
import dhl.pers.student.info.api.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
}
