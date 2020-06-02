package com.radioactiveworks.lumiere.illuminator.web.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Component;

import com.radioactiveworks.lumiere.illuminator.web.service.IProgramRunner;
import com.radioactiveworks.lumiere.illuminator.web.service.ProgramDescriptor;
import com.radioactiveworks.lumiere.illuminator.web.service.RunnerStatus;

@Component
public class SimplePythonRunner implements IProgramRunner {

	private Map<ProgramDescriptor, Process> progMap = new HashMap<>();
	private String programPath = "/home/pi/blinkt/";
	//private String programPath = "/home/ros/Downloads/";
	
	@Override
	public ProgramDescriptor runProgram(String programName) {
		if(!progMap.isEmpty())
		{
			progMap.forEach(new BiConsumer<ProgramDescriptor, Process>() {

				@Override
				public void accept(ProgramDescriptor arg0, Process arg1) {
					arg1.destroy();
					
				}
			});
		}
		try {
			Process p = Runtime.getRuntime().exec(String.format("python %s%s", programPath, programName));
			ProgramDescriptor pd = new ProgramDescriptor((int)Math.round(Math.random()*Integer.MAX_VALUE));
			
			progMap.put(pd, p);
			
			return pd;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int stopProgram(ProgramDescriptor progDesc) {
		progMap.get(progDesc).destroy();
		return 0;
	}

	@Override
	public RunnerStatus getStatus() {
		if(progMap.isEmpty())
		{
			return new RunnerStatus(false, null);
		}
		else
		{
			Entry<ProgramDescriptor, Process> entry = (Entry<ProgramDescriptor, Process>) progMap.entrySet().toArray()[0];
			
			return new RunnerStatus(true, entry.getKey());
		}
	}

	@Override
	public List<File> getCatalog() {
		File folder = new File(programPath);
		return Arrays.asList(folder.listFiles());
	}

}
