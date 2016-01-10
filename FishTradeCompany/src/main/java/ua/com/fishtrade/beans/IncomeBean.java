package ua.com.fishtrade.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.fishtrade.entity.Income;
import ua.com.fishtrade.service.IncomeService;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
@ManagedBean
public class IncomeBean {
	
	@Inject
	private IncomeService incomeServ;
	private List<Income> income = null;
	private LineChartModel lineModel;
	private Date startDate;// = "2014-08-01";
	private Date endDate; //= "2015-12-31";
	
    @PostConstruct
    public void init() {
    	//this.income = incomeServ.getIncome(startDate, endDate);
    	//Collections.sort(this.income);
        //createLineModel();
    }
    
    
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public LineChartModel getLineModel() {
		return lineModel;
	}


	private void createLineModel() {
		lineModel = initCategoryModel();
        lineModel.setTitle("Income Chart");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Date"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Income");
        yAxis.setMin(0);
        yAxis.setMax((int) (income.get(0).getTotalSum() + income.get(0).getTotalSum() * 0.1));
        //yAxis.setMax((int) (income.get(income.size() - 1).getTotalSum() + income.get(income.size() - 1).getTotalSum() * 0.1));
    }
  
	 private LineChartModel initCategoryModel() {
	        LineChartModel model = new LineChartModel();
	 
	        ChartSeries series = new ChartSeries();
	        series.setLabel("Income");
	        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	        for(Income in : income) {
	        	series.set(df.format(in.getDate()), in.getTotalSum());
	        }
	 
	        model.addSeries(series);

	        return model;
	    }
    

	public List<Income> getIncome() {
		return income;
	}

	public void setIncome(List<Income> income) {
		this.income = income;
	}
	
	public String calcIncome() {
		DateFormat df = new SimpleDateFormat("yyy-MM-dd");
		this.income = incomeServ.getIncome(df.format(startDate), df.format(endDate));
		createLineModel();
		return "Income";
	}

}
