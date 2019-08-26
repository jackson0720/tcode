package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.Buy;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.ConnUtils;

public class BuyDao {
	
	public List<Buy> listBuyByUserAccount(String account){
		String sql = "select * from buy where b_account = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, account);
			try(ResultSet rs = ps.executeQuery()) {
				List<Buy> buy = new ArrayList<>();
				while(rs.next()) {
					Buy b = new Buy();
					b.setBid(rs.getInt(1));
					b.setBorderid(rs.getString(2));
					b.setBaccount(rs.getString(3));
					b.setBtitle(rs.getString(4));
					b.setBtext(rs.getString(5));
					b.setBprice(rs.getString(6));
					buy.add(b);
				}
				return buy;
			}catch(Exception e) {
				throw new UserException(e);
			}
	}catch(Exception e) {
		throw new UserException(e);
	}
}
	
	
	public void insertBuy(String oid,String account,String title,String text,String price) {
		String sql ="insert into buy values(?,?,?,?,?)";
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
	

}
