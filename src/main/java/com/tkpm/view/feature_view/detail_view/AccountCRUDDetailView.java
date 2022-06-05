package com.tkpm.view.feature_view.detail_view;

@SuppressWarnings("serial")
public class AccountCRUDDetailView extends CRUDDetailView {
	
	private static final String[] FIELD_NAMES = {
			"Id",	
			"Tên đăng nhập",
			"Mật khẩu (mã hóa)",
			"Chức vụ",
	};
	
	public static final String TITLE = "Tài khoản";
	
	public AccountCRUDDetailView() {
		super(TITLE, FIELD_NAMES);
	}
	
}