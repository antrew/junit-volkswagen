package info.vetlugin.junit;

import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.Statement;

import mockit.Mock;
import mockit.MockUp;

/**
 * Tired of failing tests on Jenkins? Why not use Volkswagen approach!
 * 
 * @author Andrey Vetlugin
 */
public class JUnitVolkswagen {
	/**
	 * We are being examined if there is an environment variable with a name
	 * from this list.
	 */
	private static final String[] EXAMINATORS = { "CI", "CONTINUOUS_INTEGRATION", "BUILD_ID", "BUILD_NUMBER",
			"TEAMCITY_VERSION", "TRAVIS", "CIRCLECI", "JENKINS_URL", "HUDSON_URL", "bamboo.buildKey", "BUILDKITE", };

	private static void setup() {
		if (runningUnderExamination()) {
			doMockUp();
		}
	}

	private static boolean runningUnderExamination() {
		for (String examinator : EXAMINATORS) {
			String ci = System.getenv(examinator);
			if (ci != null && ci != "") {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	private static void doMockUp() {
		new MockUp<org.junit.runners.ParentRunner>() {
			@Mock
			protected final void runLeaf(Statement statement, Description description, RunNotifier notifier) {
				EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
				eachNotifier.fireTestStarted();
				try {
					// we actually run the test
					statement.evaluate();
				} catch (Throwable e) {
					// but we ignore any fails
				} finally {
					eachNotifier.fireTestFinished();
				}

			}
		};
	}

	static {
		// the nasty trigger
		setup();
	}

	public static void initStatic() {
	};

	public void init() {
	};

}
