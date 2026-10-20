$ErrorActionPreference = "Stop"
Push-Location $PSScriptRoot
try {
    if (-not (Test-Path "build\classes\com\campus\lostandfound\UI\LoginPage.class")) {
        & "$PSScriptRoot\compile.ps1"
        if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
    }
    & java "-Dfile.encoding=UTF-8" -cp "build\classes;src;lib\*" com.campus.lostandfound.UI.LoginPage
} finally {
    Pop-Location
}
