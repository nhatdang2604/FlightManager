package com.tkpm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.tkpm.entities.Airport;
import com.tkpm.service.AirportService;
import com.tkpm.view.feature_view.FlightManagerFeatureView;
import com.tkpm.view.feature_view.detail_view.AirportCRUDDetailView;
import com.tkpm.view.feature_view.detail_view.CRUDDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightManagerTabbedControllerView;
import com.tkpm.view.feature_view.table.AirportCRUDTableView;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.ManagerMainFrame;
import com.tkpm.view.frame.form.AirportForm;

public class ManagerController extends CustomerController {

	//Forms
	protected AirportForm createAirportForm;
	protected AirportForm updateAirportForm;
	
	//Services
	protected AirportService airportService;
	
	public ManagerController(BaseMainFrame mainFrame) {
		super(mainFrame);
		airportService = AirportService.INSTANCE;
	}

	@Override
	protected void initFeatures() {
		
		//Features from Customer role
		super.initFeatures();
		
		initFlightManagerFeatures();
		initReportFeatures();
	}
	
	protected void initFlightManagerFeatures() {
		initAirportCRUDFeature();
		initFlightCRUDFeature();
	}
	
	protected void initAirportCRUDFeature() {
		
		createAirportForm = new AirportForm(mainFrame);
		updateAirportForm = new AirportForm(mainFrame);
		createAirportForm.setTitle("Tạo sân bay");
		updateAirportForm.setTitle("Cập nhật sân bay");
		
		FlightManagerFeatureView featureView = (FlightManagerFeatureView) mainFrame
				.getFeatureViews()
				.get(ManagerMainFrame.FLIGHT_MANAGER_FEATURE_INDEX);
		
		FlightManagerTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		initAirportClickRowDisplayDetail(controllerView);
		initAirportCreate(controllerView);
		initAirportRead(controllerView);
		initAirportUpdate(controllerView);
		initAirportDelete(controllerView);
	};
	
	protected void initAirportClickRowDisplayDetail(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		
		table.getSelectionModel().addListSelectionListener(event -> {
			if (table.getSelectedRow() > -1) {
				Airport airport = table.getSelectedAirport();
				if (null != airport) {
					detail.setDataToDetailPanel(airport);
				}
			}
		});
	}
	
	protected void initAirportCreate(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		
		//Init "Create airport" button
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			createAirportForm.open();
		});
		
		//Init submit button of the airport form
		createAirportForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (createAirportForm.areThereAnyEmptyStarField()) {
				createAirportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed
			Airport airport = createAirportForm.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport) {
				createAirportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.createAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			createAirportForm.setError(AirportForm.NO_ERROR);
			createAirportForm.close();
			
		});
	}
	
	protected void initAirportRead(FlightManagerTabbedControllerView controllerView) {
		List<Airport> airports = airportService.findAllAirports();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		table.setAirports(airports);
		table.update();
	}
	
	protected void initAirportUpdate(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		table.getUpdateButton().addActionListener(event -> {
			Airport airport = table.getSelectedAirport();
			if (null == airport) {
				return;
			}
			updateAirportForm.setAirport(airport);
			updateAirportForm.setVisible(true);
		});
		
		updateAirportForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (updateAirportForm.areThereAnyEmptyStarField()) {
				updateAirportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed 
			//	(if same name => check if the id is the same or not)
			Airport airport = updateAirportForm.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport && !airport.getId().equals(testAirport.getId())) {
				updateAirportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.updateAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			updateAirportForm.setError(AirportForm.NO_ERROR);
			updateAirportForm.close();
		});
		
	}
	
	protected void initAirportDelete(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		
		//Init "Delete airport" button
		detail.getButtons().get(CRUDDetailView.DELETE_BUTTON_INDEX).addActionListener(event -> {
			
			int input = JOptionPane.showConfirmDialog(mainFrame,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Airport> airports = table.getSelectedAirports();
				
				//Get the id from the airport
				List<Integer> ids = airports
						.stream()
						.map(airport -> airport.getId())
						.collect(Collectors.toList());
				
				//Delete thoose airports
				airportService.deleteAirports(ids);
				
				//Update the table view
				initAirportRead(controllerView);
				
				//Success message
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
			
			
			
		});
	}
	
	protected void initFlightCRUDFeature() {
		//TODO:
	}
	
	protected void initReportFeatures() {
		initReportByMonthFeature();
		initReportByYearFeature();
	}
	
	protected void initReportByMonthFeature() {
		//TODO:
	}
	
	protected void initReportByYearFeature() {
		//TODO:
	}
	
}
