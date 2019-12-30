/**********|**********|**********| 
Program: Inventory.cpp /StoreItem.cpp /StoreItem.h 
Course: Programming Fundamentals 
Year: 2017/2018 Trimester 2 
Name: Nicholas Toh Zhern Hau 
ID: 1161104589	 
Lecture Section: TC01 
Tutorial Section: TT01 
Email: tohnic1999@gmail.com 
Phone: 019-6606712
*********|**********|**********/
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <iomanip>
#include <cstdlib>	//stoi (older version)
#include <cstdio>	//delete, rename
#include <cstring>	
#include <math.h>	//round
#include <utility>	//iter_swap
#include "StoreItem.h"
using namespace std;

const int SIZE = 14; //The size of one record [key, recordName, values (2-13)]



int initFile (string filename); //Creates file if it doesn't exist yet
int readFile (string filename, vector<string>& storage); //Reads file and stores in a designated storage
int readFile (string filename, vector<StoreItem>& storage); //Reads file and stores in a designated storage
int deleteFile (const char * filename);	//Deletes file, used in write file
int writeFile (string filename, vector <string> storage);	//Writes into a file from a storage
int writeFile (string filename, vector <StoreItem> storage); //Writes into a file from a storage

int getInput(int lowerLimit, int upperLimit, string optionalText = "", string errorText = "");	//Used for conditioned int inputs
int getInput(vector <int> inputVector, int exitCondition, string optionalText = "", string errorText = "");	//Used for conditioned int inputs
char getInput(char lowerLimit, char upperLimit, string optionalText = "", string errorText= "");	//Used for conditioned char inputs
double getInput(double lowerLimit, double upperLimit, string optionalText = "", string errorText= "");	//Used for conditioned double inputs
string getStrInput(string optionalText = ""); //Used to get string inputs

void createRecord (string & name, vector <string> nameList, vector <string> & valueList); //Gets temporary record from user
void updateField (vector <string> nameList, StoreItem & record); //Updates a field
void deleteField (vector <string> nameList, StoreItem & record); //Deletes a field
int assignKey (vector <int> & keys);	//Assigns a unique key from the keys vector
int validateKey (int inputKey, vector <int> keys);	//Checks if a key is valid
int deleteKey (int inputKey, vector <int> & keys); 	//Deletes a key
int keyToRecord (int inputKey, vector <StoreItem> masterRecord); //Exchanges the key for the location of the record in the master record
void updateKeys (vector <int> & keys, vector <StoreItem> masterRecord); //Updates the keys vector after a record is sorted
vector <int> fullStringSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, string searchString); //Searches for a record that contains the string in a specified data field
vector <int> subStringSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, string searchString); //Searches for a record that contains the substring in a specified data field
vector <int> compareGreaterNumSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, int num);	//Searches for a record that has a number greater than num in a specified data field
vector <int> compareSmallerNumSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, int num);	//Searches for a record that has a number smaller than num in a specified data field
void ascendSortRecord (int DFKey, vector <StoreItem> & masterRecord);	//Sorts records in ascending order
void descendingSortRecord (int DFKey, vector <StoreItem> & masterRecord); //Sorts records in descending order
void ascendSortRecordInt (int DFKey, vector <StoreItem> & masterRecord);	//Sorts records in ascending order based on int
void descendingSortRecordInt (int DFKey, vector <StoreItem> & masterRecord); //Sorts records in descending order based on int
void ascendSortRecordDouble (int DFKey, vector <StoreItem> & masterRecord);	//Sorts records in ascending order based on double
void descendingSortRecordDouble (int DFKey, vector <StoreItem> & masterRecord); //Sorts records in descending order based on double
void ascendSortRecordStr (int DFKey, vector <StoreItem> & masterRecord);	//Sorts records in ascending order based on strings
void descendingSortRecordStr (int DFKey, vector <StoreItem> & masterRecord); //Sorts records in descending order based on strings
void ascKeySortRecord (vector <StoreItem> & masterRecord);	//Sorts based on keys in ascending order
void dscKeySortRecord (vector <StoreItem> & masterRecord);	//Sorts based on keys in descending order
 

int displayMainMenu(vector <StoreItem> masterRecord); //Main Menu
int displayShowRecordMenu (vector <StoreItem> masterRecord); //Show Record Menu
void searchByRecord (vector <string> nameList, vector <int> keys, vector <StoreItem> masterRecord); //Show data fields of individual records
vector <int> searchByDataField (vector <string> nameList, vector <int> keys, vector <StoreItem> masterRecord); //Show records based on data field
int editRecordMenu (vector <string> nameList, StoreItem record);	//Edit Record Menu
int displaySortRecordMenu (vector <string> nameList, vector <StoreItem> masterRecord);	//Sort Record Menu

void displayArray(vector <int> storage, string name); 	//DEBUGGING
void displayArray(vector <unsigned int> storage, string name); 	//DEBUGGING
void displayArray(vector <string> storage, string name); //DEBUGGING

void displayBlankRecord(vector <string> nameList);	//Displays a blank record
void displayRecord(vector <string> nameList, StoreItem record); //Displays a record
void displayRecord(vector <string> nameList, StoreItem record, vector <bool> displayDataField); //Displays a record with visibility settings
void displayRecord(vector <string> nameList, vector <string> valueList); //Displays a pseudo record using an external array
void displayAvailableRecords(vector <StoreItem> masterRecord);	//Displays all available records
void displayAvailableRecords(vector <StoreItem> masterRecord, vector <int> selectedKeys);	//Displays selected available records

