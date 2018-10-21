package com.renju.training.jenkins.caseStudy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renju.training.jenkins.caseStudy.model.Course;

@Service
public class CourseService {
    private static List<Course> courseList = new ArrayList<Course>();
    private static int courseCount = 3;

    static {
        courseList.add(new Course(1, "renju", "Spring Boot", new Date(),
                false));
        courseList.add(new Course(2, "renju", "Blockchain", new Date(), false));
        courseList.add(new Course(3, "renju", "Angular6", new Date(),
                false));
    }

    public List<Course> retrieveCourseList(String user) {
        List<Course> filteredCourseList = new ArrayList<Course>();
        for (Course course : courseList) {
            if (course.getUser().equalsIgnoreCase(user)) {
            	filteredCourseList.add(course);
            }
        }
        return filteredCourseList;
    }
    
    public Course retrieveCourse(int id) {
        for (Course course : courseList) {
            if (course.getId()==id) {
                return course;
            }
        }
        return null;
    }

    public void updateCourse(Course course){
    		courseList.remove(course);
    		courseList.add(course);
    }

    public void addCourse(String name, String desc, Date targetDate,
            boolean isDone) {
        courseList.add(new Course(++courseCount, name, desc, targetDate, isDone));
    }

    public void deleteCourse(int id) {
        Iterator<Course> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            Course todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}