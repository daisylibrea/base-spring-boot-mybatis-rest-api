package dhl.pers.student.info.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dhl.pers.student.info.api.service.impl.StudentServiceImpl;

@RequestMapping("/studentInformation")
@Controller
public class StudentController {
	
	@Autowired
	StudentServiceImpl studentServiceImpl;

	
}
