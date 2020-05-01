package org.KDD.TestCase;

import java.io.IOException;

import org.testng.annotations.Test;

public class Runner {

	public KeywordEngine keywordEngine;
	@Test
	public void runner() throws IOException, InterruptedException {
		keywordEngine=new  KeywordEngine();
		keywordEngine.start_Execution("Sheet2");
	}
}
