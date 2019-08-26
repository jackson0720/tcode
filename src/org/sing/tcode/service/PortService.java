package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.PortDao;
import org.sing.tcode.dao.PostsDao;
import org.sing.tcode.entity.Forgood;
import org.sing.tcode.entity.Port;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.PostsException;
import org.sing.tcode.exception.UserException;



public class PortService{
	
	public void deletePort(String p) {
		PortDao dao = new PortDao();
		try {
			dao.deletePort(p);
		} catch (DaoException e) {
			throw new UserException("数据库内部出错");
		}
	}
	
	
	 public List<Port> listPort(){
		 PortDao dao  = new PortDao();
		 try {
			return dao.listPort();
		} catch (DaoException e) {
			 throw new UserException("数据库内部出错");
		}
	 }
	public void insertPort(Port p) {
		PortDao dao = new PortDao ();
		try {
			dao.insertPort(p);
		} catch (DaoException e) {
			System.out.println(p.getPaccount());
			System.out.println(p.getPaccount());
			System.out.println(p.getPaccount());
			throw new UserException("数据库内部出错");
		}
	}
	public List<Port> listPortByname(String name){
		PortDao dao = new PortDao();
		try {
			return dao.listPortByname(name);
		} catch (Exception e) {
			throw new UserException("数据库内部出错");
		}
	}
	public static void main(String[] args) {
		PortService dao = new PortService();
		List<Port> list = dao.listPortByname("ssa122");
		for(Port p : list)
		{
			System.out.println(p.getPtext());
		}
		
	}
	
	/**
	 * 当前帖子下共有多少赞
	 * @param pid
	 * @return
	 */
	public int countGoods(String pid) {
		PostsDao dao = new PostsDao();
		try {
			return dao.CountGoods(pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误："+e.getMessage());
		}
	}
	
	/**
	 * 添加一个赞
	 * @param goods
	 */
	public void AddGoods(Forgood goods) {
		PostsDao dao = new PostsDao();
		try {
			dao.addGoods(goods);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误:"+e.getMessage());
		}
	}
	
	/**
	 * 根据当前帐号和当前帖子id删除此赞
	 * @param account
	 * @return 
	 */
	public String DelGoods(String account,String pid) {
		PostsDao dao = new PostsDao();
		try {
			dao.DelGoods(account,pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误:"+e.getMessage());
		}
		return pid;
	}
	
	public int StateGoods(Forgood good) {
		PostsDao dao = new PostsDao();
		try {
			return dao.stateGoods(good);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new PostsException("SQL错误："+e.getMessage());
		}
	}
}

