Each folder contains a number of java (and j--) files to be used to test the extensions done for the corresponding project's step.

You can add these .java files in the folder j--/tests/pass, and then run the compiler specifying them as arguments in Run -> Run configurations... -> select the j-- launcher -> tab arguments.

The files in Step0 should be run as: 
	-d outputfolder tests/pass/FileName.java
	
The files in Step1 should be run as: 
	-t tests/pass/FileName.java
	
The files in Step2 should be run as: 
	-p tests/pass/FileName.java
	
The files in Step4 should be run as: 
	-pa tests/pass/FileName.java
	-a tests/pass/FileName.java	

The files in Step0 should be run as: 
	-d outputfolder tests/pass/FileName.java
	



You can	also extend the junit test-suite of the compiler using the above files. 
The slides "02_compilation" explain how to do this: Section 4 'The j-- Compiler Source Tree', Sub-section 'Extending the j-- Compiler: a demo of the final project'