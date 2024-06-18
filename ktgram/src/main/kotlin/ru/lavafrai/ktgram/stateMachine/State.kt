package ru.lavafrai.ktgram.stateMachine

open class State() {
    val name: String
        get() = _name ?: this.javaClass.name.split(".").last().lowercase()

    private var _name: String? = null

    private constructor(name: String): this() {
        this._name = name
    }

    companion object {
        internal fun fromName(name: String): State {
            return State(name)
        }
    }

    override operator fun equals(other: Any?): Boolean {
        if (other !is State) return false

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}