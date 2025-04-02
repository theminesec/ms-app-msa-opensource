# ms-app-msa-opensource
# Application Setup Guide

## 1. Setup the maven credential
> **Note**: You might want to set the credential in your machine's global `gradle.properties` instead of the project's `gradle.properties` to avoid accidentally committing it into git.

In your machine's global gradle properties:

```text file="~/.gradle/gradle.properties"
# minesec client registry
MINESEC_REGISTRY_LOGIN=minesec-product-support
MINESEC_REGISTRY_TOKEN={token-value}
```
The `token` will be managed by our customer support team, if you want to request one please [contact us](mailto:support@theminesec.com?subject=Request%20for%20registry%20credential)


## 2. Update Application Information
Update the basic information of your application, such as the **package name** and **version number**.

## 3. Replace the License File
Replace the license file in your application project with the appropriate one.

## 4. Prepare Application Logos
- **Full Logo**: Displayed on the activation UI page.
- **Square Logo**: Used in some loading indicators.
- Recommended sizes:
    - **Full Logo**: `240 × 60`
    - **Square Logo**: `60 × 60`
- Place these vector files in both the `drawable` and `drawable-night` folders in your application.

## 5. Update Colors
Modify the `colors.xml` file with your own color scheme. Make sure to update both the `values` and `values-night` folders.

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

## 7. Customize Application Features
Modify the `App.kt` file to configure and customize the features of your application.