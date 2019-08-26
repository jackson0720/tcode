package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.UserType;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class UserGroupDao {

	public void delete(String name) {
		String sql = "delete from usertype where utype_name = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			int row = ps.executeUpdate();
			  if(row == 0) {
				  throw new DaoException("删除失败");
			  }
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	public void update(String privilege,String desc,String name) {
		String sql = "update usertype set utype_privilege = ?,utype_desc = ? where utype_name = ?";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, privilege);
			ps.setString(2, desc);
			ps.setString(3, name);
			int row = ps.executeUpdate();
			  if(row == 0) {
				  throw new DaoException("修改失败");
			  }
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	public List<UserType> listGroup() {
		String sql = "select * from usertype";
		try (Connection conn = ConnUtils.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			try(ResultSet rs = ps.executeQuery()) {
				List<UserType> g = new ArrayList<>();
				while(rs.next()) {
					UserType group = new UserType();
					group.setUtype_no(rs.getInt(1));
					group.setUtype_id(rs.getInt(2));
					group.setUtype_name(rs.getString(3));
					group.setType_admin(rs.getString(4));
					group.setType_desc(rs.getString(5));
					g.add(group);
				}
				return g;
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
