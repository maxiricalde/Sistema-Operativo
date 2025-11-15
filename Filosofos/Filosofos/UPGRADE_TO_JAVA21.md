Filosofos — Upgrade to Java 21
================================

This document contains quick steps to compile and run the Filosofos project with Java 21.

1) Install JDK 21 and verify (see repository root UPGRADE_JAVA21.md for links)

2) From the project folder run the helper script:
   - In PowerShell (repo root):
     .\Filosofos\build.ps1
   - Or explicitly: .\Filosofos\build.ps1 -JdkPath "C:\\path\\to\\jdk-21"

3) Expected output: compiled .class files in the `bin` directory. Run with:
   - java -cp bin MainFilosofo

If compilation fails due to language features or APIs, share the error and I can help patch the code.
