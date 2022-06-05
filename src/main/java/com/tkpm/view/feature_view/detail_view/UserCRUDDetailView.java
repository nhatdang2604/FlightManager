package com.tkpm.view.feature_view.detail_view;

@SuppressWarnings("serial")
public class UserCRUDDetailView extends CRUDDetailView {
	
	private static final String[] FIELD_NAMES = {
			"Id",	
			"Tên đăng nhập",
			"Mật khẩu (mã hóa)",
			"Chức vụ",
	};
	
	public static final String TITLE = "Tài khoản";
	
	public UserCRUDDetailView() {
		super(TITLE, FIELD_NAMES);
	}
	
}