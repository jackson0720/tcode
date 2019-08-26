package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.VipBuy;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.ConnUtils;

public class VipBuyDao {
	
		public void insertBuy(String oid,String account,String title,String text,String price) {
			String sql ="insert into vip_buy values(?,?,?,?,?)";
			try(Connection conn = ConnUtils.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, oid);
				ps.setString(2,account);
				ps.setString(3,title);
				ps.setString(4,text);
				ps.setString(5,price);
				int row =ps.executeUpdate();
				if(row==0) {
					throw new DaoException("添加失败");
				}
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	
	public List<VipBuy> listVipBuyByAccount(String account){
		String sql = "select * from vip_buy where vb_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, account);
			try(ResultSet rs = ps.executeQuery()) {
				List<VipBuy> buy = new ArrayList<>();
				while(rs.next()) {
					VipBuy vb = new VipBuy();
					vb.setVbid(rs.getInt(1));
					vb.setVborderid(rs.getString(2));
					vb.setVbaccount(rs.getString(3));
					vb.setVbtitle(rs.getString(4));
					vb.setVbtext(rs.getString(5));
					vb.setVbprice(rs.getString(6));
					buy.add(vb);
				}
				return buy;
			}catch(Exception e) {
				throw new UserException(e);
			}
	}catch(Exception e) {
		throw new UserException(e);
	}
	}

}
