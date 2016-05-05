package zombienado_v1.projecttemplate.model;

import org.junit.Test;

import zombienado_v1.client.proxyModel.Model;

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
