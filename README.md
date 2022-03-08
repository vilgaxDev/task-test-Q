# 📑 Task Management task







## 📦 Requirements

- [Java JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or higher to run the project.
- [Maven](http://maven.apache.org/) to build the project.
- [Node.js](https://nodejs.org/en/) to run the React frontend.
- [PostgreSQL](https://www.postgresql.org/) as a database to store the persistent project data.

## 🔀 Endpoints 
#### 🔒 Security actions
Authenticate or register a new user in the system.
Method	| Path	| Description	| User authenticated	| Available from UI
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
POST	| /api/users/login | Login into the existing user account	|  | ×	
POST	| /api/users/register | Register a new user account	|  | ×


#### 📑 Backlog actions
Bind the Project task with the Project through Backlog.
Method	| Path	| Description	| User authenticated	| Available from UI
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
POST	| /api/backlog/{backlog_id}	| Add project task to the backlog	| × | ×	
GET	| /api/backlog/{backlog_id}	| Get project backlog	| × | ×
GET	| /api/backlog/{backlog_id}/{pt_id}	| Get project task backlog belonging to the project| × | ×	
PATCH	| /api/backlog/{backlog_id}/{pt_id}	| Change project task belonging to the project| × | ×
DELETE	| /api/backlog/{backlog_id}/{pt_id}	| Delete project task belonging to the project| × | ×