int main ()
{      
	
	const vector <string> nameList = {"Item Id", "Item Name", "Item Description", "Category", "Manufacturer", "Selling Price", "Cost Price", "Units in Store", "Units Sold", "Year of Date First Introduced", "Month of Date First Introduced", "Day of Date First Introduced"}; //names of data fields are constant
	
	vector <StoreItem> masterRecord; //Master record that holds all of the records
	vector <int> keys; //Holds all of the keys in the Master record
	vector <string> valueList (SIZE-2, " "); //temporary storage
	string databaseName = "database.txt";
	int menuChoice;
	bool mainMenuLoop = true, updateRecordLoop = true;
	
	
	//Create database
	initFile(databaseName);
	
	//Read from database
	readFile(databaseName, masterRecord);
	
	//Create records from database
	
	string tempRecordName;
	int tempKey;
	/*
	for (int i = 0; i < masterArray.size(); i += SIZE)
	{

		for (int j = 0; j < SIZE; j++)
		{
			if (j % SIZE == 0) 
			{
				tempKey = stoi(masterArray[j+i]);
			}
			else if (j % SIZE == 1)
			{
				tempRecordName = masterArray[j+i];
			}
			else
			{
				valueList[j-2] = masterArray[j+i];
			}

		}
			
		StoreItem record(tempRecordName, tempKey, valueList);
		masterRecord.push_back(record);
	}
	
	fill(valueList.begin(), valueList.end(), " "); //reset valueList
	*/
	//Gather all keys in one array
	for (int i = 0; i < masterRecord.size(); i++) {
		keys.push_back((masterRecord[i]).getKey());
	}
	
	//Display main menu
	while (mainMenuLoop == true) {
		int mainMenuChoice = displayMainMenu(masterRecord);
		switch (mainMenuChoice) 
		{
			case 1:		//Create record
			{
				createRecord(tempRecordName, nameList, valueList);
				tempKey =  assignKey(keys);	
				StoreItem record(tempRecordName, tempKey, valueList);
				masterRecord.push_back(record);

				updateKeys(keys, masterRecord);
				writeFile(databaseName, masterRecord);
				break;
				
			}
			
			case 2:		//Show record
			{
				
					displayBlankRecord(nameList);
					vector <int> selectedKeys = searchByDataField(nameList, keys, masterRecord);
					displayAvailableRecords(masterRecord, selectedKeys);
					searchByRecord(nameList, selectedKeys, masterRecord);
				/*
				bool showRecordLoop = true;
				while (showRecordLoop == true)
				{
					int showRecordMenuChoice = displayShowRecordMenu(masterRecord);
					displayBlankRecord(nameList);
					switch (showRecordMenuChoice)
					{
						case 1: //Search by record
						{
							searchByRecord (nameList, keys, masterRecord);
							break;
						}
						
						case 2:	//Search by data field
						{
							vector <int> selectedKeys = searchByDataField(nameList, masterRecord);
							
							displayAvailableRecords(masterRecord, selectedKeys);
							
							cout << "*****************" << endl;							
							break;
						
						}	
						
						case 3:	//Exit menu
						{
							showRecordLoop = false;
							break;
						}
						default:
						{
							cout << "Something went wrong with showRecordMenuChoice  ---> " << showRecordMenuChoice << endl;
							break;
						}
					}
					
				}
				*/
				break;
			}
			
			case 3:	//Edit a record
			{
				int tempKey = getInput(keys, 0, "Please enter the record you wish to edit ('0' to exit): ", "Invalid key, please try again: ");
				if (tempKey != 0)
				{
					int key = keyToRecord(tempKey, masterRecord);
					
					int choice = editRecordMenu(nameList, masterRecord[key]);
					switch (choice)
					{
						case 1: //Update field
						{
							updateField(nameList, masterRecord[key]);
							break;
						}
						case 2: //Delete field
						{
							deleteField(nameList, masterRecord[key]);
							break;
						}
					}		
					break;
				}
				
				else
				{
					break;
				}
			}
			case 4:		//Delete record
			{
				displayAvailableRecords(masterRecord);
				int tempKey = getInput(keys, 0, "Please enter the record you wish to delete ('0' to exit): ", "Invalid key, please try again: ");
				if (tempKey != 0)
				{
					int key = keyToRecord(tempKey, masterRecord);
					deleteKey(tempKey, keys);
					masterRecord.erase(masterRecord.begin()+key);
					
					break;
				}
				
				else
				{
					break;
				}
			}
			case 5:		//Sort records
			{
				int choice = displaySortRecordMenu(nameList, masterRecord);
				switch (choice)
				{
					case 1:	//Sort alphabetically
					{
						int tempKey = getInput(0, 12, "Please enter the field that you wish to sort ('0' to exit): ", "Invalid key. Please try again: ");
						int DFKey = tempKey - 1;
						int choice = getInput(1, 2, "1 - Ascending, 2 - Descending: ", "Invalid input. Please try again: ");
						switch (choice)
						{
							case 0: //Exit
							{
								break;
							}
							
							case 1:	//Ascending
							{
								switch (tempKey)
								{
									case 0:
									{
										break;
									}	
									case 1:
									case 2:
									case 3:
									case 4:
									case 5:
									{
										ascendSortRecordStr(DFKey, masterRecord);
										break;
									}
									case 6:
									case 7:
									{
										ascendSortRecordDouble(DFKey, masterRecord);
										break;
									}
									case 8:
									case 9:
									case 10:
									case 11:
									case 12:
									{	
										ascendSortRecordInt(DFKey, masterRecord);
										break;
									}
								}
								break;
							}
							case 2:	//Descending
							{
								switch (tempKey)
								{
									case 0:
									{
										break;
									}	
									case 1:
									case 2:
									case 3:
									case 4:
									case 5:
									{
										descendingSortRecordStr(DFKey, masterRecord);
										break;
									}
									case 6:
									case 7:
									{
										descendingSortRecordDouble(DFKey, masterRecord);
										break;
									}
									case 8:
									case 9:
									case 10:
									case 11:
									case 12:
									{	
										descendingSortRecordInt(DFKey, masterRecord);
										break;
									}
								}
								break;
							}
						}
						break;
					}
					
					case 2: //Sort based on key
					{
						int choice = getInput(1, 2, "1 - Ascending, 2 - Descending: ", "Invalid input. Please try again: ");
						switch (choice)
						{
							case 1:	//Ascending
							{
								ascKeySortRecord(masterRecord);
								break;
							}
							case 2:	//Descending
							{
								dscKeySortRecord(masterRecord);
								break;
							}
						}
						break;
					}
				}
				break;
			}
			case 6: 	//Exit program
			{
				mainMenuLoop = false;
				break;
			}
			default:
			{
				cout << "Something went wrong. Shutting down...";
				return 0;
			}
		}
		fill(valueList.begin(), valueList.end(), " ");
	}
	//DEBUGGING
	//displayArray (masterArray, "masterArray");

	//cout << endl;
	
	//displayArray (keys, "keys");
	
	writeFile(databaseName, masterRecord);
	
	return 0;
}



/*****************************************************
			FILE I/O FUNCTIONS
******************************************************/
int initFile (string filename) //Creates file if none exists
{ 
	fstream f;
    f.open(filename);
    if (!f.is_open()) 
	{
        f.open(filename, std::ios::out|std::ios::in|std::ios::trunc);
		if (!f.is_open())
		{
			cout << "Error creating file " << filename << "!" << endl;
			f.close(); 
			return 1;
		}
	}
	return 0;
}

int readFile(string filename, vector<string>& storage)	//Reads file and stores in a designated storage
{
	string temp;
	int pos = 0;
	
	fstream f;
	f.open(filename);
    if (!f.is_open())
	{
		cout << "Error reading file " << filename << "!" << endl;
		f.close(); 
		return 1;
	}
	else 
	{
		while ( getline(f, temp) )
		{
			if (pos == storage.size())
			{
				storage.push_back(temp);
			}
			else
			{
				storage[pos] = temp;
			}
			pos++;
		}
		f.close(); 	
	}
	return 0;
}

int readFile(string filename, vector<StoreItem>& storage)	//Reads file and stores in a designated storage
{
	string temp;
	int pos = 0;
	vector <string> tempStorage (SIZE-2, " ");
	
	fstream f;
	f.open(filename);
    if (!f.is_open())
	{
		cout << "Error reading file " << filename << "!" << endl;
		f.close(); 
		return 1;
	}
	else 
	{
		string tempRecordName;
		int tempKey;
		while ( getline(f, temp) )	//Reads the exact amount of data to create a StoreItem object each cycle
		{
			if (pos == 0)
			{
				tempKey = stoi(temp);
			}
			
			else if (pos == 1)
			{
				tempRecordName = temp;
			}
			
			else
			{
				tempStorage[pos-2] = temp;
				
				if (pos == SIZE-1)
				{
					StoreItem record (tempRecordName, tempKey, tempStorage);
					storage.push_back(record);
					pos = -1;
				}
			}
			pos++;
		}
		f.close(); 	
	}
	return 0;
}

