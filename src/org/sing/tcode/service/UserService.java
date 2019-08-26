package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.UserDao;
import org.sing.tcode.dao.UsersDao;
import org.sing.tcode.entity.Fans;
import org.sing.tcode.entity.User;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.entity.Users;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.PageBean;


public class UserService {

	public User getPasswordByEmail(String mail) {
		UserDao dao = new UserDao();
		try {
			return dao.getPasswordByEmail(mail);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public void updateVipTenYearDate(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateVipTenYearDate(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void updateVipYearDate(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateVipYearDate(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void updateVipMonthDate(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateVipMonthDate(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public  UserInfo listVipInfoByAccount(String account) {
		UserDao dao = new UserDao();
		try {
			return dao.listVipInfoByAccount(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public void updateUserGroupid(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateUserGroupid(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public void updateAdminPassword(String password,String account) {
	
		UserDao dao = new UserDao();
		try {
			dao.updateAdminPassword(password, account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
		
	}
	
	
	public List<UserInfo> listAdminByGroupid() {
		UserDao dao = new UserDao();
		try {
			return dao.listAdminByGroupid();
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void updateUserPointer(int pointer,String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateUserPointer(pointer, account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public void listAdminInfo() {
		UserDao dao = new UserDao();
		try {
			dao.listAdminInfo();
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void listUserCount() {
		UserDao dao = new UserDao();
		try {
			
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public User getUserByAccount(String userName,String email) {
		UserDao dao = new UserDao();
		
		try {
			User u = dao.getUserByAccount(userName,email);
			if(u != null && u.getU_account().equals(userName) && u.getU_email().equals(email)) {
				throw new UserException("账号或电子邮箱已被注册，请重试！");
			}else {
				return u;
			}
				
			//System.out.println(u.getU_account()+u.getU_email());
			//return u;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void updateAddUserStates(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateAddUserStates(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void updateUserStates(String email) {
		UserDao dao = new UserDao();
		try {
			dao.updateUserStates(email);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	
	public User checkStatus(String account) {
		UserDao dao = new UserDao();
		try {
			return dao.checkStatus(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器繁忙");
		}
		
	}
	
	public User listadmin(User user) {
		UserDao dao = new UserDao();
		try {
			User u = dao.listadmin(user);
			if(u!=null && u.getU_password().equals(user.getU_password())) {
				return u;
			}
			else {
				throw new UserException("用户名或密码错误");
			}
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("未知错误");
		}
	}
	
	public void updateUserState(String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateUserState(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("未知错误");
		}
	}
	
	
	public User checkIp(User user) {
		UserDao dao = new UserDao();
		try {
			return dao.checkIp(user);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("检测失败！");
		}
	}
	
	
	public void updateNum(String ip,String account) {
		UserDao dao = new UserDao();
		try {
			dao.updateNum(ip,account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("未知错误");
		}
	}
	
	
	public UserInfo getUserInfoByAccount(String name) {
		UserDao dao = new UserDao();
		try {
			return dao.getUserInfoByAccount(name);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器繁忙");
		}
	}
	
	
	
	public User getUserByName(String account,String password) {
		UserDao dao = new UserDao();
		try {
			User u = dao.getUserByName(account,password);
			
			if(u!=null && u.getU_password().equals(password) && u.getU_account().equals(account)) {
				return u;
			}
			else {
				throw new UserException("用户名或密码错误");
			}
			
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("用户名或密码错误");
		}
	}
	
	public void addUser(User user) {
		UserDao dao = new UserDao();
		try {
			//user.setIfidinfo(0);
			dao.addUser(user);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}
	
	public void addUserInfo(UserInfo user) {
		UserDao dao = new UserDao();
		try {
			dao.addUserInfo(user);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器内部错误");
		}
	}
	
	
	/**
	 * 分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<Users> listUsers(int pageNum,int pageSize) {
		UsersDao dao = new UsersDao();
		try {
			 //查询总记录数
				int total = dao.count();
				//创建pageBean,封装分页信息
				PageBean<Users> page = new PageBean<Users>(pageNum,pageSize);
				page.setTotal(total);
				//设置当前页
				//page.setPageNum(pageNum);
				//设置每页的大小
				//page.setPageSize(pageSize);
				//调用dao包进行分页查询
				List<Users> list = dao.listUsers(page);
				page.setList(list);
				return page;
			} catch (DaoException e) {
			e.printStackTrace();
			 throw new UserException(e.getMessage());
		}
	}
	public Users getUsersByname(String account) {
		UsersDao dao = new UsersDao();
		try {
			return dao.getUsersByname(account);
		} catch (DaoException e) {
			 throw new UserException(e);
		}
	}
 public void updateUsers(int state,String name) {
	 UsersDao dao = new UsersDao();
	 try {
		dao.updateUsers(state, name);
	} catch (DaoException e) {
		 throw new UserException("更改失败");
	}
 }
 public void updateUserss(int state,String name) {
	 UsersDao dao = new UsersDao();
	 try {
		dao.updateUserss(state, name);
	} catch (DaoException e) {
		 throw new UserException("更改失败");
	}
 }
 public void updateUsersss(int state,String mail) {
	 UsersDao dao = new UsersDao();
	 try {
		dao.updateUsersss(state, mail);
	} catch (DaoException e) {
		 throw new UserException("更改失败");
	}
 }
 
 public void deleteUsers(String account) {
	 UsersDao dao = new UsersDao();
	 try {
		dao.daleteUsers(account);
	} catch (Exception e) {
		 throw new UserException("删除失败");
	}
 }
 
//根据贴主账号查找到粉丝数量
	public int accountFans(String fans) {
		UserDao dao = new UserDao();
		try {
			return dao.countFans(fans);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException("服务器内部发生错误，导致粉丝全盘崩溃QAQ");
		}
	}
	
	/**
	 * 贴主粉丝的添加
	 * @param fans
	 */
	public void addFans(Fans fans) {
		UserDao dao = new UserDao();
		try {
			dao.AddFans(fans);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器内部发生错误，导致用户无法关注贴主哇QAQ");
		}
	}
	/**
	 * 贴主的粉丝减少
	 * @param account
	 * @return 
	 */
	public String delFans(String account) {
		UserDao dao = new UserDao();
		try {
			dao.DelFans(account);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new UserException("服务器内部发生爆炸，导致车门焊死无法取消关注。。。");
		}
		return account;
	}
	
}
