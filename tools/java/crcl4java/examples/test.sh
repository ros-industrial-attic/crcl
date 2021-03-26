#!/bin/sh

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

( sh clean.sh )|| (echo "Clean failed"; exit 1) || exit 1
( cd matlab; sh test.sh ) || (echo "Matlab test failed"; exit 1) || exit 1
( cd javascript/nodejs; sh test.sh ) || (echo "Javascript NodeJs test failed"; exit 1) || exit 1
( cd javascript/jjs; sh test.sh ) || (echo "Javascript JJS test failed"; exit 1) || exit 1
( cd C++; sh test.sh ) || (echo "C++ test failed"; exit 1) || exit 1
( cd java; sh test.sh ) || (echo "Java test failed"; exit 1) || exit 1
( cd python/jpype; sh test.sh ) || (echo "Python jpype test failed"; exit 1) || exit 1
( cd python/jython; sh test.sh ) || (echo "Python jython test failed"; exit 1) || exit 1

sh clean.sh

echo "ALL tests ok."
