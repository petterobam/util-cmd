echo "-----> utf-8 mode"
chcp 65001
echo "-----> ps1 current path"
$curr_path = Split-Path -Parent $MyInvocation.MyCommand.Definition
echo "-----> remove bin/"
$project_path=$curr_path.Substring(0,$curr_path.Length-4)
echo "-----> cd $project_path"
cd $project_path
echo "-----> call mvn package"
mvn package
echo "-----> add util alias to powershell"
Add-Content $PROFILE -Encoding utf8 -Value "# util-cmd utils "
Add-Content $PROFILE -Encoding utf8 -Value "function util { java -jar $project_path\target\util-cmd.jar `$(`$args[0]) `$(`$args[1]) `$(`$args[2]) `$(`$args[3]) `$(`$args[4]) `$(`$args[5]) }"
Add-Content $PROFILE -Encoding utf8 -Value "function util-update { cd $project_path; git pull; mvn package }"
echo "if [util -help] not useful, please reopen terminal !"