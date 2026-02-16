# Mason Garza - Cucumber Automation

## Overview
This project demonstrates my proficiency with [Cucumber](https://cucumber.io/) for web application automation testing. 

This repo is a self-contained framework that runs:
- **TestNG** tests (classic Selenium-style)
- **Cucumber (BDD)** scenarios (Gherkin feature files + step definitions)
- **Page Object Model (POM)** shared by both TestNG and Cucumber
- **Selective execution** via TestNG groups + Cucumber tags
- **Intentional failure demos** (for screenshots/reporting validation) that are **excluded by default** so the normal build stays green

Target site: `https://the-internet.herokuapp.com/`

**Tech Stack**
- Java 21
- Selenium 4.x
- TestNG
- Cucumber (TestNG runner)
- Maven Surefire
- ChromeDriver (Selenium Manager handles driver in most environments. This can fail on offline/locked-down environments)


## Why learn Cucumber?

Cucumber improves collaboration between technical and non-technical team members using Gherkin syntax (`Given, When, Then`) to define software behaviors in plain English. It enables teams to employ Behavior-Driven Development (BDD), creating reusable, readable automated tests that act as living documentation. It provides: 
- A human-readable layer (Gherkin feature files) that communicates intent clearly
- Tags (ex: `@smoke`, `@ui`) for clean, selective execution
- A separation between **what** the user does (feature file) and **how** it’s implemented (step definitions)

In real projects, a “Feature” is often aligned to a **user capability** (ex: “Checkout”, "Log In”, “Add-To-Cart”) rather than a single UI widget. For this demo repo, features like `checkboxes` and `dropdown` are intentionally small so each concept is easy to review.

---

## Getting Started
### Prerequisites
- **Java 21** installed from the [official website](https://www.oracle.com/java/technologies/downloads/#java21) and added to PATH
- **Maven** installed from the [official website](https://maven.apache.org/download.cgi) and added to PATH
- **Google Chrome** installed

### Clone the repository 
```bash
git clone https://github.com/MasonGarza7/cucumber-tests.git
cd cucumber-tests/java_basics/
```


## Running Tests
#### Run ALL tests, both Cucumber and TestNG (normal green build, no demo failures):
```bash
mvn clean test
```

#### Run ALL tests, both Cucumber and TestNG (normal green build, no demo failures) in Headless Mode:
```bash
mvn clean test -Dheadless=true
```

#### Run ALL tests, including both Cucumber and TestNG demo failures:
```bash
mvn clean test -DexcludedGroups= -Dtest="*Test,*Runner" "-Dcucumber.filter.tags=@smoke or @ui or @demo_fail"
or
mvn clean test -DexcludedGroups= -Dtest="*Test,*Runner" "-Dcucumber.filter.tags=not @ignore"
```

#### Run ONLY Cucumber tests (no TestNG):  
```bash
mvn clean test -Dtest=*Runner
```

#### Run ONLY TestNG tests (no Cucumber):  
```bash
mvn clean test -Dtest=*Test
```

#### Run Cucumber by tag:
```bash
mvn clean test -Dtest=*Runner "-Dcucumber.filter.tags=@smoke"
or
mvn clean test -Dtest=*Runner "-Dcucumber.filter.tags=@ui"  
```

#### Run ONLY the Cucumber failure demo (tag: `@demo_fail`):
```bash
mvn clean test -Dtest=*Runner "-Dcucumber.filter.tags=@demo_fail"
```

#### Run ONLY the TestNG failure demo (group: `demo_fail`):
```bash
mvn clean test -Dgroups=demo_fail -DexcludedGroups=
or
mvn clean test -Dtest=*Test -Dgroups=demo_fail -DexcludedGroups=
```


## Pages (Page Object Model)
Both TestNG tests and Cucumber step definitions use the same Page Objects so locators/actions are not duplicated. This is one of the primary differences/upgrades from my original Java Selenium subproject in that the demonstration test cases (Form Auth, Checkboxes, Dropdown, Demo Fail) have been rewritten to utilize Page Objects. This acts as a safeguard should any UI locators change. 

What is a “page” here? A Page Object is a class that represents:
- page locators (By selectors)
- page actions (click, type, select)
- page assertions or helper getters (read flash message, check checkbox states). I understand that some teams may discourage assertions inside pages.  

#### Flow Diagram
```
TestNG tests (…testng.tests)
      |
      v
    BaseTest  -> creates WebDriver per @Test
      |
      v
   Page Objects (…pages)
      |
      v
   Selenium WebDriver

Cucumber features (…resources/features)
      |
      v
Step Definitions (…cucumber.steps)
      |
      v
Hooks (…hooks) -> creates WebDriver per Scenario via DriverManager
      |
      v
Page Objects (…pages)
      |
      v
Selenium WebDriver
```


## File Structure
```bash
java_basics/
├─ results/                         # Stores test run output artifacts
│  ├─ logs/                         # Timestamped test run logs
│  ├─ screenshots/                  # Screenshots captured on test run failures 
│  └─ cucumber/                     # Cucumber test run results (cucumber.html + cucumber.json)
├─ src/
│  └─ test/
│     ├─ java/
│     │  └─ com/mason/selenium/
│     │     ├─ cucumber/
│     │     │  └─ steps/            # step definition classes (Given/When/Then) (glue code)
│     │     ├─ hooks/               # Cucumber @Before/@After; scenario setup/teardown
│     │     ├─ listeners/           # TestNG listeners (screenshots on failure)
│     │     ├─ pages/               # Page Object Model classes (shared)
│     │     ├─ runners/             # Cucumber TestNG runner(s) (*Runner.java)
│     │     ├─ testng/
│     │     │  └─ tests/            # TestNG tests + BaseTest
│     │     └─ utils/               # Shared utility scripts (DriverManager, LoggerUtil, Waits)
│     └─ resources/
│        └─ features/               # *.feature (Gherkin) files
├─ target/                          # Maven output build folder 
│   ├── generated-test-sources/     # Auto-generated code used during Maven’s test build phase 
│   ├── maven-status/               # Internal Maven metadata for tracking compilation/test execution
│   ├── surefire-reports/           # Default TestNG HTML/XML reports generated by Maven Surefire
│   │   └── index.html              # Maven HTML report (auto-generated each run)
│   └── test-classes/               # Compiled Java test classes ready for execution
├─ temp/                            # Temporary files used by tests (e.g., file-upload temp files) 
└─ pom.xml                          # Maven build file defining dependencies, plugins, and project configuration 
```

## Logging and Reporting Results: 
- All Cucumber tests have logs available in the console and generate the report `java_basics/results/cucumber/cucumber.html`  
    - Drag and drop the HTML file into your browser and you will see the standard Cucumber report.  
- All TestNG tests have logs available in the console, the log file, and generate the report `java_basics/target/surefire-reports/index.html`  
    - Drag and drop the HTML file into your browser and you will see the standard Surefire report.  
    - Logs are written in both the console and to the `java_basics/results/logs/` folder.  


## Screenshot on Failure:
- When a Cucumber test fails, a screenshot is automatically captured via Hooks to attach screenshots.    
    - Screenshots are stored in the `java_basics/results/screenshots/` folder with labeled, timestamped file names, and can be seen in the `cucumber.html` report.  
- When a Java test fails, a screenshot is automatically captured via the `ScreenshotListener.java` listener script.  
    - Screenshots are stored in the `java_basics/results/screenshots/` folder with labeled, timestamped file names.  


## What changed vs my main [Selenium project](https://github.com/MasonGarza7/selenium-tests)?
This repo intentionally differs from my larger Selenium framework in a few ways:
- Adds Cucumber BDD layer (feature files, steps, runner)
- Uses DriverManager + Hooks for Cucumber scenario lifecycle
- Adds selective execution patterns:
    - TestNG groups (demo_fail)
    - Cucumber tags (@smoke, @ui, @demo_fail)
- Includes intentional failure demos for both frameworks
- Standardizes output folders (results/logs, results/screenshots, results/cucumber)
- Keeps the default Maven build green by excluding failure demos by default
- Removed the majority of the TestNG test cases, keeping form authentication (login), checkboxes, and dropdowns. These were then rewritten to utilize the Page Object Models and to match the Cucumber cases.  


## Final Thoughts:
This project was interesting!  

A few weeks ago, I had zero Cucumber experience. Now, I would be much more comfortable being handed a professional project or building one.   

I did not experience any major blockers throughout this project. The only thing that tripped me up was my understanding of the use-case for Cucumber. As a QA Engineer, Cucumber seems unnecessary because you know what all the tests are and how to read the code. But Cucumber is to bridge the gap between technical and non-technical team members and allow for Behavior-Driven Development. While I have not used this professionally in my experience, other teams do, so it's worth learning!   

In conclusion, I am proud of this project and I will definitely be using it throughout my career as a cheat sheet! I look forward to continuing my learning of how to bridge the gap between business and technology.  

Thank you for reading,  
Mason Garza  