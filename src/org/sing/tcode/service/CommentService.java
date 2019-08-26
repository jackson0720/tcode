package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.CommentDao;
import org.sing.tcode.entity.Comment;
import org.sing.tcode.entity.Reply;
import org.sing.tcode.entity.bbsReply;
import org.sing.tcode.exception.CommentException;
import org.sing.tcode.exception.DaoException;

public class CommentService {

	/**
	 * 遍历出所有评论
	 * @return
	 */
	public List<Comment> listComment(String cid){
		CommentDao dao = new CommentDao();
		try{
			return dao.listComment(cid);
		}catch(DaoException e) {
			throw new CommentException(e.getMessage());
		}
	}
	
	public void saveComment(Comment comm) {
		CommentDao dao = new CommentDao();
		try {
			dao.saveComment(comm);
		}catch(DaoException e) {
			throw new CommentException(e.getMessage());
		}
	}
	/**
	 * 循环遍历出回复的信息
	 * @param pid
	 * @return
	 */
	public List<Reply> listReply(String pid){
		CommentDao dao = new CommentDao();
		try {
			return dao.listReply(pid);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new CommentException(e.getMessage());
		}
	}
	
	/**
	 * 添加回复的信息
	 * @param reply
	 */
	public void AddReply(bbsReply reply) {
		CommentDao dao = new CommentDao();
		try {
			dao.addReply(reply);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new CommentException(e.getMessage());
		}
	}
	
	/**
	 * 根据传回的帖子id和楼层id进行删除操作
	 * @param pid
	 * @param id
	 */
	public void DelReply(String pid,Integer id) {
		CommentDao dao = new CommentDao();
		try {
			dao.deleteReply(pid, id);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new CommentException(e.getMessage());
		}
	}
}
