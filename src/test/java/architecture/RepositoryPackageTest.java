package architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoF.desappgrupoFbackend")
public class RepositoryPackageTest {
    @ArchTest
    public ArchRule repositories_should_be_located_in_repository_package =
            classes().that().areAnnotatedWith(Repository.class).should().resideInAPackage("..repository..");
}

