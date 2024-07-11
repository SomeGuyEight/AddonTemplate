package com.addontemplate.common.config

data object Config
{
    const val CONFIG_FILE_NAME                  = "cobblemon-tournament"
    const val CONFIG_EXAMPLE_STRING_KEY 	    = "config_example_value_key"
    const val CONFIG_SAMPLE_INT_KEY 	        = "config_example_int_key"
    const val CONFIG_SAMPLE_BOOLEAN_KEY 	    = "config_example_boolean_key"
    const val CONFIG_SAMPLE_ENUM_KEY 	        = "config_example_enum_key"

    private const val CONFIG_EXAMPLE_STRING     = "config-example-string"
    private const val CONFIG_EXAMPLE_INT        = -1
    private const val CONFIG_EXAMPLE_BOOLEAN    = true
    private val CONFIG_EXAMPLE_ENUM             = ExampleEnum.EXAMPLE_ENUM

    private var defaultExampleString    : String        = CONFIG_EXAMPLE_STRING
    private var defaultExampleInt       : Int           = CONFIG_EXAMPLE_INT
    private var defaultExampleBoolean   : Boolean       = CONFIG_EXAMPLE_BOOLEAN
    private var defaultExampleEnum      : ExampleEnum   = CONFIG_EXAMPLE_ENUM

    fun defaultExampleString()  : String        = defaultExampleString
    fun defaultExampleInt()     : Int           = defaultExampleInt
    fun defaultExampleBoolean() : Boolean       = defaultExampleBoolean
    fun defaultExampleEnum()    : ExampleEnum   = defaultExampleEnum

    @JvmStatic
    fun initialize(
        defaultSampleString     : String?,
        defaultSampleInt        : Int?,
        defaultSampleBoolean    : Boolean?,
        defaultExampleEnum      : ExampleEnum? )
    {
        this.defaultExampleString   = defaultSampleString   ?: CONFIG_EXAMPLE_STRING
        this.defaultExampleInt      = defaultSampleInt      ?: CONFIG_EXAMPLE_INT
        this.defaultExampleBoolean  = defaultSampleBoolean  ?: CONFIG_EXAMPLE_BOOLEAN
        this.defaultExampleEnum     = defaultExampleEnum    ?: CONFIG_EXAMPLE_ENUM
    }

}
