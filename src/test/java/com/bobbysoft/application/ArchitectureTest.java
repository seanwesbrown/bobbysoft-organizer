package com.bobbysoft.application;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = ArchitectureTest.BASE_PACKAGE)
class ArchitectureTest {

    static final String BASE_PACKAGE = "com.bobbysoft.application";

    // TODO Add your own rules and remove those that don't apply to your project

    @ArchTest
    public static final ArchRule domain_model_should_not_depend_on_application_services = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..domain..").should().dependOnClassesThat()
            .resideInAPackage(BASE_PACKAGE + "..service..");

    @ArchTest
    public static final ArchRule model_should_not_depend_on_application_services = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..model..").should().dependOnClassesThat()
            .resideInAPackage(BASE_PACKAGE + "..service..");

    @ArchTest
    public static final ArchRule entity_should_not_depend_on_application_services = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..entity..").should().dependOnClassesThat()
            .resideInAPackage(BASE_PACKAGE + "..service..");

    @ArchTest
    public static final ArchRule domain_model_should_not_depend_on_the_user_interface = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..domain..").should().dependOnClassesThat()
            .resideInAnyPackage(BASE_PACKAGE + "..ui..");

    @ArchTest
    public static final ArchRule model_should_not_depend_on_the_user_interface = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..model..").should().dependOnClassesThat()
            .resideInAnyPackage(BASE_PACKAGE + "..ui..");

    @ArchTest
    public static final ArchRule entity_should_not_depend_on_the_user_interface = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..entity..").should().dependOnClassesThat()
            .resideInAnyPackage(BASE_PACKAGE + "..ui..");

    @ArchTest
    public static final ArchRule repositories_should_only_be_used_by_application_services_and_other_domain_classes = classes()
            .that().areAssignableTo(Repository.class).should().onlyHaveDependentClassesThat()
            .resideInAnyPackage(BASE_PACKAGE + "..domain..", BASE_PACKAGE + ".entity.."
                    , BASE_PACKAGE + "..model..", BASE_PACKAGE + "..service..");


    @ArchTest
    public static final ArchRule repositories_should_only_be_accessed_by_transactional_classes = classes().that()
            .areAssignableTo(Repository.class).should().onlyBeAccessed().byClassesThat()
            .areAnnotatedWith(Transactional.class);

    @ArchTest
    public static final ArchRule application_services_should_not_depend_on_the_user_interface = noClasses().that()
            .resideInAPackage(BASE_PACKAGE + "..service..").should().dependOnClassesThat()
            .resideInAnyPackage(BASE_PACKAGE + "..ui..");
}
