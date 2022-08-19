object IsoHelpers {
    fun paddingBinaryLeft(value: String): String {
        val newValue = "00000000"
        if (value.length != 8)
            return newValue.substring(0, 8 - value.length) + value
        return value
    }

    fun paddingBCDLeft(value: String): String {
        val newValue = "00"
        if (value.length != 8)
            return newValue.substring(0, 2 - value.length) + value
        return value
    }

    fun paddingLeft(value: String, length: Int, paddingWith: String): String {
        if (value.length <= length) {
            return paddingWith.repeat(length - value.length) + value
        }
        return value
    }
}