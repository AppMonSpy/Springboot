package com.appspy.hazelcast.dao;

import java.util.List;

import com.appspy.hazelcast.model.Appspy;

public interface AppspyDao {
	void insertAppspy(Appspy appspy);
	void insertAppspys(List<Appspy> appspys);
	List<Appspy> getAllAppspys();
	Appspy getAppspyById(String appId);
}
