package org.sing.tcode.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.dao.LoginInfoDao;
import org.sing.tcode.entity.LoginInfo;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class LoginInfoService {
	
	public List<LoginInfo> listadmin(LoginInfo uinfo) {
		LoginInfoDao dao = new LoginInfoDao();
		try {
			List<LoginInfo> info = dao.listadmin(uinfo);
			return info;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void addInfo(LoginInfo li) {
		LoginInfoDao dao = new LoginInfoDao();
		try {
			dao.addInfo(li);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
