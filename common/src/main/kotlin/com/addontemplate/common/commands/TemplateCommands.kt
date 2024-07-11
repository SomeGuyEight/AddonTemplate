package com.addontemplate.common.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.CommandSelection

object TemplateCommands
{
    @JvmStatic
    fun register(
        dispatcher  : CommandDispatcher<CommandSourceStack>,
        registry    : CommandBuildContext,
        selection   : CommandSelection
    ) { }

}
