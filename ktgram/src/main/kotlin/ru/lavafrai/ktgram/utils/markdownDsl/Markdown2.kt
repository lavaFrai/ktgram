package ru.lavafrai.ktgram.utils.markdownDsl

class Markdown2 {
    private val builder = StringBuilder()
    val escapable = listOf("_", "*", "[", "]", "(", ")", "~", "`", ">", "#", "+", "-", "=", "|", "{", "}", ".", "!")

    fun build(): String {
        return builder.toString()
    }

    private fun plain(text: String) {
        builder.append(text)
    }

    fun bold(block: Markdown2.() -> Unit) {
        builder.append("\u200E*")
        block()
        builder.append("*\u200E")
    }

    fun italic(block: Markdown2.() -> Unit) {
        builder.append("\u200E_")
        block()
        builder.append("_\u200E")
    }

    fun underline(block: Markdown2.() -> Unit) {
        builder.append("\u200E__")
        block()
        builder.append("__\u200E")
    }

    fun strikethrough(block: Markdown2.() -> Unit) {
        builder.append("\u200E~")
        block()
        builder.append("~\u200E")
    }

    fun spoiler(block: Markdown2.() -> Unit) {
        builder.append("\u200E||")
        block()
        builder.append("||\u200E")
    }

    fun url(url: String, text: String) {
        builder.append("[$text]($url)")
    }

    fun mention(userId: Long, text: String) {
        builder.append("[$text](tg://user?id=$userId)")
    }

    fun emoji(emoji: String, id: Long) {
        builder.append("[$emoji](tg://emoji?id=$id)")
    }

    fun monospace(text: String) {
        builder.append("\u200E`")
        + text
        builder.append("`\u200E")
    }

    fun code(text: String, lang: String = "") {
        builder.append("\u200E```$lang\n")
        + text
        builder.append("```\u200E")
    }

    fun quote(text: String) {
        builder.append("\u200E\n>")
        + text.replace("\n", "\n>")
        builder.append("\n\u200E")
    }

    fun expandableQuote(text: String) {
        builder.append("\u200E\n**>")
        + text.replace("\n", "\n>>>")
        builder.append("||\n\u200E")
    }

    operator fun String.unaryPlus() {
        plain(this)
    }

    fun escape(text: String): String {
        return escapable.fold(text) { acc, c -> acc.replace(c, "\\$c") }
    }
}


fun markdown2(block: Markdown2.() -> Unit): String {
    val markdown = Markdown2()
    markdown.block()
    return markdown.build()
}