int deleteFile (const char * filename)	//Deletes file
{
	remove(filename);
	fstream f;
	f.open(filename);
    if (f.is_open())
	{
		cout << "Error deleting file " << filename << "!" << endl;
		f.close(); 
		return 1;
	}
	return 0;
}

int writeFile (string filename, vector <string> storage) //Writes into a file from a storage
{
	fstream f;
	f.open(filename);
    if (!f.is_open())
	{
		cout << "Error writing to file " << filename << "!" << endl;
		f.close(); 
		return 1;
	}
	f.close();
	
	if (initFile("temp.txt"))
	{ 
		cout << "Error: temp file failed to be created while writing" << endl;
		return 2;
	}
	
	f.open ("temp.txt");
	for (int i = 0; i < storage.size(); i++)
	{
		f << storage[i] << endl;
	}
	f.close();
	
	remove(filename.c_str());
	rename("temp.txt", filename.c_str());
	return 0;
}

int writeFile (string filename, vector <StoreItem> storage) //Writes into a file from a storage
{
	fstream f;
	f.open(filename);
    if (!f.is_open())
	{
		cout << "Error writing to file " << filename << "!" << endl;
		f.close(); 
		return 1;
	}
	f.close();
	
	if (initFile("temp.txt"))
	{ 
		cout << "Error: temp file failed to be created while writing" << endl;
		return 2;
	}
	
	f.open ("temp.txt");
	for (int i = 0; i < storage.size(); i++)
	{
		f << storage[i].getKey() << endl << storage[i].getRecordName() << endl;	//Write each record into the file
		for (int j = 0; j < (storage[i].getRecord()).size(); j++)
		{
			f << (storage[i].getRecord())[j] << endl;
		}
	}
	f.close();
	
	remove(filename.c_str());
	rename("temp.txt", filename.c_str());
	return 0;
}
/*****************************************************
			MENU FUNCTIONS
******************************************************/
void displayAvailableRecords(vector <StoreItem> masterRecord)	//Displays all record names and their keys in the Master Record
{
	if (masterRecord.size() != 0)
	{
		cout << endl;
		cout << "Available records\n";
		cout << "*****************\n\n";
		cout << setw(8) << left << "Key";
		cout << setw(40) << left << "Record" << endl;
		
		for (int i = 0; i < masterRecord.size(); i++)
		{
			cout << setw(8) << left << masterRecord[i].getKey(); //print key
			cout << setw(40) << left << masterRecord[i].getRecordName() << endl; //print name
		}
	}
	else
	{
		cout << left << "No records." << endl;
	}
}

void displayAvailableRecords(vector <StoreItem> masterRecord, vector <int> selectedKeys) //Displays specific record names and their keys in the Master Record
{
	if (masterRecord.size() != 0 && selectedKeys.size() != 0)
	{
		cout << endl;
		cout << "Available records\n";
		cout << "*****************\n\n";
		cout << setw(8) << left << "Key";
		cout << setw(40) << left << "Record" << endl;
		
		for (int i = 0; i < masterRecord.size(); i++)
		{
			int tempKey = masterRecord[i].getKey();
			string tempName = masterRecord[i].getRecordName();
			if (!validateKey(tempKey, selectedKeys))
			{
				cout << setw(8) << left << tempKey; //print key
				cout << setw(40) << left << tempName << endl; //print name
			}
		}
	}
	else
	{
		cout << left << "No records." << endl;
	}
}

int displayMainMenu(vector <StoreItem> masterRecord)	//Displays main menu
{
	int userInput;
	int tempKey;
	string tempName;
	cout << "Welcome to the Record Setter! We take, update and delete records!\n";
	
	displayAvailableRecords(masterRecord);
	
	cout << "\nNote: If you quit the program without exiting through this menu, all changes will not be saved.\n";
	cout << "\n1 - Create a new record\n2 - Display record\n3 - Edit an existing record\n4 - Delete an existing record\n5 - Sort records\n6 - Exit the program\n";
	cout << "Choice: ";
	
	userInput = getInput(1, 6, "", "Please enter a number between 1 - 6: ");
	
	return userInput;
}

int displayShowRecordMenu (vector <StoreItem> masterRecord)	//Displays show record menu
{
	int userInput;
	int tempKey;
	string tempName;
	
	cout << endl << endl;

	displayAvailableRecords(masterRecord);
	
	cout << "\n1 - Search for a record\n2 - Search by data field\n3 - Exit the current menu\n";
	cout << "Choice: ";
	userInput = getInput (1, 3, "", "Please enter a number between 1 - 3: ");
	return userInput;
}

int displayShowRecordMenu (vector <StoreItem> masterRecord, vector <int> selectedKeys)	//Displays show record menu
{
	int userInput;
	int tempKey;
	string tempName;
	
	cout << endl << endl;

	displayAvailableRecords(masterRecord, selectedKeys);
	
	userInput = getInput(selectedKeys, 0, "", "Please enter the data field you wsh to display: ");
	return userInput;
}

