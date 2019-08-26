package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.LoginInfo;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class LoginInfoDao {
	
	public List<LoginInfo> listadmin(LoginInfo uinfo) {
		String sql = "select * from logininfo where lg_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, uinfo.getLg_account());
			try(ResultSet rs = ps.executeQuery()) {
				List<LoginInfo> info = new ArrayList<>();
				while(rs.next()) {
					LoginInfo loginfo = new LoginInfo();
					loginfo.setLg_id(rs.getInt(1));
					loginfo.setLg_account(rs.getString(2));
					loginfo.setLg_info(rs.getString(3));
					loginfo.setLg_ip(rs.getString(4));
					info.add(loginfo);
				}
				return info;
			}catch(Exception e) {
				e.printStackTrace();
				throw new DaoException(e);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void addInfo(LoginInfo li) {
		String sql = "insert into logininfo values(?,?,?)";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, li.getLg_account());
			ps.setString(2, li.getLg_info());
			ps.setString(3, li.getLg_ip());
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("更新失败！.");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
