#!/bin/bash
# author:ouyangjie02
# Usage: util cmd util install
# shell path
SHELL_PATH=$(cd `dirname $0`; pwd)
echo "-----> shell current path"
echo "cd $SHELL_PATH"
cd $SHELL_PATH
cd ..
PROJECT_PATH=$(pwd)

echo "-----> check util alias in profile is exit"
if cat ~/.bash_profile | grep "alias util=java -jar">/dev/null
then
  echo "-----> exit util alias in profile then skip"
else
  echo "-----> add util alias to profile"
  echo "alias util=java -jar $PROJECT_PATH/target/util-cmd.jar" >> ~/.bash_profile
  source ~/.bash_profile
fi

echo "-----> check util alias in zsh is exit"
if cat ~/.zshrc | grep "alias util=java -jar">/dev/null
then
  echo "-----> exit util alias in zsh then skip"
else
  echo "-----> add util alias to zsh"
  echo "alias util=java -jar $PROJECT_PATH/target/util-cmd.jar" >> ~/.zshrc
  source ~/.zshrc
fi

echo "-----> call mvn package"
mvn package
echo "if [util -help] not useful, please reopen terminal !"