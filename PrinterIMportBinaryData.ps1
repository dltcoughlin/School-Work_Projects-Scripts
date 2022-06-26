
$hives = Get-ChildItem Registry::HKEY_USERS\ |Where-Object {$_.Name -notmatch '_Classes' -and $_.Name -notmatch '.DEFAULT'}
$item = Get-Content -Path .\BinaryData.csv

foreach ($user in $hives){
    $path = 'Registry::' + $user+ '\Printers\DevModePerUser'
    try{
        Remove-ItemProperty -path $path
    }
    catch {}
    New-ItemProperty -Path $path -Name Canon -PropertyType Binary -Value $binaryData
}
