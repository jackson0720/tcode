package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.Fans;
import org.sing.tcode.entity.User;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class UserDao {
	
	public User getPasswordByEmail(String mail) {
		String sql = "select u_password,u_account from users where u_email = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, mail);
			try(ResultSet rs = ps.executeQuery()) {
				User users = null;
				if(rs.next()) {
					users = new User();
					users.setU_password(rs.getString(1));
					users.setU_account(rs.getString(2));
				}
				return users;
			}catch(Exception e) {
				throw new DaoException(e.getMessage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	public void updateVipTenYearDate(String account) {
		String sql = "update vip_info set vi_enddate=dateadd(yy,10,vi_enddate) where vi_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public void updateVipYearDate(String account) {
		String sql = "update vip_info set vi_enddate=dateadd(yy,1,vi_enddate) where vi_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public void updateVipMonthDate(String account) {
		String sql = "update vip_info set vi_enddate=dateadd(m,1,vi_enddate) where vi_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public UserInfo listVipInfoByAccount(String account) {
		String sql = "select * from userinfo ui,usertype ut,vip_info vi where ui.u_group = ut.utype_id and ui.u_account = vi.vi_account and ui.u_group = '48' and ui.u_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, account);
			try(ResultSet rs = ps.executeQuery()) {
				UserInfo users = null;
				if(rs.next()) {
					users = new UserInfo();
					users.setU_account(rs.getString(1));
					users.setU_nick(rs.getString(3));
					users.setU_tel(rs.getString(14));
					users.setSex(rs.getString(30));
					users.setU_birthday(rs.getDate(9));
					users.setU_email(rs.getString(2));
					users.setU_constellation(rs.getString(32));
					users.setU_wechat(rs.getString(20));
					users.setU_career(rs.getString(31));
					users.setU_qq(rs.getString(19));
					users.setU_weibo(rs.getString(21));
					users.setU_dev(rs.getString(17));
					users.setU_university(rs.getString(18));
					users.setU_remark(rs.getString(22));
					users.setU_group(rs.getString(26));
					users.setU_pic(rs.getString(23));
					users.setU_name(rs.getString(12));
					users.setU_idcard(rs.getString(15));
					users.setU_points(rs.getInt(4));
				}
				return users;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	public void updateUserGroupid(String account) {
		String sql = "update userinfo set u_group = 48 where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public void updateAdminPassword(String password,String account) {
		String sql = "update users set u_password = ? where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, password);
			ps.setString(2, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public List<UserInfo> listAdminByGroupid() {
		String sql = "select * from users u,userinfo ui where u.u_account=ui.u_account and ui.u_group = '255'";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				List<UserInfo> u = new ArrayList<>();
				while(rs.next()) {
					UserInfo users = new UserInfo();
					users.setU_account(rs.getString(1));
					users.setU_email(rs.getString(2));
					users.setU_state(rs.getInt(5));
					users.setU_groupid(rs.getInt(12));
					users.setU_regtime(rs.getDate(14));
					u.add(users);
				}
				return u;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public void updateUserPointer(int pointer,String account) {
		String sql = "update userinfo set u_points += ? where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, pointer);
			ps.setString(2, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public void listAdminInfo() {
		String sql = "select * from users u,userinfo ui where u.u_account=ui.u_account and ui.u_group = '255'";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				//List<UserInfo> u = new ArrayList<>();
				while(rs.next()) {
					UserInfo users = new UserInfo();
					users.setU_account(rs.getString(1));
					users.setU_email(rs.getString(2));
					users.setU_state(rs.getInt(5));
					users.setU_groupid(rs.getInt(12));
					users.setU_regtime(rs.getDate(14));
					//u.add(users);
				}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public void updateUserInfo(UserInfo info) {
		String sql = "update userinfo set u_nick = ?,u_tel = ?,u_birthday = ?,u_sex = ?,u_constellation = ?,u_career = ?,u_wechat = ?,u_qq = ?,u_weibo = ?,u_dev = ?,u_university = ? where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, info.getU_nick());
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public void listUserCount() {
		String sql = "select Count(*) from users";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			try(ResultSet rs =ps.executeQuery()){
				while(rs.next()) {
					int count = rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public User getUserByAccount(String userName,String email) {
		String sql = "select * from users where u_account = ? and u_email = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userName);
			ps.setString(2,email);
			try(ResultSet rs = ps.executeQuery()) {
				User users = null;
				if(rs.next()) {
					users = new User();
					users.setU_account(rs.getString(1));
					users.setU_password(rs.getString(2));
					users.setU_email(rs.getString(3));
					users.setU_IP(rs.getString(4));
					users.setU_state(rs.getInt(5));
					users.setU_num(rs.getInt(6));
				}
				return users;
			}catch(Exception e) {
				throw new DaoException(e.getMessage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public void updateAddUserStates(String account) {
		String sql = "update users set u_state = 1 where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public void updateUserStates(String email) {
		String sql = "update users set u_state = 1 where u_email = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public User checkStatus(String account) {
		String sql = "select * from users u,ifstate i where u.u_state = i.is_sid and u.u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			try(ResultSet rs = ps.executeQuery()) {
				User users = null;
				if(rs.next()) {
					users = new User();
					users.setU_account(rs.getString(1));
					users.setU_password(rs.getString(2));
					users.setU_state(rs.getInt(5));
				}
				return users;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	public User listadmin(User user) {
		String sql = "select * from users u,userinfo ui,usertype ut where u.u_account = ui.u_account and ui.u_group = ut.utype_id and u.u_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getU_account());
			try(ResultSet rs = ps.executeQuery()) {
				User users = null;
				if(rs.next()) {
					users = new User();
					users.setU_account(rs.getString(1));
					users.setU_password(rs.getString(2));
					users.setU_email(rs.getString(31));
				}
				return users;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void updateUserState(String account) {
		String sql = "update users set u_state = 0 where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	public User checkIp(User user) {
		String sql = "select u_IP from users where u_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getU_account());
			try(ResultSet rs = ps.executeQuery()) {
				User users = null;
				if(rs.next()) {
					users = new User();
					users.setU_IP(rs.getString(4));
				}
				return users;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public void updateNum(String ip,String account) {
		String sql = "update users set u_num += 1,u_IP = ? where u_account = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, ip);
			ps.setString(2, account);
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public void addUserInfo(UserInfo user) {
		String sql = "insert into userinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn = ConnUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getU_account());
			ps.setString(2, user.getU_email());
			ps.setString(3, user.getU_nick());
			ps.setInt(4, user.getU_points());
			ps.setInt(5, user.getIfidinfo());
			ps.setInt(6, user.getU_groupid());
			ps.setInt(7, user.getU_state());
			ps.setDate(8, new java.sql.Date(user.getU_regtime().getTime()));
			ps.setDate(9, new java.sql.Date(user.getU_birthday().getTime()));
			ps.setString(10, user.getSex());
			ps.setString(11, user.getU_idinfo());
			ps.setString(12, user.getU_name());
			ps.setString(13, user.getU_constellation());
			ps.setString(14, user.getU_tel());
			ps.setString(15, user.getU_idcard());
			ps.setString(16, user.getU_career());
			ps.setString(17, user.getU_dev());
			ps.setString(18, user.getU_university());
			ps.setString(19, user.getU_qq());
			ps.setString(20, user.getU_wechat());
			ps.setString(21, user.getU_weibo());
			ps.setString(22, user.getU_remark());
			ps.setString(23, user.getU_pic());
			int row = ps.executeUpdate();
			  if(row == 0) {
				  throw new DaoException("注册失败");
			  }
			  
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public User getUserByName(String account,String password) {
		String sql = "select * from users where u_account = ? and u_password = ?";
		User users = null;
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, account);
			ps.setString(2, password);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					users = new User();
					users.setU_account(rs.getString(1));
					users.setU_password(rs.getString(2));
					users.setU_email(rs.getString(3));
					users.setU_IP(rs.getString(4));
					users.setU_state(rs.getInt(5));
					users.setU_num(rs.getInt(6));
				}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return users;
	}
	
	public UserInfo getUserInfoByAccount(String name) {
		String sql = "select * from userinfo ui,usertype ut where ui.u_group = ut.utype_id and ui.u_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, name);
			try(ResultSet rs = ps.executeQuery()) {
				UserInfo users = null;
				if(rs.next()) {
					users = new UserInfo();
					users.setU_account(rs.getString(1));
					users.setU_nick(rs.getString(3));
					users.setU_tel(rs.getString(14));
					users.setSex(rs.getString(10));
					users.setU_birthday(rs.getDate(9));
					users.setU_email(rs.getString(2));
					users.setU_constellation(rs.getString(13));
					users.setU_wechat(rs.getString(20));
					users.setU_career(rs.getString(16));
					users.setU_qq(rs.getString(19));
					users.setU_weibo(rs.getString(21));
					users.setU_dev(rs.getString(17));
					users.setU_university(rs.getString(18));
					users.setU_remark(rs.getString(22));
					users.setU_group(rs.getString(26));
					users.setU_pic(rs.getString(23));
					users.setU_name(rs.getString(12));
					users.setU_idcard(rs.getString(15));
					users.setU_points(rs.getInt(4));
				}
				return users;
			}catch(Exception e) {
				e.printStackTrace();
				throw new DaoException(e);
			}
		}catch(Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	
	public void addUser(User user) {
		String sql = "insert into users values(?,?,?,?,?,?)";
		try(Connection conn = ConnUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			System.out.println(user.getU_account());
			ps.setString(1, user.getU_account());
			ps.setString(2, user.getU_password());
			ps.setString(3, user.getU_email());
			ps.setString(4, user.getU_IP());
			ps.setInt(5, user.getU_state());
			ps.setInt(6, 0);
			int row = ps.executeUpdate();
			  if(row == 0) {
				  throw new DaoException("注册失败");
			  }
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	
	
	
	/**
	 * 添加粉丝
	 * @param fans
	 */
	public void AddFans(Fans fans) {
		String sql = "insert fans values(?,?)";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, fans.getF_focus());
			ps.setString(2, fans.getF_account());

			int row = ps.executeUpdate();
			if(row == 0) {
				throw new DaoException("添加失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	//删除粉丝
	public void DelFans(String account) {
		String sql = "delete from fans where f_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, account);
			int row = ps.executeUpdate();
			if(row == 0) {
				throw new DaoException("删除失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	//查找到粉丝的数量
		//根据贴主账号查找到粉丝数量
		public int countFans(String fans) {
			String sql = "select count(f_id) from fans where f_focus=?";
			try(Connection conn = ConnUtils.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, fans);
				try(ResultSet rs = ps.executeQuery()){
					rs.next();
					int f=rs.getInt(1);
					return f;
				}catch(Exception e) {
					throw new DaoException(e);
				}	
			}catch(Exception e) {
				throw new DaoException(e);
			}
		}
	

}
