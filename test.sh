#!/bin/sh
set -v
cp 7500.dat tree.dat
time java TestBST
cp 7500.dat tree.dat
time java TestAVL
