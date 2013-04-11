Genome_Comparison
=================

Stage 3 - Project

Classifying genome comparison features through the use of Bayesian Networks


Current To Do List
=================
- Build GUI
- Testing of SB-DEL / SB-In & Insertion / Deletion
- Output Results to text file or list / Database setup
- FileChooser - allow user to select comparison file

Done
=================
- Parse basic match information from BLAST file
- Creation of Vector to hold match attributes
- Loading of Bayesian Network, created externally via WEKA GUI
- Bayesian Network now built from training data
- Create a set of n instances where n is number of matches found
- Populate set with match information
- Test/Train Bayesian Network
- Second network implemented for comparison of two matches
- Detection of Total & Partial matches
- Detection of SNP 
- Detection of SB-IN / SB-DEL
- Detection of Insertion / Deletion


Instructions - running via eclipse/IDE
=================
- To train the network remove the comment from the TrainNet.train() function
- The classifier is saved after running the code, no need to retrain each time
- The training data can be altered by editing the MatchTraining.arff file
- Different comparison files may be used - drop them into the Test_Comparison_Files folder and alter the file path for the BlastReader method in Main




