import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.engine.discovery.DiscoverySelectors;

public class StatsTestRunner {

    public static void main(String[] args) {
        System.out.println("Running Stats Tests...\n");

        // Build the discovery request
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(StatsTest.class))
                .build();

        // Create the launcher
        Launcher launcher = org.junit.platform.launcher.core.LauncherFactory.create();

        // Add a listener to capture the test summary
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // Execute the tests
        launcher.execute(request, listener);

        // Fetch and display the summary
        TestExecutionSummary summary = listener.getSummary();
        System.out.println("=== Test Execution Summary ===");
        System.out.println("Total tests run: " + summary.getTestsFoundCount());
        System.out.println("Tests passed: " + summary.getTestsSucceededCount());
        System.out.println("Tests failed: " + summary.getTestsFailedCount());
        System.out.println("Tests aborted: " + summary.getTestsAbortedCount());
        System.out.println("Tests skipped: " + summary.getTestsSkippedCount());
        System.out.println("Time taken: " + summary.getTimeFinished() + "ms");

        // If there are failures, display detailed information
        if (summary.getTestsFailedCount() > 0) {
            System.out.println("\n=== Failed Tests Details ===");
            summary.getFailures().forEach(failure -> {
                System.out.println("\nTest failed: " + failure.getTestIdentifier().getDisplayName());
                System.out.println("Error message: " + failure.getException().getMessage());
                failure.getException().printStackTrace(System.out);
            });
        }

        // Print final status
        System.out.println("\n=== Final Status ===");
        if (summary.getTestsFailedCount() == 0) {
            System.out.println("ALL TESTS PASSED ✓");
        } else {
            System.out.println("SOME TESTS FAILED ✗");
        }
    }
}