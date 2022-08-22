package utils

object IsoHelpers {
    fun paddingLeft(value: String, length: Int, paddingWith: String): String {
        if (value.length <= length) {
            return paddingWith.repeat(length - value.length) + value
        }
        return value
    }
}