package com.radioactiveworks.lumiere.illuminator.web.service;

import java.util.HashMap;
import java.util.Map;

public class RunnerStatus {
	
	private Map<Boolean, ProgramDescriptor> status;	

	public RunnerStatus(boolean active, ProgramDescriptor desc) {
		status = new HashMap<Boolean, ProgramDescriptor>();
		status.put(Boolean.valueOf(active), desc);
	}
	
	public Map<Boolean, ProgramDescriptor> getStatus() {
		return status;
	}
	
}
