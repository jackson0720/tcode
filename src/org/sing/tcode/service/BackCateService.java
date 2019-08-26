package org.sing.tcode.service;

import java.util.*;

import org.sing.tcode.dao.BackCateDao;
import org.sing.tcode.entity.Port;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;

public class BackCateService {

	public List<Port> listBackCate(String ptype){
		BackCateDao dao = new BackCateDao();
		try {
			return dao.listBackCart(ptype);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}
}
