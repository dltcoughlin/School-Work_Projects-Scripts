$windowsUpdate = Get-ItemProperty -Path HKLM:\Software\Policies\Microsoft\Windows\WindowsUpdate\AU
if ($windowsUpdate.AuOptions -ne "3")
{
    $status = 1
}
if ($status -eq 1)
{
    Set-ItemProperty -Path HKLM:\Software\Policies\Microsoft\Windows\WindowsUpdate\AU -Name "AuOptions" -Value 3
}
