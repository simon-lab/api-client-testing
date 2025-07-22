@echo off
pause
call mvn clean test
call mvn allure:serve
pause