package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.sing.tcode.entity.Port;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.service.PortService;
import org.sing.tcode.util.ConnUtils;

public class PortDao {

	public void deletePort(String p) {
		String sql = "delete from port where port_no=?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, p);
				int row = ps.executeUpdate();
				if(row == 0) {
					throw new DaoException("删除错误");
				}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	public List<Port> listPortByname(String name){
		String sql="select * from port where port_no like ? or port_account like ? or port_type like ? or port_text like ? or port_time like ? or ifsuccess like ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				 ps.setString(1,name+'%');
				 ps.setString(2,name+'%');
				 ps.setString(3,name+'%');
				 ps.setString(4,name+'%');
				 ps.setString(5,name+'%');
				 ps.setString(6,name+'%');
			 try(ResultSet rs = ps.executeQuery()) {
				 List<Port> list = new ArrayList<Port>();
				 Port pr = null;
				while(rs.next()) {
					pr=new Port();
					pr.setPno(rs.getString(1));
					pr.setPaccount(rs.getString(2));
					pr.setPtype(rs.getString(3));
					pr.setPtext(rs.getString(4));
					pr.setPtime(rs.getString(5));
					pr.setIfsuccess(rs.getInt(6));
					pr.setSpplate(rs.getString(7));
					list.add(pr);
				}
				return list;
			} catch (Exception e) {
				 throw new DaoException(e);
			}
		} catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	
	
	public List<Port> listPort(){
		String sql ="select * from port";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql); 
				ResultSet rs = ps.executeQuery()){
				List<Port> list = new ArrayList<Port>();
				while(rs.next()) {
					Port pr = new Port();
					pr.setPno(rs.getString(1));
					pr.setPaccount(rs.getString(2));
					pr.setPtype(rs.getString(3));
					pr.setPtext(rs.getString(4));
					pr.setPtime(rs.getString(5));
					pr.setIfsuccess(rs.getInt(6));
					pr.setSpplate(rs.getString(7));
					list.add(pr);
				}
				return list;
			} catch (Exception e) {
				 throw new DaoException(e);
			}		 
		} 
	
	public void insertPort(Port p) {
		String sql ="insert into port values(dbo.CreatePortID(),?,?,?,GETDATE(),'0','PL1009')";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1,p.getPaccount());
			ps.setString(2,p.getPtype());
			ps.setString(3,p.getPtext());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	public static void main(String[] args) {
		PortDao dao = new PortDao();
		System.out.println(dao.listPort().toString());
	}
}
