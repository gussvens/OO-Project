package edu.chalmers.projecttemplate.model;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
	private static final int NUM_INCREMENTATIONS = 128;

	@Test
	public void testIncrementResult() {
		final Model project = new Model();

		for (int i = 0; i < NUM_INCREMENTATIONS; i++) {
			//project.incrementPresses();
		}

		//Assert.assertEquals(NUM_INCREMENTATIONS, project.getPresses());
	}
}
