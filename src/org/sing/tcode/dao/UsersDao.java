package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.Users;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;
import org.sing.tcode.util.PageBean;


public class UsersDao {
	public int count() {
		String sql ="select COUNT(*) from userinfo u,usertype ut where u.u_group = ut.utype_id";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql); 
			 	ResultSet rs = ps.executeQuery()) {
			rs.next();
			int count=0;
			count=rs.getInt(1);
			return count;
			
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public static void main(String[] args) {
		UsersDao dao = new UsersDao();
		PageBean<Users> page=new PageBean<Users>(1, 2);
		System.out.println(dao.listUsers(page));
	}
	
	public List<Users> listUsers(PageBean<Users> page){
		String sql ="select * from (select ROW_NUMBER() over(order by u_group) as rownum,u_account,u_email,u_regtime,u_state,ut.utype_name,u_sex,u_nick,u_name,u_constellation,u_tel,u_idcard,u_university from userinfo u,usertype ut where u.u_group = ut.utype_id) as temp where rownum between ? and ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1,page.getPageRow());
				ps.setInt(2,page.getFectyRow());
			try(ResultSet rs = ps.executeQuery()) {
				List<Users> list = new ArrayList<Users>();
				while(rs.next()) {
					Users us = new Users();
					us.setU_account(rs.getString(2));
					us.setU_email(rs.getString(3));
					us.setU_regtime(rs.getString(4));
					us.setU_state(rs.getBoolean(5));
					us.setUtype_name(rs.getString(6));
					us.setSex(rs.getString(7));
					us.setU_nick(rs.getString(8));
					us.setU_name(rs.getString(9));
					us.setU_constellation(rs.getString(10));
					us.setU_tel(rs.getString(11));
					us.setU_idcard(rs.getString(12));
					us.setU_university(rs.getString(13));
					list.add(us);
				}
				return list;
			} catch (Exception e) {
				throw new DaoException("错误");
			}
			} catch (Exception e) {
				e.printStackTrace();
				 throw new DaoException(e);
			}		 
		} 
	public Users getUsersByname(String account) {
		String sql ="select u_account,u_email,u_regtime,u_state,ut.utype_name,u_sex,u_nick,u_name,u_constellation,u_tel,u_idcard,u_university from userinfo u,usertype ut where u.u_group = ut.utype_id and u_account =?";
	try(Connection conn = ConnUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql); ){
		ps.setString(1,account);
		try(ResultSet rs = ps.executeQuery()) {
			Users us =null;
			if(rs.next()) {
				us = new Users();
				us.setU_account(rs.getString(1));
				us.setU_email(rs.getString(2));
				us.setU_regtime(rs.getString(3));
				us.setU_state(rs.getBoolean(4));
				us.setUtype_name(rs.getString(5));
				us.setSex(rs.getString(6));
				us.setU_nick(rs.getString(7));
				us.setU_name(rs.getString(8));
				us.setU_constellation(rs.getString(9));
				us.setU_tel(rs.getString(10));
				us.setU_idcard(rs.getString(11));
				us.setU_university(rs.getString(12));
			}
		return us;
		} catch (Exception e) {
			 throw new DaoException(e);
		}
	} catch (Exception e) {
		 throw new DaoException(e);
	}
	}
	public void updateUsers(int state,String name) {
		String sql ="update userinfo set u_state =? where u_account=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1,state);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (Exception e) {
		 throw new DaoException(e);
		}
	}
	public void updateUserss(int state,String name) {
		String sql ="update users set u_state =? where u_account=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1,state);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (Exception e) {
		 throw new DaoException(e);
		}
	}
	public void updateUsersss(int state,String mail) {
		String sql ="update userinfo set u_state =? where u_email=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1,state);
			ps.setString(2, mail);
			ps.executeUpdate();
		} catch (Exception e) {
		 throw new DaoException(e);
		}
	}
	public void daleteUsers(String account) {
		String sql="delete from userinfo where u_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1,account);
			ps.executeUpdate();
		} catch (Exception e) {
		 throw new DaoException(e);
		}
	}

}
