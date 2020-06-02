package com.radioactiveworks.lumiere.illuminator.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.radioactiveworks.lumiere.illuminator.web.service.IProgramRunner;

@RestController
@RequestMapping
(
		path = "/lumiere/program"
)
public class SimpleLightProgramController {
	
	private IProgramRunner progRunner;
	
	@Autowired
	public SimpleLightProgramController(IProgramRunner runner) {
		progRunner = runner;
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			path = "{progName}"
			)
	public int runProgram(@PathVariable("progName") String progName)
	{
		return progRunner.runProgram(progName).getId();
	}
	
	@RequestMapping(
			method = RequestMethod.GET
			)
	public List<String> getPrograms()
	{
		return progRunner.getCatalog().parallelStream().map((file) -> {return file.getName();}).collect(Collectors.toList());
	}

}
