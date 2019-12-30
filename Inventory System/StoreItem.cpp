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
#include "StoreItem.h"
using namespace std;

/***********************************
			IMPORTANT KEYWORDS:
1. Record	  - A collection of sections that hold data
2. Data field - The sections of a record that hold values set by the user. 
3. Key		  - A unique identifier that distinguishes one record from another
4. Data field key - A unique identifier that refers to the data field being modified
3. insert(ed) - Whether a data field was entered a value during the creation of record. 
4. update(ed) - Modify the contents of an inserted data field
5. delete(ed) - Delete the contents of an inserted data field
6. display(ed) - Change the visibility of an inserted data field

***********************************/

/*****************************************************
			STOREITEM CLASS
	NOTES: a. 	If key is a value selected from the menu which has a starting index of 1, make sure to key-- before inputting it,
				all keys start from 0.
				
		   b.	While all inputs are checked by the class, please note that the safeguards in place only prevent the program from crashing.
				Instead, validate all inputs manually before passing them into the StoreItem member functions.
				
	1. StoreItem(string name, int inputKey, vector<string> inputVector) Constructor
		- Accepts a name, unique key and a string vector of data fields
		- If the key < 0, then the key is a bad key
			* Status is set to zombie state and constructor returns
		- If the input vector is not of the correct size
			* Status is set to zombie state and consturctor returns
		- Initializes internal variables with the input values
		
	2. int StoreItem::updateField(string value, int inputDFKey)
		- Accepts an int inputDFKey of the data field and its new value from user 
		- Checks if the int falls within the int range of the record array
			* Returns 1 if the key is not within range of 0 and record.size()
		- Updates appropriate data field in record with value
		- Returns 0 data field is updated successfully
		
	3. int StoreItem::deleteField(int inputKey)
		- Accepts an int inputKey of the data field to be deleted from user 
		- Checks if the int falls within the int range of the record array
			* Returns 1 if the key is not within range of 0 and record.size(
		- Replaces content in data field with an empty string ""
		- Returns 0 data field is deleted successfully	
		
******************************************************/

int StoreItem::updateField(string value, int inputDFKey) {
	if (inputDFKey < 0 || inputDFKey > record.size()) {
		cout << "Error: key out of range" << endl;	//inputDFKey out of range
		return 1;
	}
	
	record[inputDFKey] = value;
	
	return 0;
}

int StoreItem::deleteField(int inputDFKey){
	if (inputDFKey < 0 || inputDFKey > record.size()){
		cout << "Error: key out of range" << endl;	//key out of range
		return 1;
	}

	record[inputDFKey] = " ";

	return 0;
}

StoreItem::StoreItem(string name, int inputKey, vector<string> inputVector) 
{
	if (inputKey < 0) {
		cout << "Error: negative value of key is forbidden" << endl;	//inputKey out of range
		debuggingMode = true; //Set to zombie state
		return;		//need to do error handling
	}
	if (inputVector.size() != SIZE) {
		cout << "Error: input vector not of size " << SIZE << endl;
		debuggingMode = true; //Set to zombie state
		return;
	}
	
	for (int i = 0; i < inputVector.size(); ++i) {
		record[i] = inputVector[i];
	}
	
	recordName = name;
	key = inputKey;
	return;
}

StoreItem::StoreItem()		//DEBUGGING USE ONLY
{
	debuggingMode = true;
}


						
					