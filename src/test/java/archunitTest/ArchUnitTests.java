package archunitTest;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
@AnalyzeClasses(packages = "org.myApplication")
public class ArchUnitTests {
    private static final String packageName = "org.myApplication";
    @ArchTest
    final ArchRule isLayeredArchitectureRespected = layeredArchitecture().consideringAllDependencies()
            .layer("app").definedBy(packageName + ".app..")
            .layer("domain").definedBy(packageName + ".domain..")
            .layer("extern").definedBy(packageName + ".extern.api..", packageName + ".extern.db..")
            .whereLayer("extern").mayNotBeAccessedByAnyLayer()
            .whereLayer("app").mayOnlyBeAccessedByLayers("extern")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("extern", "app");
}
