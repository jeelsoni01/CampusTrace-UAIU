$ErrorActionPreference = "Stop"
$projectRoot = $PSScriptRoot
$logFile = Join-Path $projectRoot "launcher.log"

function Write-LauncherLog([string]$message) {
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    Add-Content -LiteralPath $logFile -Value "[$timestamp] $message"
}

function Test-MySqlReady {
    $client = [System.Net.Sockets.TcpClient]::new()
    try {
        $task = $client.ConnectAsync("127.0.0.1", 3306)
        if (-not $task.Wait(700)) { return $false }
        return $client.Connected
    } catch {
        return $false
    } finally {
        $client.Dispose()
    }
}

function Show-LauncherError([string]$message) {
    Add-Type -AssemblyName System.Windows.Forms
    [System.Windows.Forms.MessageBox]::Show(
        $message,
        "Universal AI University Lost & Found",
        [System.Windows.Forms.MessageBoxButtons]::OK,
        [System.Windows.Forms.MessageBoxIcon]::Error
    ) | Out-Null
}

try {
    Write-LauncherLog "Launcher started."
    if (-not (Test-MySqlReady)) {
        $xamppStart = "C:\xampp\mysql_start.bat"
        if (-not (Test-Path -LiteralPath $xamppStart)) {
            throw "XAMPP MySQL was not found at C:\xampp. Install or restore XAMPP, then try again."
        }
        Write-LauncherLog "Starting XAMPP MySQL."
        Start-Process -FilePath "$env:SystemRoot\System32\cmd.exe" -ArgumentList "/c", "`"$xamppStart`"" -WindowStyle Hidden
        $ready = $false
        for ($attempt = 0; $attempt -lt 30; $attempt++) {
            Start-Sleep -Milliseconds 500
            if (Test-MySqlReady) {
                $ready = $true
                break
            }
        }
        if (-not $ready) {
            throw "XAMPP MySQL did not start within 15 seconds. Open the XAMPP Control Panel and check the MySQL log."
        }
    }

    $mainClass = Join-Path $projectRoot "build\classes\com\campus\lostandfound\UI\LoginPage.class"
    $needsCompile = -not (Test-Path -LiteralPath $mainClass)
    if (-not $needsCompile) {
        $classTime = (Get-Item -LiteralPath $mainClass).LastWriteTimeUtc
        $needsCompile = Get-ChildItem -LiteralPath (Join-Path $projectRoot "src") -Recurse -Filter "*.java" |
                Where-Object { $_.LastWriteTimeUtc -gt $classTime } |
                Select-Object -First 1
    }
    if ($needsCompile) {
        Write-LauncherLog "Compiling updated Java sources."
        & (Join-Path $projectRoot "compile.ps1")
        if ($LASTEXITCODE -ne 0) {
            throw "The Java project could not be compiled. See launcher.log and compile from NetBeans for details."
        }
    }

    $existing = Get-CimInstance Win32_Process -Filter "Name='javaw.exe' OR Name='java.exe'" -ErrorAction SilentlyContinue |
            Where-Object { $_.CommandLine -like "*com.campus.lostandfound.UI.LoginPage*" } |
            Select-Object -First 1
    if ($existing) {
        Write-LauncherLog "Application is already running."
        exit 0
    }

    $javaw = (Get-Command javaw.exe -ErrorAction Stop).Source
    $classPath = "$projectRoot\build\classes;$projectRoot\src;$projectRoot\lib\*"
    Write-LauncherLog "Opening the application."
    Start-Process -FilePath $javaw `
            -ArgumentList "-Dfile.encoding=UTF-8", "-cp", "`"$classPath`"", "com.campus.lostandfound.UI.LoginPage" `
            -WorkingDirectory $projectRoot
} catch {
    Write-LauncherLog "ERROR: $($_.Exception.Message)"
    Show-LauncherError $_.Exception.Message
    exit 1
}
