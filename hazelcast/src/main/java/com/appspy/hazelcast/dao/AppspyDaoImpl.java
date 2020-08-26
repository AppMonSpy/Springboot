package com.appspy.hazelcast.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.appspy.hazelcast.model.Appspy;

@Repository
public class AppspyDaoImpl extends JdbcDaoSupport implements AppspyDao{
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insertAppspy(Appspy app) {
		String sql = "INSERT INTO appspy " +
				"(appId, appName) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				app.getAppId(), app.getAppName()
		});
	}
	
	@Override
	public void insertAppspys(List<Appspy> appspys) {
		String sql = "INSERT INTO appspy " + "(appId, appName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Appspy appspy = appspys.get(i);
				ps.setString(1, appspy.getAppId());
				ps.setString(2, appspy.getAppName());
			}
			
			public int getBatchSize() {
				return appspys.size();
			}
		});

	}
	@Override
	public List<Appspy> getAllAppspys(){
		String sql = "SELECT * FROM appspy";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Appspy> result = new ArrayList<Appspy>();
		for(Map<String, Object> row:rows){
			Appspy app = new Appspy();
			app.setAppId((String)row.get("appId"));
			app.setAppName((String)row.get("appName"));
			result.add(app);
		}
		
		return result;
	}

	@Override
	public Appspy getAppspyById(String appId) {
		String sql = "SELECT * FROM appspy WHERE appId = ?";
		return (Appspy)getJdbcTemplate().queryForObject(sql, new Object[]{appId}, new RowMapper<Appspy>(){
			@Override
			public Appspy mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Appspy app = new Appspy();
				app.setAppId(rs.getString("appId"));
				app.setAppName(rs.getString("appName"));
				return app;
			}
		});
	}
}