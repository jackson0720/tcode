package org.sing.tcode.service;



import java.util.List;

import org.sing.tcode.dao.UserGroupDao;
import org.sing.tcode.entity.UserType;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;


public class UserGroupService {
	
	public void delete(String name) {
		UserGroupDao dao = new UserGroupDao();
		try {
			dao.delete(name);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器繁忙");
		}
		
	}
	
	
	public void update(String privilege,String desc,String name) {
		UserGroupDao dao = new UserGroupDao();
		try {
			dao.update(privilege, desc, name);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException("服务器繁忙");
		}
		
	}
	
	public List<UserType> listGroup() {
		UserGroupDao dao = new UserGroupDao();
		try {
			List<UserType> list = dao.listGroup();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException("服务器繁忙");
		}
		
	}

}
