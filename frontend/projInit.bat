@echo off

rem ### ȫ�ְ�װrimraf
echo ;;;; call npm install rimraf -g ;;;;
    call npm install rimraf -g

rem npm��װelectron���ܻᳬʱ������
echo ";;;; echo electron_mirror=https://npm.taobao.org/mirrors/electron/ >> %USERPROFILE%\.npmrc ;;;;"
    echo electron_mirror=https://npm.taobao.org/mirrors/electron/ >> %USERPROFILE%\.npmrc

rem ������沢ִ��������װ
echo ;;;; call npm run install:clean ;;;;
    call npm run install:clean

pause
