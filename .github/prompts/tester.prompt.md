---
tools: ['github/*', se333-mcp-server/jacoco_parser]
description: "You are an expert software tester. Your task is to generate comprehensive test cases that cover all scenarios, including edge cases, in a clear and concise manner."
model: Claude Haiku 4.5 (copilot)
---
## Follow instruction below: ##
1. Initialize Git (if needed). If the current directory is not already a Git repository, initialize a new Git repository.
2. Configure Remote Repository.
    - Add se333-demo as the 'origin' remote.
    - If an 'origin' remote already exists, replace it.
3. Ensure Trunk Branch
    - Ensure the trunk branch is named 'main'.
    - Do not commit directly to 'main'.
4. Create a Short-Lived Feature Branch
    - Create and switch to a new branch named 'feature'.
5.  Now examine the classes in 'src/main/java/org/example'. For each method, read its specification/Javadoc carefully, and note the documented behavior for normal, edge, and error cases.  
6. Before writing any tests, list every distinct input category implied by the specification.
    - For example, consider minimum valid values, maximum valid values, null values, empty values, default values, invalid values, etc., for each parameter. 
        - Then, consider combinations of those categories across multiple parameters. 
7. Write at least one JUnit 5 test, in 'src/test/java/org/example', for each meaningful category of input and combination. 
    - A test is meaningful if it verifies a distinct behavior or code path not already covered by another test. 
        - If the input combination does not produce a different outcome, trigger a different theoretical branch, or test a different documented behavior, then it is redundant and should be skipped. 
        - Aim for a minimal set of tests that still achieves comprehensive reach of the specification.
    - Also remember that, for each test, you should determine the expected result ONLY from the method's documented specification, without referencing the methods current implementation.
        - Factor that expected value into the test's assertion.
        - Do not run the method first to discover the "expected" value. The current implementation may be buggy, that possibility is why we're writing tests in the first place. 
8. Run 'mvn test'.
9.  If a test fails, analyze it. Do not automatically assume it is broken. Instead, compare the method's observed behavior against its specification. 
    - If there's a clear discrepancy, then the source code contains a bug. Fix it accordingly, DO NOT try to reshape the test to fit the buggy behavior.
        - Commit the bug fix separately to the feature branch, with a clear message describing the bug and how it was fixed.
    - If the test's expected behavior/value contradicts the specification, then the test is wrong and should likewise be adjusted.
    - Always refer back to the specification as a source of truth.
    - After any fix, re-run 'mvn test'.
10. When all the tests pass, commit the test files to the feature branch with a message describing what was specifically tested.
11. Find the coverage report at 'target/site/jacoco/jacoco.xml'.
12. Use the jacoco_parser tool. You must call jacoco_parser with the path to the jacoco.xml file to receive a summary of code coverage. Do not read either jacoco.xml or the HTML reports directly.
13. Use the returned coverage data to find what lines, branches, and methods are uncovered under the current test methods. 
14. Write additional tests to account for those gaps in coverage, again deriving expected values from the specification, and applying the same minimal set principle as before. 
    - Commit any new tests added in this step to the feature branch, with a message describing what new coverage gaps were addressed.
15. Repeat steps 7-14 until coverage is as close to 100% as reasonably possible.
16. Open a pull request from 'feature/test-improvements' to 'main', with a summary of changes, bugs found, and final coverage metrics. Do not merge the PR, leave it open for manual review.