void searchByRecord (vector <string> nameList, vector <int> keys, vector <StoreItem> masterRecord) //Show data fields of individual records
{
	int tempKey = -1;
	int DFKey = -1;
	
	vector <bool> displayDataField (SIZE-2, false);	//Holds the visibility of each data field
	vector <int> displayRecordKeys; //Holds the visibility (keys) of each record
	
	do
	{
		cout << "Please enter the key of the record to be displayed ('0' to exit, '-1' to display all records): ";
		while(!(cin >> tempKey))
		{
			cin.clear();
			cin.ignore(99, '\n');
			cout << "Only posititve integers are allowed: ";
		}
		while (validateKey(tempKey, keys) && tempKey != -1 && tempKey != 0)
		{
			cout << "Invalid key. Please try again: ";
			while(!(cin >> tempKey))
			{
				cin.clear();
				cin.ignore(99, '\n');
				cout << "Only posititve integers are allowed: ";
			}
		}
		cin.ignore();
		
		if (tempKey != -1 && tempKey != 0)
		{
			if (validateKey(tempKey, displayRecordKeys))
			{
				displayRecordKeys.push_back(tempKey);
			}
		}
		
		else if(tempKey == -1)
		{
			displayRecordKeys = keys;
		}
			
		//cout << displayRecordKeys.size() << " " << keys.size() << endl;
	} while (tempKey != 0 && tempKey != -1 && displayRecordKeys.size() != keys.size());
	
	if (displayRecordKeys.size() != 0)
	{
		do 
		{
			tempKey = getInput(0, 14, "Please enter the key of the field that you wish to display (0 to exit, 13 to display everything, 14 to reset): ", "Invalid key. Please try again (0 to exit, 13 to display everything, 14 to reset): ");
			DFKey = tempKey - 1;
			while (tempKey != 0)
			{
				if (tempKey == 13) //Displays all data fields
				{
					fill(displayDataField.begin(), displayDataField.end(), true);	
					break;
				}
				else if (tempKey == 14) //Displays no data fields
				{
					fill(displayDataField.begin(), displayDataField.end(), false);
				}
				else
				{
					displayDataField[DFKey] = true;	//Enables visibility on that data field
				}
				tempKey = getInput(0, 14, "Please enter the key of the field that you wish to display (0 to exit, 13 to display everything, 14 to reset): ", "Invalid key. Please try again (0 to exit, 13 to display everything, 14 to reset): ");
				DFKey = tempKey - 1;
			}
			
		} while (tempKey != 0 && tempKey != 13 && tempKey != 14);

		
		
		for (int i = 0; i < displayRecordKeys.size(); i++)
		{
			int key = keyToRecord(displayRecordKeys[i], masterRecord); //Convert key to position of the record in the masterRecord
			displayRecord(nameList, masterRecord[key], displayDataField);
		}
		
		string dummyValue;
		cout << "Press enter to continue: ";
		getline(cin, dummyValue);
	}
	/*
	if (tempKey != -1 && tempKey != 0)
	{
		while (validateKey(tempKey, keys) && tempKey != -1 && tempKey != 0)
		{
			cout << "Invalid key. Please try again: ";
			cin >> tempKey;
		}
	
		int key = keyToRecord(tempKey, masterRecord); //Convert key to position of the record in the masterRecord
		
		int tempKey = getInput(0, 14, "Please enter the key of the field that you wish to display (0 to exit, 13 to display everything, 14 to reset): ", "Invalid key. Please try again (0 to exit, 13 to display everything, 14 to reset): ");
		
		int DFKey = tempKey -1;
		
		while (tempKey != 0)
		{
			if (tempKey == 13) //Displays all data fields
			{
				fill(displayDataField.begin(), displayDataField.end(), true);	
				break;
			}
			else if (tempKey == 14) //Displays no data fields
			{
				fill(displayDataField.begin(), displayDataField.end(), false);
			}
			else
			{
				displayDataField[DFKey] = true;	//Enables visibility on that data field
			}
			tempKey = getInput(0, 14, "Please enter the key of the field that you wish to display (0 to exit, 13 to display everything, 14 to reset): ");
			DFKey = tempKey - 1;
		}
		displayRecord(nameList, masterRecord[key], displayDataField);
	}
	
	*/
}

vector <int> searchByDataField (vector <string> nameList, vector <int> keys, vector <StoreItem> masterRecord)	//Show records based on data field
{
	vector <int> selectedKeys = keys;
	int tempKey = -1;
	int counter = 0;
	int DFKey;
	
	while (tempKey != 0 && counter < 3)	//Only 3 conditions can be stacked
	{
		tempKey = getInput(0, 12, "Please enter the key of the field that you wish to search ('0' to exit): ", "Invalid key. Please try again: ");
		
		if (tempKey != 0)
		{
			counter++;
			DFKey = tempKey - 1;
			string userInput = getStrInput("Please enter the value that you wish to search (Enter '>' or '<' followed by numbers for comparison): ");
			char * cstruserInput = new char [userInput.length()+1];	//Create new pointer that points to an array of char
			strcpy (cstruserInput, userInput.c_str());	//Copy userinput to that new array of char
			
			if (cstruserInput[0] != '>' && cstruserInput[0] != '<')	//Not a numeric comparison search
			{
				int choice = getInput(1, 2, "Please enter 1 for full string search or 2 for sub-string search: ", "Please enter 1 for full string search or 2 for sub-string search: ");
				
				if (choice == 1)	//Full string search
				{
					selectedKeys = fullStringSearch(masterRecord, selectedKeys, DFKey, userInput);
				}
				
				else if (choice == 2)	//Sub string search
				{
					selectedKeys = subStringSearch(masterRecord, selectedKeys, DFKey, userInput);
				}	
			}
				
			else if (cstruserInput[0] == '>')	//Comparison search
			{
				if (DFKey < 5)	//not a numeric field
				{
					cout << "Not a numeric field" << endl << endl;
					counter--;	//Not a condition
				}
				
				else
				{
					userInput.erase(userInput.begin());	//removes '>' from userInput so that it can be used to search
					if (userInput != "")
					{
						int intUserInput = stoi(userInput); //Data field MUST be convertible to an integral type
						selectedKeys = compareGreaterNumSearch(masterRecord, selectedKeys, DFKey, intUserInput);
					}
					else
					{
						cout << "Invalid search condition" << endl;
						counter --;
					}
				}
			}
			
			else if (cstruserInput[0] == '<')
			{
				if (DFKey < 5)	//not a numeric field
				{
					cout << "Not a numeric field" << endl << endl;
					counter--;
				}
				
				else
				{
				userInput.erase(userInput.begin()); //removes '>' from userInput so that it can be used to search
				if (userInput != "")
					{
						int intUserInput = stoi(userInput); //Data field MUST be convertible to an integral type
						selectedKeys = compareSmallerNumSearch(masterRecord, selectedKeys, DFKey, intUserInput);
					}
					else
					{
						cout << "Invalid search condition" << endl;
						counter --;
					}
				}
			}
		}
	}
	return selectedKeys;
}

int editRecordMenu (vector <string> nameList, StoreItem record)	//Edit Record Menu
{
	displayRecord(nameList, record);
	cout  << "\n1 - Update a field\n2 - Delete a field\n";
	int choice = getInput(1, 2, "Choice: ", "Invalid choice. Please try again (1 - Update a field, 2 - Delete a field): ");
	return choice;
}

int displaySortRecordMenu (vector <string> nameList, vector <StoreItem> masterRecord)	//Sort Record Menu
{
	displayAvailableRecords(masterRecord);
	cout  << "\n1 - Sort by data field\n2 - Sort by key\n";
	int choice = getInput(1, 2, "Choice: ", "Invalid choice. Please try again (1 - Sort by data field, 3 - Sort by key): ");
	return choice;
}

/*****************************************************
			INPUT VALIDATION FUNCTIONS
******************************************************/

char getInput(char lowerLimit, char upperLimit, string optionalText, string errorText)	//used for conditioned char inputs
{
	char input;
	cout << optionalText;
	while(!(cin >> input))
	{
		cin.clear();
		cin.ignore(99, '\n');
		cout << "Only posititve integers are allowed: ";
	}
	while (input < lowerLimit || input > upperLimit && input != '0') //'0' will be an exit condition
	{	
		cout << errorText;
		while(!(cin >> input))
		{
			cin.clear();
			cin.ignore(99, '\n');
			cout << "Only posititve integers are allowed: ";
		}
	}
	cin.ignore();
	return input;
}

int getInput(int lowerLimit, int upperLimit, string optionalText, string errorText)	//used for conditioned int inputs
{
	int input;
	cout << optionalText;
	while(!(cin >> input))
	{
		cin.clear();
		cin.ignore(99, '\n');
		cout << "Only posititve integers are allowed: ";
	}
	while (input < lowerLimit || input > upperLimit && input != 0)	//'0' will be an exit condition
	{
		cout << errorText;
		while(!(cin >> input))
		{
			cin.clear();
			cin.ignore(99, '\n');
			cout << "Only posititve integers are allowed: ";
		}
	}
	cin.ignore(99, '\n');
	return input;
}

