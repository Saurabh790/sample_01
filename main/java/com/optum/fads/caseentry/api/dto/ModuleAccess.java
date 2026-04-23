/**
 * 
 */
package com.optum.fads.caseentry.api.dto;

import java.util.HashMap;

/**
 * @author sbiry
 *
 */
public enum ModuleAccess {
		STUDY("1"), JOBMONITOR("2"), WIZARD("3"), HCM("4");

	 private static final HashMap<String, ModuleAccess> MAP = new HashMap<String, ModuleAccess>();

	    private String value;

	    private ModuleAccess(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return this.value;
	    }

	    public static ModuleAccess getByName(String name) {
	        return MAP.get(name);
	    }

	    static {
	        for (ModuleAccess field : ModuleAccess.values()) {
	            MAP.put(field.getValue(), field);
	        }
	    }
	
}
