import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;


@RunWith(JUnitReportingRunner.class)
public class MollySmithTest extends JUnitStories {
    String storyFile = "molly_smith.story";

    public MollySmithTest() {
        super();
        this.configuredEmbedder().candidateSteps().add(new steps.MollySmith());
    }

    @Override
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration().useStoryLoader(
                new LoadFromClasspath(getClass().getClassLoader())
        );
        return configuration.useStoryReporterBuilder(
                new StoryReporterBuilder().withFormats(Format.CONSOLE)
        );
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(storyFile);
    }
}