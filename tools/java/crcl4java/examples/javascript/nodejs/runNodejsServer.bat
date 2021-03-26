
REM If you didn't put nodejs or Python27 in the default locations this 
REM may need to be edited.
set PATH=%PATH%;C:\Program Files\nodejs;C:\Python27;

call node crclNodejsServer.js
echo node crclNodejsServer.js exit Code = %ERRORLEVEL%