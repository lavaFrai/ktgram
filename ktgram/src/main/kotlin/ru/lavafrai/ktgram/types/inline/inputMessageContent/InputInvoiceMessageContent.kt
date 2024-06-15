package ru.lavafrai.ktgram.types.inline.inputMessageContent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.ktgram.types.payments.LabeledPrice

/**
 * Represents the content of an invoice message to be sent as the result of an inline query.
 *
 * @param title Product name, 1-32 characters
 * @param description Product description, 1-255 characters
 * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal processes.
 * @param providerToken Payment provider token, obtained via @BotFather. Pass an empty string for payments in Telegram Stars.
 * @param currency Three-letter ISO 4217 currency code, see more on currencies. Pass “XTR” for payments in Telegram Stars.
 * @param prices Price breakdown, a JSON-serialized list of components (e.g. product price, tax, discount, delivery cost, delivery tax, bonus, etc.). Must contain exactly one item for payments in Telegram Stars.
 * @param maxTipAmount The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double). For example, for a maximum tip of US$ 1.45 pass max_tip_amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies). Defaults to 0. Not supported for payments in Telegram Stars.
 * @param suggestedTipAmounts A JSON-serialized array of suggested amounts of tip in the smallest units of the currency (integer, not float/double). At most 4 suggested tip amounts can be specified. The suggested tip amounts must be positive, passed in a strictly increased order and must not exceed max_tip_amount.
 * @param providerData A JSON-serialized object for data about the invoice, which will be shared with the payment provider. A detailed description of the required fields should be provided by the payment provider.
 * @param photoUrl URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service.
 * @param photoSize Photo size in bytes
 * @param photoWidth Photo width
 * @param photoHeight Photo height
 * @param needName Pass True if you require the user's full name to complete the order. Ignored for payments in Telegram Stars.
 * @param needPhoneNumber Pass True if you require the user's phone number to complete the order. Ignored for payments in Telegram Stars.
 * @param needEmail Pass True if you require the user's email address to complete the order. Ignored for payments in Telegram Stars.
 * @param needShippingAddress Pass True if you require the user's shipping address to complete the order. Ignored for payments in Telegram Stars.
 * @param sendPhoneNumberToProvider Pass True if the user's phone number should be sent to the provider. Ignored for payments in Telegram Stars.
 * @param sendEmailToProvider Pass True if the user's email address should be sent to the provider. Ignored for payments in Telegram Stars.
 * @param isFlexible Pass True if the final price depends on the shipping method. Ignored for payments in Telegram Stars.
 */
@Serializable
class InputInvoiceMessageContent(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("payload") val payload: String,
    @SerialName("provider_token") val providerToken: String? = null,
    @SerialName("currency") val currency: String,
    @SerialName("prices") val prices: List<LabeledPrice>,
    @SerialName("max_tip_amount") val maxTipAmount: Int? = null,
    @SerialName("suggested_tip_amounts") val suggestedTipAmounts: List<Int>? = null,
    @SerialName("provider_data") val providerData: String? = null,
    @SerialName("photo_url") val photoUrl: String? = null,
    @SerialName("photo_size") val photoSize: Int? = null,
    @SerialName("photo_width") val photoWidth: Int? = null,
    @SerialName("photo_height") val photoHeight: Int? = null,
    @SerialName("need_name") val needName: Boolean? = null,
    @SerialName("need_phone_number") val needPhoneNumber: Boolean? = null,
    @SerialName("need_email") val needEmail: Boolean? = null,
    @SerialName("need_shipping_address") val needShippingAddress: Boolean? = null,
    @SerialName("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean? = null,
    @SerialName("send_email_to_provider") val sendEmailToProvider: Boolean? = null,
    @SerialName("is_flexible") val isFlexible: Boolean? = null,
): InputMessageContent() {
}