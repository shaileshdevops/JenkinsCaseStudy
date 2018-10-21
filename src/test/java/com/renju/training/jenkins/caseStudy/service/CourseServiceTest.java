package com.renju.training.jenkins.caseStudy.service;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.renju.training.jenkins.caseStudy.model.Course;

public class CourseServiceTest {

	CourseService courseService;
	List<Course> expectedCourses = null;
	
	@BeforeMethod
	public void setup() {
		courseService = new CourseService();
		
		expectedCourses = new ArrayList<Course>();
		expectedCourses.add(new Course(1, "renju", "SpringBoot", new Date(),
                false));
		expectedCourses.add(new Course(2, "renju", "Blockchain", new Date(), false));
    	expectedCourses.add(new Course(3, "renju", "Angular6", new Date(),
                false));
	}
	
	
    @Test(priority=1)
	public void testRetrieveCourseList() {
		List<Course> actualCourses = courseService.retrieveCourseList("renju");
		assertEquals(expectedCourses, actualCourses);
	}
	
	@Test(priority=2)
	public void testRetrieveCourse() {
		Course actualCourse = courseService.retrieveCourse(1);
		Course expectedCourse = new Course(1, "renju", "SpringBoot", new Date(),
                false);
		assertEquals(expectedCourse, actualCourse);
	}
	
	@Test(priority=3)
	public void testAddCourse() {
		Course addCourse = new Course(4, "renju", "ReactJS", new Date(),
                false);
		expectedCourses.add(addCourse);
		courseService.addCourse("renju", "ReactJS", new Date(), false);
		assertEquals(expectedCourses, courseService.retrieveCourseList("renju"));
		expectedCourses.remove(addCourse);
		courseService.deleteCourse(4);
	}
	
	@Test(priority=4)
	public void testRemoveCourse() {
		Course removeCourse = new Course(2, "renju", "Blockchain", new Date(),
                false);
		expectedCourses.remove(removeCourse);
		courseService.deleteCourse(2);
		assertEquals(expectedCourses, courseService.retrieveCourseList("renju"));
	}

}
