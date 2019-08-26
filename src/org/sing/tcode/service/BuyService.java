package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.BuyDao;
import org.sing.tcode.entity.Buy;
import org.sing.tcode.exception.UserException;

public class BuyService {
	
	
	
	
	
	public List<Buy> listBuyByUserAccount(String account){
		BuyDao dao = new BuyDao();
		try {
			return dao.listBuyByUserAccount(account);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public void insertBuy(String oid,String account,String title,String text,String price) {
		BuyDao dao = new BuyDao();
		try {
			dao.insertBuy(oid, account, title, text, price);
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

}
