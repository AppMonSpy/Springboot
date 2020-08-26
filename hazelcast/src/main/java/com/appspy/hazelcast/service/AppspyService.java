package com.appspy.hazelcast.service;

import java.util.List;

import com.appspy.hazelcast.model.Appspy;

public interface AppspyService {
	void insertAppspy(Appspy appspy);
	void insertAppspys(List<Appspy> appspys);
	List<Appspy> getAllAppspys();
	void getAppspyById(String appid);
}