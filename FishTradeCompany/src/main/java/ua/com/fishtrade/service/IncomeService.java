package ua.com.fishtrade.service;

import java.util.List;

import ua.com.fishtrade.entity.Income;

public interface IncomeService {
	
	public List<Income> getIncome(String startDate, String endDate);

}
