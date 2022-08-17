package converters

class Base64ValueConverter(private val value: String) : BaseValueConverter {
    override fun toHex(): String {
        return value
    }

    override fun toBcd(): String {
        TODO("Not yet implemented")
    }

    override fun toBase64(): String {
        return value
    }

    override fun toAscii(): String {
        TODO("Not yet implemented")
    }

    override fun toBinary(): String {
        return value
    }

}