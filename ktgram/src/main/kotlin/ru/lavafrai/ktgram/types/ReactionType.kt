import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * The reaction is based on an emoji.
 *
 * @param type Type of the reaction, “emoji” or "custom_emoji"
 * @param emoji Reaction emoji. Currently, it can be one of "👍", "👎", "❤", "🔥", "🥰", "👏", "😁", "🤔", "🤯", "😱", "🤬", "😢", "🎉", "🤩", "🤮", "💩", "🙏", "👌", "🕊", "🤡", "🥱", "🥴", "😍", "🐳", "❤‍🔥", "🌚", "🌭", "💯", "🤣", "⚡", "🍌", "🏆", "💔", "🤨", "😐", "🍓", "🍾", "💋", "🖕", "😈", "😴", "😭", "🤓", "👻", "👨‍💻", "👀", "🎃", "🙈", "😇", "😨", "🤝", "✍", "🤗", "🫡", "🎅", "🎄", "☃", "💅", "🤪", "🗿", "🆒", "💘", "🙉", "🦄", "😘", "💊", "🙊", "😎", "👾", "🤷‍♂", "🤷", "🤷‍♀", "😡"
 * @param customEmojiId Custom emoji identifier
 */
@Serializable
class ReactionType(
    @SerialName("type") val type: String,
    @SerialName("emoji") val emoji: String? = null,
    @SerialName("custom_emoji_id") val customEmojiId: String? = null,
) : TelegramObject() {
    companion object {
        val LIKE = ReactionType("emoji", "👍")
        val DISLIKE = ReactionType("emoji", "👎")
        val LOVE = ReactionType("emoji", "❤")
        val FIRE = ReactionType("emoji", "🔥")
        val HEART_EYES = ReactionType("emoji", "😍")
        val CLAPPING = ReactionType("emoji", "👏")
        val LAUGHING = ReactionType("emoji", "😁")
        val THINKING = ReactionType("emoji", "🤔")
        val MIND_BLOWN = ReactionType("emoji", "🤯")
        val SCREAMING = ReactionType("emoji", "😱")
        val ANGRY = ReactionType("emoji", "🤬")
        val CRYING = ReactionType("emoji", "😢")
        val PARTY = ReactionType("emoji", "🎉")
        val STAR_EYES = ReactionType("emoji", "🤩")
        val VOMIT = ReactionType("emoji", "🤮")
        val POOP = ReactionType("emoji", "💩")
        val PRAY = ReactionType("emoji", "🙏")
        val OK = ReactionType("emoji", "👌")
        val DOVE = ReactionType("emoji", "🕊")
        val CLOWN = ReactionType("emoji", "🤡")
        val YAWNING = ReactionType("emoji", "🥱")
        val DRUNK = ReactionType("emoji", "🥴")
        val WHALE = ReactionType("emoji", "🐳")
        val HEART_FIRE = ReactionType("emoji", "❤‍🔥")
        val MOON = ReactionType("emoji", "🌚")
        val HOTDOG = ReactionType("emoji", "🌭")
        val HUNDRED = ReactionType("emoji", "💯")
        val LAUGHING_CRYING = ReactionType("emoji", "🤣")
        val LIGHTNING = ReactionType("emoji", "⚡")
        val BANANA = ReactionType("emoji", "🍌")
        val TROPHY = ReactionType("emoji", "🏆")
        val BROKEN_HEART = ReactionType("emoji", "💔")
        val THINKING_FACE = ReactionType("emoji", "🤨")
        val NEUTRAL_FACE = ReactionType("emoji", "😐")
        val STRAWBERRY = ReactionType("emoji", "🍓")
        val CHAMPAGNE = ReactionType("emoji", "🍾")
        val KISS = ReactionType("emoji", "💋")
        val MIDDLE_FINGER = ReactionType("emoji", "🖕")
        val DEVIL = ReactionType("emoji", "😈")
        val SLEEPING = ReactionType("emoji", "😴")
        val CRYING_FACE = ReactionType("emoji", "😭")
        val NERD = ReactionType("emoji", "🤓")
        val GHOST = ReactionType("emoji", "👻")
        val COMPUTER = ReactionType("emoji", "👨‍💻")
        val EYES = ReactionType("emoji", "👀")
        val PUMPKIN = ReactionType("emoji", "🎃")
        val SEE_NO_EVIL = ReactionType("emoji", "🙈")
        val ANGEL = ReactionType("emoji", "😇")
        val FEARFUL = ReactionType("emoji", "😨")
        val HANDSHAKE = ReactionType("emoji", "🤝")
        val WRITING_HAND = ReactionType("emoji", "✍")
        val HUG = ReactionType("emoji", "🤗")
        val SALUTE = ReactionType("emoji", "🫡")
        val SANTA = ReactionType("emoji", "🎅")
        val CHRISTMAS_TREE = ReactionType("emoji", "🎄")
        val SNOWMAN = ReactionType("emoji", "☃")
        val NAIL_POLISH = ReactionType("emoji", "💅")
        val ZANY_FACE = ReactionType("emoji", "🤪")
        val MOAI = ReactionType("emoji", "🗿")
        val COOL = ReactionType("emoji", "🆒")
        val HEART_WITH_ARROW = ReactionType("emoji", "💘")
        val HEAR_NO_EVIL = ReactionType("emoji", "🙉")
        val UNICORN = ReactionType("emoji", "🦄")
        val KISSING_FACE = ReactionType("emoji", "😘")
        val PILL = ReactionType("emoji", "💊")
        val SPEAK_NO_EVIL = ReactionType("emoji", "🙊")
        val COOL_FACE = ReactionType("emoji", "😎")
        val ALIEN_MONSTER = ReactionType("emoji", "👾")
        val MAN_SHRUGGING = ReactionType("emoji", "🤷‍♂")
        val SHRUGGING = ReactionType("emoji", "🤷")
        val WOMAN_SHRUGGING = ReactionType("emoji", "🤷‍♀")
        val ANGRY_FACE = ReactionType("emoji", "😡")
    }
}
