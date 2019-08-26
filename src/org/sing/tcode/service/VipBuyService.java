package org.sing.tcode.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.sing.tcode.dao.VipBuyDao;
import org.sing.tcode.entity.VipBuy;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;

public class VipBuyService {
	
	public void insertBuy(String oid,String account,String title,String text,String price) {
		VipBuyDao dao = new VipBuyDao();
		try {
			dao.insertBuy(oid, account, title, text, price);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	
	public List<VipBuy> listBuyByUserAccount(String account){
		VipBuyDao dao = new VipBuyDao();
		try {
			return dao.listVipBuyByAccount(account);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

}
