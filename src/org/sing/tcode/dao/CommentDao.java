package org.sing.tcode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.Reply;
import org.sing.tcode.entity.bbsReply;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.util.ConnUtils;

public class CommentDao {
	
	/**
	 * 根据当前帖子Id查询到当前帖子评论区内的所有内容之后倒序
	 * @return
	 */
	public List<Comment> listComment(String cid){
		String sql="select * from comment where c_cid = ? order by c_no desc";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, cid);
			try(ResultSet rs = ps.executeQuery()){
				List<Comment> list = new ArrayList<>();
				while(rs.next()) {
					Comment comm = new Comment();
					comm.setC_no(rs.getInt(1));
					comm.setC_cid(rs.getString(2));
					comm.setC_text(rs.getString(3));
					comm.setC_caccount(rs.getString(4));
					comm.setC_ctime(rs.getDate(5));
				
					list.add(comm);
				}
				return list;
			}catch(Exception e) {
				throw new DaoException("SQL错误："+e.getMessage());
			}	
		}catch(Exception e) {
			throw new DaoException("SQL错误："+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param save comment
	 */
	public void saveComment(Comment comment) {
		String sql = "insert comment values(?,?,?,?)";
		try (Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, comment.getC_cid()); //帖子ID
			ps.setString(2, comment.getC_text());	//评论内容
			ps.setString(3, comment.getC_caccount()); //用户账号
			ps.setTimestamp(4,new Timestamp(comment.getC_ctime().getTime())); //评论时间
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("记录添加失败");
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	
	
	/**
	 * delete comment
	 * @param cid
	 */
	public void deleteComment(int cid) {
		String sql = "delete from comment where c_no = ?";
		try (Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			// 设置参数
			ps.setInt(1, cid);
			//执行SQL
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DaoException("Delete fail.");
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	/**
	 * 循环遍历出回复表内的所有内容
	 * @return
	 */
	public List<Reply> listReply(String pid){
		String sql = "select * from bbs_reply b,comment c where b.comment_storey = c.c_no and p_pid = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, pid);
			List<Reply> list = new ArrayList<>();
			try(ResultSet rs = ps.executeQuery();){
				Reply r = null;
				while(rs.next()) {
					r = new Reply();
					r.setId(rs.getInt(1));
					r.setPid(rs.getString(2));
					r.setCstorey(rs.getInt(3));
					r.setUcommentId(rs.getString(4));
					r.setUreplyId(rs.getString(5));
					r.setUreplyNick(rs.getString(6));
					r.setContent(rs.getString(7));
					r.setReplyTime(rs.getString(8));
					r.setNo(rs.getInt(9));
					r.setCid(rs.getString(10));
					r.setText(rs.getString(11));
					r.setAccouont(rs.getString(12));
					r.setCtime(rs.getString(13));
					list.add(r);
				}
				return list;
			}catch(Exception e) {
				throw new DaoException(e);
			}
			
		}catch(Exception e) {
			throw new DaoException(e);
		}
	}
	
	/**
	 * 添加回复信息
	 * @param reply
	 */
	public void addReply(bbsReply reply) {
		String sql = "insert bbs_reply values(?,?,?,?,?,?,?)";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, reply.getPid());
			ps.setInt(2, reply.getCstorey());
			ps.setString(3, reply.getUcommentId());
			ps.setString(4, reply.getUreplyId());
			ps.setString(5, reply.getUreplyNick());
			ps.setString(6, reply.getContent());
			ps.setTimestamp(7, new Timestamp(reply.getReplyTime().getTime()));
			int row = ps.executeUpdate();
			if(row == 0) {
				throw new DaoException("添加失败！");
			}
		}catch(Exception e) {
			throw new DaoException("SQL错误："+e);
		}
	}
	
	/**
	 *根据帖子的id和当前楼层的id删除回复信息
	 * @param pid
	 * @param id
	 */
	public void deleteReply(String pid,Integer id) {
		String sql = "delete from bbs_reply where p_pid = ? and id = ?";
		try(Connection conn = ConnUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, pid);
			ps.setInt(2, id);
			
			int row = ps.executeUpdate();
			if(row == 0) {
				throw new DaoException("数据库操作失败，无法删除!");
			}
			
		}catch(Exception e) {
			throw new DaoException("数据库操作失败:"+e);
		}
	}

	public static void main(String[] args) {
		CommentDao dao = new CommentDao();
		List<Reply>comm = dao.listReply("201907310001");
		System.out.println(comm.toString());
	}
}

