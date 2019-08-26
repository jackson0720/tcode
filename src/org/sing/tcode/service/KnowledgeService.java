package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.KnowledgeDao;
import org.sing.tcode.entity.Knowledge;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;

public class KnowledgeService {
	
	public List<Knowledge> listKnowledge() {
		KnowledgeDao dao = new KnowledgeDao();
		try {
			return dao.listKnowledoge();
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	public void addKnowledge(Knowledge k) {
		KnowledgeDao dao = new KnowledgeDao();
		try {
			dao.insertKnowledge(k);
		}catch(DaoException e) {
			e.getStackTrace();
			throw new UserException("SQL错误"+e.getMessage());
		}
	}
	

}
