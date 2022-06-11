package com.tkpm.view.component;

public class ModelMenu {

	String name;
	MenuType type;
	
	public static enum MenuType {
		TITLE, MENU, EMPTY
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public ModelMenu(String name, MenuType type) {
		this.name = name;
		this.type = type;
	}
	
	public ModelMenu() {
		
	}
}
