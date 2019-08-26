package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.sing.tcode.entity.Posts;
 
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.util.ConnUtils;
import org.sing.tcode.util.PageBean;

public class PostssDao {
	
	public List<Posts> listPosts(PageBean<Posts> page){
		String sql = "select * from (select ROW_NUMBER() over(order by p_pid) as rownum,* from posts) as temp where rownum between ? and ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1,page.getPageRow());
				ps.setInt(2,page.getFectyRow());
				try(ResultSet rs = ps.executeQuery()) {
					List<Posts> list = new ArrayList<Posts>();
					while(rs.next()) {
						Posts posts = new Posts();
						posts.setP_pid(rs.getString(2));
						posts.setP_ptheme(rs.getString(3));
						posts.setP_ptime(new java.sql.Date(rs.getDate(4).getTime()));
						posts.setP_paccount(rs.getString(5));
						posts.setP_ptext(rs.getString(6));
						posts.setP_pgood(rs.getInt(7));
						posts.setP_pplate(rs.getString(8));
						list.add(posts);
					}
					return list;
			} catch (Exception e) {
				throw new DaoException("错误");
				}
		}catch(SQLException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * count
	 */
	public int count() {
		String sql = "select count(*) from posts";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void addPosts(Posts posts) {
		String sql = "insert into posts values(dbo.CreateOrderID(),?,?,?,?,?,?)";
		try(Connection conn = ConnUtils.getConnection();
				  PreparedStatement ps = conn.prepareStatement(sql)){
				  ps.setString(1, posts.getP_ptheme());
				  ps.setTimestamp(2, new Timestamp(posts.getP_ptime().getTime()));
				  ps.setString(3, posts.getP_paccount());
				  ps.setString(4, posts.getP_ptext());
				  ps.setInt(5, posts.getP_pgood());
				  ps.setString(6, posts.getP_pplate());
				  int row = ps.executeUpdate();
				  if(row == 0) {
					  throw new UserException("上传失败");
				  }
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	public Posts viewPostByTheme(String pid){
		String sql = "select * from Posts ps,Plate pt,comment ct where ps.p_pplate = pt.p_no and ps.p_pid = ?";
		try(Connection conn = ConnUtils.getConnection();
			  PreparedStatement ps = conn.prepareStatement(sql)){
			  ps.setString(1, pid);
			  try(ResultSet rs = ps.executeQuery()) {
				  Posts p =null;
				  if(rs.next()) {
					  p = new Posts();
					  p.setP_pid(rs.getString(1));
					  p.setP_ptheme(rs.getString(2));
				      p.setP_ptime(rs.getDate(3));
					  p.setP_paccount(rs.getString(4));
					  p.setP_ptext(rs.getString(5));
				      p.setP_pgood(rs.getInt(6));
					  p.setP_pplate(rs.getString(9));
					  
				  }
				  return p;
			  }catch(Exception e) {
				  throw new DaoException(e);
			  }
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		PostssDao dao = new PostssDao();
		PageBean<Posts> page=new PageBean<Posts>(5, 10);
		System.out.println(dao.listPosts(page));
	}
	
	public List<Posts> listPostByName(String pname){
		String sql = "select * from Posts ps,Plate pt where ps.p_pplate = pt.p_no and pt.p_name = ?";
		try(Connection conn = ConnUtils.getConnection();
			  PreparedStatement ps = conn.prepareStatement(sql)){
			  ps.setString(1, pname);
			  try(ResultSet rs = ps.executeQuery()) {
				  List<Posts> post = new ArrayList<>();
				  while(rs.next()) {
					  Posts p = new Posts();
					  p.setP_pid(rs.getString(1));
					  p.setP_ptheme(rs.getString(2));
				      p.setP_ptime(rs.getDate(3));
					  p.setP_paccount(rs.getString(4));
					  p.setP_ptext(rs.getString(5));
				      p.setP_pgood(rs.getInt(6));
					  p.setP_pplate(rs.getString(7));
					  post.add(p);
				  }
				  return post;
			  }catch(Exception e) {
				  throw new DaoException(e);
			  }
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	

	public void deletePosts(String[]  ids) {
		String sql = "delete from posts where p_pid=?";
		try
		(Connection conn=ConnUtils.getConnection();){
			try (PreparedStatement ps=conn.prepareStatement(sql)){
				conn.setAutoCommit(false);
				for (String id : ids) {
					ps.setString(1, id);
					ps.addBatch();
				}
				ps.executeBatch();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
		
		} catch (Exception e) {		
			throw new RuntimeException(e);
		}
	}
}
