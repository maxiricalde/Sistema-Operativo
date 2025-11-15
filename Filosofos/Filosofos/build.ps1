param(
    [string]$JdkPath
)

# Determine javac to use
if ($JdkPath) {
    $javac = Join-Path $JdkPath 'bin\javac.exe'
    $java   = Join-Path $JdkPath 'bin\java.exe'
} else {
    $javac = 'javac'
    $java  = 'java'
}

Write-Host "Using javac: $javac"

try {
    $versionOutput = & $javac -version 2>&1
} catch {
    Write-Error "Failed to run javac. Ensure JDK 21 is installed or pass -JdkPath. $_"
    exit 1
}

Write-Host "javac reported: $versionOutput"

if ($versionOutput -notmatch '21') {
    Write-Warning "Detected javac does not appear to be Java 21. Continuing but consider passing -JdkPath or updating JAVA_HOME."
}

$srcDir = Join-Path $PSScriptRoot 'src'
$binDir = Join-Path $PSScriptRoot 'bin'

if (-not (Test-Path $binDir)) { New-Item -ItemType Directory -Path $binDir | Out-Null }

$javaFiles = Get-ChildItem -Path $srcDir -Recurse -Filter '*.java' | ForEach-Object { $_.FullName }
if ($javaFiles.Count -eq 0) { Write-Host "No .java files found under $srcDir"; exit 0 }

Write-Host "Compiling source files to $binDir..."

# Try --release 21 first
try {
    & $javac --release 21 -d $binDir @javaFiles
    if ($LASTEXITCODE -eq 0) { Write-Host "Compilation succeeded."; exit 0 }
    Write-Warning "--release 21 compile failed (exit code $LASTEXITCODE). Trying fallback flags."
} catch {
    Write-Warning "--release attempt threw an error: $_. Trying fallback flags."
}

# Fallback: -source/-target
try {
    & $javac -source 21 -target 21 -d $binDir @javaFiles
    if ($LASTEXITCODE -eq 0) { Write-Host "Compilation succeeded with -source/-target."; exit 0 }
    Write-Error "Compilation failed with -source/-target (exit code $LASTEXITCODE)."
    exit $LASTEXITCODE
} catch {
    Write-Error "Compilation failed: $_"
    exit 1
}
