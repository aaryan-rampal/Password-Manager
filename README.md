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

Stretch Goals: Not Implemented Yet
- view a specific previous entry using any of the entry fields or index 
- specify a *minimum* entropy for the generated password
- delete previous entries
- edit previous entries to change any of the fields
- **encrypt** the file when I'm done using it
- **decrypt** the file using a password
- store the encrypted file on my computer and have it be easily transportable
