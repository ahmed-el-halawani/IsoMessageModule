package converters

class BinaryValueConverter(private val value: String) : BaseValueConverter {
    override fun toHex(): String {
        return value
    }

    override fun toBcd(): String {
        TODO("Not yet implemented")
    }

    override fun toBase64(): String {
        TODO("Not yet implemented")
    }

    override fun toAscii(): String {
        TODO("Not yet implemented")
    }

    override fun toBinary(): String {
        return value
    }

}