@echo off
if "%1"=="" (
 goto error
) else (
 goto action %1
)

:error
echo.
echo ��������Ҫ�������ļ���·�����������϶��ļ��е��������ļ��ϡ�
echo.
pause
goto end

:action %1
echo --------------------------------------------------------------------------
echo ���β�����ɾ�� [%1] �ļ��������е�svn��ǣ������ز�����
echo     Y �����ļ���
echo     N �˳�
echo ---------------------------------------------------------------------------
choice /c YN /m ��ѡ��˵�(��ctrl+c��N�˳�)��

if %errorlevel% equ 2 goto end
echo ���������ļ��У�%1
echo ���Ժ�...
for /r %1 %%a in (.) do @if exist "%%a\.svn" rd /s /q "%%a\.svn"
echo �������!
echo ��������˳�...
pause>echo.

:end
exit

