# customer__contact--import
### String Calculator
The StringCalculator is included here within the __service__ package.
### Instructions for use
* Both the _customer__contact--import_ & _customer__contact--save-to-database_ should be running.
* Set the File Path where the .csv is stored as shown below.
* Run the _/customers/contacts?filepath=file_path_ to save the data to database.
* Records can be retrieved from database using _/customers/{customer_ref}/contacts_

Rest Call to retrieve the content from the .csv file & Save to Database\
** NOTE There is a test file available in the GIT Repository root\
http://localhost:8090/customers/contacts/import?filepath=customer_contacts.csv

Rest Call used to get a record from the Database\
http://localhost:8080/customers/123/contacts

Note to different port numbers.
