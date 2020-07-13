package com.futureX.CourseCatelog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.futureX.CourseCatelog.Model.Course;
import com.futureX.CourseCatelog.Model.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ch.qos.logback.core.net.server.Client;

@RestController
public class CatelogController {

	@Autowired
	EurekaClient eurekaClient;
	
	@RequestMapping("/")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getHomePage(){
    	
    	String message="";
    	
    	InstanceInfo instanceInfo=eurekaClient.getNextServerFromEureka("COURSE_SERVICE", false);
    	String CourseAppUrl=instanceInfo.getHomePageUrl();
    	
    	
    	RestTemplate restTemplate=new RestTemplate();
    	message=restTemplate.getForObject(CourseAppUrl, String.class);
        return "welcome to CATELOG homepage "+ "and "+ message;
    }
    
    @GetMapping("/catelog")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getCourseThroughCat(){
    	
    	String courses="";
    	//String CourseAppUrl="http://localhost:9090/courses";
    	
    	InstanceInfo instanceInfo=eurekaClient.getNextServerFromEureka("COURSE_SERVICE", false);
    	String CourseAppUrl=instanceInfo.getHomePageUrl();
    	
    	CourseAppUrl=CourseAppUrl+"/courses";
    	
    	RestTemplate restTemplate=new RestTemplate();
    	courses=restTemplate.getForObject(CourseAppUrl, String.class);
        return "Our courses are:  "+ courses;
    }
    
    @GetMapping("/firstCourse")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getSpecificCourse(){
    	
    	Course courses = new Course();
    	User user=new User();
    	//String CourseAppUrl="http://localhost:9090/courses/1";
    	
    	InstanceInfo instanceInfo=eurekaClient.getNextServerFromEureka("COURSE_SERVICE", false);
    	String CourseAppUrl=instanceInfo.getHomePageUrl();
    	CourseAppUrl=CourseAppUrl+"/courses/1";

    	RestTemplate restTemplate=new RestTemplate();
    	courses=restTemplate.getForObject(CourseAppUrl, Course.class);
    	
    	instanceInfo=eurekaClient.getNextServerFromEureka("USER_SERVICE", false);
    	String UserAppUrl=instanceInfo.getHomePageUrl();
    	UserAppUrl=UserAppUrl+ "/"+ courses.getCourseid();
    	
    	String usersList = restTemplate.getForObject(UserAppUrl,String.class);
    	
    	return("Our first course is "+courses.getCoursename() +"***** and Enrolled users are ***** "+usersList);
    }

    public String displayDefaultHome() {
        return("Welcome to FutureX Course Catalog "+"Please try after some time");
    }

}
