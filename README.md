# File Scanner

###Problem Statement

Let a file be a general property file, where key value pairs are stored. We have a three different properties file, with different contents. Whenever someone makes change (add/delete/modify property) in the properties file and then saves, the application’s task is to print the property file’s name with old key pair value and new key pair value on the console.

Example:

Suppose propA.properties has contents like

	Name = Amrita
	Designation = MD

and some one changed the above file contents to:

	Name = Amrita
	Designation = CMD

Output in the console should be:

	File Changed : propA.properties
	Property Changed: Designation
	Old Value: MD
	New Value: CMD
