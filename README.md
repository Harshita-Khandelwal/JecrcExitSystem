# JecrcExitSystem
JECRC exit system is a desktop application to keep a track record of the student's leaving time of college by scanning the barcode on their id cards using the bar code reader. It is developed in java swing, JDBC, javamail and poi-API. Database used is MySQL.

Working:
Guardside interface
1. Whenever a student leave the college he/she has to show his identity card to the guard at the exit gates, who will scan it through a BarCode reader. Scanning id card using barcode will show the student's identity and information on the screen. Alterntively barcode can be manually entered to get the student's information.
2. After verifying the student’s identity using photo shown on the screen, guard will press enter due to which the student’s exit time would be saved in the database i.e. the current time.

User interface
1. Authentication of the users using username and password before entering the system and accessing the interface. 
2. There are different users HOD, admin and class-coordinator. Each user have different interface depending on their role.
3. HOD’s can check his/her respective department’s student’s presence in the college. 
4. Admin can see records of all the students of all the branches irrespective of his/her department.
5. Class coordinators can see class-wise records of students.
6. The GUI help users to select a particular day or date at which they want to check the record. Also record of present day can be accessed easily through another page. Records are dispayed in table format showing the count of how many times a student has moved out of the college campus.
7. On selecting a row complete details of the student is visible to the user both personal and exit date & time details.
8. Student’s report can be mailed to parents. 
9. Another feature provided to users is updating email id and changing password.

Data admin interface
1. Student’s records are added using excel converter. All the student's details are inserted into the database from an excel file using this excel converter.
2. User’s records can be inserted/deleted/updated by the data admin


Procedure to run:
1. Create database using backup.sql file. Make sure sql connector file is in classpath.
2. Run guardside class in ExitPointUser folder to get the interface for guards at the exit gates.
3. Run log class in UserGUI folder to get the login interface for HOD/admin/class coordinator.
4. Run updateinfo class in updateInfoGUI folder for data admin interface. Make sure all the poi api files are included in the environment variable.

Features:
1. Barcode scanning of id cards for student information
2. Saving all the records of date and time when a student exit the college gates.
3. Centralized database
4. Personalized interfaces for each user type- guard, HOD, admin, class coordinator and data admin
3. Day/ date/ today view of students.
4. On selecting a student row ,display of personal information and all the details of exit date-time of student.
5. Student record mail to parents 
6. Updating email and password
7. Adding/ deleting/ Updating of student, admin, HOD in the database
8. Students insertion using excel convertor.
