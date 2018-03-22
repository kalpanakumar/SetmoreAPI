package com.setmoreApi.services;

public enum ServicesEnums {
	
	SERVICES("services"), STAFFS("staffs"), SLOTS("slots"), CUSTOMER("customer/create"), APPOINTMENT("appointment/create"), NONE("");
	
	private String _value;
	
	private ServicesEnums(String value) {
        _value = value;
    }
	
    @Override
	public String toString() {
      return  _value;
    }
    
    public static ServicesEnums getServicesEnums(String type) {
        try {
        		return Enum.valueOf(ServicesEnums.class, type.toUpperCase().trim());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean isValid(ServicesEnums type) {
        return !(type == null || type == NONE);
    }
    
}
