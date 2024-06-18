package ru.lavafrai.ktgram.types

enum class ChatAction(val action: String) {
    TYPING("typing"),
    UPLOADING_PHOTO("upload_photo"),
    RECORDING_VIDEO("record_video"),
    UPLOADING_VIDEO("upload_video"),
    RECORD_ING_VOICE("record_voice"),
    UPLOAD_VOICE("upload_voice"),
    UPLOAD_DOCUMENT("upload_document"),
    CHOOSE_STICKER("choose_sticker"),
    FIND_LOCATION("find_location"),
    RECORD_VIDEO_NOTE("record_video_note"),
    UPLOAD_VIDEO_NOTE("upload_video_note"),
}