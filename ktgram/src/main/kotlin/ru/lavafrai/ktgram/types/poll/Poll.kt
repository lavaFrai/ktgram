package ru.lavafrai.ktgram.types.poll

import ru.lavafrai.ktgram.types.media.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.TelegramObject

/**
 * This object contains information about a poll.
 *
 * @param id Unique poll identifier
 * @param question ru.lavafrai.ktgram.types.poll.Poll question, 1-300 characters
 * @param questionEntities *Optional.* Special entities that appear in the question. Currently, only custom emoji entities are allowed in poll questions
 * @param options List of poll options
 * @param totalVoterCount Total number of users that voted in the poll
 * @param isClosed True, if the poll is closed
 * @param isAnonymous True, if the poll is anonymous
 * @param type ru.lavafrai.ktgram.types.poll.Poll type, currently can be “regular” or “quiz”
 * @param allowsMultipleAnswers True, if the poll allows multiple answers
 * @param correctOptionId *Optional.* 0-based identifier of the correct answer option. Available only for polls in the quiz mode, which are closed, or was sent (not forwarded) by the bot or to the private chat with the bot.
 * @param explanation *Optional.* Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 0-200 characters
 * @param explanationEntities *Optional.* Special entities like usernames, URLs, bot commands, etc. that appear in the explanation
 * @param openPeriod *Optional.* Amount of time in seconds the poll will be active after creation
 * @param closeDate *Optional.* Point in time (Unix timestamp) when the poll will be automatically closed
 */
@Serializable
class Poll(
    @SerialName("id") val id: String,
    @SerialName("question") val question: String,
    @SerialName("question_entities") val questionEntities: List<MessageEntity>? = null,
    @SerialName("options") val options: List<PollOption>,
    @SerialName("total_voter_count") val totalVoterCount: Int,
    @SerialName("is_closed") val isClosed: Boolean,
    @SerialName("is_anonymous") val isAnonymous: Boolean,
    @SerialName("type") val type: String,
    @SerialName("allows_multiple_answers") val allowsMultipleAnswers: Boolean,
    @SerialName("correct_option_id") val correctOptionId: Int? = null,
    @SerialName("explanation") val explanation: String? = null,
    @SerialName("explanation_entities") val explanationEntities: List<MessageEntity>? = null,
    @SerialName("open_period") val openPeriod: Int? = null,
    @SerialName("close_date") val closeDate: Int? = null,
) : TelegramObject()