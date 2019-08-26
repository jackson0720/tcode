package org.sing.tcode.dao;

import java.sql.*;
import java.util.*;

import org.sing.tcode.entity.Port;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class BackCateDao {

	/**
	 * ��վ����
	 * @param ptype
	 * @return
	 */
	public List<Port> listBackCart(String ptype){
		String sql = "select * from port where port_type=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, ptype);
				try(ResultSet rs = ps.executeQuery()) {
					List<Port> list = new ArrayList<Port>();
					while(rs.next()) {
						Port port = new Port();
						port.setPno(rs.getString(1));
						port.setPaccount(rs.getString(2));
						port.setPtype(rs.getString(3));
						port.setPtext(rs.getString(4));
						port.setPtime(rs.getString(5));
						port.setIfsuccess(rs.getInt(6));
						port.setSpplate(rs.getString(7));
						list.add(port);
					}
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					throw new DaoException(e);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	
}
