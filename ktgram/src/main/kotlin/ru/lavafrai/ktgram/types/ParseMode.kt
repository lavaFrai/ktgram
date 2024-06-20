package ru.lavafrai.ktgram.types


/**
 * Parse mode for text messages.
 */
enum class ParseMode(val mode: String) {
    /**
     * Markdown parse mode.
     */
    MARKDOWN("Markdown"),

    /**
     * HTML parse mode.
     */
    HTML("HTML"),

    /**
     * MarkdownV2 parse mode.
     */
    MARKDOWN_V2("MarkdownV2"),

    /**
     * None parse mode.
     */
    NONE("")
}