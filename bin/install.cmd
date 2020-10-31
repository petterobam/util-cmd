@Echo Off
echo "----- utf-8 mode"
chcp 65001
@Title util-cmd-install@ouyangjie02
echo "----- bat current path"
Set curr_path=%~dp0
echo "----- remove bin/"
Set project_path=%curr_path:~0,-4%
echo "----- cd %project_path%"
cd %project_path%
echo "----- call mvn package"
call mvn package
echo "----- check is exit util alias"
find /I "@doskey util" C:\cmd-alias.bat
IF ERRORLEVEL 1 goto 1
IF ERRORLEVEL 0 goto 0
:0
echo "----- exit util alias then exit"
echo "if [util -help] not useful, please reopen terminal !"
pause
goto exit
:1
:: add \r\n to end of C:\cmd-alias.bat
echo "----- add util alias"
echo :: util-cmd Terminal常用小功能命令行工具 >> C:\cmd-alias.bat
echo @doskey util=java -jar %project_path%target\util-cmd.jar $* >> C:\cmd-alias.bat
:: echo @doskey util-update=cd %project_path% ^&^& git pull ^&^& mvn package  >> C:\cmd-alias.bat
echo "----- register cmd-alias.bat"
regedit /s cmd-alias.reg
echo "if [util -help] not useful, please reopen terminal !"
refreshenv
pause
goto exit