int getInput(vector <int> inputVector, int exitCondition, string optionalText, string errorText)	//used for conditioned int inputs
{
	int input;
	bool match = false;
	
	cout << optionalText;
	
	while(!(cin >> input))
	{
		cin.clear();
		cin.ignore(99, '\n');
		cout << "Only posititve integers are allowed: ";
	}
	
	if (input == exitCondition)	//If exit condition is met, exit the function
	{	
		return input;
	}
	
	for (int i: inputVector) //Search through vector for a match
	{	
		if (i == input)
		{
			match = true;
		}
		else
		{
			match = match || false;
		}
	}
	
	while (match == false) //Keep getting new input until it satisfies the vector check
	{
		cout << errorText;
		while(!(cin >> input))
		{
			cin.clear();
			cin.ignore(99, '\n');
			cout << "Only posititve integers are allowed: ";
		}
		
		if (input == exitCondition)	//If exit condition is met, exit the function
		{	
			return input;
		}
		
		for (int i: inputVector)
		{
			//cout << i << " ---> " << input << endl;
			if (i == input)
			{
				match = true;
			}
			else
			{
				match = match || false;
			}
		}
	}

	cin.ignore(99, '\n');
	return input;
}

double getInput(double lowerLimit, double upperLimit, string optionalText, string errorText)	//used for conditioned int inputs
{
	double input;
	cout << optionalText;
	while(!(cin >> input))
	{
		cin.clear();
		cin.ignore(99, '\n');
		cout << "Only posititve integers are allowed: ";
	}
	while (input < lowerLimit || input > upperLimit && input != 0)	//'0' will be an exit condition
	{
		cout << errorText;
		while(!(cin >> input))
		{
			cin.clear();
			cin.ignore(99, '\n');
			cout << "Only posititve integers are allowed: ";
		}
	}
	cin.ignore(99, '\n');
	return input;
}

string getStrInput(string optionalText) //Gets string input
{
	string input;
	cout << optionalText;
	getline(cin, input);
	return input;
}

/*****************************************************
			DISPLAY FUNCTIONS
******************************************************/
void displayArray(vector <int> storage, string name) //Displays the vector with its name
{
	if (storage.size() != 0)
	{
		cout << endl << name << endl;
		for (int i = 0; i < storage.size(); ++i)
		{
			cout << i << " ---> " << storage[i] << endl;
		} 
	}
	else 
	{
		cout << name <<" is empty" << endl;
	}
}

void displayArray(vector <unsigned int> storage, string name) //Displays the vector with its name
{
	if (storage.size() != 0)
	{
		cout << endl << name << endl;
		for (int i = 0; i < storage.size(); ++i)
		{
			cout << i << " ---> " << storage[i] << endl;
		} 
	}
	else 
	{
		cout << name <<" is empty" << endl;
	}
}

void displayArray(vector <string> storage, string name) //Displays the vector with its name
{
	if (storage.size() != 0)
	{
		cout << endl << name << endl;
		for (int i = 0; i < storage.size(); ++i)
		{
			cout << i << " ---> " << storage[i] << endl;
		} 
	}
	else 
	{
		cout << name <<" is empty" << endl;
	}
}

void displayBlankRecord(vector <string> nameList) //Displays a blank record when the data held in a record is not important
{
	cout << endl;
	cout << setw(8) << left << "Key" ;
	cout << setw(40) << left << "Data field " << endl;
	for (int i = 0; i <= 11; i++)
	{
		cout << setw(8) << left << i+1;
		cout << setw(40) << left << nameList[i] << endl;
	}
	
	cout << endl << endl;
}

void displayRecord(vector <string> nameList, StoreItem record) //Displays a record 
{
	cout << endl;
	cout << "Record Name: " << record.getRecordName() << endl << endl;
	cout << setw(8) << left << "Key" ;
	cout << setw(40) << left << "Data field ";
	cout << setw (20) << left << "Value" << endl;
	for (int i = 0; i <= 11; i++)
	{	
		cout << setw(8) << left << i+1;
		cout << setw(40) << left << nameList[i];
		cout << setw(20) << left << (record.getRecord())[i] << endl;
	}				
}

void displayRecord(vector <string> nameList, StoreItem record, vector <bool> displayDataField) //Displays a record with specified visibilities for each data field
{
	cout << endl;
	cout << "Record Name: " << record.getRecordName() << endl << endl;
	cout << setw(8) << left << "Key" ;
	cout << setw(40) << left << "Data field ";
	cout << setw (20) << left << "Value" << endl;
	for (int i = 0; i <= 11; i++)
	{
		if (displayDataField[i] == true) //If the data field is visible, display it
		{
			cout << setw(8) << left << i+1;
			cout << setw(40) << left << nameList[i];
			cout << setw(20) << left << (record.getRecord())[i] << endl;
		}
	}		
}

void displayRecord(vector <string> nameList, vector <string> valueList) //Displays a record using external data fields
{
	cout << endl;
	cout << setw(8) << left << "Key" ;
	cout << setw(40) << left << "Data field ";
	cout << setw (20) << left << "Value" << endl;
	for (int i = 0; i <= 11; i++)
	{
		cout << setw(8) << left << i+1;
		cout << setw(40) << left << nameList[i];
		cout << setw(20) << left << valueList[i] << endl;
	}				
}

