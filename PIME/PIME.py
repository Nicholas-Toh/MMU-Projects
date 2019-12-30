# -*- coding: utf-8 -*-
from Tkinter import *
import string
import re
import tkMessageBox
import time
import datetime
import linecache
import math


root = Tk()
root.minsize(600, 700)
root.resizable(0, 0)
root.configure(background='white')

class PIME_UI:
    def __init__(self, root):

        self.root = root
        root.title("PIME - The Free Ride Sharing Solution for Students")

        #self.pickUpLoc 
        #self.dropOffLoc 
        #self.noOfPassenger 
        #self.distance 
        #self.depatureTime
        #self.rating 
        #self.driverIndex 
        #self.driverDict

        self.noOfPassenger = 1
        self.depatureTime = ""
        self.rating = 0
        self.driverIndex = 0
        self.driverDict = [['MMU','Dpulze', 'John', 'BNV3512', '0162836838'], ['MMU', 'Shaftsbury Square', 'David', 'WMV4031', '0163931822'], ['MMU', 'Domain', 'Aaron', 'QCK2013', '0163351186'], ['MMU', 'Putrajaya Sentral', 'Alan', 'BAC4223', '0162345687'], ['MMU', 'Cyberia', 'Bruce', 'CDB3243', '0161366789'], ['MMU', 'The Arc', 'Carl', 'GCB1114', '0134931322'], ['MMU', 'Mutiara Ville', 'Daniel', 'LDB2234', '0108934572'], ['Dpulze' , 'MMU', 'Issac', 'HKU4232', '0163688186'], ['Shaftsbury Square' , 'MMU', 'Matthew', 'DGA1262', '0176751386'], ['Domain', 'MMU', 'Yusuf', 'ZDE3783', '0168834484'], ['Putrajaya Sentral', 'MMU', 'Jeffrey', 'RDS5613', '0166633456'], ['Cyberia', 'MMU', 'Laurence', 'HGD9231', '0165656780'], ['The Arc', 'MMU', 'Jonathan', 'GHR2304', '0167644352'], ['Mutiara Ville', 'MMU', 'Harold', 'FBV8623', '0166484202']]
        
        self.starBox = ["*", "* *", "* * *", "* * * *", "* * * * *"]
        self.newRating = 0
###   REGISTRATRION   ###
        global exampleName
        global exampleDOB
        global exampleStdId
        global exampleHpNum
        global exampleEmail

        global HomeButton1
        HomeButton1 = Button(root, text="Back to Home", command = lambda: self.gotoMainPage("SignIn"))
        global HomeButton2
        HomeButton2 = Button(root, text="Back to Home", command = lambda: self.gotoMainPage("Register"))

#This is only for Correct input
        exampleName = StringVar()
        exampleDOB = StringVar()
        exampleStdId = StringVar()
        exampleHpNum = StringVar()
        exampleEmail = StringVar()
        global nameInputValid
        global DOBInputValid
        global stdIdValid
        global hpNumValid
        global emailValid
        global activateClear
        
        exampleName.set("Ex: Mary Jane")
        exampleDOB.set("Ex: dd/mm/yyyy")
        exampleStdId.set("Ex: 1161101234")
        exampleHpNum.set("Ex: 0123456789")
        exampleEmail.set("Ex: name@gmail.com")

          
        self.nameInputValid = True
        self.DOBInputValid = True
        self.stdIdValid = True
        self.hpNumValid = True
        self.emailValid = True
        self.activateClear = [True, True, True, True, True] #[0] is nameInput, [1] is DOBInput, [2] is StdIdInput, [3] is hpNumInput, [4] is emailInput. If true clearing of the input is activated
###    JOURNEY     ###
        self.advertisement = PhotoImage(file="ads.gif")
        self.PIMEads = Label(root, image=self.advertisement)
        self.PIMEads.photo = self.advertisement

### creates a rating file with 0 in it if it doesnt exist
        self.ratingArray = []
        try:
            f = open("rating.txt", "r+")
        except IOError:
            print("windowsError")
            f = open("rating.txt", "w+")
            for x in range (0, 2*len(self.driverDict)):
                print ("hi")
                f.write("0\n")
            f.close()
