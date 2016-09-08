# Java-Spring-RESTful
These are few of the open APIs I implemented during my internship in HCL Technologies. 

Windows Platform Instructions:

STEPS TO SET-UP REST API:

1. In Eclipse: Help --> Install New Software --> Spring IDE (latest version: first option), M2E (Maven to Eclipse plugin).

2. Extract contents of ZIP into a folder you create: LOGIN API.

3. In Eclipse, Import --> Existing Maven Projects: Navigate to LOGIN API folder --> Finish.

4. Right Click on project --> Run As... --> Spring Boot App.

5. Goto web browser: localhost:8080/ to access the project created.

STEPS TO SET-UP DATABASE SUPPORT:

1. Download and install mySQL Database software and open it.

2. Copy the sample SQL queries given and create a database for the login support. 

USE FOLLOWING SAMPLE DATA TO TEST API:

  USERNAME: user1		    PASSWORD:hcl		TYPE: USER	

  USERNAME: user2		    PASSWORD:hans		TYPE: USER

  USERNAME: SuperUser		PASSWORD:pass		TYPE: ADMIN

NOTE: Passwords in Database encrypted. 
To test own data, Run the other project, PasswordEncryption as a JAVA project and find equivalent code. 
