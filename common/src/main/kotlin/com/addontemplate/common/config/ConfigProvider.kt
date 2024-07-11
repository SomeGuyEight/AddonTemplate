package com.cobblemontournament.common.config

import com.addontemplate.common.AddonTemplate
import com.addontemplate.common.config.Config
import com.addontemplate.common.util.AddonTemplateUtil
import com.addontemplate.common.util.StoreUtil
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.slf4j.Logger
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Path

object ConfigProvider
{
    private val LOGGER: Logger get() = AddonTemplate.LOGGER

    @Suppress("unused")
    // TODO implement functionality for broken configs
    //  - detecting & saving broken file with suffix "_broken"
    //      - this way no configs are deleted if someone makes a small mistake editing them
    private var broken: Boolean = false

    @JvmStatic
    fun registerConfigs(
        path: Path )
    {
        LOGGER.info( "Loading Addon Template Configs" )
        val file = path.resolve( "${Config.CONFIG_FILE_NAME}-config.json" ).toFile()
        registerConfig( path, file )
    }

    private fun registerConfig(
        path: Path,
        file: File )
    {
        val name = file.nameWithoutExtension
        val identifier = "Config '$name'"

        val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
        val json = StoreUtil.getJsonOrCreateFile( file, gson )

        if ( json == null ) {
            LOGGER.info( "{} is missing, generating default one...", identifier )
            try {
                createConfig( JsonObject(), file )
            } catch ( e: IOException ) {
                LOGGER.error( "{} failed to generate!", identifier )
                LOGGER.trace( e.stackTrace.toString() )
                broken = true
            }
        } else {
            try {
                loadConfig( json, file )
            } catch ( e: IOException ) {
                LOGGER.error( "{} failed to load!", identifier )
                LOGGER.trace( e.stackTrace.toString() )
                broken = true
            }
        }
    }

    private fun createConfig(
        json: JsonObject,
        file: File )
    {
        val pw: PrintWriter
        try {
            pw = PrintWriter( file )
        } catch ( e: FileNotFoundException ) {
            throw RuntimeException( e )
        }

        json.addProperty( Config.CONFIG_EXAMPLE_STRING_KEY  , Config.defaultExampleString() )
        json.addProperty( Config.CONFIG_SAMPLE_INT_KEY 	    , Config.defaultExampleInt().toString() )
        json.addProperty( Config.CONFIG_SAMPLE_BOOLEAN_KEY  , Config.defaultExampleBoolean().toString() )
        json.addProperty( Config.CONFIG_SAMPLE_ENUM_KEY 	, Config.defaultExampleEnum().toString() )

        pw.write( json.toString() )
        pw.flush()
        pw.close()
    }

    private fun loadConfig(
        json: JsonObject,
        file: File )
    {
        val exampleString = json.get( Config.CONFIG_EXAMPLE_STRING_KEY )?.toString()?.filterNot { it == '"' }
            ?: Config.defaultExampleString()

        val exampleIntElement = json.get( Config.CONFIG_SAMPLE_INT_KEY )
        val exampleInt = if (exampleIntElement != null) {
            exampleIntElement.toString().filterNot { it == '"' }.toIntOrNull()
        } else Config.defaultExampleInt()

        val exampleBooleanElement = json.get( Config.CONFIG_SAMPLE_BOOLEAN_KEY )
        val exampleBoolean = if (exampleBooleanElement != null) {
            exampleBooleanElement.toString().filterNot { it == '"' }.toBooleanStrictOrNull()
        } else Config.defaultExampleBoolean()

        val exampleEnumElement = json.get( Config.CONFIG_SAMPLE_ENUM_KEY )
        val exampleEnum = if (exampleEnumElement != null) {
            AddonTemplateUtil.getExampleEnumOrNull( exampleEnumElement.toString().filterNot { it == '"' } )
        } else Config.defaultExampleEnum()

        Config.initialize(
            defaultSampleString     = exampleString,
            defaultSampleInt        = exampleInt,
            defaultSampleBoolean    = exampleBoolean,
            defaultExampleEnum      = exampleEnum )
    }

}
