package org.sing.tcode.service;

import java.util.List;

import org.sing.tcode.dao.VipDao;
import org.sing.tcode.entity.Vip;
import org.sing.tcode.exception.UserException;

public class VipService {

	public List<Vip> listVipInfo(){
		VipDao dao = new VipDao();
		try {
			return dao.listVipInfo();
		}catch(Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
}
