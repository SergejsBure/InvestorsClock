program project1;

uses
  ShellApi, SysUtils, LazUTF8;

var
  JRE_Folder, JAR_Folder: WideString;

{$R *.res}

begin
  JRE_Folder := UTF8ToUTF16(GetCurrentDir + '\JRE\bin\javaw.exe');
  JAR_Folder := UTF8ToUTF16('-jar "' + GetCurrentDir + '\InvestorsClock.jar"');
  ShellExecuteW(0, 'open', PWideChar(JRE_Folder), PWideChar(JAR_Folder), nil, 1);
end.

