@echo on
copy %1 tree.dat
java TestBST
copy %1 tree.dat
java TestAVL