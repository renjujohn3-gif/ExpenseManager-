# Expense Manager — GitHub Actions Ready

A cloud-build-ready Android Expense Manager project for building an installable APK directly from GitHub Actions using an Android phone.

## Features
- Manual expense entry
- Local SQLite storage
- Expense total and recent expenses
- QR/barcode camera scanner
- GPay transaction text paste/share workflow

## GPay limitation
Android apps cannot silently read another app's private GPay payment history. This app therefore supports manual entry plus a paste/share workflow for GPay transaction details.

## Required GitHub structure
After uploading the project CONTENTS to your GitHub repository, the repository root must look like this:

```text
ExpenseManager/
├── .github/
│   └── workflows/
│       └── build-apk.yml
├── app/
├── .gitignore
├── .gitattributes
├── build.gradle
├── gradle.properties
├── settings.gradle
└── README.md
```

## Build from an Android phone
1. Extract this ZIP.
2. Open the extracted `ExpenseManager` folder.
3. In your Android file manager, enable **Show hidden files** if `.gitignore` or `.github` is not visible.
4. In GitHub, create/open your repository.
5. Upload the CONTENTS of `ExpenseManager`, not the ZIP itself.
6. Confirm that `.github/workflows/build-apk.yml` exists at the repository root.
7. Open **Actions** → **Build Expense Manager APK**.
8. Tap **Run workflow** if needed.
9. Wait for the workflow to finish.
10. Open the completed run and download the artifact **expense-manager-debug-apk**.
11. Extract the artifact and install `app-debug.apk` on your Android phone.

## Important
The GitHub workflow installs Gradle 8.7 on the runner, so a Gradle wrapper JAR is not required in this ZIP.
