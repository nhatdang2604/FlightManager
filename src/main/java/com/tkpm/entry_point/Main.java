package com.tkpm.entry_point;

import com.tkpm.service.AccountService;

public class Main {

	public static void main(String[] args) {
		AccountService server = AccountService.INSTANCE;
		System.out.println("Finish testing");
	}

}
