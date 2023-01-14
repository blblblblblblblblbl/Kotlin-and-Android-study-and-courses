package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.google.gson.annotations.SerializedName

data class PostCommentData (

    @SerializedName("approved_at_utc"               ) var approvedAtUtc              : String?           = null,
    @SerializedName("subreddit"                     ) var subreddit                  : String?           = null,
    @SerializedName("selftext"                      ) var selftext                   : String?           = null,
    @SerializedName("user_reports"                  ) var userReports                : ArrayList<String> = arrayListOf(),
    @SerializedName("saved"                         ) var saved                      : Boolean?          = null,
    @SerializedName("mod_reason_title"              ) var modReasonTitle             : String?           = null,
    @SerializedName("gilded"                        ) var gilded                     : Int?              = null,
    @SerializedName("clicked"                       ) var clicked                    : Boolean?          = null,
    @SerializedName("title"                         ) var title                      : String?           = null,
    @SerializedName("link_flair_richtext"           ) var linkFlairRichtext          : ArrayList<String> = arrayListOf(),
    @SerializedName("subreddit_name_prefixed"       ) var subredditNamePrefixed      : String?           = null,
    @SerializedName("hidden"                        ) var hidden                     : Boolean?          = null,
    @SerializedName("pwls"                          ) var pwls                       : Int?              = null,
    @SerializedName("link_flair_css_class"          ) var linkFlairCssClass          : String?           = null,
    @SerializedName("downs"                         ) var downs                      : Int?              = null,
    @SerializedName("thumbnail_height"              ) var thumbnailHeight            : Int?              = null,
    @SerializedName("top_awarded_type"              ) var topAwardedType             : String?           = null,
    @SerializedName("parent_whitelist_status"       ) var parentWhitelistStatus      : String?           = null,
    @SerializedName("hide_score"                    ) var hideScore                  : Boolean?          = null,
    @SerializedName("name"                          ) var name                       : String?           = null,
    @SerializedName("quarantine"                    ) var quarantine                 : Boolean?          = null,
    @SerializedName("link_flair_text_color"         ) var linkFlairTextColor         : String?           = null,
    @SerializedName("upvote_ratio"                  ) var upvoteRatio                : Double?           = null,
    @SerializedName("author_flair_background_color" ) var authorFlairBackgroundColor : String?           = null,
    @SerializedName("subreddit_type"                ) var subredditType              : String?           = null,
    @SerializedName("ups"                           ) var ups                        : Int?              = null,
    @SerializedName("total_awards_received"         ) var totalAwardsReceived        : Int?              = null,
    @SerializedName("media_embed"                   ) var mediaEmbed                 : MediaEmbed?       = MediaEmbed(),
    @SerializedName("thumbnail_width"               ) var thumbnailWidth             : Int?              = null,
    @SerializedName("author_flair_template_id"      ) var authorFlairTemplateId      : String?           = null,
    @SerializedName("is_original_content"           ) var isOriginalContent          : Boolean?          = null,
    @SerializedName("author_fullname"               ) var authorFullname             : String?           = null,
    @SerializedName("secure_media"                  ) var secureMedia                : String?           = null,
    @SerializedName("is_reddit_media_domain"        ) var isRedditMediaDomain        : Boolean?          = null,
    @SerializedName("is_meta"                       ) var isMeta                     : Boolean?          = null,
    @SerializedName("category"                      ) var category                   : String?           = null,
    @SerializedName("secure_media_embed"            ) var secureMediaEmbed           : SecureMediaEmbed? = SecureMediaEmbed(),
    @SerializedName("link_flair_text"               ) var linkFlairText              : String?           = null,
    @SerializedName("can_mod_post"                  ) var canModPost                 : Boolean?          = null,
    @SerializedName("score"                         ) var score                      : Int?              = null,
    @SerializedName("approved_by"                   ) var approvedBy                 : String?           = null,
    @SerializedName("is_created_from_ads_ui"        ) var isCreatedFromAdsUi         : Boolean?          = null,
    @SerializedName("author_premium"                ) var authorPremium              : Boolean?          = null,
    @SerializedName("thumbnail"                     ) var thumbnail                  : String?           = null,
    @SerializedName("edited"                        ) var edited                     : Boolean?          = null,
    @SerializedName("author_flair_css_class"        ) var authorFlairCssClass        : String?           = null,
    @SerializedName("author_flair_richtext"         ) var authorFlairRichtext        : ArrayList<String> = arrayListOf(),
    @SerializedName("gildings"                      ) var gildings                   : Gildings?         = Gildings(),
    @SerializedName("post_hint"                     ) var postHint                   : String?           = null,
    @SerializedName("content_categories"            ) var contentCategories          : String?           = null,
    @SerializedName("is_self"                       ) var isSelf                     : Boolean?          = null,
    @SerializedName("mod_note"                      ) var modNote                    : String?           = null,
    @SerializedName("created"                       ) var created                    : Int?              = null,
    @SerializedName("link_flair_type"               ) var linkFlairType              : String?           = null,
    @SerializedName("wls"                           ) var wls                        : Int?              = null,
    @SerializedName("removed_by_category"           ) var removedByCategory          : String?           = null,
    @SerializedName("banned_by"                     ) var bannedBy                   : String?           = null,
    @SerializedName("author_flair_type"             ) var authorFlairType            : String?           = null,
    @SerializedName("domain"                        ) var domain                     : String?           = null,
    @SerializedName("allow_live_comments"           ) var allowLiveComments          : Boolean?          = null,
    @SerializedName("selftext_html"                 ) var selftextHtml               : String?           = null,
    @SerializedName("likes"                         ) var likes                      : String?           = null,
    @SerializedName("suggested_sort"                ) var suggestedSort              : String?           = null,
    @SerializedName("banned_at_utc"                 ) var bannedAtUtc                : String?           = null,
    @SerializedName("url_overridden_by_dest"        ) var urlOverriddenByDest        : String?           = null,
    @SerializedName("view_count"                    ) var viewCount                  : String?           = null,
    @SerializedName("archived"                      ) var archived                   : Boolean?          = null,
    @SerializedName("no_follow"                     ) var noFollow                   : Boolean?          = null,
    @SerializedName("is_crosspostable"              ) var isCrosspostable            : Boolean?          = null,
    @SerializedName("pinned"                        ) var pinned                     : Boolean?          = null,
    @SerializedName("over_18"                       ) var over18                     : Boolean?          = null,
    @SerializedName("preview"                       ) var preview                    : Preview?          = Preview(),
    @SerializedName("all_awardings"                 ) var allAwardings               : ArrayList<String> = arrayListOf(),
    @SerializedName("awarders"                      ) var awarders                   : ArrayList<String> = arrayListOf(),
    @SerializedName("media_only"                    ) var mediaOnly                  : Boolean?          = null,
    @SerializedName("can_gild"                      ) var canGild                    : Boolean?          = null,
    @SerializedName("spoiler"                       ) var spoiler                    : Boolean?          = null,
    @SerializedName("locked"                        ) var locked                     : Boolean?          = null,
    @SerializedName("author_flair_text"             ) var authorFlairText            : String?           = null,
    @SerializedName("treatment_tags"                ) var treatmentTags              : ArrayList<String> = arrayListOf(),
    @SerializedName("visited"                       ) var visited                    : Boolean?          = null,
    @SerializedName("removed_by"                    ) var removedBy                  : String?           = null,
    @SerializedName("num_reports"                   ) var numReports                 : String?           = null,
    @SerializedName("distinguished"                 ) var distinguished              : String?           = null,
    @SerializedName("subreddit_id"                  ) var subredditId                : String?           = null,
    @SerializedName("author_is_blocked"             ) var authorIsBlocked            : Boolean?          = null,
    @SerializedName("mod_reason_by"                 ) var modReasonBy                : String?           = null,
    @SerializedName("removal_reason"                ) var removalReason              : String?           = null,
    @SerializedName("link_flair_background_color"   ) var linkFlairBackgroundColor   : String?           = null,
    @SerializedName("id"                            ) var id                         : String?           = null,
    @SerializedName("is_robot_indexable"            ) var isRobotIndexable           : Boolean?          = null,
    @SerializedName("num_duplicates"                ) var numDuplicates              : Int?              = null,
    @SerializedName("report_reasons"                ) var reportReasons              : String?           = null,
    @SerializedName("author"                        ) var author                     : String?           = null,
    @SerializedName("discussion_type"               ) var discussionType             : String?           = null,
    @SerializedName("num_comments"                  ) var numComments                : Int?              = null,
    @SerializedName("send_replies"                  ) var sendReplies                : Boolean?          = null,
    @SerializedName("media"                         ) var media                      : String?           = null,
    @SerializedName("contest_mode"                  ) var contestMode                : Boolean?          = null,
    @SerializedName("author_patreon_flair"          ) var authorPatreonFlair         : Boolean?          = null,
    @SerializedName("author_flair_text_color"       ) var authorFlairTextColor       : String?           = null,
    @SerializedName("permalink"                     ) var permalink                  : String?           = null,
    @SerializedName("whitelist_status"              ) var whitelistStatus            : String?           = null,
    @SerializedName("stickied"                      ) var stickied                   : Boolean?          = null,
    @SerializedName("url"                           ) var url                        : String?           = null,
    @SerializedName("subreddit_subscribers"         ) var subredditSubscribers       : Int?              = null,
    @SerializedName("created_utc"                   ) var createdUtc                 : Int?              = null,
    @SerializedName("num_crossposts"                ) var numCrossposts              : Int?              = null,
    @SerializedName("mod_reports"                   ) var modReports                 : ArrayList<String> = arrayListOf(),
    @SerializedName("is_video"                      ) var isVideo                    : Boolean?          = null

)