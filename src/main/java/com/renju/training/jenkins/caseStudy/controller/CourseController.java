package com.renju.training.jenkins.caseStudy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renju.training.jenkins.caseStudy.model.Course;
import com.renju.training.jenkins.caseStudy.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-courses", method = RequestMethod.GET)
	public String showCourseList(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("courseList", service.retrieveCourseList(name));
		return "list-courses";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	@RequestMapping(value = "/add-course", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("course", new Course(0, getLoggedInUserName(model),
				"Enter Course Name", new Date(), false));
		return "course";
	}

	@RequestMapping(value = "/delete-course", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

		if(id==1)
			throw new RuntimeException("Something went wrong");
		
		service.deleteCourse(id);
		return "redirect:/list-courses";
	}

	@RequestMapping(value = "/update-course", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Course todo = service.retrieveCourse(id);
		model.put("course", todo);
		return "course";
	}

	@RequestMapping(value = "/update-course", method = RequestMethod.POST)
	public String updateCourse(ModelMap model, @Valid Course course,
			BindingResult result) {

		if (result.hasErrors()) {
			return "course";
		}

		course.setUser(getLoggedInUserName(model));

		service.updateCourse(course);

		return "redirect:/list-courses";
	}

	@RequestMapping(value = "/add-course", method = RequestMethod.POST)
	public String addCourse(ModelMap model, @Valid Course course, BindingResult result) {

		if (result.hasErrors()) {
			return "course";
		}

		service.addCourse(getLoggedInUserName(model), course.getDesc(), course.getTargetDate(),
				false);
		return "redirect:/list-courses";
	}
}
