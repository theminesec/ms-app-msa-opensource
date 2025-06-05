# Application Rebranding Guideline

## 1. Prepare Development Environment
### 1.1 Set compile environment to jdk8
> **Note**: the source code project is built based on JDK8. Please kindly choose JDK8 to compile the project
Download jdk8: https://adoptium.net/temurin/releases/?version=8    (choose Windows x64 and .msi) <br>
Run the Installer <br>

Go to `File` > `Settings` > `Build, Execution, Deployment` > `Build Tools` > `Gradle`
![Screenshot 2025-05-09 144326](https://github.com/user-attachments/assets/3a3fc2d5-584c-404b-b45b-e4ce7b426027)
Select `Gradle JDK` <br>
select `Add JDK from disk...` <br>

Locate and select the file that you just installed.<br>
![Screenshot 2025-05-09 144422](https://github.com/user-attachments/assets/bf57357f-6839-4b6f-a5e2-657aae370596)
Click `Sync Now` after configuration.

<br>

### 1.2 Setup the maven credential
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
The `token` will be managed by our customer support team, if you want to request one please [contact us](mailto:support@theminesec.com?subject=Request%20for%20registry%20credential) <br>
<br>

## 2. Update Application Information
Update the basic information of your application, such as the **package name** and **version number**.
### 2.1 Update package name and applicationId
Edit `namespace` and `applicationId` in `app/build.gradle.kts` file <br>

```kotlin
Example:
namespace = "com.minesec.b"
applicationId = "com.minesec.b"
```
Refactor the package name (highlighted in blue) to match your `namespace` and `applicationId` <br>
![image](https://github.com/user-attachments/assets/e7553471-be5b-4399-b0f1-31b6e58ed895)<br>

`app/google-services.json`: <br>
This json file is configured in the firebase (google) and download from google firebase console. You have to login to your firebase to configure your own application information(applicationId) and then generate the json file

### 2.2 Update application version
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

### 2.3 Replace license
Replace the license file in your application project with the appropriate one.
> **Note**: The required LICENSE file will be provided by MineSec. Please contact us if you have not received it before you start rebranding the app.

License file is found in the `assets` folder.

![image](https://github.com/user-attachments/assets/085a0128-19ae-4ffb-9ad1-93d87def0744)

In `app/build.gradle.kts`, modify the string to match the name of your license:

![image](https://github.com/user-attachments/assets/6a19bc9e-19f3-4c1a-b7ab-5be86deff6e3)


## 3. Branding & Visual Adjustment
### 3.1 Replace the logo
- **Full Logo**: Displayed on the activation UI page.
- **Square Logo**: Used in some loading indicators.
- Recommended sizes:
    - **Full Logo**: `240 × 60`
    - **Square Logo**: `60 × 60`
- Place these vector files in both the `drawable` and `drawable-night` folders in your application.


## 3. Branding & Visual Adjustment
### 3.1 Replace the logo
- **Full Logo**: Displayed on the activation UI page.
- **Square Logo**: Used in some loading indicators.
- Recommended sizes:
    - **Full Logo**: `240 × 60`
    - **Square Logo**: `60 × 60`
- Place these vector files in both the `drawable` and `drawable-night` folders in your application.

### 3.1 Change application logos
Convert your image from PNG to SVG before importing it into Android Studio. This allows you to resize the image without losing quality.<br>
In Android Studio, click `View` > `Tool Windows` > `Resource Manager` <br>
Click `+` > `Vector Asset`> click `Local file(SVG,PSD)` if you want to import images from your computer <br>
Adjust the size and opacity to meet your requirements(maintain the original proportions to avoid distortion) <br>
Copy the code and paste it in `logo_full.xml` or `logo_square.xml` ( found in app/src/main/res/drawable and app/src/main/res/drawable-night)


### 3.2 Update Color and theme
Go to `app\src\main\res\values` and `app\src\main\res\values-night`<br>
Modify the `colors.xml` file with your own color scheme(change the color code (#??????)). <br>
> **Note**: If you are using color with alpha(transparency),note that Android Studio expects the ARGB format (#AARRGGBB) <br>
For example : #0A6AC8 (70% transparency) is #B30A6AC8 in Android Studio

Go to settings on your phone to switch between `light mode` and `dark mode`

## 4. Change app name and localization
### 4.1 Change app name
Edit both `strings.xml` files in `app/src/main/res/values/strings.xml` and `app/src/debug/res/values/strings.xml` <br>
```
<string name="app_name">your_app_name</string>
```
### 4.2 Update Strings for Localization

#### Change the language in the app, we have preconfigured several language options:
Click the icon on the top left corner<br>![image](https://github.com/user-attachments/assets/401df812-d134-4c79-b6e4-c3ede1658bbc) <br>
Click on `Settings` <br>
Scroll to the bottom<br>![image](https://github.com/user-attachments/assets/82a18e59-af2f-4b91-8452-7ae852983582) <br>
Click on `English` to change the language

#### If your language is not listed:
Go to `app/src/main/res/values/strings.xml`<br>
Click on `Open editor`
![image](https://github.com/user-attachments/assets/48afaa50-d71e-4e5f-9aa3-0e8e757b17e5)
Click on the icon 
![image](https://github.com/user-attachments/assets/934a9487-33a8-4dd1-9409-8ac59cda67b1) <br>
Select the language you want to add <br>
Ensure that your device is set to the language that you just added. Close the app completely (swipe it away from recent apps) and reopen it.

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


Copy `strings.xml` from `app/src/main/res/values/` and paste into `strings.xml` from `app/src/main/res/values-xx/` <br>
Translate ALL strings

```
#Example:
#English (values/strings.xml)
<string name="login">Login</string>

#Chinese (values-zh/strings.xml)
<string name="login">登录</string>
```

> **Note**: Some error messages are generated by the backend server and cannot be customized into local language for now.


## 5. Customize Application Features
Modify the `App.kt` file to configure and customize the features of your application.


## 6. Building the app for release
`Build`>`Generate Signed App Bundle or APK...`

![image](https://github.com/user-attachments/assets/fe2a1392-af3d-4345-b36b-5e6d3c856ce7)

### 6.1 Generate aab file
Select the `Android App Bundle`radio button (first option)
### 6.2 Generate apk file
Select the `APK` radio button (second option)

### 6.3 If you do not have a key store
Click `Create new...` and Android Studio will guide you through the process of creating a key store

### 6.4 If you already have an existing key store
Click `Choose existing...` and select the key store that you created before

Make sure your credentials in machine's global `gradle.properties` matches the key store that you are using.
Fill in the  `Keystore password`, `key alias` and `Key password` 
Click `Next`
Choose `release`
Click `Create`








