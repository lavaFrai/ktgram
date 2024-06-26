package ru.lavafrai.ktgram.types

enum class UpdateType {
    Message,
    EditedMessage,
    ChannelPost,
    EditedChannelPost,
    InlineQuery,
    ChosenInlineResult,
    CallbackQuery,
    ShippingQuery,
    PreCheckoutQuery,
    Poll,
    PollAnswer,
    MyChatMember,
    ChatMember,
    ChatJoinRequest,
    MessageReaction,
    ChatBoost,
    RemovedChatBoost,
    DeletedBusinessMessages,
    BusinessConnection,
    EditedBusinessMessage,
    BusinessMessage,
    Unknown,
}

class UnknownUpdateTypeException(u: Update) : Exception("Failed to detect update type for: $u")