/*****************************************************
			RECORD RELATED FUNCTIONS
******************************************************/
void createRecord(string & name, vector <string> nameList, vector <string> & valueList)	//Creates a new record and places it into the masterRecord
{
	name = getStrInput("Please enter the name of your record: "); //Gets name for the new record
	displayRecord(nameList, valueList);
	unsigned int key = getInput(-1, valueList.size(), //Get DFKey
	"Please enter the key of the field that you wish to insert ('-1' to exit to main menu, '0' when done): ", 
	"Please enter a number between 0 and " + to_string(valueList.size())),
	DFKey = key - 1; //Vector starts from 0
	
	if (key == -1)
	{
		return;
	}
	
	while (key != 0) 
	{
		switch (key)
		{
			case 0:
			{
				break;
			}
			
			case 1:
			case 2:
			case 3:		//First 5 fields are strings
			case 4:
			case 5:
			{
				valueList[DFKey] = getStrInput("Please enter the value that you want to enter: ");
				break;
			}
			case 6:
			case 7:		//Next 2 fields are doubles
			{
				valueList[DFKey] = to_string(getInput(0.0, 1.8E+307, "Please enter a positive value of the field: ", "Please enter a non-negative number: "));
				
				break;
			}
			case 8:		//All other fields are int
			case 9:
			{
				valueList[DFKey] = to_string(getInput(0, 2147483647, "Please enter a positive value of the field: ", "Please enter a non-negative number: "));
				
				break;
			}
			
			case 10:	//Next 3 fields are int (date)
			{	
				if (valueList[9] != " ")
				{
					if (stoi(valueList[9]) % 4 == 0)	//if the previous entry was a leap year, reset the day fields
					{
						valueList[11] = " ";
					}
				}
				valueList[DFKey] = to_string(getInput(1900, 2019, 	//only 1900 to 2019 are supported
				"Please enter the value of the field: (only 1900 - 2019) ", 
				"Please enter a number within 1900 - 2019: "));
				break;
			}
			case 11:
			{
				valueList[DFKey] = to_string(getInput(1, 12, 
				"Please enter the value of the field: (only 1 - 12) ", 
				"Please enter a number within 1 - 12: "));
				
				if (valueList[11] == "31" &&  (valueList[DFKey] == "2" || valueList[DFKey] == "4" || valueList[DFKey] == "6 "|| valueList[DFKey] == "9" || valueList[DFKey] == "11")) //If the new month doesnt have 31 days, reset the day field
				{
					valueList[11] = " ";
				}
				break;
			}
			case 12:
			{	if (valueList[10] != " ")
				{
					switch (stoi(valueList[10]))
					{
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
						{
							valueList[DFKey] = to_string(getInput(1, 31, 
							"Please enter the value of the field: (only 1 - 31) ", 
							"Please enter a number within 1 - 31: "));
							break;
						}
						
						case 2: 
						{
							if (stoi(valueList[9]) % 4 == 0)	//test for leap year
							{
								valueList[DFKey] = to_string(getInput(1, 29, 
								"Please enter the value of the field: (only 1 - 29) ", 
								"Please enter a number within 1 - 29: "));
								break;
							}
							
							else
							{
								valueList[DFKey] = to_string(getInput(1, 28, 
								"Please enter the value of the field: (only 1 - 28) ", 
								"Please enter a number within 1 - 28: "));
								break;
							}
						}
						
						case 4:
						case 6:
						case 9:
						case 11:
						{
							valueList[DFKey] = to_string(getInput(1, 30, 
							"Please enter the value of the field: (only 1 - 30) ", 
							"Please enter a number within 1 - 30: "));
							break;
						}
						
						default:
						{
							cout << "Day bounds checking error: invalid input at valueList[10] ---> " << valueList[10];
							return;
						}
					}
					
					break;
				}
				else 
				{
					valueList[DFKey] = to_string(getInput(1, 31, 
					"Please enter the value of the field: (only 1 - 31) ", 
					"Please enter a number within 1 - 31: " ));
					break;
				}
			}
			default: 
			{
				cout << "Error: invalid key ---> " << key;
				return;
			}
		}
		displayRecord(nameList, valueList);
		key = getInput(0, valueList.size(), 
		"Please enter the key of the field that you wish to insert ('0' to exit): ", 
		"Please enter a number between 0 and ");
		DFKey = key - 1;
	}
}

void updateField (vector <string> nameList, StoreItem& record) //Updates a field - the conditions for each data field is the same as createRecord
{	
	displayRecord(nameList, record);
	unsigned int key = getInput(0, (record.getRecord()).size(), 
	"Please enter the key of the field that you wish to update ('0' to exit): ", 
	"Please enter a number between 0 and " + to_string((record.getRecord()).size()) + ": "),
	DFKey = key - 1;
	string value;
	
	while (key != 0) 
	{
		switch (key)
		{
			case 0:
			{
				break;
			}
			
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			{
				value = getStrInput("Please enter the value that you want to enter: ");
				record.updateField(value, DFKey);
				break;
			}
			case 6:
			case 7:
			{
				value = to_string(getInput(0.0, 1.8E+307, "Please enter a positive value of the field: ", "Please enter a non-negative number: "));
				record.updateField(value, DFKey);
				break;
			} 
			case 8:
			case 9:
			{
				value = to_string(getInput(0, 2147483647, "Please enter a positive value of the field: ", "Please enter a non-negative number: "));
				record.updateField(value, DFKey);
				break;
			} 
			
			case 10:
			{
				if ((record.getRecord())[9] != " ")
				{
					if (stoi((record.getRecord())[9]) % 4 == 0)	//if the previous entry was a leap year, reset the day fields
					{
						record.deleteField(11);
					}
				}
				value = to_string(getInput(1900, 2019, 
				"Please enter the value of the field: (only 1900 - 2019) ", 
				"Please enter a number within 1900 - 2019: "));
				record.updateField(value, DFKey);
				break;
			}
			case 11:
			{
				value = to_string(getInput(1, 12, 
				"Please enter the value of the field: (only 1 - 12) ", 
				"Please enter a number within 1 - 12: "));
				record.updateField(value, DFKey);
				
				if ((record.getRecord())[11] == "31" &&  ((record.getRecord())[DFKey] == "2" || (record.getRecord())[DFKey] == "4" || (record.getRecord())[DFKey] == "6 "|| (record.getRecord())[DFKey] == "9" || (record.getRecord())[DFKey] == "11")) //If the new month doesnt have 31 days, reset the day field
				{
					record.deleteField(11);
				}
				break;
			}
			case 12:
			{	if ((record.getRecord())[10] != " ")
				{
					switch (stoi((record.getRecord())[10]))
					{
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
						{
							value = to_string(getInput(1, 31, 
							"Please enter the value of the field: (only 1 - 31) ", 
							"Please enter a number within 1 - 31: "));
							record.updateField(value, DFKey);
							break;
						}
						
						case 2: 
						{
							if (stoi((record.getRecord())[9]) % 4 == 0)	//test for leap year
							{
								value = to_string(getInput(1, 29, 
								"Please enter the value of the field: (only 1 - 29) ", 
								"Please enter a number within 1 - 29: "));
								record.updateField(value, DFKey);
								break;
							}
							
							else
							{
								value = to_string(getInput(1, 28, 
								"Please enter the value of the field: (only 1 - 28) ", 
								"Please enter a number within 1 - 28: "));
								record.updateField(value, DFKey);
								break;
							}
						}
						
						case 4:
						case 6:
						case 9:
						case 11:
						{
							value = to_string(getInput(1, 30, 
							"Please enter the value of the field: (only 1 - 30) ", 
							"Please enter a number within 1 - 30: "));
							record.updateField(value, DFKey);
							break;
						}
						
						default:
						{
							cout << "Day bounds checking error: invalid input at valueList[10] ---> " << (record.getRecord())[10];
							return;
						}
					}
					
					break;
				}
				else 
				{
					value = to_string(getInput(1, 31, 
					"Please enter the value of the field: (only 1 - 31) ", 
					"Please enter a number within 1 - 31: " ));
					record.updateField(value, DFKey);
					break;
				}
			}
			default: 
			{
				cout << "Error: invalid key ---> " << key;
				return;
			}
		}
		displayRecord(nameList, record);
		key = getInput(0, (record.getRecord()).size(), 
		"Please enter the key of the field that you wish to update ('0' to exit): ", 
		"Please enter a number between 0 and ");
		DFKey = key - 1;
	}
}

