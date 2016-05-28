package zombienado_beta.projecttemplate.model;

import org.junit.Test;

import zombienado_beta.client.model.Model;

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
