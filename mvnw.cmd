@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM Begin all REM://
@echo off

@REM Set the current directory to the location of this script
set WRAPPER_DIR=%~dp0

@REM Check for JAVA_HOME
if not "%JAVA_HOME%"=="" goto hasJavaHome
for %%i in (java.exe) do set "JAVACMD=%%~$PATH:i"
if not "%JAVACMD%"=="" goto hasJava
echo Error: JAVA_HOME is not set and java.exe is not in PATH.
exit /b 1

:hasJavaHome
set "JAVACMD=%JAVA_HOME%\bin\java.exe"

:hasJava
@REM Determine Maven home from wrapper properties
set WRAPPER_PROPERTIES=%WRAPPER_DIR%.mvn\wrapper\maven-wrapper.properties
set WRAPPER_JAR=%WRAPPER_DIR%.mvn\wrapper\maven-wrapper.jar

@REM Download wrapper jar if not present
if exist "%WRAPPER_JAR%" goto runMaven

echo Downloading Maven Wrapper...
@REM Try PowerShell download
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar' -OutFile '%WRAPPER_JAR%'}"

if not exist "%WRAPPER_JAR%" (
    echo Failed to download Maven Wrapper. Please install Maven manually.
    exit /b 1
)

:runMaven
"%JAVACMD%" ^
  -Dmaven.multiModuleProjectDirectory="%WRAPPER_DIR%" ^
  -jar "%WRAPPER_JAR%" ^
  %*
