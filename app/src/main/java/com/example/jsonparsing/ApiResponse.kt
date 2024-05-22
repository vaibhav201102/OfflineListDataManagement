package com.example.jsonparsing

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status"            ) var status          : Int?                 = null,
    @SerializedName("message"           ) var message         : String?              = null,
    @SerializedName("data"              ) var data            : ArrayList<Data>      = arrayListOf(),
    @SerializedName("app_center"        ) var appCenter       : ArrayList<AppCenter> = arrayListOf(),
    @SerializedName("home"              ) var home            : ArrayList<Home>      = arrayListOf(),
    @SerializedName("more_apps"         ) var moreApps        : ArrayList<MoreApps>  = arrayListOf(),
    @SerializedName("native_add"        ) var nativeAdd       : NativeAdd?           = NativeAdd(),
    @SerializedName("translator_ads_id" ) var translatorAdsId : TranslatorAdsId?     = TranslatorAdsId()
){
    data class Data (

        @SerializedName("id"               ) var id             : Int?    = null,
        @SerializedName("app_id"           ) var appId          : Int?    = null,
        @SerializedName("position"         ) var position       : Int?    = null,
        @SerializedName("name"             ) var name           : String? = null,
        @SerializedName("thumb_image"      ) var thumbImage     : String? = null,
        @SerializedName("app_link"         ) var appLink        : String? = null,
        @SerializedName("package_name"     ) var packageName    : String? = null,
        @SerializedName("full_thumb_image" ) var fullThumbImage : String? = null,
        @SerializedName("splash_active"    ) var splashActive   : Int?    = null

    )

    data class SubCategory (

        @SerializedName("id"              ) var id             : Int?    = null,
        @SerializedName("app_id"          ) var appId          : Int?    = null,
        @SerializedName("position"        ) var position       : Int?    = null,
        @SerializedName("name"            ) var name           : String? = null,
        @SerializedName("icon"            ) var icon           : String? = null,
        @SerializedName("star"            ) var star           : String? = null,
        @SerializedName("installed_range" ) var installedRange : String? = null,
        @SerializedName("app_link"        ) var appLink        : String? = null,
        @SerializedName("banner"          ) var banner         : String? = null,
        @SerializedName("is_active"       ) var isActive       : Int?    = null,
        @SerializedName("image_active"    ) var imageActive    : Int?    = null,
        @SerializedName("banner_image"    ) var bannerImage    : String? = null,


    )

    data class AppCenter (

        @SerializedName("id"           ) var id          : Int?                   = null,
        @SerializedName("position"     ) var position    : Int?                   = null,
        @SerializedName("name"         ) var name        : String?                = null,
        @SerializedName("is_active"    ) var isActive    : Int?                   = null,
        @SerializedName("catgeory"     ) var catgeory    : String?                = null,
        @SerializedName("sub_category" ) var subCategory : ArrayList<SubCategory> = arrayListOf()

    )

    data class Home (

        @SerializedName("id"           ) var id          : Int?                   = null,
        @SerializedName("position"     ) var position    : Int?                   = null,
        @SerializedName("name"         ) var name        : String?                = null,
        @SerializedName("is_active"    ) var isActive    : Int?                   = null,
        @SerializedName("catgeory"     ) var catgeory    : String?                = null,
        @SerializedName("sub_category" ) var subCategory : ArrayList<SubCategory> = arrayListOf()

    )

    data class MoreApps (

        @SerializedName("id"           ) var id          : Int?                   = null,
        @SerializedName("position"     ) var position    : Int?                   = null,
        @SerializedName("name"         ) var name        : String?                = null,
        @SerializedName("is_active"    ) var isActive    : Int?                   = null,
        @SerializedName("catgeory"     ) var catgeory    : String?                = null,
        @SerializedName("sub_category" ) var subCategory : ArrayList<SubCategory> = arrayListOf()

    )

    data class NativeAdd (

        @SerializedName("id"             ) var id            : Int?    = null,
        @SerializedName("position"       ) var position      : Int?    = null,
        @SerializedName("image"          ) var image         : String? = null,
        @SerializedName("playstore_link" ) var playstoreLink : String? = null,
        @SerializedName("package_name"   ) var packageName   : String? = null,
        @SerializedName("is_active"      ) var isActive      : Int?    = null,
        @SerializedName("image_active"   ) var imageActive   : Int?    = null

    )

    data class TranslatorAdsId (

        @SerializedName("banner"       ) var banner       : String? = null,
        @SerializedName("interstitial" ) var interstitial : String? = null

    )
}