void deleteField (vector <string> nameList, StoreItem & record) //Deletes a field
{
	displayRecord(nameList, record);
	unsigned int key = getInput(0, (record.getRecord()).size(), 
	"Please enter the key of the field that you wish to delete ('0' to exit): ", 
	"Please enter a number between 0 and " + to_string((record.getRecord()).size())),
	DFKey = key - 1;
	string value;
	
	while (key != 0) 
	{
		record.deleteField(DFKey);
		displayRecord(nameList, record);
		key = getInput(0, (record.getRecord()).size(), 
		"Please enter the key of the field that you wish to delete ('0' to exit): ", 
		"Please enter a number between 0 and ");
		DFKey = key - 1;
	}
}
int assignKey (vector <int> & keys) //Assigns a unique key based on the existing key vector
{
	int tempKey;
	bool match = true;
	for (int i = 1; match != false; i++) 
	{
		match = false;
		for (int j = 0; j < keys.size(); j++)
		{
			if (i == keys[j])
			{
				match = true;
				j = keys.size();
			}
			
			else 
			{
				match = match || false;
			}
		}
		if (match == false)
		{
			tempKey = i;	
			keys.push_back(i);	//assigns a key to the new record starting from 1
		}
	}
	return tempKey;
}

int validateKey (int inputKey, vector <int> keys) //Checks if a key is present in the key vector
{
	for (int key : keys)
	{ 
		if (inputKey == key)
		{
			return 0;	//Key is good
		}
	}
	return 1; //If the loop managed to finish without a match being found, key is bad
}

int deleteKey (int inputKey, vector <int> & keys) //Deletes key when a record is deleteds
{
	for (int i = 0; i < keys.size(); i++)
	{
		if (inputKey == keys[i])
		{
			keys.erase(keys.begin() + i);
			return 0;	//Delete successful
		}
	}
	return 1; //Delete failed
}
int keyToRecord (int inputKey, vector <StoreItem> masterRecord)  //Converts the key of a record to its position in the masterRecord
{
	for (int i = 0; i < masterRecord.size(); i++)
	{
		if (inputKey == masterRecord[i].getKey())
		{
			return i;
		}
	}
	return -1;	//if no match is found at the end of the loop, return -1
}

void updateKeys (vector <int> & keys, vector <StoreItem> masterRecord) //Updates the keys vector after a record is sorted
{
	for (int i = 0; i < masterRecord.size(); i++) 
	{
		keys[i] = (masterRecord[i]).getKey();
	}
}

vector <int> fullStringSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, string searchString) //Searches in a given list for records that have the exact string in the specific data field
{
	if (selectedKeys.size() != 0) //Instead of pushing new keys into the vector, it deletes existing keys that do not have a match
	{
		for (int i = 0; i < selectedKeys.size(); i++)
		{
			int key = keyToRecord(selectedKeys[i], masterRecord);	//convert input key to the position of the record in the master record
			
			if (searchString != (masterRecord[key].getRecord())[DFKey])	
			{
				selectedKeys.erase(selectedKeys.begin()+i);	
				i--;	//Necessary because the no. of elements reduced by 1 and shifted to the left in the selectedKey vector
			}
		}
	}
	/*
	else //If the input vector is empty, push keys instead
	{
		for (int i = 0; i < masterRecord.size(); i++)
		{
			if (searchString == (masterRecord[i].getRecord())[DFKey])	
			{
				selectedKeys.push_back(masterRecord[i].getKey());

			}
		}
	}
	*/
	return selectedKeys;
}

vector <int> subStringSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, string searchString) //Searches in a given list for records that include string in the specific data field
{
	if (selectedKeys.size() != 0) //Instead of pushing new keys into the vector, it deletes existing keys that do not have a match
	{
		for (int i = 0; i < selectedKeys.size(); i++)
		{
			int key = keyToRecord(selectedKeys[i], masterRecord);	//convert input key to the position of the record in th master record
			
			if ((masterRecord[key].getRecord())[DFKey].find(searchString) == string::npos)	
			{
				selectedKeys.erase(selectedKeys.begin()+i);
				i--;	//Necessary because the no. of elements reduced by 1 and shifted to the left in the selectedKey vector
				
			}
		}
	}
	
	/*
	else //If the input vector is empty, push keys instead
	{
		for (int i = 0; i < masterRecord.size(); i++)
		{
			if ((masterRecord[i].getRecord())[DFKey].find(searchString) != string::npos) 	//first get record in master record at index i, then loop through the record vector with subscript j and look
																							//for the substring searchString
			{
				selectedKeys.push_back(masterRecord[i].getKey());
			}
		}
	}
	*/
	return selectedKeys;
}

vector <int> compareGreaterNumSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, int num)	//Searches for a record that has a number greater than num in a specified data field
{
	if (selectedKeys.size() != 0)
	{
		for (int i = 0; i < selectedKeys.size(); i++)
		{
			int key = keyToRecord(selectedKeys[i], masterRecord);	//convert input key to the position of the record in th master record
			
			if ((masterRecord[key].getRecord())[DFKey] == " ")
			{
				selectedKeys.erase(selectedKeys.begin()+i);
				i--;
			}
				
			else if (stoi(masterRecord[key].getRecord()[DFKey]) <= num) 	//first get record in master record at index i, then look at index DFKey and convert to int then compare
			{
				selectedKeys.erase(selectedKeys.begin()+i);
				i--;
			}
		}
	}
	
	/*
	else
	{
		for (int i = 0; i < masterRecord.size(); i++)
		{
			if ((masterRecord[i].getRecord())[DFKey] != " ")
			{
				if (stoi(masterRecord[i].getRecord()[DFKey]) > num) 	//first get record in master record at index i, then look at index DFKey and convert to int then compare
				{
					selectedKeys.push_back(masterRecord[i].getKey());
				}
			}
		}
	}
	*/	
	return selectedKeys;
}
vector <int> compareSmallerNumSearch (vector <StoreItem> masterRecord, vector <int> selectedKeys, int DFKey, int num)	//Searches for a record that has a number smaller than num in a specified data field
{
	if (selectedKeys.size() != 0)
	{
		for (int i = 0; i < selectedKeys.size(); i++)
		{
			int key = keyToRecord(selectedKeys[i], masterRecord);	//convert input key to the position of the record in th master record
			
			if ((masterRecord[key].getRecord())[DFKey] == " ")
			{
				selectedKeys.erase(selectedKeys.begin()+i);
				i--;
			}
				
			else if (stoi(masterRecord[key].getRecord()[DFKey]) >= num) 	//first get record in master record at index i, then look at index DFKey and convert to int then compare
			{
				selectedKeys.erase(selectedKeys.begin()+i);
				i--;
			}
		}
	}
	
	/*
	else
	{
		for (int i = 0; i < masterRecord.size(); i++)
		{
			if ((masterRecord[i].getRecord())[DFKey] != " ")
			{
				if (stoi(masterRecord[i].getRecord()[DFKey]) < num) 	//first get record in master record at index i, then look at index DFKey and convert to int then compare
				{
					selectedKeys.push_back(masterRecord[i].getKey());
				}
			}
		}
	}
	*/	
	return selectedKeys;
}

