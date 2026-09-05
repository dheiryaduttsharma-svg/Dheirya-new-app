# NK ASHOKA BUS SERVICE GAGRET — Fleet App v2

Android app starter for ADMIN + one STAFF member.

Categories:
- GREASE
- SERVICE
- TYRE
- GPS
- DIESEL
- REPAIR
- OTHER
- ALL RECORDS

The app stores records locally on the phone. The current version does **not** provide automatic online STAFF → ADMIN syncing or real PDF generation; those require a cloud backend/export implementation.

## Build APK without a PC
1. Create a GitHub repository and upload all files/folders from this project.
2. Open the repository → **Actions**.
3. Select **Build NK Ashoka APK**.
4. Tap **Run workflow** (or push to `main`, which starts it automatically).
5. When the workflow finishes, open the run and scroll to **Artifacts**.
6. Download **NK-Ashoka-Fleet-App-debug** and extract the APK.

## Build with Android Studio
Open the project folder and build `app` → `assembleDebug`.
