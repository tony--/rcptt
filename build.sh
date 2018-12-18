#!/bin/sh
#*******************************************************************************
# Copyright (c) 2009, 2016 Xored Software Inc and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#  
# Contributors:
# 	Xored Software Inc - initial API and implementation and/or initial documentation
#*******************************************************************************
OPTIONS="-Dtycho.localArtifacts=ignore $@"

mvn clean verify -f releng/mirroring/pom.xml $OPTIONS || exit 100 

echo "======================Building Core======================"
mvn clean verify -f releng/core/pom.xml $OPTIONS || exit 101

echo "======================Building Runtime 3x======================"
mvn clean verify -f releng/runtime/pom.xml -P runtime3x $OPTIONS || exit 102

echo "======================Building Runtime 4x======================"
mvn clean verify -f releng/runtime/pom.xml -P runtime4x $OPTIONS || exit 103

echo "======================Building IDE and Modules======================"
mvn clean verify -f releng/ide/pom.xml $OPTIONS || exit 105

# Build RAP before RCPTT repository since it is required.
echo "======================Building RAP Core======================"
mvn clean verify -f releng/rap/pom.xml -P core $OPTIONS || exit 110
echo "======================Building RAP IDE part======================"
mvn clean verify -f releng/rap/pom.xml -P ide $OPTIONS || exit 111

echo "======================Building RCP and Full site======================"
mvn clean verify -f releng/rcptt/pom.xml $OPTIONS || exit 106
