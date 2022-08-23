package IsoFieldConverter

abstract class BaseIsoFieldConverter<T>(val paddingWith: Char? = ' ') {
    abstract fun getLength(numberOfChars: Int): Int
    abstract fun fromHex(hexValue: String): T
    abstract fun toHex(value: T, fieldLength: Int): String

    abstract fun inHexLength(numberOfChars: Int): Int


    fun paddingLeft(value: String, length: Int): String {
        return if (value.length < length)
            (paddingWith?.toString()?.repeat(length - value.length) + value)
        else value
    }

    fun paddingRight(value: String, length: Int): String {
        return if (value.length < length)
            (value + paddingWith?.toString()?.repeat(length - value.length))
        else value
    }


}