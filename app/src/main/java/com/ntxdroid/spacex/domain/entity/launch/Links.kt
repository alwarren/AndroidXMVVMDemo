package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("mission_patch")
    val missionPatch: String?,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String?,
    @SerializedName("reddit_campaign")
    val redditCampaign: String?,
    @SerializedName("reddit_launch")
    val redditLaunch: String?,
    @SerializedName("reddit_recovery")
    val redditRecovery: String?,
    @SerializedName("reddit_media")
    val redditMedia: String?,
    val presskit: String?,
    @SerializedName("article_link")
    val articleLink: String?,
    val wikipedia: String?,
    @SerializedName("video_link")
    val videoLink: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?,
    @SerializedName("flickr_images")
    val flickrImages: List<String>
) {
    companion object {
        val empty = Links(null, null, null, null, null,
            null, null, null, null, null, null, emptyList())    }
}