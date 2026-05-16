[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
$ErrorActionPreference = 'Stop'

# Define paths
$mavenVersion = "3.9.6"
$mavenUrl = "https://archive.apache.org/dist/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip"
$zipPath = "$pwd\apache-maven.zip"
$extractPath = "$pwd\maven"
$mavenBin = "$extractPath\apache-maven-$mavenVersion\bin"

# Check if Maven is already downloaded and extracted
if (-not (Test-Path "$mavenBin\mvn.cmd")) {
    Write-Host "Downloading Maven..."
    Invoke-WebRequest -Uri $mavenUrl -OutFile $zipPath
    Write-Host "Extracting Maven..."
    Expand-Archive -Path $zipPath -DestinationPath $extractPath -Force
}

# Add Maven to Path for this session
$env:Path = "$mavenBin;" + $env:Path

Write-Host "Building project with Maven..."
mvn clean package

if ($LASTEXITCODE -eq 0) {
    Write-Host "Build successful. Running application..."
    Start-Process -FilePath "java" -ArgumentList "-jar `"target/BankManagementSystem-1.0-SNAPSHOT.jar`""
} else {
    Write-Host "Build failed."
}
