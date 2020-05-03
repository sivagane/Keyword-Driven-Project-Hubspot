package org.KDD.TestCase;

import java.io.IOException;

import org.testng.annotations.Test;

public class Runner {

	public KeywordEngine keywordEngine;
	@Test(priority=1)
	public void runner() throws IOException, InterruptedException {
		keywordEngine=new  KeywordEngine();
		keywordEngine.start_Execution("Sheet2");
	}
	
	@Test(priority=2)
	public void runner1() throws IOException, InterruptedException {
		keywordEngine=new  KeywordEngine();
		keywordEngine.start_Execution("Sheet3");
	}
}
