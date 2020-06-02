package com.radioactiveworks.lumiere.illuminator.web.service;

import java.io.File;
import java.util.List;

public interface IProgramRunner {
	
	public ProgramDescriptor runProgram(String programName);
	
	public int stopProgram(ProgramDescriptor progDesc);
	
	public List<File> getCatalog();
	
	public RunnerStatus getStatus();

}
