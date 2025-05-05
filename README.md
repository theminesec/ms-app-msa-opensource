![image](https://github.com/user-attachments/assets/559ad407-7f4c-49bd-b3f7-8146aac56036)# ms-app-msa-opensource
# Application Setup Guide

## 1. Setup the maven credential
> **Note**: You might want to set the credential in your machine's global `gradle.properties` instead of the project's `gradle.properties` to avoid accidentally committing it into git.

In your machine's global gradle properties:

```text file="~/.gradle/gradle.properties"
# minesec client registry
GITHUB_USERNAME=minesec-product-support
GITHUB_TOKEN={token-value}
```
Replace the placeholder path with your actual keystore location:
```text file="~/.gradle/gradle.properties"
MS_KS_FILE=../keystore/your_keystore.jks

MS_KS_STORE_TYPE=jks

MS_KS_STORE_PASS=your_store_password

MS_KS_KEY_ALIAS=your_key_alias

MS_KS_KEY_PASS=your_key_password
```
The `token` will be managed by our customer support team, if you want to request one please [contact us](mailto:support@theminesec.com?subject=Request%20for%20registry%20credential)

## 2. Change jdk22 to jdk8
Download jdk8: https://adoptium.net/temurin/releases/?version=8    (.msi)

Change environment variables
![image](https://github.com/user-attachments/assets/552b40b3-4968-47cb-834c-db5b5792e5d2)



## 4. Update Application Information
Update the basic information of your application, such as the **package name** and **version number**.

### a. Package name
Edit `namespace` and `applicationId` in `app/build.gradle.kts` file <br>

```kotlin
Example:
namespace = "com.minesec.b"
applicationId = "com.minesec.b"
```
Refactor the package name (highlighted in blue) to match your `namespace` and `applicationId` <br>
![image](https://github.com/user-attachments/assets/e7553471-be5b-4399-b0f1-31b6e58ed895)<br>

app/google-services.json:
This json file is configured in the firebase (google) and download from google firebase console. You have to login to your firebase to configure your own application information(applicationId) and then generate the json file

### b. Version number
> **Note**: MineSec will provide the `customerId`
Edit  `app/google-services.json` <br>

```kotlin
//Example:
        // DO NOT CHANGE, re-cert require
        val majorVersion = 2
        // DO NOT CHANGE, re-cert require
        val minorVersion = 0
        // msa component lib version
        //val patchVersion: Int = 75
        val patchVersion: Int = libs.versions.mineSecMsaLibs.get().takeLast(3).takeLastWhile { it.isDigit() }.toInt()
        // license update, build config changes
        val buildVersion = 1
        // MineSec
        val customerId = 111 //changes were made
```

## 5. Replace the License File
Replace the license file in your application project with the appropriate one.
> **Note**: The required LICENSE file will be provided by MineSec.Please contact us if you have not received it before you start rebranding the app.

License file is found in the `assets` folder.

![image](https://github.com/user-attachments/assets/085a0128-19ae-4ffb-9ad1-93d87def0744)

In `app/build.gradle.kts`, modify the string to match the name of your license:

![image](https://github.com/user-attachments/assets/6a19bc9e-19f3-4c1a-b7ab-5be86deff6e3)

## 4. Prepare Application Logos
- **Full Logo**: Displayed on the activation UI page.
- **Square Logo**: Used in some loading indicators.
- Recommended sizes:
    - **Full Logo**: `240 × 60`
    - **Square Logo**: `60 × 60`
- Place these vector files in both the `drawable` and `drawable-night` folders in your application.

### Change application logos
In Android Studio, click `View` > `Tool Windows` > `Resource Manager` <br>
Click `+` > `Import Drawables` to open a file browser. <br>
![image](https://github.com/user-attachments/assets/cc2ab0f2-ebaf-4667-b8b7-d1bc11ffdf06)<br>
Go to the folder that you save your logos <br>
Click on it , click ok <br>
Import your logo

For full logo, edit `logo_full.xml`
```
<bitmap android:src="@drawable/your_full_logo_name"
```
For square logo, edit `logo_square.xml`
```
<bitmap android:src="@drawable/your_square_logo_name"
```

## 5. Update Colors
Go to `app\src\main\res\values` and `app\src\main\res\values-night`<br>
Modify the `colors.xml` file with your own color scheme(change the color code (#??????)). <br>
Go to settings on your phone to switch between `light mode` and `dark mode`

## 6. Update Strings for Localization
Customize the `strings.xml` file with your own information. Ensure support for multiple languages by updating the following directories:
- `values` (default)
- `values-ar` (Arabic)
- `values-de` (German)
- `values-es` (Spanish)
- `values-nl` (Dutch)
- `values-pt` (Portuguese)
- `values-fr` (French)
- `values-zh` (Chinese)
- `values-ckb` (Kurdish)
### Change app name
Edit both `strings.xml` files in `app/src/main/res/values/strings.xml` and `app/src/debug/res/values/strings.xml` <br>
```
<string name="app_name">your_app_name</string>
```

## 7. Customize Application Features
Modify the `App.kt` file to configure and customize the features of your application.
