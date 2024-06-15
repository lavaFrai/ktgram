package ru.lavafrai.ktgram.types.inline.inlineQueryResult

import WebAppInfo
import ru.lavafrai.ktgram.types.inline.InlineQuery
import ru.lavafrai.ktgram.types.inline.inputMessageContent.InputMessageContent
import ru.lavafrai.ktgram.types.media.MessageEntity
import ru.lavafrai.ktgram.types.replymarkup.inlineKeyboard.InlineKeyboardMarkup


class InlineQueryAnswerEnvironment() {
    val result: MutableList<InlineQueryResult> = mutableListOf()

    /**
     * @param id Unique identifier for this result, 1-64 Bytes
     * @param title Title of the result
     * @param inputMessageContent Content of the message to be sent
     * @param replyMarkup Inline keyboard attached to the message
     * @param url URL of the result
     * @param hideUrl Pass True if you don't want the URL to be shown in the message
     * @param description Short description of the result
     * @param thumbnailUrl Url of the thumbnail for the result
     * @param thumbnailWidth Thumbnail width
     * @param thumbnailHeight Thumbnail height
     */
    fun article(
        title: String,
        inputMessageContent: InputMessageContent,
        replyMarkup: InlineKeyboardMarkup? = null,
        url: String? = null,
        hideUrl: Boolean? = null,
        description: String? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null,
        id: String = title.takeLast(64),
    ) {
        add(
            InlineQueryResultArticle(
                id = id,
                title = title,
                inputMessageContent = inputMessageContent,
                replyMarkup = replyMarkup,
                url = url,
                hideUrl = hideUrl,
                description = description,
                thumbnailUrl = thumbnailUrl,
                thumbnailWidth = thumbnailWidth,
                thumbnailHeight = thumbnailHeight
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param audioUrl A valid URL for the audio file
     * @param title Title
     * @param caption Caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the audio caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param performer Performer
     * @param audioDuration Audio duration in seconds
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the audio
     */
    fun audio(
        audioUrl: String,
        title: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        performer: String? = null,
        audioDuration: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = audioUrl.takeLast(64),
    ) {
        add(
            InlineQueryResultAudio(
                id = id,
                audioUrl = audioUrl,
                title = title,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                performer = performer,
                audioDuration = audioDuration,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param audioFileId A valid file identifier for the audio file
     * @param caption Caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the audio caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the audio
     */
    fun cachedAudio(
        audioFileId: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = audioFileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedAudio(
                id = id,
                audioFileId = audioFileId,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param title Title for the result
     * @param documentFileId A valid file identifier for the file
     * @param description Short description of the result
     * @param caption Caption of the document to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the document caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the file
     */
    fun cachedDocument(
        title: String,
        documentFileId: String,
        description: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = documentFileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedDocument(
                id = id,
                title = title,
                documentFileId = documentFileId,
                description = description,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param gifFileId A valid file identifier for the GIF file
     * @param title Title for the result
     * @param caption Caption of the GIF file to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the GIF animation
     */
    fun cachedGif(
        gifFileId: String,
        title: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = gifFileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedGif(
                id = id,
                gifFileId = gifFileId,
                title = title,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                showCaptionAboveMedia = showCaptionAboveMedia,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param mpeg4FileId A valid file identifier for the MPEG4 file
     * @param title Title for the result
     * @param caption Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the video animation
     */
    fun cachedMpeg4Gif(
        mpeg4FileId: String,
        title: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = mpeg4FileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedMpeg4Gif(
                id = id,
                mpeg4FileId = mpeg4FileId,
                title = title,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                showCaptionAboveMedia = showCaptionAboveMedia,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param photoFileId A valid file identifier of the photo
     * @param title Title for the result
     * @param description Short description of the result
     * @param caption Caption of the photo to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the photo caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the photo
     */
    fun cachedPhoto(
        photoFileId: String,
        title: String? = null,
        description: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = photoFileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedPhoto(
                id = id,
                photoFileId = photoFileId,
                title = title,
                description = description,
                caption = caption,
                parseMode = parseMode,
                captionEntities = captionEntities,
                showCaptionAboveMedia = showCaptionAboveMedia,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param stickerFileId A valid file identifier of the sticker
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the sticker
     */
    fun cachedSticker(
        stickerFileId: String,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = stickerFileId.takeLast(64),
    ) {
        add(
            InlineQueryResultCachedSticker(
                id = id,
                stickerFileId = stickerFileId,
                replyMarkup = replyMarkup,
                inputMessageContent = inputMessageContent
            )
        )
    }

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param videoFileId A valid file identifier for the video file
     * @param title Title for the result
     * @param description Short description of the result
     * @param caption Caption of the video to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the video caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the video
     */
    fun cachedVideo(
        videoFileId: String,
        title: String,
        description: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = videoFileId.takeLast(64),
    ) = add(
        InlineQueryResultCachedVideo(
            id = id,
            videoFileId = videoFileId,
            title = title,
            description = description,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            showCaptionAboveMedia = showCaptionAboveMedia,
            replyMarkup = replyMarkup,
            inputMessageContent = inputMessageContent
        )
    )

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param voiceFileId A valid file identifier for the voice message
     * @param title Voice message title
     * @param caption Caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the voice message caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the voice message
     */
    fun cachedVoice(
        voiceFileId: String,
        title: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = voiceFileId.takeLast(64),
    ) = add(InlineQueryResultCachedVoice(
            id=id,
            voiceFileId=voiceFileId,
            title=title,
            caption=caption,
            parseMode=parseMode,
            captionEntities=captionEntities,
            replyMarkup=replyMarkup,
            inputMessageContent=inputMessageContent
        ))

    /**
     * @param id Unique identifier for this result, 1-64 Bytes
     * @param phoneNumber Contact's phone number
     * @param firstName Contact's first name
     * @param lastName Contact's last name
     * @param vcard Additional data about the contact in the form of a vCard, 0-2048 bytes
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the contact
     * @param thumbnailUrl Url of the thumbnail for the result
     * @param thumbnailWidth Thumbnail width
     * @param thumbnailHeight Thumbnail height
     */
    fun contact(
        phoneNumber: String,
        firstName: String,
        lastName: String? = null,
        vcard: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null,
        id: String = phoneNumber.takeLast(64),
    ) = add(InlineQueryResultContact(
            id=id,
            phoneNumber=phoneNumber,
            firstName=firstName,
            lastName=lastName,
            vcard=vcard,
            replyMarkup=replyMarkup,
            inputMessageContent=inputMessageContent,
            thumbnailUrl=thumbnailUrl,
            thumbnailWidth=thumbnailWidth,
            thumbnailHeight=thumbnailHeight
        ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param title Title for the result
     * @param caption Caption of the document to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the document caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param documentUrl A valid URL for the file
     * @param mimeType MIME type of the content of the file, either “application/pdf” or “application/zip”
     * @param description Short description of the result
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the file
     * @param thumbnailUrl URL of the thumbnail (JPEG only) for the file
     * @param thumbnailWidth Thumbnail width
     * @param thumbnailHeight Thumbnail height
     */
    fun document(
        title: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        documentUrl: String,
        mimeType: String,
        description: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null,
        id: String = documentUrl.takeLast(64),
    ) =  add(InlineQueryResultDocument(
        id=id,
        title=title,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        documentUrl=documentUrl,
        mimeType=mimeType,
        description=description,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent,
        thumbnailUrl=thumbnailUrl,
        thumbnailWidth=thumbnailWidth,
        thumbnailHeight=thumbnailHeight
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param gameShortName Short name of the game
     * @param replyMarkup Inline keyboard attached to the message
     */
    fun game(
        gameShortName: String,
        replyMarkup: InlineKeyboardMarkup? = null,
        id: String = gameShortName.takeLast(64),
    ) = add(InlineQueryResultGame(
        id=id,
        gameShortName=gameShortName,
        replyMarkup=replyMarkup
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param gifUrl A valid URL for the GIF file. File size must not exceed 1MB
     * @param gifWidth Width of the GIF
     * @param gifHeight Height of the GIF
     * @param gifDuration Duration of the GIF in seconds
     * @param thumbnailUrl URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
     * @param thumbnailMimeType MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
     * @param title Title for the result
     * @param caption Caption of the GIF file to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the GIF animation
     */
    fun gif(
        gifUrl: String,
        gifWidth: Int? = null,
        gifHeight: Int? = null,
        gifDuration: Int? = null,
        thumbnailUrl: String,
        thumbnailMimeType: String? = null,
        title: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = "${gifUrl}_${thumbnailUrl}".takeLast(64),
    ) = add(InlineQueryResultGif(
        id=id,
        gifUrl=gifUrl,
        gifWidth=gifWidth,
        gifHeight=gifHeight,
        gifDuration=gifDuration,
        thumbnailUrl=thumbnailUrl,
        thumbnailMimeType=thumbnailMimeType,
        title=title,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        showCaptionAboveMedia=showCaptionAboveMedia,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent
    ))

    /**
     * @param id Unique identifier for this result, 1-64 Bytes
     * @param latitude Location latitude in degrees
     * @param longitude Location longitude in degrees
     * @param title Location title
     * @param horizontalAccuracy The radius of uncertainty for the location, measured in meters; 0-1500
     * @param livePeriod Period in seconds during which the location can be updated, should be between 60 and 86400, or 0x7FFFFFFF for live locations that can be edited indefinitely.
     * @param heading For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
     * @param proximityAlertRadius For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the location
     * @param thumbnailUrl Url of the thumbnail for the result
     * @param thumbnailWidth Thumbnail width
     * @param thumbnailHeight Thumbnail height
     */
    fun location(
        latitude: Float,
        longitude: Float,
        title: String,
        horizontalAccuracy: Float? = null,
        livePeriod: Int? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null,
        id: String = "loc_${latitude}_${longitude}_$title".takeLast(64),
    ) = add(InlineQueryResultLocation(
        id=id,
        latitude=latitude,
        longitude=longitude,
        title=title,
        horizontalAccuracy=horizontalAccuracy,
        livePeriod=livePeriod,
        heading=heading,
        proximityAlertRadius=proximityAlertRadius,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent,
        thumbnailUrl=thumbnailUrl,
        thumbnailWidth=thumbnailWidth,
        thumbnailHeight=thumbnailHeight
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param mpeg4Url A valid URL for the MPEG4 file. File size must not exceed 1MB
     * @param mpeg4Width Video width
     * @param mpeg4Height Video height
     * @param mpeg4Duration Video duration in seconds
     * @param thumbnailUrl URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
     * @param thumbnailMimeType MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
     * @param title Title for the result
     * @param caption Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the video animation
     */
    fun mpeg4Gif(
        mpeg4Url: String,
        mpeg4Width: Int? = null,
        mpeg4Height: Int? = null,
        mpeg4Duration: Int? = null,
        thumbnailUrl: String,
        thumbnailMimeType: String? = null,
        title: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = "${mpeg4Url}_${thumbnailUrl}".takeLast(64),
    ) = add(InlineQueryResultMpeg4Gif(
        id=id,
        mpeg4Url=mpeg4Url,
        mpeg4Width=mpeg4Width,
        mpeg4Height=mpeg4Height,
        mpeg4Duration=mpeg4Duration,
        thumbnailUrl=thumbnailUrl,
        thumbnailMimeType=thumbnailMimeType,
        title=title,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        showCaptionAboveMedia=showCaptionAboveMedia,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param photoUrl A valid URL of the photo. Photo must be in JPEG format. Photo size must not exceed 5MB
     * @param thumbnailUrl URL of the thumbnail for the photo
     * @param photoWidth Width of the photo
     * @param photoHeight Height of the photo
     * @param title Title for the result
     * @param description Short description of the result
     * @param caption Caption of the photo to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the photo caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the photo
     */
    fun photo(
        photoUrl: String,
        thumbnailUrl: String,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        title: String? = null,
        description: String? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = "${photoUrl}_${thumbnailUrl}".takeLast(64),
    ) = add(InlineQueryResultPhoto(
        id=id,
        photoUrl=photoUrl,
        thumbnailUrl=thumbnailUrl,
        photoWidth=photoWidth,
        photoHeight=photoHeight,
        title=title,
        description=description,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        showCaptionAboveMedia=showCaptionAboveMedia,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent
    ))

    /**
     * @param id Unique identifier for this result, 1-64 Bytes
     * @param latitude Latitude of the venue location in degrees
     * @param longitude Longitude of the venue location in degrees
     * @param title Title of the venue
     * @param address Address of the venue
     * @param foursquareId Foursquare identifier of the venue if known
     * @param foursquareType Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param googlePlaceId Google Places identifier of the venue
     * @param googlePlaceType Google Places type of the venue. (See supported types.)
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the venue
     * @param thumbnailUrl Url of the thumbnail for the result
     * @param thumbnailWidth Thumbnail width
     * @param thumbnailHeight Thumbnail height
     */
    fun venue(
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        foursquareId: String? = null,
        foursquareType: String? = null,
        googlePlaceId: String? = null,
        googlePlaceType: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        thumbnailUrl: String? = null,
        thumbnailWidth: Int? = null,
        thumbnailHeight: Int? = null,
        id: String = "venue_${latitude}_${longitude}_$title".takeLast(64),
    ) = add(InlineQueryResultVenue(
        id=id,
        latitude=latitude,
        longitude=longitude,
        title=title,
        address=address,
        foursquareId=foursquareId,
        foursquareType=foursquareType,
        googlePlaceId=googlePlaceId,
        googlePlaceType=googlePlaceType,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent,
        thumbnailUrl=thumbnailUrl,
        thumbnailWidth=thumbnailWidth,
        thumbnailHeight=thumbnailHeight
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param videoUrl A valid URL for the embedded video player or video file
     * @param mimeType MIME type of the content of the video URL, “text/html” or “video/mp4”
     * @param thumbnailUrl URL of the thumbnail (JPEG only) for the video
     * @param title Title for the result
     * @param caption Caption of the video to be sent, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the video caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param showCaptionAboveMedia Pass True, if the caption must be shown above the message media
     * @param videoWidth Video width
     * @param videoHeight Video height
     * @param videoDuration Video duration in seconds
     * @param description Short description of the result
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the video. This field is required if InlineQueryResultVideo is used to send an HTML-page as a result (e.g., a YouTube video).
     */
    fun video(
        videoUrl: String,
        mimeType: String,
        thumbnailUrl: String,
        title: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        videoWidth: Int? = null,
        videoHeight: Int? = null,
        videoDuration: Int? = null,
        description: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = "${videoUrl}_${thumbnailUrl}".takeLast(64),
    ) = add(InlineQueryResultVideo(
        id=id,
        videoUrl=videoUrl,
        mimeType=mimeType,
        thumbnailUrl=thumbnailUrl,
        title=title,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        showCaptionAboveMedia=showCaptionAboveMedia,
        videoWidth=videoWidth,
        videoHeight=videoHeight,
        videoDuration=videoDuration,
        description=description,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent
    ))

    /**
     * @param id Unique identifier for this result, 1-64 bytes
     * @param voiceUrl A valid URL for the voice recording
     * @param title Recording title
     * @param caption Caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the voice message caption. See formatting options for more details.
     * @param captionEntities List of special entities that appear in the caption, which can be specified instead of parse_mode
     * @param voiceDuration Recording duration in seconds
     * @param replyMarkup Inline keyboard attached to the message
     * @param inputMessageContent Content of the message to be sent instead of the voice recording
     */
    fun voice(
        voiceUrl: String,
        title: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        voiceDuration: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
        inputMessageContent: InputMessageContent? = null,
        id: String = voiceUrl.takeLast(64),
    ) = add(InlineQueryResultVoice(
        id=id,
        voiceUrl=voiceUrl,
        title=title,
        caption=caption,
        parseMode=parseMode,
        captionEntities=captionEntities,
        voiceDuration=voiceDuration,
        replyMarkup=replyMarkup,
        inputMessageContent=inputMessageContent
    ))

    fun add(value: InlineQueryResult) = result.add(value)

    fun buildResult(): List<InlineQueryResult> {
        return result
    }
}

suspend fun InlineQuery.answer(content: InlineQueryAnswerEnvironment.() -> Unit) {
    val env = InlineQueryAnswerEnvironment()
    env.content()

    this.answer(env.buildResult())
}