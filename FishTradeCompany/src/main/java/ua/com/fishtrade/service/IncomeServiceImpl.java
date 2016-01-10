package ua.com.fishtrade.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;


import ua.com.fishtrade.dao.IncomeDao;
import ua.com.fishtrade.entity.Income;

@Component
@Named
public class IncomeServiceImpl implements IncomeService {
	@Inject
	private IncomeDao incomeDao;

	@Override
	public List<Income> getIncome(String startDate, String endDate) {
		return incomeDao.getIncome(startDate, endDate);
	}

}
