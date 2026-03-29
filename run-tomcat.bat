@echo off
setlocal

set "CATALINA_HOME=%~dp0..\.."
set "CATALINA_BASE=%CATALINA_HOME%"
set "PATH=%CATALINA_HOME%\bin;%PATH%"

call "%CATALINA_HOME%\bin\startup.bat"

endlocal
