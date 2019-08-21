/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension;

import io.spring.initializr.metadata.Dependency;
import io.spring.initializr.web.project.ProjectRequest;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SpringBatchTestBuildCustomizer}.
 *
 * @author Tim Riemer
 */
class SpringBatchTestBuildCustomizerTests extends AbstractExtensionTests {

	@Test
	void batchTestIsAddedWithBatch() {
		ProjectRequest request = createProjectRequest("batch");
		generateMavenPom(request).hasDependency(Dependency.createSpringBootStarter("batch"))
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependency(springBatchTest()).hasDependenciesSize(3);
	}

	@Test
	void batchTestIsNotAddedWithoutSpringBatch() {
		ProjectRequest request = createProjectRequest("web");
		generateMavenPom(request).hasDependency(Dependency.createSpringBootStarter("web"))
				.hasDependency(Dependency.createSpringBootStarter("test", Dependency.SCOPE_TEST))
				.hasDependenciesSize(2);
	}

	private static Dependency springBatchTest() {
		Dependency dependency = Dependency.withId("spring-batch-test", "org.springframework.batch",
				"spring-batch-test");
		dependency.setScope(Dependency.SCOPE_TEST);
		return dependency;
	}

}
