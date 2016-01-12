#!/bin/bash

# uploading reference:
# http://central.sonatype.org/pages/manual-staging-bundle-creation-and-deployment.html
# releasing reference:
# http://central.sonatype.org/pages/releasing-the-deployment.html

set -e
set -u

NAME=junit-volkswagen
VERSION="0.0.1"

gradle clean build writePom

cd build/libs/

# sign
gpg -ab ${NAME}-${VERSION}.pom
gpg -ab ${NAME}-${VERSION}.jar
gpg -ab ${NAME}-${VERSION}-javadoc.jar
gpg -ab ${NAME}-${VERSION}-sources.jar

# create a bundle
jar -cvf bundle.jar \
	${NAME}-${VERSION}.pom \
	${NAME}-${VERSION}.pom.asc \
	${NAME}-${VERSION}.jar \
	${NAME}-${VERSION}.jar.asc \
	${NAME}-${VERSION}-javadoc.jar \
	${NAME}-${VERSION}-javadoc.jar.asc \
	${NAME}-${VERSION}-sources.jar \
	${NAME}-${VERSION}-sources.jar.asc

echo "1. go to https://oss.sonatype.org/"
echo "2. upload bundle"
echo "3. close"
echo "4. release"
