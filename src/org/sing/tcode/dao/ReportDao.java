package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class ReportDao {

	public void deleteReport(String po) {
		String sql = "delete from port where port_no=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, po);
				int row = ps.executeUpdate();
				if(row == 0) {
					throw new DaoException("删除错误");
				}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
