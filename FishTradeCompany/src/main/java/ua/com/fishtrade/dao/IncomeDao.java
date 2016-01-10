package ua.com.fishtrade.dao;

import java.util.List;
import ua.com.fishtrade.entity.*;

public interface IncomeDao {
	
	public List<Income> getIncome(String startDate, String endDate);

}
