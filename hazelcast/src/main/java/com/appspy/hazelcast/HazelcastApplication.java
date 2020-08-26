package com.appspy.hazelcast;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.appspy.hazelcast.model.Appspy;
import com.appspy.hazelcast.service.AppspyService;

@SpringBootApplication
public class HazelcastApplication {

	@Autowired
	AppspyService appspyService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HazelcastApplication.class, args);
		AppspyService appspyService = context.getBean(AppspyService.class);
		
		
		Appspy app1= new Appspy();
		app1.setAppId("1");
		app1.setAppName("Adhyatm");
		
		Appspy app2= new Appspy();
		app2.setAppId("2");
		app2.setAppName("Pandit");
		
		Appspy app3= new Appspy();
		app3.setAppId("3");
		app3.setAppName("AppSpy");

		
		appspyService.insertAppspy(app1);

		List<Appspy> appspys = new ArrayList<>();
		appspys.add(app2);
		appspys.add(app3);
		appspyService.insertAppspys(appspys);
		
		
		long l1 = System.currentTimeMillis();
		System.out.println("Inside the main class making call to service first time");
		List<Appspy> appspyList1 = appspyService.getAllAppspys();
		for (Appspy appspy : appspyList1) {
			System.out.println(appspy.toString());
		}
		long l2 = System.currentTimeMillis();
		
		long l3 = System.currentTimeMillis();
		System.out.println("Inside the main class making call to service second time where it will use hazelcast");
		List<Appspy> appspyList2 = appspyService.getAllAppspys();
		for (Appspy appspy : appspyList2) {
			System.out.println(appspy.toString());
		}
		long l4 = System.currentTimeMillis();
		System.out.println("Database call time taken : " +(l2-l1));
		System.out.println("Hazelcast call time taken : " +(l3-l4));
	}
}
