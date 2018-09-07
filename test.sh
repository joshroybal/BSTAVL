#!/bin/sh
set -v
time java TestBST colors.txt
time java TestAVL colors.txt
time java TestBST tiny.txt
time java TestAVL tiny.txt
time java TestBST small.txt
time java TestAVL small.txt
time java TestBST medium.txt
time java TestAVL medium.txt
time java TestBST large.txt
time java TestAVL large.txt
