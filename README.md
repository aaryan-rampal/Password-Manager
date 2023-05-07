# SafePass 

## Offline and encrypted password manager

Built using the [Jackson](https://github.com/FasterXML/jackson) library for json representation, the 
[Nbvcxz](https://github.com/GoSimpleLLC/nbvcxz) library for password entropy calculations, and the 
[Tink](https://github.com/google/tink) library for encryption.


A lot of people are notoriously bad at making good passwords. I think instead of remembering strong 20-30 character 
passwords, my application would make it easier for people to secure themselves online. Moreover, if people feel 
uneasy about trusting applications with their sensitive information, they can look through the code for this project
to make sure that it is secure. I am not using any external API's for this project, so it's a 
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

Stretch Goals: Not Implemented Yet

- view a specific previous entry using any of the entry fields or index 

- specify a *minimum* entropy for the generated password

- edit previous entries to change any of the fields

- **encrypt** the file when I'm done using it

- **decrypt** the file using a password

- store the encrypted file on my computer and have it be easily transportable

[//]: # (Note: The files `JsonReader.java`, `JsonWriter.java`, `Writable.java`, `JsonReaderTest.java`, `JsonWriterTest.java`, and )

[//]: # (`JsonTest.java` were inspired by the )

[//]: # ([sample project provided by CPSC 210]&#40;https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git&#41;.) 
The files `Event.java`, `EventLog.java`, `EventTest.java`, and `EventLogTest.java` were inspired by the [alarm project provided by
CPSC 210](https://github.students.cs.ubc.ca/CPSC210/AlarmSystem).

# Example of log

The following log depicts the user loading the data from the workroom. This adds the 
3 entries (Google, Reddit, and Paypal) that are on file to the program. The user then adds
another entry (Bing) then removes an entry (Paypal). They then save their work.
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

# Improvements
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
