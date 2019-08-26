package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.Knowledge;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class KnowledgeDao {
	
	public List<Knowledge> listKnowledoge(){
		String sql ="select * from knowledge";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
				List<Knowledge> list = new ArrayList<>();
				while(rs.next()){
					Knowledge k = new Knowledge();
					k.setName(rs.getString(2));
					k.setPrice(rs.getInt(3));
					k.setTitle(rs.getString(4));				
					k.setRemark(rs.getString(5));
					list.add(k);
				}
				return list;
		} catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	public void insertKnowledge(Knowledge k) {
		String sql ="insert knowledge(k_name,k_price,k_title,k_remark)values(?,?,?,?)";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, k.getName());
			ps.setInt(2,k.getPrice());
			ps.setString(3,k.getTitle());
			ps.setString(4, k.getRemark());
			int row =ps.executeUpdate();
			if(row==0) {
				throw new DaoException("添加失败");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
 
}
