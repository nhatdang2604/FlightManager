package com.tkpm.controller;

import com.tkpm.entities.User;
import com.tkpm.service.UserService;
import com.tkpm.view.feature_view.UserManagerFeatureView;
import com.tkpm.view.feature_view.detail_view.CRUDDetailView;
import com.tkpm.view.feature_view.detail_view.UserCRUDDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.UserManagerTabbedControllerView;
import com.tkpm.view.feature_view.table.UserCRUDTableView;
import com.tkpm.view.frame.AdminMainFrame;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.form.CreateAccountForm;

public class AdminController extends ManagerController {

	//Forms
	protected CreateAccountForm createUserForm;
	
	//Service
	protected UserService userService;
	
	public AdminController(BaseMainFrame mainFrame) {
		super(mainFrame);
		userService = UserService.INSTANCE;
	}

	@Override
	protected void initFeatures() {
		
		//Features from Customer role
		super.initFeatures();
		
		initUserManagerFeatures();
	}
	
	protected void initUserManagerFeatures() {
		UserManagerFeatureView featureView = (UserManagerFeatureView) mainFrame
				.getFeatureViews()
				.get(AdminMainFrame.USER_MANAGER_FEATURE_INDEX);
		
		UserManagerTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		initUserCRUDFeature(controllerView);
	}
	
	protected void initUserCRUDFeature(UserManagerTabbedControllerView controllerView) {
		createUserForm = new CreateAccountForm(mainFrame);
//		updateAirportForm = new AirportForm(mainFrame);
//		createAirportForm.setTitle("Tạo sân bay");
//		updateAirportForm.setTitle("Cập nhật sân bay");
//		
		initUserClickRowDisplayDetail(controllerView);
		initUserCreate(controllerView);
//		initUserRead(controllerView);
//		initUserUpdate(controllerView);
//		initUserDelete(controllerView);
	};
	
	protected void initUserClickRowDisplayDetail(UserManagerTabbedControllerView controllerView) {
		UserCRUDDetailView detail = controllerView.getUserCRUDDetailView();
		UserCRUDTableView table = controllerView.getUserCRUDTableView();
		
		table.getSelectionModel().addListSelectionListener(event -> {
			if (table.getSelectedRow() > -1) {
				User user = table.getSelectedUser();
				if (null != user) {
					detail.setDataToDetailPanel(user);
				}
			}
		});
	}
	
	protected void initUserCreate(UserManagerTabbedControllerView controllerView) {
		UserCRUDDetailView detail = controllerView.getUserCRUDDetailView();
		
		//Init "Create user" button
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			createUserForm.open();
		});
		
		//Init submit button of the user form
		createUserForm.getSubmitButton().addActionListener(event -> {
					
			//Validate the form
			if (createUserForm.areThereAnyEmptyStarField()) {
				createUserForm.setError(CreateAccountForm.EMPTY_FIELD_ERROR);
				return;
			}
					
			//Check if there is an user with the same name existed
			User user = createUserForm.submit();
			User testUser = userService.findUserByUsername(user.getUsername());
			if (null != testUser) {
				createUserForm.setError(CreateAccountForm.EXISTED_USERNAME_ERROR);
				return;
			}
					
			//validate success case
			user = userService.createUser(user);
					
			//Update the table view
			initUserRead(controllerView);
					
			//Close the form
			createUserForm.setError(CreateAccountForm.NO_ERROR);
			createUserForm.close();
					
		});
		
	}
	
	protected void initUserRead(UserManagerTabbedControllerView controllerView) {
//		List<Airport> airports = airportService.findAllAirports();
//		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
//		table.setAirports(airports);
//		table.update();
	}
	
//	protected void initAirportUpdate(FlightManagerTabbedControllerView controllerView) {
//		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
//		table.getUpdateButton().addActionListener(event -> {
//			Airport airport = table.getSelectedAirport();
//			if (null == airport) {
//				return;
//			}
//			updateAirportForm.setAirport(airport);
//			updateAirportForm.setVisible(true);
//		});
//		
//		updateAirportForm.getSubmitButton().addActionListener(event -> {
//			
//			//Validate the form
//			if (updateAirportForm.areThereAnyEmptyStarField()) {
//				updateAirportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
//				return;
//			}
//			
//			//Check if there is an airport with the same name existed 
//			//	(if same name => check if the id is the same or not)
//			Airport airport = updateAirportForm.submit();
//			Airport testAirport = airportService.findAirportByName(airport.getName());
//			if (null != testAirport && !airport.getId().equals(testAirport.getId())) {
//				updateAirportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
//				return;
//			}
//			
//			//validate success case
//			airport = airportService.updateAirport(airport);
//			
//			//Update the table view
//			initAirportRead(controllerView);
//			
//			//Close the form
//			updateAirportForm.setError(AirportForm.NO_ERROR);
//			updateAirportForm.close();
//		});
//		
//	}
//	
//	protected void initAirportDelete(FlightManagerTabbedControllerView controllerView) {
//		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
//		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
//		
//		//Init "Delete airport" button
//		detail.getButtons().get(CRUDDetailView.DELETE_BUTTON_INDEX).addActionListener(event -> {
//			
//			int input = JOptionPane.showConfirmDialog(mainFrame,
//	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
//	        		"Xóa",
//	        		JOptionPane.YES_NO_OPTION);
//			
//			if (JOptionPane.YES_OPTION == input) {
//				List<Airport> airports = table.getSelectedAirports();
//				
//				//Get the id from the airport
//				List<Integer> ids = airports
//						.stream()
//						.map(airport -> airport.getId())
//						.collect(Collectors.toList());
//				
//				//Delete thoose airports
//				airportService.deleteAirports(ids);
//				
//				//Update the table view
//				initAirportRead(controllerView);
//				
//				//Success message
//				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
//			}
//			
//			
//			
//		});
//	}
	
}
