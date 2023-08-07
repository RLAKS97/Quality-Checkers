#### COEN 6761 - Software Testing and Validation Project - Summer 2023
# Team Quality-Checkers

#### Initial steps to setup project in Eclipse

#### To Run Application
To get started with running the project

1. Check the status
   ```shell
    mvn dependency:resolve
    mvn package
   ```
2. If the dependency issue not resolved, add it externally from [Maven Repository](https://mvnrepository.com/). 
3. Download the required .jar files
4. Go to File -> Project Structure -> Modules -> dependencies and all the externally downloaded jar.

# Importing and Running a Maven Project in Eclipse

This guide explains how to import a Maven project into Eclipse, download the required dependencies, and run the imported project.

## Prerequisites

- [Eclipse IDE](https://www.eclipse.org/downloads/) installed on your system.
- A Maven project's source code.

## Steps

1. **Open Eclipse:**
   Open your Eclipse IDE.

2. **Import Project:**
   - Go to `File` > `Import`.
   - Choose `Existing Maven Projects` and click `Next`.

3. **Select Root Directory:**
   - Click `Browse` and navigate to the directory containing your Maven project.
   - Select the project's root directory and click `Finish`.

4. **Download Dependencies:**
   - Eclipse will automatically recognize the project as a Maven project and download the necessary dependencies.
   - You'll see a progress bar at the bottom indicating the download process.

5. **View Project:**
   - Once the download is complete, your project will appear in the `Project Explorer` on the left side of Eclipse.

6. **Run Project:**
   - Right-click on the project in the `Project Explorer`.
   - Go to `Run As` and select the appropriate run configuration (e.g., `Java Application` for a Java project).

7. **Provide Input (if needed):**
   - If your project requires input, a console window will appear.
   - Input any required data and press `Enter`.

8. **View Output:**
   - The console will display the output of your program.

## Troubleshooting

- If you encounter dependency-related issues, ensure that your `pom.xml` file has the correct dependencies and versions listed.

- If the project doesn't build or run as expected, double-check your project's configuration, classpath, and code.

#### To Test Application

1. Edit the JUnit run-configuration in IDE to point to all or any of the test cases.
2. Run the @Test individually by clicking run on the left side of each method.
3. Run the PIT Mutation Test by using the shortcut "run as" --> PIt Mutation test to generate Mutation test report.

#### To run Coverage using JaCoCo
1. In order to generate test cases and the jacoco report run the shell command given below in the project's directory where Pom.xml is present.
    ```shell
    mvn clean install
   ```  
