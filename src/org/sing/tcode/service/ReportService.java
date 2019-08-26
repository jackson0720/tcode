package org.sing.tcode.service;

import org.sing.tcode.dao.ReportDao;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;

public class ReportService {

	public void deleteReport(String po) {
		ReportDao dao = new ReportDao();
		try {
			dao.deleteReport(po);
		} catch (DaoException e) {
			throw new UserException(e.getMessage());
		}
	}
}