void ascendSortRecord (int DFKey, vector <StoreItem> & masterRecord)	//Sorts records in ascending order
{
	int index, minIndex;
	auto minValue = (masterRecord[0].getRecord())[DFKey]; //Determine automatically the data type of the data field 
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		minIndex = index;
		minValue = (masterRecord[index].getRecord())[DFKey];
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << minValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			if ((masterRecord[i].getRecord())[DFKey] < minValue)
			{
				minIndex = i;
				minValue = (masterRecord[i].getRecord())[DFKey];
				newMinFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+minIndex, masterRecord.begin()+index);
		}
	}
}
			
			
void descendingSortRecord (int DFKey, vector <StoreItem> & masterRecord) //Sorts records in descending order
{
	int index, maxIndex;
	auto maxValue = (masterRecord[0].getRecord())[DFKey];
	bool newMaxFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		maxIndex = index;
		maxValue = (masterRecord[index].getRecord())[DFKey];
		newMaxFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << maxValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			if ((masterRecord[i].getRecord())[DFKey] > maxValue)
			{
				maxIndex = i;
				maxValue = (masterRecord[i].getRecord())[DFKey];
				newMaxFound = true; //Improvement on the previous algorithm, now it only swaps only if a new maximum is found
			}
		}
		
		if (newMaxFound)
		{
			iter_swap(masterRecord.begin()+maxIndex, masterRecord.begin()+index);
		}
	}
}
void ascendSortRecordInt (int DFKey, vector <StoreItem> & masterRecord)	//Sorts records in ascending order based on int
{
	int index, minIndex;
	string temp;
	int minValue;	
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		minIndex = index;
		temp = (masterRecord[index].getRecord())[DFKey];
		if (temp != " ")
		{
			minValue = stoi(temp);
		}
		
		else
		{
			minValue = -1;
		}
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << minValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			temp = (masterRecord[i].getRecord())[DFKey];
			if (temp == " ")
			{
				temp = "-1";
			}
			
			if (stoi(temp) < minValue)
			{
				minIndex = i;
				minValue = stoi(temp);
				newMinFound = true; 
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+minIndex, masterRecord.begin()+index);
		}
	}
}
void descendingSortRecordInt (int DFKey, vector <StoreItem> & masterRecord) //Sorts records in descending order based on int
{
	int index, maxIndex;
	string temp;
	int maxValue; 
	bool newMaxFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		maxIndex = index;
		temp = (masterRecord[index].getRecord())[DFKey];
		if (temp != " ") 
		{
			maxValue = stoi(temp);
		}
		
		else
		{
			maxValue = -1;
		}
		newMaxFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << maxValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			temp = (masterRecord[i].getRecord())[DFKey];
			if (temp == " ")
			{
				temp = "-1";
			}
			
			if (stoi(temp) > maxValue)
			{
				maxIndex = i;
				maxValue = stoi(temp);
				newMaxFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMaxFound)
		{
			iter_swap(masterRecord.begin()+maxIndex, masterRecord.begin()+index);
		}
	}
}
void ascendSortRecordDouble (int DFKey, vector <StoreItem> & masterRecord)	//Sorts records in ascending order based on double
{
	int index, minIndex;
	string temp;
	double minValue; 
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		minIndex = index;
		temp = (masterRecord[index].getRecord())[DFKey];
		if (temp != " ")
		{
			minValue = atof(temp.c_str());
		}
		
		else
		{
			minValue = -1.0;
		}
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << minValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			temp = (masterRecord[i].getRecord())[DFKey];
			if (temp == " ")
			{
				temp = "-1.0";
			}
			
			if (atof(temp.c_str()) < minValue)
			{
				minIndex = i;
				minValue = atof(temp.c_str());
				newMinFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+minIndex, masterRecord.begin()+index);
		}
	}
}
void descendingSortRecordDouble (int DFKey, vector <StoreItem> & masterRecord) //Sorts records in descending order based on double
{
	int index, maxIndex;
	string temp = (masterRecord[0].getRecord())[DFKey];
	double maxValue = atof(temp.c_str()); 
	bool newMaxFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		maxIndex = index;
		temp = (masterRecord[index].getRecord())[DFKey];
		if (temp != " ")
		{
			maxValue = atof(temp.c_str());
		}
		
		else
		{
			maxValue = -1.0;
		}
		maxValue = atof(temp.c_str());
		newMaxFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << maxValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			temp = (masterRecord[i].getRecord())[DFKey];
			if (temp == " ")
			{
				temp = "-1.0";
			}
			if (atof(temp.c_str()) > maxValue)
			{
				maxIndex = i;
				maxValue = atof(temp.c_str());
				newMaxFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMaxFound)
		{
			iter_swap(masterRecord.begin()+maxIndex, masterRecord.begin()+index);
		}
	}
}
void ascendSortRecordStr (int DFKey, vector <StoreItem> & masterRecord)	//Sorts records in ascending order based on strings
{
	int index, minIndex;
	string minValue = (masterRecord[0].getRecord())[DFKey]; 
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		minIndex = index;
		minValue = (masterRecord[index].getRecord())[DFKey];
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << minValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			if ((masterRecord[i].getRecord())[DFKey] < minValue)
			{
				minIndex = i;
				minValue = (masterRecord[i].getRecord())[DFKey];
				newMinFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+minIndex, masterRecord.begin()+index);
		}
	}
}
void descendingSortRecordStr (int DFKey, vector <StoreItem> & masterRecord) //Sorts records in descending order based on strings
{
	int index, maxIndex;
	string maxValue = (masterRecord[0].getRecord())[DFKey]; 
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		maxIndex = index;
		maxValue = (masterRecord[index].getRecord())[DFKey];
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << maxValue << " compared to i: " << i << ", " << (masterRecord[i].getRecord())[DFKey] << endl;
			if ((masterRecord[i].getRecord())[DFKey] < maxValue)
			{
				maxIndex = i;
				maxValue = (masterRecord[i].getRecord())[DFKey];
				newMinFound = true; //Improvement on the previous algorithm, now it only swaps only if a new minimum is found
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+maxIndex, masterRecord.begin()+index);
		}
	}
}
void ascKeySortRecord (vector <StoreItem> & masterRecord)	//Sorts based on keys in ascending order
{
	int index, minIndex;
	int minValue = masterRecord[0].getKey();
	bool newMinFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		minIndex = index;
		minValue = masterRecord[index].getKey();
		newMinFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			//cout << "At index = " << index << ", " << minValue << " compared to i: " << i << ", " << masterRecord[i].getKey() << endl;
			if (masterRecord[i].getKey() < minValue)
			{
				minIndex = i;
				minValue = masterRecord[i].getKey();
				newMinFound = true;
			}
		}
		
		if (newMinFound)
		{
			iter_swap(masterRecord.begin()+ minIndex, masterRecord.begin()+index);
		}
	}
}
void dscKeySortRecord (vector <StoreItem> & masterRecord)	//Sorts based on keys in descending order
{
	int index, maxIndex;
	int maxValue = masterRecord[0].getKey();
	bool newMaxFound = false;
	for (index = 0; index < (masterRecord.size() - 1); index++)
	{
		maxIndex = index;
		maxValue = masterRecord[index].getKey();
		newMaxFound = false;
		for (int i = index + 1; i < masterRecord.size(); i++)
		{
			if (masterRecord[i].getKey() > maxValue)
			{
				maxIndex = i;
				maxValue = masterRecord[i].getKey();
				newMaxFound = true;
			}
		}
		
		if (newMaxFound)
		{
			iter_swap(masterRecord.begin()+maxIndex, masterRecord.begin()+index);
		}
	}
}

						
						
						
						
						
						
						
						
						
						
						
						
						