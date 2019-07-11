package architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service")
public class ServicePackageTest {

    @ArchTest
    static final ArchRule the_services_must_have_the_Service_annotation =
            classes().that().arePublic()
                    .should().beAnnotatedWith(Service.class);
}

