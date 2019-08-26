package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.sing.tcode.entity.Vip;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.ConnUtils;

public class VipDao {

	
	public List<Vip> listVipInfo(){
		String sql = "select * from vipinfo";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()) {
				List<Vip> v = new ArrayList<>();
				while(rs.next()) {
					Vip vip = new Vip();
					vip.setVid(rs.getInt(1));
					vip.setVname(rs.getString(2));
					vip.setVdate(rs.getString(3));
					vip.setVtext(rs.getString(4));
					vip.setVprice(rs.getString(5));
					v.add(vip);
				}
				return v;
			}catch(Exception e) {
				e.printStackTrace();
				throw new UserException(e);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

}
