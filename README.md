# Password Manager

## Offline and encrypted


I will build a password manager for my project. It will be able to generate and store **random** (or user-entered) 
passwords which it will save in an **encrypted** file on exit. It would be able to decrypt the file given that the 
correct password is entered.

A lot of people are notoriously bad at making good passwords. I think instead of remembering strong 20-30 character 
passwords, my application would make it easier for people to secure themselves online. Moreover, if people feel 
uneasy about trusting applications with their sensitive information, they can look through the code for this project
to make sure that it is secure. Moreover, I am also not allowed to call any API's anyway for this project so it's a 
completely offline password manager, which was the first password manager I personally used. 

I am interested in cryptography and encryption. I did research on the different types of algorithms that can be 
used to encrypt data; learned about the massive shift that happened in 1997 where the NIST tried to move onto a more
secure encryption algorithm and invited collaborators. This is also especially relevant today since almost the same
scenario is happening again but with post quantum cryptography. Moreover, it's just interesting to learn the math 
behind algorithms and I think applying them in a project would be the best way to learn more about them. 


As a user, I want to be able to:
- create entries with a name, url, password, and notes and addmultiple entries to one collective file
- generate a random password
- generate a random passphrase
- enter a *custom* password
- show score for the password in an entry 
- view a list of all entries
- option to save file on exit
- option to load previously saved file on startup
- delete previous entries

[//]: # (Stretch Goals: Not Implemented Yet)

[//]: # (- view a specific previous entry using any of the entry fields or index )

[//]: # (- specify a *minimum* entropy for the generated password)

[//]: # (- edit previous entries to change any of the fields)

[//]: # (- **encrypt** the file when I'm done using it)

[//]: # (- **decrypt** the file using a password)

[//]: # (- store the encrypted file on my computer and have it be easily transportable)

Note: The files `JsonReader.java`, `JsonWriter.java`, `Writable.java`, `JsonReaderTest.java`, `JsonWriterTest.java`, and 
`JsonTest.java` were inspired by the 
[sample project provided by CPSC 210](https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git). The files
`Event.java`, `EventLog.java`, `EventTest.java`, and `EventLogTest.java` were inspired by the [alarm project provided by
CPSC 210](https://github.students.cs.ubc.ca/CPSC210/AlarmSystem).

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing list entries
after loading a file or creating entries
- You can generate the second required action related to adding Xs to a Y by pressing the delete button on main menu
after loading a file or creating entries and entering the index of the entry you want to delete
- You can locate my visual component by the splash screen on startup
- You can save the state of my application by pressing the save button after loading a file or creating entries a
- You can reload the state of my application by pressing the load button after loading a file or creating entries a

# Phase 4: Task 2

```dtd
Thu Apr 13 15:56:55 PDT 2023
Loaded entries from workroom.json.
Thu Apr 13 15:56:56 PDT 2023
Added entry #1 with name Google.
Thu Apr 13 15:56:56 PDT 2023
Added entry #2 with name Reddit.
Thu Apr 13 15:56:56 PDT 2023
Added entry #3 with name Paypal.
Thu Apr 13 15:57:07 PDT 2023
Added entry #4 with name Bing.
Thu Apr 13 15:57:10 PDT 2023
Removed entry #3 with name Paypal.
Thu Apr 13 15:57:12 PDT 2023
Saved entries to workroom.json.
```

# Phase 4: Task 3
I would make a few changes to my project to improve its design. Firstly, I had separate functions
for saving and loading from the console or from the GUI where most of the code was the same, with 
a few minor changes. I could have made an abstract save/load function which both the console and 
GUI call with differing boolean values which would put the duplicate code together and call the 
relevant section of the code which actually differs between the console and GUI. 

Another part of my code I would change is the numerous `final static String` fields I had in 
`PasswordManager.java` and `GUI.java`. For the former, I needed to store the keywords that the user
would use somewhere so I can easily refer to them. For the latter, I needed to store the action 
commands I had for the numerous buttons in my GUI. Instead of making all these strings final and 
static at the start of my program (which I think causes it to look untidy), I could make two enum 
classes which store the names of these variables for the `PasswordManager` and `GUI` classes. Then,
I could import the enum class into these two classes and use the enums like how I use the
`final static String` fields. 