call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo Cannot start application

:fail
echo.
echo There were errors

:startbrowser
start firefox -URL http://localhost:8080/crud/v1/tasks
:end
echo.
echo Work is finished.