package com.blblblbl.myapplication.data.data_classes.responses.me

import com.google.gson.annotations.SerializedName


data class MeResponse (

  @SerializedName("is_employee"                ) var isEmployee              : Boolean?          = null,
  @SerializedName("seen_layout_switch"         ) var seenLayoutSwitch        : Boolean?          = null,
  @SerializedName("has_visited_new_profile"    ) var hasVisitedNewProfile    : Boolean?          = null,
  @SerializedName("pref_no_profanity"          ) var prefNoProfanity         : Boolean?          = null,
  @SerializedName("has_external_account"       ) var hasExternalAccount      : Boolean?          = null,
  @SerializedName("pref_geopopular"            ) var prefGeopopular          : String?           = null,
  @SerializedName("seen_redesign_modal"        ) var seenRedesignModal       : Boolean?          = null,
  @SerializedName("pref_show_trending"         ) var prefShowTrending        : Boolean?          = null,
  @SerializedName("subreddit"                  ) var subreddit               : Subreddit?        = Subreddit(),
  @SerializedName("pref_show_presence"         ) var prefShowPresence        : Boolean?          = null,
  @SerializedName("snoovatar_img"              ) var snoovatarImg            : String?           = null,
  @SerializedName("snoovatar_size"             ) var snoovatarSize           : String?           = null,
  @SerializedName("gold_expiration"            ) var goldExpiration          : String?           = null,
  @SerializedName("has_gold_subscription"      ) var hasGoldSubscription     : Boolean?          = null,
  @SerializedName("is_sponsor"                 ) var isSponsor               : Boolean?          = null,
  @SerializedName("num_friends"                ) var numFriends              : Int?              = null,
  @SerializedName("features"                   ) var features                : Features?         = Features(),
  @SerializedName("can_edit_name"              ) var canEditName             : Boolean?          = null,
  @SerializedName("verified"                   ) var verified                : Boolean?          = null,
  @SerializedName("new_modmail_exists"         ) var newModmailExists        : String?           = null,
  @SerializedName("pref_autoplay"              ) var prefAutoplay            : Boolean?          = null,
  @SerializedName("coins"                      ) var coins                   : Int?              = null,
  @SerializedName("has_paypal_subscription"    ) var hasPaypalSubscription   : Boolean?          = null,
  @SerializedName("has_subscribed_to_premium"  ) var hasSubscribedToPremium  : Boolean?          = null,
  @SerializedName("id"                         ) var id                      : String?           = null,
  @SerializedName("has_stripe_subscription"    ) var hasStripeSubscription   : Boolean?          = null,
  @SerializedName("oauth_client_id"            ) var oauthClientId           : String?           = null,
  @SerializedName("can_create_subreddit"       ) var canCreateSubreddit      : Boolean?          = null,
  @SerializedName("over_18"                    ) var over18                  : Boolean?          = null,
  @SerializedName("is_gold"                    ) var isGold                  : Boolean?          = null,
  @SerializedName("is_mod"                     ) var isMod                   : Boolean?          = null,
  @SerializedName("awarder_karma"              ) var awarderKarma            : Int?              = null,
  @SerializedName("suspension_expiration_utc"  ) var suspensionExpirationUtc : String?           = null,
  @SerializedName("has_verified_email"         ) var hasVerifiedEmail        : Boolean?          = null,
  @SerializedName("is_suspended"               ) var isSuspended             : Boolean?          = null,
  @SerializedName("pref_video_autoplay"        ) var prefVideoAutoplay       : Boolean?          = null,
  @SerializedName("has_android_subscription"   ) var hasAndroidSubscription  : Boolean?          = null,
  @SerializedName("in_redesign_beta"           ) var inRedesignBeta          : Boolean?          = null,
  @SerializedName("icon_img"                   ) var iconImg                 : String?           = null,
  @SerializedName("has_mod_mail"               ) var hasModMail              : Boolean?          = null,
  @SerializedName("pref_nightmode"             ) var prefNightmode           : Boolean?          = null,
  @SerializedName("awardee_karma"              ) var awardeeKarma            : Int?              = null,
  @SerializedName("hide_from_robots"           ) var hideFromRobots          : Boolean?          = null,
  @SerializedName("password_set"               ) var passwordSet             : Boolean?          = null,
  @SerializedName("link_karma"                 ) var linkKarma               : Int?              = null,
  @SerializedName("force_password_reset"       ) var forcePasswordReset      : Boolean?          = null,
  @SerializedName("total_karma"                ) var totalKarma              : Int?              = null,
  @SerializedName("seen_give_award_tooltip"    ) var seenGiveAwardTooltip    : Boolean?          = null,
  @SerializedName("inbox_count"                ) var inboxCount              : Int?              = null,
  @SerializedName("seen_premium_adblock_modal" ) var seenPremiumAdblockModal : Boolean?          = null,
  @SerializedName("pref_top_karma_subreddits"  ) var prefTopKarmaSubreddits  : Boolean?          = null,
  @SerializedName("has_mail"                   ) var hasMail                 : Boolean?          = null,
  @SerializedName("pref_show_snoovatar"        ) var prefShowSnoovatar       : Boolean?          = null,
  @SerializedName("name"                       ) var name                    : String?           = null,
  @SerializedName("pref_clickgadget"           ) var prefClickgadget         : Int?              = null,
  @SerializedName("created"                    ) var created                 : Int?              = null,
  @SerializedName("gold_creddits"              ) var goldCreddits            : Int?              = null,
  @SerializedName("created_utc"                ) var createdUtc              : Int?              = null,
  @SerializedName("has_ios_subscription"       ) var hasIosSubscription      : Boolean?          = null,
  @SerializedName("pref_show_twitter"          ) var prefShowTwitter         : Boolean?          = null,
  @SerializedName("in_beta"                    ) var inBeta                  : Boolean?          = null,
  @SerializedName("comment_karma"              ) var commentKarma            : Int?              = null,
  @SerializedName("accept_followers"           ) var acceptFollowers         : Boolean?          = null,
  @SerializedName("has_subscribed"             ) var hasSubscribed           : Boolean?          = null,
  @SerializedName("linked_identities"          ) var linkedIdentities        : ArrayList<String> = arrayListOf(),
  @SerializedName("seen_subreddit_chat_ftux"   ) var seenSubredditChatFtux   : Boolean?          = null

)