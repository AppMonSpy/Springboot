package com.appspy.hazelcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.appspy.hazelcast.dao.AppspyDao;
import com.appspy.hazelcast.model.Appspy;

@Service
@CacheConfig(cacheNames = "appspys")
public class AppspyServiceImpl implements AppspyService {

	@Autowired
	AppspyDao appspyDao;

	@Override
	public void insertAppspy(Appspy appspy) {
		appspyDao.insertAppspy(appspy);
	}

	@Override
	public void insertAppspys(List<Appspy> appspys) {
		appspyDao.insertAppspys(appspys);
	}

	@Override
	@Cacheable()
	public List<Appspy> getAllAppspys() {
		System.out.println("Inside the service layer");
		return appspyDao.getAllAppspys();

	}

	@Override
	public void getAppspyById(String appId) {
		Appspy appspy = appspyDao.getAppspyById(appId);
		System.out.println(appspy);
	}

}