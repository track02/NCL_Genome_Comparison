Genome_Comparison
=================

Stage 3 - Project

Classifying genome comparison features through the use of Bayesian Networks



Current To Do List
=================
- Expand FileReader - retrieve subject/query points (Find start point and add number of bases)
- Classify SNP / indels within matches - check gaps in BLAST file
- Output Results to text file or list

Future To Do List
=================
- Expand Bayesian Network - classify further features 
- Consider BioJava library for easier comparison file analysis
- Further Bayes Net training/testing
- FileChooser - allow user to select comparison file
- GUI - display results/queries
- Database setup


Done
=================
- Parse basic match information from BLAST file
- Creation of Vector to hold match attributes
- Loading of Bayesian Network, created externally via WEKA GUI
- Create a set of n instances where n is number of matches found
- Populate set with match information
- Test/Train Bayesian Network


Instructions - running via eclipse/IDE
=================
- To train the network remove the comment from the TrainNet.train() function
- The classifier is saved after running the code, no need to retrain each time
- The training data can be altered by editing the MatchTraining.arff file
- Different comparison files may be used - drop them into the Test_Comparison_Files folder and alter the file path for the BlastReader method in Main




