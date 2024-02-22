package com.bkmzdev.childdrawer

import android.content.Context
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.instream.MobileInstreamAds

class AdSDK {
    fun load_banner_ad(context: Context, banner: BannerAdView, banner_ad_id:String){
            MobileInstreamAds.setAdGroupPreloading(true)
            MobileAds.enableLogging(true)
            banner.setAdUnitId(banner_ad_id)// BANER
            banner.setAdSize(BannerAdSize.fixedSize(context, 320, 70))
            val adRequest: AdRequest = AdRequest.Builder().build()
            banner.run {
                loadAd(adRequest) }
    }
}