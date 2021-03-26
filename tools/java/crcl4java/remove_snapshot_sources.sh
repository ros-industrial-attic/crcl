#!/bin/sh

set -x;

find ~/.m2/repository -name \*-SNAPSHOT-sources.jar\* -ls
find ~/.m2/repository -name \*-SNAPSHOT-sources.jar\* -exec rm '{}' \;

find ../.. -name \*-SNAPSHOT-sources.jar\* -ls
find ../.. -name \*-SNAPSHOT-sources.jar\* -exec rm '{}' \;