### creates a blank userData file if it doesnt exist
        try:
            f = open("userData.txt", "r+")
        except IOError:
            print("windowsError")
            f = open("userData.txt", "w+")


        self.mainPage()

        
    def mainPage(self):

        global emptyFrame
        global mainTitle
        global subtitle
        global registerButton
        global signInButton
        global mainTitleFrame
        global subtitleFrame
        global buttonFrame
        
        emptyFrame = Frame(root, bg="white")           ##creates some space at the top 
        emptyFrame.pack(fill=X, pady=45)

        mainTitleFrame = Frame(root, bd=1, relief=SUNKEN, background="white")
        mainTitleFrame.pack(fill=X)
        mainTitle = Label(mainTitleFrame, text="PIME", font=("Constantia", 59), bg="white", fg="DodgerBlue3")

        subtitleFrame = Frame(root, bd=0, relief=SUNKEN, background="white")
        subtitleFrame.pack(fill=X, pady=25)
        subtitle = Label(subtitleFrame, text="Welcome to PIME, student!", font=("Verdana", 14), bg="white", fg="Black")

        mainTitle.pack()
        subtitle.pack()

        buttonFrame = Frame(root, relief=SUNKEN, background="white")
        buttonFrame.pack(fill=X, pady=30, padx=150)
        registerButton = Button(buttonFrame, text="Register", command= lambda: self.Registration("Register"))           
        signInButton = Button(buttonFrame, text="Sign in", command= lambda: self.Registration("SignIn"))

        registerButton.pack(side=LEFT, anchor=CENTER)
        signInButton.pack(side=RIGHT, anchor=CENTER)
    def gotoMainPage(self, command):
        if command=="Register":
            instructionsLabel.pack_forget()
            registrationContainer.pack_forget()
            inputField.grid_forget()
            nameInput.grid_forget()
            DOBInput.grid_forget()
            StdIdInput.grid_forget()
            hpNumInput.grid_forget()
            emailInput.grid_forget()
            passwordInput.grid_forget()
            nameLabel.grid_forget()
            DOBLabel.grid_forget()
            StdIdLabel.grid_forget()
            hpNumLabel.grid_forget()
            emailLabel.grid_forget()
            passwordLabel.grid_forget()
            emptyFrame2.grid_forget()
            passengerSubmitButton.grid_forget()
            driverSubmitButton.grid_forget()
            TOSCheckBox.grid_forget()
            passengerSubmitButtonFrame.pack_forget()
            driverSubmitButtonFrame.pack_forget()
            emptyFrame.pack_forget()
            mainTitle.pack_forget()
            mainTitleFrame.pack_forget()
            HomeButton2.pack_forget()
            self.mainPage()
        elif command=="SignIn":
            emptyFrame3.pack_forget()
            signInButton.pack_forget()
            registrationContainer.pack_forget()
            inputField.grid_forget()
            StdIdInput.grid_forget()
            StdIdLabel.grid_forget()
            passwordLabel.grid_forget()
            passwordInput.grid_forget()
            emptyFrame.pack_forget()
            mainTitle.pack_forget()
            mainTitleFrame.pack_forget()
            HomeButton1.pack_forget()
            emptyFrame.pack_forget()
            subtitleFrame.pack_forget()
            self.mainPage()
        elif command == "SummaryPage":
            encapsulateFrame.pack_forget()
            driverDetailsTitleFrame.pack_forget()
            tripSummaryTitle.pack_forget()
            chosenDriverInfoFrame.pack_forget()
            chosenDriverInfoGrid.grid_forget()
            chosenPickUpPointTitle.grid_forget()
            chosenPickUpPoint.grid_forget()
            chosenDropOffPointTitle.grid_forget()
            chosenDropOffPoint.grid_forget()
            departureTimeTitle.grid_forget()
            departureTimeLabel.grid_forget()
            arrivalTimeTitle.grid_forget()
            arrivalTimeLabel.grid_forget()
            elapsedTimeTitle.grid_forget()
            elapsedTimeLabel.grid_forget()
            distanceTitle.grid_forget()
            distanceLabel.grid_forget()
            driverNameTitle.grid_forget()
            driverName.grid_forget()
            driverRatingTitle.grid_forget()
            ratingSelectionMenu.grid_forget()
            submitRatingButton.pack_forget()
            driversPaymentButton.pack_forget()
            mainTitle.pack_forget()
            mainTitleFrame.pack_forget()
            emptyFrame.pack_forget()
            subtitleFrame.pack_forget()
            self.mainPage()
        elif command == "Payment":
            driverSummaryTitle.pack_forget()
            driverSummaryNoOfPassengers.pack_forget()
            driverSummaryDistance.pack_forget()
            driverSummaryRating.pack_forget()
            driverSummaryGoHome.pack_forget()
            driverSalary.pack_forget()
            driverSalaryInfoMsg.pack_forget()
            averageRatingLabel.pack_forget()
            noOfTripsLabel.pack_forget()
            bookAnotherRideButton.pack_forget()
            self.mainPage()

    def Registration(self, command):

        #mainTitle.pack_forget()
        subtitle.pack_forget()
        registerButton.pack_forget()
        signInButton.pack_forget()
        #mainTitleFrame.pack_forget()
        subtitleFrame.pack_forget()
        buttonFrame.pack_forget()

        #Jump#

    
        def newAcc(self):
            global instructionsLabel
            global registrationContainer
            global inputField
            global nameInput
            global DOBInput
            global StdIdInput
            global hpNumInput
            global emailInput
            global passwordInput
            global nameLabel
            global DOBLabel
            global StdIdLabel
            global hpNumLabel
            global emailLabel
            global passwordLabel
            global passwordLabel2
            global emptyFrame2
            global passengerSubmitButton
            global driverSubmitButton
            global passengerSubmitButtonFrame
            global driverSubmitButtonFrame
            global TOSCheckBox
            global TOSCheckBoxValue



            instructionsLabel = Label(emptyFrame, text="Please complete your details", font=("Verdana", 14), bg="white", fg="DodgerBlue3", bd=1)
            instructionsLabel.pack(fill=X)

            registrationContainer = Frame(root, bd=1, relief=SUNKEN, bg="white")
            registrationContainer.pack(fill=X, pady=50, padx=30)

            inputField = Frame(registrationContainer, bd=1, bg="white")
            inputField.pack()

            nameLabel = Label(inputField, text="Full Name:", font=("Verdana", 11), bg="white", fg="DodgerBlue3")
            nameLabel.grid(row=0, column=1, sticky=E)
            DOBLabel = Label(inputField, text="Date of Birth:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            DOBLabel.grid(row=1, column=1, sticky=E)
            StdIdLabel = Label(inputField, text="MMU Student ID:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            StdIdLabel.grid(row=2, column=1, sticky=E)
            hpNumLabel = Label(inputField, text="Mobile Number:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            hpNumLabel.grid(row=3, column=1, sticky=E)
            emailLabel = Label(inputField, text="Email:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            emailLabel.grid(row=4, column=1, sticky=E)
            passwordLabel = Label(inputField, text="Password:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            passwordLabel.grid(row=5, column=1, sticky=E)
            passwordLabel2 = Label(inputField, text="(8-12 characters)", font=("Verdana", 8), bg="white", fg="DodgerBlue3", bd=2)
            passwordLabel2.grid(row=6, column=1, sticky=E)
            TOSCheckBoxValue = IntVar()
            TOSCheckBox = Checkbutton(inputField, text="I have read the terms and conditions",variable=TOSCheckBoxValue)
            TOSCheckBox.grid(row=6, column=2)


            
            def clearField(command, activateClear):
                print activateClear
                if command == "name":
                    if activateClear[0] == True:
                        nameInput.delete(0, END)
                        print("deleting")
                        nameInput.unbind("<Button-1>")
                elif command == "DOB":
                    if activateClear[1] == True:
                        DOBInput.delete(0, END)
                        print("deleting")
                        DOBInput.unbind("<Button-1>")
                elif command == "stdId":
                    if activateClear[2] == True:
                        StdIdInput.delete(0, END)
                        print("deleting")
                        StdIdInput.unbind("<Button-1>")
                elif command == "hpNum":
                    if activateClear[3] == True:
                        hpNumInput.delete(0, END)
                        print("deleting")
                        hpNumInput.unbind("<Button-1>")
                elif command == "email":
                    if activateClear[4] == True:
                        emailInput.delete(0, END)
                        print("deleting")
                        emailInput.unbind("<Button-1>")
                    

            
            emptyFrame2 = Label(inputField, text="", bg="white") ##align the entry to center
            emptyFrame2.grid(row=0, column=0)
            nameInput = Entry(inputField)
            nameInput.insert(0, exampleName.get())
            nameInput.bind("<Button-1>", lambda _:clearField("name", self.activateClear))
            nameInput.grid(row=0, column=2)
            emptyFrame2.grid(row=1, column=0)
            DOBInput = Entry(inputField)
            DOBInput.insert(0, exampleDOB.get())
            DOBInput.bind("<Button-1>", lambda _:clearField("DOB", self.activateClear))
            DOBInput.grid(row=1, column=2) 
            emptyFrame2.grid(row=2, column=0)
            StdIdInput = Entry(inputField)
            StdIdInput.insert(0, exampleStdId.get())
            StdIdInput.bind("<Button-1>", lambda _:clearField("stdId", self.activateClear))
            StdIdInput.grid(row=2, column=2)
            emptyFrame2.grid(row=3, column=0)
            hpNumInput = Entry(inputField)
            hpNumInput.insert(0, exampleHpNum.get())
            hpNumInput.bind("<Button-1>", lambda _:clearField("hpNum", self.activateClear))
            hpNumInput.grid(row=3, column=2)
            emptyFrame2.grid(row=4, column=0)
            emailInput = Entry(inputField)
            emailInput.insert(0, exampleEmail.get())
            emailInput.bind("<Button-1>", lambda _:clearField("email", self.activateClear))
            emailInput.grid(row=4, column=2)
            emptyFrame2.grid(row=5, column=0)
            passwordInput = Entry(inputField, show="*")
            passwordInput.grid(row=5, column=2)

            
            passengerSubmitButtonFrame = Frame(registrationContainer, padx=100, bg="white")
            driverSubmitButtonFrame = Frame(registrationContainer)
            passengerSubmitButtonFrame.pack(side=LEFT)
            driverSubmitButtonFrame.pack(side=LEFT)
            passengerSubmitButton = Button(passengerSubmitButtonFrame, text="Register as Passenger", height=3, command=lambda: self.checkRegistration())
            passengerSubmitButton.pack(anchor=CENTER)
            driverSubmitButton = Button(driverSubmitButtonFrame, text="Register as Driver", height=3, command=lambda: self.someUselessBox())
            driverSubmitButton.pack(anchor=CENTER)

            HomeButton2.pack(side=BOTTOM, anchor=CENTER)

            

            
        def signIn(self):
            global emptyFrame3
            global signInButton
            global registrationContainer
            global inputField
            global StdIdInput
            global StdIdLabel
            global passwordLabel
            global passwordInput
            HomeButton2.pack_forget()

            
            print("hello")
            
            mainTitleFrame.pack(fill=X)
            mainTitle.pack(fill=X)
            subtitleFrame.pack(fill=X, pady=25)#empty frame
            registrationContainer = Frame(root, bd=1, relief=SUNKEN, bg="white")
            registrationContainer.pack(fill=X, padx=30, pady=20)
            inputField = Frame(registrationContainer, bd=1, bg="white")
            inputField.grid()
            emptyFrame3 = Label(inputField, text="                          ", bg="white") ##align the entry to center
            StdIdLabel = Label(inputField, text="MMU Student ID:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)            
            StdIdLabel.grid(row=0, sticky=E)
            passwordLabel = Label(inputField, text="Password:", font=("Verdana", 11), bg="white", fg="DodgerBlue3", bd=2)
            passwordLabel.grid(row=1, sticky=E)
            emptyFrame3.grid(row=0, column=1)
            StdIdInput = Entry(inputField)
            StdIdInput.grid(row=0, column=2)
            emptyFrame3.grid(row=1, column=1)
            passwordInput = Entry(inputField, show="*")
            passwordInput.grid(row=1, column=2)
            signInButton = Button(root, text="Sign In", height=2, command=lambda: self.checkSignIn())
            signInButton.pack(anchor=CENTER)
            HomeButton1.pack(side=BOTTOM, anchor=CENTER)
             
        #Jump here#
        if command == "Register":
            print("Registering")
            newAcc(self)            
        elif command == "SignIn":
            print("Signing In")
            signIn(self)
            
    def someUselessBox(self):
        tkMessageBox.showinfo("Coming Soon!", "This feature is still under development. Stay tuned at PIME.com for more updates!")
    
    def checkRegistration(self):
        global name
        global DOB
        global stdId
        global hpNum
        global email
        global password
        global tosClicked

        name = nameInput.get()
        DOB = DOBInput.get()
        stdId = StdIdInput.get()
        hpNum = hpNumInput.get()
        email = emailInput.get()
        password = passwordInput.get()
        tosClicked = TOSCheckBoxValue.get()
        print tosClicked
        
        messagesBox = ["> No name inserted.\n",
                       "> Name too long. Range is between 3 to 21 characters.\n",
                       "> Name too small. Range is between 3 to 21 characters.\n",
                       "> Invalid date. Please enter in dd/mm/yyyy\n",
                       "> Please enter the Student ID in numbers, ex: 1161303845\n",
                       "> Non-numeric detected. Please enter the Student ID in numbers, ex: 1161303845\n",
                       "> Student ID is too long. Range is between 5 to 10 characters.\n",
                       "> Student ID is too short. Range is between 5 to 10 characters.\n",
                       "> No input for mobile no. Please enter your mobile no following the format, ex: 0104641737\n",
                       "> Non-numeric detected. Please enter the mobile no. in numbers, ex: 0104641737\n",
                       "> Invalid mobile no. Please enter a valid mobile no. in between 9 to 12 characters.\n",
                       "> No input for email.\n",
                       "> Invalid email.\n",
                       "> You must accept the Terms of Service.\n",
                       "> You have entered 2 or more spaces in your name.\n",
                       "> Only alphabets are allowed in your name.\n",
                       "> Invalid Password. Must be within 8 - 12 characters.\n",
                       "> Student Id already exists. Please sign in or try another one.\n"
                       ]

        messagesToDisplay = []
        boxString = ""

        self.emailValid = True

        
# To check the name input
        if name == "":
            messagesToDisplay.append(0)
            self.nameInputValid = False
        
        elif "  " in name:
            messagesToDisplay.append(14)
            self.nameInputValid = False
            
        elif not all(x.isalpha() or x.isspace() for x in name):
            messagesToDisplay.append(15)
            self.nameInputValid = False
            print("not alpha")
        
        elif len(name) > 21:
            messagesToDisplay.append(1)
            self.nameInputValid = False
  
        elif len(name) < 3:
            messagesToDisplay.append(2)
            self.nameInputValid = False
        else:
            self.nameInputValid = True

            
        #print messagesToDisplay
            
# To check the date of birth
        try:
            valid_date = time.strptime(DOB, '%m/%d/%Y')
            self.DOBInputValid = True

        except ValueError:
            messagesToDisplay.append(3)
            self.DOBInputValid = False

            
# To check the student ID            
        try:
            number = int(stdId)
            self.stdIdValid = True
            stdIdValueValid = True
        except ValueError:
            stdIdValueValid = False
        UserID = []
        DataCounter = 1
        cdd = 0
        DC = 1
        with open('userData.txt') as f:
            LineCounter = (sum(1 for _ in f))
            if LineCounter == 0:
                stdIdExistenceValid = True
            
            else:
                DataFile = open("userData.txt", "r")
                while DataCounter <= LineCounter:
                    UserID.append(DataFile.readline().rstrip('\n'))
                    DataCounter = DataCounter + 1
                DataFile.close()
                
                while DC <= LineCounter / 2:
                    if UserID[cdd] == stdId:
                        stdIdExistenceValid = False
                        break
                    else:
                        self.stdIdValid = True
                        stdIdExistenceValid = True
                    cdd = cdd + 2
                    DC = DC + 1
        if stdId == "":
            messagesToDisplay.append(4)
            self.stdIdValid = False
        elif stdIdExistenceValid == False:
            messagesToDisplay.append(17)
            self.stdIdValid = False
        elif stdIdValueValid == False:
            messagesToDisplay.append(5)
            self.stdIdValid = False
        elif len(stdId) > 10:
            messagesToDisplay.append(6)
            self.stdIdValid = False
        elif len(stdId) < 5:
            messagesToDisplay.append(7)
            self.stdIdValid = False
        

# To check the Mobile Number
        if hpNum == "":
            messagesToDisplay.append(8)
            self.hpNumValid = False
        else:
            self.hpNumValid = True

        try:
            number = int(hpNum)
            if int(hpNum) < 100000000 or int(hpNum) > 99999999999:
                messagesToDisplay.append(10)
                self.hpNumValid = False
            else:
                self.hpNumValid = True

        except ValueError:
            messagesToDisplay.append(9)
            self.hpNumValid = False






# To check the email
        if email == "":
            messagesToDisplay.append(11)
            self.emailValid = False
        else:
            match = re.match('^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-zA-Z]{2,6})$', email)
            if match == None:
                messagesToDisplay.append(12)
                self.emailValid = False
            else:
                self.hpNumValid = True

# To check the password
        if len(password) < 8 or len(password) > 12:
            messagesToDisplay.append(16)

# To check the TOS box
        if tosClicked != 1:
            messagesToDisplay.append(13)


#Replace relevant example messages
        print self.nameInputValid
        print self.DOBInputValid
        print self.stdIdValid
        print self.hpNumValid
        print self.emailValid


# This is the process happens after the user input        
        if self.nameInputValid == True:
            exampleName.set(name)
            self.activateClear[0] = False
        else:
            exampleName.set("Ex: Mary Jane")
            self.activateClear[0] = True
            
        if self.DOBInputValid == True:
            exampleDOB.set(DOB)
            self.activateClear[1] = False
        else:
            exampleDOB.set("Ex: mm/dd/yyyy")
            self.activateClear[1] = True
            
        if self.stdIdValid == True:
            exampleStdId.set(stdId)
            self.activateClear[2] = False
        else:
            exampleStdId.set("Ex: 1161101234")
            self.activateClear[2] = True

        if self.hpNumValid == True:
            exampleHpNum.set(hpNum)
            self.activateClear[3] = False
        else:
            exampleHpNum.set("Ex: 0123456789")
            self.activateClear[3] = True
            
        if self.emailValid == True:
            exampleEmail.set(email)
            self.activateClear[4] = False
            
        else:
            exampleEmail.set("Ex: name@gmail.com")
            self.activateClear[4] = True
        print (self.activateClear) 
        if messagesToDisplay != []:   
            for iterable in messagesToDisplay:
                boxString = boxString+"\n"+messagesBox[iterable]
                #print boxString
            tkMessageBox.showinfo("Alert", boxString)
            instructionsLabel.pack_forget()
            registrationContainer.pack_forget()
            inputField.grid_forget()
            nameInput.grid_forget()
            DOBInput.grid_forget()
            StdIdInput.grid_forget()
            hpNumInput.grid_forget()
            emailInput.grid_forget()
            passwordInput.grid_forget()
            nameLabel.grid_forget()
            DOBLabel.grid_forget()
            StdIdLabel.grid_forget()
            hpNumLabel.grid_forget()
            emailLabel.grid_forget()
            passwordLabel.grid_forget()
            emptyFrame2.grid_forget()
            passengerSubmitButton.grid_forget()
            driverSubmitButton.grid_forget()
            TOSCheckBox.grid_forget()
            passengerSubmitButtonFrame.pack_forget()
            driverSubmitButtonFrame.pack_forget()
            self.Registration("Register")
        else:
            instructionsLabel.pack_forget()
            registrationContainer.pack_forget()
            inputField.grid_forget()
            nameInput.grid_forget()
            DOBInput.grid_forget()
            StdIdInput.grid_forget()
            hpNumInput.grid_forget()
            emailInput.grid_forget()
            passwordInput.grid_forget()
            nameLabel.grid_forget()
            DOBLabel.grid_forget()
            StdIdLabel.grid_forget()
            hpNumLabel.grid_forget()
            emailLabel.grid_forget()
            passwordLabel.grid_forget()
            emptyFrame2.grid_forget()
            passengerSubmitButton.grid_forget()
            TOSCheckBox.grid_forget()
            driverSubmitButton.grid_forget()
            passengerSubmitButtonFrame.pack_forget()
            driverSubmitButtonFrame.pack_forget()
            with open('userData.txt', 'a') as AppendFile:
                AppendFile.write(stdId)
                AppendFile.write('\n')
                AppendFile.write(password)
                AppendFile.write('\n')

            exampleName.set("Ex: Mary Jane")
            exampleDOB.set("Ex: mm/dd/yyyy")
            exampleStdId.set("Ex: 1161101234")
            exampleHpNum.set("Ex: 0123456789")
            exampleEmail.set("Ex: name@gmail.com")

            self.activateClear = [True, True, True, True, True]
            self.Registration("SignIn")

                                  
    def checkSignIn(self):
        print ("checking sign in")
        stdId = StdIdInput.get()
        password = passwordInput.get()
        UserID = []
        Password = []
        DataCounter = 2
        with open('userData.txt') as f:
            LineCounter = (sum(1 for _ in f))
            print LineCounter
        DataFile = open("userData.txt", "r")


        while DataCounter<=LineCounter+1:

            if DataCounter % 2 == 0:
                UserID.append(DataFile.readline().rstrip('\n'))
                DataCounter = DataCounter + 1

            else:
                Password.append(DataFile.readline().rstrip('\n'))
                DataCounter = DataCounter + 1

        DataFile.close()


        print('\n Reading data from text file and inserting into array', LineCounter)
        print(UserID)
        print(Password)
        print('\n')




        Valid = 0
        

        DataCounter = 1
        LoginCounter = 0
        while DataCounter<=LineCounter/2:

            if UserID[LoginCounter] == stdId and Password[LoginCounter] == password:
                print("Sign in successful!")
                DataCounter = LineCounter
                Valid = 1

            else:
                LoginCounter = LoginCounter+1
                DataCounter = DataCounter + 1

        if Valid != 1:
            print ("Invalid user ID or password!")
            tkMessageBox.showinfo("Alert", "Invalid user ID or password!")
        else:
            self.locationSelectionPage()

    def locationSelectionPage(self):
        #To globalize Tkinter elements
        global locationSelection
        global pickUpSelectionTitle
        global pickUpSelectionMenu
        global dropOffSelectionTitle
        global dropOffSelectionMenu
        global searchDriverButton
        global locationSelectionFrame
        global pickUpSelectionFrame
        global pickUpSelectionGrid
        global dropOffSelectionFrame
        global dropOffSelectionGrid
        global searchDriverFrame
        global driverDict
        global distancePairing
        global locArray
        global qualifiedDrivers

        #Assignment of values
        distancePairing = [['MMU', 'Dpulze', 3 ] , ['MMU', 'Shaftsbury Square', 4] , ['MMU', 'Domain', 2 ] , ['MMU', 'Putrajaya Sentral', 8 ] , ['MMU', 'Cyberia', 1 ] , ['MMU', 'The Arc', 2 ] , ['MMU', 'Mutiara Ville', 4 ]]
        statusPickUp = 0
        statusDropOff = 0
        pickUpUni = 0 
        dropOffUni = 0
        locArray = []
        qualifiedDrivers = []
    
        
        #To destroy previous Tkinter elements
        emptyFrame3.pack_forget()
        HomeButton1.pack_forget()
        signInButton.pack_forget()
        registrationContainer.pack_forget()
        inputField.grid_forget()
        StdIdInput.grid_forget()
        StdIdLabel.grid_forget()
        passwordLabel.grid_forget()
        passwordInput.grid_forget()

        #Defining frame in root/frames
        locationSelectionFrame = Frame(root, pady=10, bg="white")
        locationSelectionFrame.pack(side = TOP)
        pickUpSelectionFrame = Frame(root)
        pickUpSelectionFrame.pack()
        pickUpSelectionGrid = Frame(pickUpSelectionFrame, pady=10, bg="white")
        pickUpSelectionGrid.grid()
        dropOffSelectionFrame = Frame(root)
        dropOffSelectionFrame.pack()
        dropOffSelectionGrid = Frame(dropOffSelectionFrame, pady=10, bg="white")
        dropOffSelectionGrid.grid()
        searchDriverFrame = Frame(root , pady=20, bg="white")
        searchDriverFrame.pack()

        #Defining elements within frames
        locationSelection = Label(locationSelectionFrame,text="Select pickup & drop-off point", bg="white",font=("Verdana", 14))
        pickUpSelectionTitle = Label(pickUpSelectionGrid,text="Pickup point:", bg="white")
        dropOffSelectionTitle = Label(dropOffSelectionGrid,text="Drop-off point:", bg="white")
        searchDriverButton = Button(searchDriverFrame, text="Search Driver", command=lambda: self.locationValidation())
        #Dropdown list for pickup selection
        self.pickUpLocTemp = StringVar(root) # Create a Tkinter variable
        pickUpDict = {'MMU', 'Dpulze', 'Shaftsbury Square', 'Domain', 'Putrajaya Sentral', 'Cyberia', 'The Arc', 'Mutiara Ville'} # Pickup dictionary 
        self.pickUpLocTemp.set('MMU') # set the default option
        pickUpSelectionMenu = OptionMenu(pickUpSelectionGrid, self.pickUpLocTemp, *pickUpDict)
        # on change dropdown value
        def change_dropdown(*args):
            print( self.pickUpLocTemp.get() )
        # link function to change dropdown
        self.pickUpLocTemp.trace('w', change_dropdown)
        #Dropdown list for drop-off selection 
        self.dropOffLocTemp = StringVar(root) # Create a Tkinter variable
        dropOffDict = {'MMU', 'Dpulze', 'Shaftsbury Square', 'Domain', 'Putrajaya Sentral', 'Cyberia', 'The Arc', 'Mutiara Ville'} # Pickup dictionary 
        self.dropOffLocTemp.set('MMU') # set the default option
        dropOffSelectionMenu = OptionMenu(dropOffSelectionGrid, self.dropOffLocTemp, *dropOffDict)
        # on change dropdown value
        def change_dropdown(*args):
            print( self.dropOffLocTemp.get() )
        # link function to change dropdown
        self.dropOffLocTemp.trace('w', change_dropdown)


        #To display elements in an orderly way
        locationSelection.pack()
        pickUpSelectionTitle.grid(row=0,column=0)
        pickUpSelectionMenu.grid(row=0, column=1)
        dropOffSelectionTitle.grid(row=0,column=0)
        dropOffSelectionMenu.grid(row=0, column=1)
        searchDriverButton.pack()

    def locationValidation(self):
        self.pickUpLoc = self.pickUpLocTemp.get()
        self.dropOffLoc = self.dropOffLocTemp.get()
                
        locationValidationMessagesBox = ["> Either the pickup or drop-off location has to be MMU\n",
                               "> Pickup and drop-off location cannot be MMU at the same time\n"
                               ]

        if self.pickUpLoc != "MMU" and self.dropOffLoc != "MMU":
                tkMessageBox.showinfo("Alert", locationValidationMessagesBox[0])
                
        if self.pickUpLoc == "MMU" and self.dropOffLoc == "MMU":
                tkMessageBox.showinfo("Alert", locationValidationMessagesBox[1])

        if self.pickUpLoc != "MMU" and self.dropOffLoc == "MMU":
            self.availableDrivers()

        if self.pickUpLoc == "MMU" and self.dropOffLoc != "MMU":
            self.availableDrivers()
                
    def availableDrivers(self):

        #To globalize Tkinter elements
        global availableDriverListFrame
        global nameAndRatingListFrame
        global nameAndRatingListGrid
        global selectDriverFrame
        global selectDriverGrid
        global confirmDriverFrame
        global availableDriverListTitle
        global selectDriverTitle
        global confirmDriverButton
        global driverSelectionMenu

        #To determine distance between self.pickUpLoc and self.dropOffLoc
        counter = 0
        while counter < len(distancePairing):
            if self.pickUpLoc == distancePairing[counter][0] and self.dropOffLoc == distancePairing[counter][1]:
                self.distance = distancePairing[counter][2]
            if self.pickUpLoc == distancePairing[counter][1] and self.dropOffLoc == distancePairing[counter][0]:
                self.distance = distancePairing[counter][2]
            counter = counter + 1
        
        #To put pickup location and drop-off location inside locArray
        locArray.append(self.pickUpLoc)
        locArray.append(self.dropOffLoc)
        
        #To put the qualified drivers into an array
        counter = 0
        while counter < len(self.driverDict):
            if locArray[0] == self.driverDict[counter][0] and locArray[1] == self.driverDict[counter][1]:
                qualifiedDrivers.append(self.driverDict[counter][2])
            counter = counter + 1
        
        #To destroy previous Tkinter elements
        locationSelection.pack_forget()
        pickUpSelectionTitle.grid_forget()
        pickUpSelectionMenu.grid_forget()
        dropOffSelectionTitle.grid_forget()
        dropOffSelectionMenu.grid_forget()
        locationSelectionFrame.pack_forget()
        pickUpSelectionFrame.pack_forget()
        pickUpSelectionGrid.grid_forget()
        dropOffSelectionFrame.pack_forget()
        dropOffSelectionGrid.grid_forget()
        searchDriverFrame.pack_forget()
        
        #Defining frame in root/frames
        availableDriverListFrame = Frame(root,bg="white",pady=10)
        availableDriverListFrame.pack()
        nameAndRatingListFrame = Frame(root,bg="white",pady=10)
        nameAndRatingListFrame.pack()
        nameAndRatingListGrid = Frame(nameAndRatingListFrame)
        nameAndRatingListGrid.grid()
        selectDriverFrame = Frame(root,bg="white",pady=10)
        selectDriverFrame.pack()
        selectDriverGrid = Frame(selectDriverFrame,bg="white",pady=10)
        selectDriverGrid.pack()
        confirmDriverFrame = Frame(root,bg="white",pady=20)
        confirmDriverFrame.pack()

        #Defining elements within frames
        availableDriverListTitle = Label(availableDriverListFrame,text="Available driver",bg="white",font=("Verdana", 14))
        selectDriverTitle = Label(selectDriverGrid, text="Select Driver:",bg="white")
        confirmDriverButton = Button(confirmDriverFrame, text="Goooo", command=lambda: self.driverDetails())
        #Dropdown list for driver selection
        self.driverSelectionTemp = StringVar(root) # Create a Tkinter variable
        self.driverSelectionTemp.set(qualifiedDrivers[0]) # set the default option
        driverSelectionMenu = OptionMenu(selectDriverGrid, self.driverSelectionTemp, *qualifiedDrivers)
        # on change dropdown value
        def change_dropdown(*args):
            print( self.driverSelectionTemp.get() )
        # link function to change dropdown
        self.driverSelectionTemp.trace('w', change_dropdown)

        #To display elements in an orderly way
        availableDriverListTitle.pack()
        selectDriverTitle.grid(row=0,column=0)
        driverSelectionMenu.grid(row=0,column=1)
        confirmDriverButton.pack()
        self.driverSelection = self.driverSelectionTemp.get()

    def driverDetails(self):
        #To globalize Tkinter elements
        global encapsulateFrame
        global driverDetailsTitleFrame
        global chosenDriverInfoFrame
        global chosenDriverInfoGrid
        global validateArrivalFrame
        global driverDetailsTitle
        global chosenPickUpPointTitle
        global chosenPickUpPoint
        global chosenDropOffPointTitle
        global chosenDropOffPoint
        global driverNameTitle
        global driverName
        global carNoTitle
        global carNo
        global mobileNoTitle
        global mobileNo
        global driverRatingTitle
        global driverRatingLabel
        global departureTimeTitle
        global departureTimeLabel
        global searchDriverButton

        
        #To determine and store departure time
        self.nowDepart = datetime.datetime.now()
        self.departHour = self.nowDepart.hour
        self.departMinute = self.nowDepart.minute
        self.departSecond = self.nowDepart.second
        if self.departHour >= 12:
            period = "PM"
        else:
            period = "AM"
        if float(self.departSecond) <10.0:
            self.departSecond = "0"+str(self.departSecond)
        if float(self.departMinute) <10.0:
            self.departMinute = "0"+str(self.departMinute)
        self.departureTime = str(self.departHour) + ':' + str(self.departMinute) + ':' + str(self.departSecond) + period
        #reset to INT
        self.departHour = self.nowDepart.hour
        self.departMinute = self.nowDepart.minute
        self.departSecond = self.nowDepart.second

        #To find out the driver index by checking the chosen driver's name against the driverDict
        counter = 0
        while counter < len(self.driverDict):
            if self.driverSelection == self.driverDict[counter][2]:
                self.driverIndex = counter
            counter = counter + 1
            
        f = open('rating.txt', 'r+')
        lines = f.readlines()
        self.driverRating = float(lines[2*self.driverIndex])
        #To destroy previous Tkinter elements
        availableDriverListFrame.pack_forget()
        nameAndRatingListFrame.pack_forget()
        nameAndRatingListGrid.grid_forget()
        selectDriverFrame.pack_forget()
        selectDriverGrid.grid_forget()
        confirmDriverFrame.pack_forget()
        availableDriverListTitle.pack_forget()
        #driverNameTitle.grid_forget()
        #driverRatingTitle.pack_forget()
        selectDriverTitle.grid_forget()
        driverSelectionMenu.grid_forget()
        confirmDriverButton.pack_forget()
        
        #Defining frame in root/frames
        encapsulateFrame = Frame(root, pady=10, bd=1, bg="white", borderwidth=2, relief="groove")
        encapsulateFrame.pack()
        driverDetailsTitleFrame = Frame(encapsulateFrame,pady=10,bg="white")
        driverDetailsTitleFrame.pack()
        chosenDriverInfoFrame = Frame(encapsulateFrame)
        chosenDriverInfoFrame.pack()
        chosenDriverInfoGrid = Frame(chosenDriverInfoFrame,pady=10,bg="white")
        chosenDriverInfoGrid.grid()
        validateArrivalFrame = Frame(root,pady=20,bg="white")
        validateArrivalFrame.pack()
        

        #Defining elements within frames
        driverDetailsTitle = Label(driverDetailsTitleFrame,text="Driver's details",bg="white",font=("Verdana", 14))
        chosenPickUpPointTitle = Label(chosenDriverInfoGrid,text= "Pickup point:",bg="white")
        chosenPickUpPoint = Label(chosenDriverInfoGrid,text= self.pickUpLoc,bg="white")
        chosenDropOffPointTitle = Label(chosenDriverInfoGrid,text= "Drop-off point:",bg="white")
        chosenDropOffPoint = Label(chosenDriverInfoGrid,text= self.dropOffLoc,bg="white")
        driverNameTitle = Label(chosenDriverInfoGrid,text= "Driver's name:",bg="white")
        driverName = Label(chosenDriverInfoGrid,text= self.driverDict[self.driverIndex][2],bg="white" )
        carNoTitle = Label(chosenDriverInfoGrid,text= "Car No:",bg="white")
        carNo = Label(chosenDriverInfoGrid,text= self.driverDict[self.driverIndex][3],bg="white" )
        mobileNoTitle = Label(chosenDriverInfoGrid,text= "Mobile no:",bg="white")
        mobileNo = Label(chosenDriverInfoGrid,text = self.driverDict[self.driverIndex][4],bg="white" )
        driverRatingTitle = Label(chosenDriverInfoGrid,text= "Driver rating:",bg="white")
        driverRatingLabel = Label(chosenDriverInfoGrid,text= self.driverRating,bg="white")
        departureTimeTitle = Label(chosenDriverInfoGrid,text= "Departure time:",bg="white")
        departureTimeLabel = Label(chosenDriverInfoGrid,text= self.departureTime,bg="white")
        searchDriverButton = Button(validateArrivalFrame, text="Hitchhiked!", command=lambda: self.journey())

        #To display elements in an orderly way
        driverDetailsTitle.pack()
        chosenPickUpPointTitle.grid(row = 0,column = 0, sticky=E)
        chosenPickUpPoint.grid(row = 0,column = 1, sticky=W)
        chosenDropOffPointTitle.grid(row = 1,column = 0, sticky=E)
        chosenDropOffPoint.grid(row=1,column= 1, sticky=W)
        driverNameTitle.grid(row = 2,column = 0, sticky=E)
        driverName.grid(row =2,column=1, sticky=W)
        carNoTitle.grid(row = 3,column = 0, sticky=E)
        carNo.grid(row = 3,column = 1, sticky=W)
        mobileNoTitle.grid(row = 4,column = 0, sticky=E)
        mobileNo.grid(row = 4, column = 1, sticky=W)
        driverRatingTitle.grid(row = 5,column = 0, sticky=E)
        driverRatingLabel.grid(row = 5,column = 1, sticky=W)
        departureTimeTitle.grid(row = 6,column = 0, sticky=E)
        departureTimeLabel.grid(row = 6,column = 1, sticky=W)
        searchDriverButton.pack()

    def journey(self):
        encapsulateFrame.pack_forget()
        driverDetailsTitleFrame.pack_forget()
        driverDetailsTitle.pack_forget()
        chosenDriverInfoFrame.pack_forget()
        chosenDriverInfoGrid.grid_forget()
        validateArrivalFrame.pack_forget()
        driverDetailsTitle.pack_forget()
        chosenPickUpPointTitle.grid_forget()
        chosenPickUpPoint.grid_forget()
        chosenDropOffPointTitle.grid_forget()
        chosenDropOffPoint.grid_forget()
        driverNameTitle.grid_forget()
        driverName.grid_forget()
        carNoTitle.grid_forget()
        carNo.grid_forget()
        mobileNoTitle.grid_forget()
        mobileNo.grid_forget()
        driverRatingTitle.grid_forget()
        driverRatingLabel.grid_forget()
        departureTimeTitle.grid_forget()
        departureTimeLabel.grid_forget()
        searchDriverButton.pack_forget()
        self.PIMEads.pack()

        root.after(3000, self.checkJourney)


    def checkJourney(self):
        global dropOffButton
        global dropOffLabel

        verifiedDropOffStatus = input("Please enter 1 when the driver has arrived: ")
        while verifiedDropOffStatus != 1:
            verifiedDropOffStatus = input("Please enter 1 when the driver has arrived: ")
        dropOffButton = Button(root, text="End Journey", command = lambda: self.displaySummary())
        dropOffLabel = Label(root, text="Driver has arrived. Please press the button to exit.")
        dropOffLabel.pack(pady = 10)
        dropOffButton.pack()

    def displaySummary(self):
        self.PIMEads.pack_forget()
        dropOffLabel.pack_forget()
        dropOffButton.pack_forget()

        global encapsulateFrame
        global driverDetailsTitleFrame
        global tripSummaryTitle
        global chosenDriverInfoFrame
        global chosenDriverInfoGrid
        global chosenPickUpPointTitle
        global chosenPickUpPoint
        global chosenDropOffPointTitle
        global chosenDropOffPoint
        global departureTimeTitle
        global departureTimeLabe
        global arrivalTimeTitle
        global arrivalTimeLabel
        global elapsedTimeTitle
        global elapsedTimeLabel
        global distanceTitle
        global distanceLabel        
        global driverNameTitle
        global driverName
        global driverRatingTitle
        global ratingSelectionMenu
        global submitRatingButton
        global driversPaymentButton
        #stores the number of trips
        f = open('rating.txt', 'r+')
        lines = f.readlines()
        print(lines)
        self.totalTrip = int(lines[2 * self.driverIndex +1]) + 1
        lines[2 * self.driverIndex +1] = str(self.totalTrip) + "\n"
        print lines
        f.close()
        f = open('rating.txt', 'w+')
        for x in lines:
            f.write(x)
            
        #arrival time
        self.nowArrival = datetime.datetime.now()
        self.arriveHour = self.nowArrival.hour
        self.arriveMinute = self.nowArrival.minute
        self.arriveSecond = self.nowArrival.second
        if self.arriveHour >= 12:
            period = "PM"
        else:
            period = "AM"
        if float(self.arriveSecond) <10.0:
            self.arriveSecond = "0"+str(self.arriveSecond)
        if float(self.arriveMinute) <10.0:
            self.arriveMinute = "0"+str(self.arriveMinute)
        self.arrivalTime = str(self.arriveHour) + ':' + str(self.arriveMinute) + ':' + str(self.arriveSecond) + period
        self.arriveHour = self.nowArrival.hour
        self.arriveMinute = self.nowArrival.minute
        self.arriveSecond = self.nowArrival.second

        #elapsed time
        
        self.elapsedHour = self.arriveHour - self.departHour
        self.elapsedMinute = self.arriveMinute - self.departMinute
        self.elapsedSecond = self.arriveSecond - self.departSecond

        
        
        if float(self.elapsedSecond) <10.0:
            if float(self.elapsedSecond) <0.0:
                self.elapsedSecond = self.elapsedSecond + 60
                self.elapsedMinute = self.elapsedMinute - 1
            else:
                self.elapsedSecond = "0"+str(self.elapsedSecond)
            
                
        if float(self.elapsedMinute) <10.0:
            if float(self.elapsedMinute) <0.0:
                self.elapsedMinute = self.elapsedMinute + 60
            else:
                self.elapsedMinute = "0"+str(self.elapsedMinute)
        
        self.elapsedTime = str(self.elapsedHour) + ':' + str(self.elapsedMinute) + ':' + str(self.elapsedSecond)
        print (self.elapsedTime)

        #distance
        self.distanceString = str(self.distance)+"KM"

        #tripSummaryTitle = Label(driverDetailsTitleFrame,text="Trip Summary",bg="white",font=("Verdana", 14))
        
        tripSummaryTitle = Label(driverDetailsTitleFrame,text="Trip Summary",bg="white",font=("Verdana", 14))
        chosenPickUpPointTitle = Label(chosenDriverInfoGrid,text= "Pickup point:",bg="white")
        chosenPickUpPoint = Label(chosenDriverInfoGrid,text= self.pickUpLoc,bg="white")
        chosenDropOffPointTitle = Label(chosenDriverInfoGrid,text= "Drop-off point:",bg="white")
        chosenDropOffPoint = Label(chosenDriverInfoGrid,text= self.dropOffLoc,bg="white")
        driverNameTitle = Label(chosenDriverInfoGrid,text= "Driver's name:",bg="white")
        driverName = Label(chosenDriverInfoGrid,text= self.driverDict[self.driverIndex][2],bg="white" )
        driverRatingTitle = Label(chosenDriverInfoGrid,text= "Driver rating:",bg="white")
        driverRatingLabel = Label(chosenDriverInfoGrid,text= self.driverRating,bg="white")
        departureTimeTitle = Label(chosenDriverInfoGrid,text= "Departure time:",bg="white")
        departureTimeLabel = Label(chosenDriverInfoGrid,text= self.departureTime,bg="white")
        arrivalTimeTitle = Label(chosenDriverInfoGrid,text= "Arrival time:",bg="white")
        arrivalTimeLabel = Label(chosenDriverInfoGrid,text= self.arrivalTime,bg="white")
        elapsedTimeTitle = Label(chosenDriverInfoGrid,text= "Elapsed time:",bg="white")
        elapsedTimeLabel = Label(chosenDriverInfoGrid,text= self.elapsedTime,bg="white")
        distanceTitle = Label(chosenDriverInfoGrid,text= "Distance:",bg="white")
        distanceLabel = Label(chosenDriverInfoGrid,text= self.distanceString ,bg="white")
        #Dropdown list for pickup selection
        self.ratingSelection = StringVar(root) # Create a Tkinter variable
        self.ratingSelection.set("---Please rate your driver---") # set the default option
        ratingSelectionMenu = OptionMenu(chosenDriverInfoGrid, self.ratingSelection, *self.starBox)
        # on change dropdown value
        def change_dropdown(*args):
                stars = self.ratingSelection.get()
                print stars
                if stars == "*":
                    self.newRating = 1
                elif stars == "* *":
                    self.newRating = 2
                elif stars == "* * *":
                    self.newRating = 3
                elif stars == "* * * *":
                    self.newRating = 4
                elif stars == "* * * * *":
                    self.newRating = 5
                else:
                    self.newRating = 0
                print self.newRating
        # link function to change dropdown
        self.ratingSelection.trace('w', change_dropdown)
        stars = str(self.ratingSelection.get())
                
        #print self.newRating

        submitRatingButton = Button(root, text="Submit", command=lambda: self.submitRating())
        
        encapsulateFrame.pack()
        driverDetailsTitleFrame.pack()
        tripSummaryTitle.pack()
        chosenDriverInfoFrame.pack()
        chosenDriverInfoGrid.grid()
        #driverDetailsTitle.pack()
        chosenPickUpPointTitle.grid(row = 0,column = 0, sticky=E)
        chosenPickUpPoint.grid(row = 0,column = 1, sticky=W)
        chosenDropOffPointTitle.grid(row = 1,column = 0, sticky=E)
        chosenDropOffPoint.grid(row=1,column= 1, sticky=W)
        departureTimeTitle.grid(row = 2,column = 0, sticky=E)
        departureTimeLabel.grid(row = 2,column = 1, sticky=W)
        arrivalTimeTitle.grid(row = 3,column = 0, sticky=E)
        arrivalTimeLabel.grid(row = 3,column = 1, sticky=W)
        elapsedTimeTitle.grid(row = 4,column = 0, sticky=E)
        elapsedTimeLabel.grid(row = 4,column = 1, sticky=W)
        distanceTitle.grid(row = 5,column = 0, sticky=E)
        distanceLabel.grid(row = 5,column = 1, sticky=W)
        driverNameTitle.grid(row = 6,column = 0, sticky=E)
        driverName.grid(row =6,column=1, sticky=W)
        driverRatingTitle.grid(row = 7,column = 0, sticky=E)
        ratingSelectionMenu.grid(row=7,column=1)
        #driverRatingLabel.grid(row = 7,column = 1, sticky=W)
        #departureTimeTitle.grid(row = 6,column = 0, sticky=E)
        #departureTimeLabel.grid(row = 6,column = 1, sticky=W)
        submitRatingButton.pack(pady=5)
       
        
        print "Driver index is "+str(self.driverIndex)

    def submitRating(self):
        global driversPaymentButton
        global bookAnotherRideButton
        if self.newRating ==0:
            tkMessageBox.showinfo("Alert", "Please select a rating for the driver.")
        else:
            f = open('rating.txt', 'r+')
            line = f.readlines()
            #print self.newRating
            averageRating = ((float(line[2 * self.driverIndex])) *(self.totalTrip - 1)*1.0 + self.newRating) / (self.totalTrip)
            #print "average rating is " +str(averageRating)
            #print "Line "+str(2* self.driverIndex)
            line[2 * self.driverIndex] = str(averageRating) + "\n"
            f.close()
            f = open('rating.txt', 'w')
            for z in line:
                f.write(z)
            submitRatingButton.pack_forget()
            bookAnotherRideButton = Button(root, text="Book another ride?", command = lambda: self.goToBookingPage())
            bookAnotherRideButton.pack()
            driversPaymentButton = Button(root, text="Driver's payment", command=lambda: self.Payment())   
            driversPaymentButton.pack()

    def goToBookingPage(self):
        encapsulateFrame.pack_forget()
        driverDetailsTitleFrame.pack_forget()
        tripSummaryTitle.pack_forget()
        chosenDriverInfoFrame.pack_forget()
        chosenDriverInfoGrid.grid_forget()
        chosenPickUpPointTitle.grid_forget()
        chosenPickUpPoint.grid_forget()
        chosenDropOffPointTitle.grid_forget()
        chosenDropOffPoint.grid_forget()
        departureTimeTitle.grid_forget()
        departureTimeLabel.grid_forget()
        arrivalTimeTitle.grid_forget()
        arrivalTimeLabel.grid_forget()
        elapsedTimeTitle.grid_forget()
        elapsedTimeLabel.grid_forget()
        distanceTitle.grid_forget()
        distanceLabel.grid_forget()
        driverNameTitle.grid_forget()
        driverName.grid_forget()
        driverRatingTitle.grid_forget()
        ratingSelectionMenu.grid_forget()
        driversPaymentButton.pack_forget()
        bookAnotherRideButton.pack_forget()
        self.locationSelectionPage()


    def Payment(self):
        global driverSummaryTitle
        global driverSummaryNoOfPassengers
        global driverSummaryDistance
        global driverSummaryRating
        global driverSummaryGoHome
        global driverSalary
        global driverSalaryInfoMsg
        global averageRatingLabel
        global noOfTripsLabel

        encapsulateFrame.pack_forget()
        driverDetailsTitleFrame.pack_forget()
        tripSummaryTitle.pack_forget()
        chosenDriverInfoFrame.pack_forget()
        chosenDriverInfoGrid.grid_forget()
        chosenPickUpPointTitle.grid_forget()
        chosenPickUpPoint.grid_forget()
        chosenDropOffPointTitle.grid_forget()
        chosenDropOffPoint.grid_forget()
        departureTimeTitle.grid_forget()
        departureTimeLabel.grid_forget()
        arrivalTimeTitle.grid_forget()
        arrivalTimeLabel.grid_forget()
        elapsedTimeTitle.grid_forget()
        elapsedTimeLabel.grid_forget()
        distanceTitle.grid_forget()
        distanceLabel.grid_forget()
        driverNameTitle.grid_forget()
        driverName.grid_forget()
        driverRatingTitle.grid_forget()
        ratingSelectionMenu.grid_forget()
        driversPaymentButton.pack_forget()
        mainTitle.pack_forget()
        mainTitleFrame.pack_forget()
        emptyFrame.pack_forget()
        subtitleFrame.pack_forget()
        bookAnotherRideButton.pack_forget()

        a = 'The number of passenger is '
        b = 'The distance travelled for this ride is '
        c = 'Your rating for this ride is '

        NumPassenger = a + str(self.noOfPassenger)
        DISTANCE = b + str(self.distance)
        RATING = c + str(self.newRating)

        f = open('rating.txt', 'r+')
        line = f.readlines()
        averageRatingString = "Your average rating is "+str(line[2 * self.driverIndex])
        noOfTripsString = "Your total number of trips are "+str(line[2 * self.driverIndex + 1])
        
        
        driverSummaryTitle = Label(root, text = 'This is your info for 1 ride', bg="white", font=("Verdana", 15))
        driverSummaryTitle.pack(pady=20)
        driverSummaryNoOfPassengers = Label(root, text = NumPassenger)
        driverSummaryNoOfPassengers.pack()
        driverSummaryDistance = Label(root, text = DISTANCE)
        driverSummaryDistance.pack()
        driverSummaryRating = Label(root, text = RATING)
        driverSummaryRating.pack()
        averageRatingLabel = Label(root, text = averageRatingString)
        averageRatingLabel.pack()
        noOfTripsLabel = Label(root, text = noOfTripsString)
        noOfTripsLabel.pack()
        a = 'The number of passenger is '
        b = 'The distance travelled for this ride is '
        c = 'Your rating for this ride is '

        NumPassenger = a + str(self.noOfPassenger)
        DISTANCE = b + str(self.distance)
        RATING = c + str(self.newRating)
        
        percentage = (self.newRating/5)*100.0
        Total_salary = self.distance*(self.noOfPassenger)+self.distance*percentage   

        SALARY = 'Your total salary is RM' + str(Total_salary)

        driverSalary = Label(root, text = SALARY)
        driverSalary.pack()
        driverSalaryInfoMsg = Label(root,text='Your salary will be sent to your account in two or three working days')
        driverSalaryInfoMsg.pack()
        driverSummaryGoHome = Button(root, text="Go Home", command= lambda: self.gotoMainPage("Payment"))
        driverSummaryGoHome.pack()

        
        
   

    
    
        
                                
        
PIME_APP = PIME_UI(root)
root.mainloop()
