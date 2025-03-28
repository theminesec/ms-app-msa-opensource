package com.minesec.msa

import com.minesec.appmain.ComponentApplication
import com.minesec.appmain.store.ApplicationConfigOpts
import com.minesec.appmain.store.PaymentMethodStore
import com.minesec.msa.client.sdk.payment.PaymentMethods

class App : ComponentApplication() {
    companion object {
        init {
            ApplicationConfigOpts.merchantName = "HK-Kwolong-711"
            ApplicationConfigOpts.merchantId = "M1655978292"
            ApplicationConfigOpts.terminalId = "M3FE3E232G"
            // Payment host base url
            ApplicationConfigOpts.payHostUrl = BuildConfig.srsBaseUrl
            // TMS host base url
            ApplicationConfigOpts.tmsBaseUrl = BuildConfig.tmsBaseUrl
            // License file name
            ApplicationConfigOpts.license = BuildConfig.License
            // If your host support upload the e-signature ,you can enable it
            ApplicationConfigOpts.isSupportElectronicSignature = false
            // If you need to support other payment methods,just like QR
            // If you enable it ,you have to specify your QR payment mode and put ApplicationConfigOpts.isEnableCustomizedMultipleQR = true
            // Specify your ApplicationConfigOpts.qrPaymentScanMode:Merchant Scan only, Guest Scan only or both.
            ApplicationConfigOpts.isSupportPaymentMethodSelection = false
            // If you need to enable screenshot in application,default is false
            ApplicationConfigOpts.isEnableScreenshot = true
            // If you need to disable settlement
            //ApplicationConfigOpts.isEnableSettlement = false
            // If you need to hide the settle all button
            // ApplicationConfigOpts.isEnableSettleAll = false
            // If you need to disable auth transaction
            // ApplicationConfigOpts.isEnablePreAuth = false
            // If you need to disable the card refund in navigation menu
            // ApplicationConfigOpts.isEnableCardRefund = false
            // If you need to disable the refund in history
            // ApplicationConfigOpts.isEnableRefund = false
            // If you need to disable the void in history
            // ApplicationConfigOpts.isEnableVoid = false
            // if you need to disable the card retrieve refund in history (long press button)
            // ApplicationConfigOpts.isEnableRetrievalRefund = false
            // if you need to enable refund for QR transaction in history
            // ApplicationConfigOpts.isEnableQrCodeRefund = true
            // If you need to mask the pan format with fist 6 and last 4
            // ApplicationConfigOpts.isMaskPanFormatWith64 = true
            // If you need to enable POS reference ID entry
            // ApplicationConfigOpts.isEnablePOSReferenceEntry = true
            // If you need to enable GEO-fencing feature
            // ApplicationConfigOpts.isEnableGeoFencing = true
            // If you need to specify your own customized payment methods
//            var paymentMethodModelList = listOf(
//                PaymentMethodStore.Model("VISA", PaymentMethods.VISA_BRAND.code),
//                PaymentMethodStore.Model("MC", PaymentMethods.MC_BRAND.code),
//                PaymentMethodStore.Model("UPI", PaymentMethods.UPI_BRAND.code),
//                PaymentMethodStore.Model("AMEX", PaymentMethods.AMEX_BRAND.code),
//                PaymentMethodStore.Model("JCB", PaymentMethods.JCB_BRAND.code),
//                PaymentMethodStore.Model("DISCOVER", PaymentMethods.DISCOVER_BRAND.code),
//                PaymentMethodStore.Model("DINERS", PaymentMethods.DINERS_CARD.code),
//                PaymentMethodStore.Model("HUAWEI PAY", PaymentMethods.HUAWEI_PAY.code),
//                PaymentMethodStore.Model("SAMSUNG PAY", PaymentMethods.SAMSUNG_PAY.code),
//                PaymentMethodStore.Model("APPLE PAY", PaymentMethods.APPLE_PAY.code),
//                PaymentMethodStore.Model("GOOGLE PAY", PaymentMethods.GOOGLE_PAY.code)
//            )
        }
    }
}