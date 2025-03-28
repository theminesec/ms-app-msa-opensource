# ms-app-msa-opensource
# Application Setup Guide

## 1. Update Application Information
Update the basic information of your application, such as the **package name** and **version number**.

## 2. Replace the License File
Replace the license file in your application project with the appropriate one.

## 3. Prepare Application Logos
- **Full Logo**: Displayed on the activation UI page.
- **Square Logo**: Used in some loading indicators.
- Recommended sizes:
    - **Full Logo**: `240 × 60`
    - **Square Logo**: `60 × 60`
- Place these vector files in both the `drawable` and `drawable-night` folders in your application.

## 4. Update Colors
Modify the `colors.xml` file with your own color scheme. Make sure to update both the `values` and `values-night` folders.

## 5. Update Strings for Localization
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

## 6. Customize Application Features
Modify the `App.kt` file to configure and customize the features of your application.