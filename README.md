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



