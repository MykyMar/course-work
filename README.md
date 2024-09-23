# course-work

**AQA (Java) coursework. Robotdreams course.**

Start from `dockerConfig`. Then read the `docs/test_design.md` and `docs/test_plan.md`. 

To run the Cucumber test, use the `.xml` or run the following Maven command:

```bash
mvn clean test -DTEST_SUITE=runCucTestsForAllBrowsers
mvn clean test -DTEST_SUITE=runCucumberTests
