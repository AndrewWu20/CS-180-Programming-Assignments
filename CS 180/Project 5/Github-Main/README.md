# CS180Project5

Github for our CS 18000 class Project 5, Quiz option

Instructions for Compiling and Running the program:
The program and all of its features can be run and accessed by first executing the Server.java class, and then executing the Client.java class. 

Assignment Submissions:
Project Report - Andrew Wu
Vocareum Workspace - Arul Chauhan
Project Presentation - Andrew Wu

Menu Class: 
The Menu class is responsible for the flow of the quiz program. It calls methods from various classes to achieve this process. The class implements functionality that allows users to access the program in a systematic and orderly manner.

Course Class:
The Course class is responsible for all the fucntionality that is related to quizzes under a particular Course. The functionalities that have been included are the following:

1. Reading in a quiz - Method Name: readQuiz(String quizName) 
This method is responsible for reading the quizzes uploaded as a file by a teacher. This means the method recognises questions and their corresponding choices from the file.

2. Adding a quiz - Method Name: addQuiz(Quiz quiz)
This method ensures that each quiz has been added to the file that corresponds to its course. This allows a quiz to be associated with its respective course.

3. Editing a quiz - Method Name: editQuiz()
This method allows a teacher to edit various aspects of a quiz. This includes functionality to edit the name of the quiz, edit and replace a particular question as well as delete a particular question.

4. Delete a quiz - Method Name: deleteQuiz(Quiz quiz)
This method allows a teacher to delete a certain quiz from the program. 

5. toString Method
The toString method of this class returns the course name followed by the quizzes that are assoociated with that quiz.



Quiz Class:
The quiz class controls the main fucntionality for quizzes on the platform. he functionalities that have been included are the following:

1. Creation of questions - Method Name: addQuestion(int i)
This method allows a user to create questions for quizzes.

2. Editing of questions - Method Name: editQuestion()
This method allows you to select a particular question edit the choice and replace the question with another one or edit the choices for that specific question. 

3. Deletion of a question - Method Name: deleteQuestion(int questionToDelete)
This method enables a teacher to delete a certain question. 

4. toString Method
The toString method of this class returns the course name followed by the quizzes that are assoociated with that quiz.

5. Import a quiz which students can take - Method Name: importQuiz(String filepath, String quizName)
This method allows a teacher to import a file in order to upload a quiz.

6. Read a quiz from a file imported by a teacher - Method Name: readQuiz(String quizFileName, String quizName)
This method reads the lines fo the quiz file imported in by a teacher in order to create a new quiz that can be taken up by students. This includes reading each question and their corresponding choices.

7. Shuffle quizzes - Method Name: shuffleQuiz(Quiz quiz)
This method allows teachers to shuffle the questions and each question's choices. 

8. Taking up quizzes by students - Method Name: takeQuiz(String username, int attempt)
This method allows students to take up quizzes by entering their answer choices for each question. It records the answers for each student and saves the attempt to a new file. 



Account Class:
The Account class implements all the functionality related to an account that a user can create to access the functionailities of our program.
The functionalities that have been included are the following:


1. Creating an Account - Method Name: createAccount()
This method takes in three inputs: the role of the user (teacher or student), username and the password. The username is a string that cannot contain
a space in it and cannot be identical to any other usernames that have been made. The password also is a string that cannot contain a space in it but can be identical to other password created. 

2. Logging into an account - Method Name: login()
This method implements the functionaility for logging into an account. This method takes in two inputs: username and password. This method leads helps
determine whether or not a user can access the platform.

3. Deleting an Account - Method Name: deleteAccount(String username)
This method takes in a username as a parameter which deletes an account. This means the user cannot login to their account again.

4. Edit account - Method Name: editAccount(String username)
This method allows users to be able to edit their login credentials. This means the user can choose to edit their username or their password if they want to and use those credentials to login the next time.

5. Check the type of user - Method Name: checkUserType(String userName)
This method checks the type of user that is logging in. Based on the username, the method determines whether the user is a teacher or a student to accordingly display the functionaility that is distinct to each user role. 


Teacher Class:
The teacher class is responsible for creating courses, editing courses and viewing student quiz submissions. The functionalities that have been included are the following:

1. Creating a course - Method Name: createCourse(Account a)
This method enables teachers to be able to create courses while also checking if there are any other courses with the same name that already exits. 

2. Editing a course - Method Name: editCourse()
This method enables teachers to edit a course of their choice. This method allows students to create quizzes form the temrinal or by importing a file, view quizzes, delete a quiz, edit a quiz, view quiz scores. 

3. Viewing the scores of a particular student - Method Name: viewStudent()
This method allows the teacher to view a particular student's quiz scores. 


ClientThread Class:
The client thread class is responsible for receiving user input and executing what the input is. Within the client thread class, the inputs that the class can recieve and execute are ranging from account information input, including changing credentials and deleting accounts, to the log in and create account buttons, and the exit button along with quiz related executions, like importing quizzes and getting the list of quizzes related to a course.


CourseData Class:
The course data class is responsible for all of the course related functions. Specifically, the class itself stores the list of courses that are created and stored within the program and course related functions. Some examples of course functions are getting the list of courses within the program, renaming courses created within the program, getting the list of quizzes related the course, and other functions related to the course.



Server Class:
The server class is responsible for the connection between clients and the program itself. The server class handles information transfers as well as allowing the clients to access the program. The server class must be launched every time a client would like to connect to the program.









Quiz file format:

@ Question body

~ Choice 1

~ Choice 2

~ Choice 3

~ etc.

(new line)

@ Question 2 body

~ Choice 1

~ Choice 2

~ Choice 3

~ etc.

(new line)
