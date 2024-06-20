package ru.lavafrai.ktgram.examples.markdown

import ru.lavafrai.ktgram.client.Bot
import ru.lavafrai.ktgram.client.DefaultBotProperties
import ru.lavafrai.ktgram.dispatcher.Router
import ru.lavafrai.ktgram.dispatcher.handle
import ru.lavafrai.ktgram.dispatcher.routing
import ru.lavafrai.ktgram.dispatcher.text
import ru.lavafrai.ktgram.types.ParseMode
import ru.lavafrai.ktgram.utils.markdownDsl.markdown2

fun Router<*>.addHandlers() {
    text {
        handle {
            val text = markdown2 {
                + "Plain text\n"
                bold { + "Bold text\n" }
                italic { + "Italic text\n" }
                underline { + "Underline text\n" }
                strikethrough { + "Strikethrough text\n" }
                spoiler { + "Spoiler text\n" }

                bold {
                    + "Bold "
                    italic {
                        + "italic bold "
                        strikethrough {
                            + "italic bold strikethrough "
                            spoiler {
                                + "italic bold strikethrough spoiler "
                            }
                        }
                        underline {
                            + "underline italic bold"
                        }
                    }
                    + " bold\n"
                }

                url("https://example.com", "inline URL")
                + "\n"

                mention(123456789, "inline mention of a user")
                + "\n"
                emoji("👍", 5368324170671202286)
                + "\n"

                monospace("inline fixed-width code\n")

                code("pre-formatted fixed-width code block")
                code("fun main() {\n    println(\"Code block written in the Kotlin programming language\")\n}", "kotlin")

                quote("Block quotation started\nBlock quotation continued\nBlock quotation continued\nBlock quotation continued\nThe last line of the block quotation")

                expandableQuote("The expandable block quotation started right after the previous block quotation\n" +
                        "It is separated from the previous block quotation by an empty bold entity\n" +
                        "Expandable block quotation continued\n" +
                        "Hidden by default part of the expandable block quotation started\n" +
                        "Expandable block quotation continued\n" +
                        "The last line of the expandable block quotation with the expandability mark")

                + escape("Also you can escape symbols: _ * [ ] ( ) ~ ` > # + - = | { } . ! to use them in yor text\n")
                + "Or add \\ symbol before them to escape yourself\n"
            }

            message.reply(text)
            message.reply("Also supported markdown v1 and <b><i>HTML</i></b> markdown", parseMode = ParseMode.HTML)
            message.reply("And non parse mode is possible too!", parseMode = ParseMode.NONE)
        }
    }
}


fun main() {
    val properties = DefaultBotProperties(
        parseMode = ParseMode.MARKDOWN_V2
    )

    val bot = Bot(System.getenv("TELEGRAM_BOT_TOKEN") ?: throw RuntimeException("TELEGRAM_BOT_TOKEN env variable is not set"), properties = properties)

    val dispatcher = bot.dispatcher
    dispatcher.routing {
        addHandlers()
    }

    bot.runPolling()
}