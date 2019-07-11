package architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller")
public class ControllerNamingTest {
    @ArchTest
    ArchRule controllers_should_be_named_controllers =
            classes().that().resideInAPackage("..controller..").should().haveSimpleNameEndingWith("Controller");

}


